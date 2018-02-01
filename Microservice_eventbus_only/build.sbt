lazy val root = (project in file(".")).
  settings(
    name := "Another_verticle_test_bus",
    version := "1.0",
    scalaVersion := "2.12.4",
    mainClass in Compile := Some("pkg.ClusteredBusVerticleTest")
  )


libraryDependencies += "io.vertx" %% "vertx-lang-scala"  % "3.5.0"


assemblyMergeStrategy in assembly := {
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case x if (x.startsWith("vertx-lang")|| x.endsWith("codegen.json")) => MergeStrategy.first  
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last  
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case  "codegen.json" => MergeStrategy.last  
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

