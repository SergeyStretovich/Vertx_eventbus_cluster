package pkg

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.eventbus.EventBusOptions
import io.vertx.scala.core.{DeploymentOptions, Vertx, VertxOptions}

object ClusteredBusVerticleTest {

  def main(args: Array[String]): Unit =
  {
    //val vertx = Vertx.vertx()
    val vertOpt=io.vertx.scala.core.VertxOptions()
      .setClustered(true)
      .setClusterHost("127.0.0.1")
      .setClusterPort(9999);
    vertOpt.setEventBusOptions( EventBusOptions()
      .setClustered(true)
      .setClusterPublicHost("localhost"));

    val vertx=Vertx.clusteredVertx( vertOpt,
      result =>
        {
          System.out.println("Cluster started");
          val vertx = result.result()
          vertx.deployVerticle(ScalaVerticle.nameForVerticle[BusVerticle])
        })
  }

}
