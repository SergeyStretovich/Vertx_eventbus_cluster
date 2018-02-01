package pkg

import io.vertx.lang.scala.ScalaVerticle
import scala.concurrent.Future

object BusVerticle {
  val testAddress = "testAddress"
}

class BusVerticle extends ScalaVerticle {

  val countries=Map("NZ"->"New Zealand","SG"->"Singapore","AU"->"Australia")

  override def startFuture(): Future[_] = {
    vertx
      .eventBus
      .consumer[String](BusVerticle.testAddress)
      .handler(
        message=> {
          val repl=countries.getOrElse(message.body(),"no value")
          message.reply(repl)
        }
      )
      .completionFuture
  }
}