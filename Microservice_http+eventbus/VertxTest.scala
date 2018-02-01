package pkg

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.Vertx
import io.vertx.scala.core.eventbus.EventBusOptions

object VertxTest {
  def main(args: Array[String]): Unit = {

    val vertOpt=io.vertx.scala.core.VertxOptions()
      .setClustered(true)
      .setClusterHost("127.0.0.1")
      .setClusterPort(9999);
    vertOpt.setEventBusOptions( EventBusOptions()
      .setClustered(true)
      .setClusterPublicHost("localhost"));

    Vertx.clusteredVertx( vertOpt,
      result =>
      {
        System.out.println("Cluster started");
        val vertm = result.result()
        vertm.deployVerticle(ScalaVerticle.nameForVerticle[ClusterEventBusVerticle])
      })
  }
}
