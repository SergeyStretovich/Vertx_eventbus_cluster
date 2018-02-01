package pkg

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router

import scala.concurrent.Future
import scala.util.{Failure, Success}

class ClusterEventBusVerticle extends ScalaVerticle {


  override def startFuture(): Future[_] = {
    val router = Router.router(vertx)
    router.route(io.vertx.core.http.HttpMethod.GET, "/countries/:code")
      .handler((routingContext: io.vertx.scala.ext.web.RoutingContext) => {
        var response = routingContext.response()
        response.putHeader("content-type", "text/plain")
        val code = routingContext.request().getParam("code").getOrElse("")
        println("code= "+code)
        val future: Future[io.vertx.scala.core.eventbus.Message[String]] =
          vertx
            .eventBus
            .sendFuture("testAddress", code)

        future.onComplete {
          case Success(repliedMessage) => {
            response.end(repliedMessage.body())
          }
          case Failure(ex) => response.end("nothing")
        }

      })
    vertx
      .createHttpServer()
      .requestHandler(router.accept(_))
      .listenFuture(8666, "127.0.0.1")
      .map { httpServer =>
        println(
          s"""httpServer.isMetricsEnabled: ${httpServer.isMetricsEnabled}
             |httpServer connected on port: ${httpServer.actualPort}
             |""".stripMargin)
      }
  }

}
