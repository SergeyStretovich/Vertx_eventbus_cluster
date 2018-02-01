lazy val root = (project in file(".")).
  settings(
    name := "Vertix_cluster_test",
    version := "1.0",
    scalaVersion := "2.12.4",
    mainClass in Compile := Some("pkg.Program")
  )


libraryDependencies += "io.vertx" %% "vertx-lang-scala"                        % "3.5.0"
libraryDependencies += "io.vertx" %  "vertx-hazelcast"                         % "3.5.0"
libraryDependencies += "io.vertx" %% "vertx-web-scala"                         % "3.5.0"
libraryDependencies += "io.vertx" %% "vertx-web-common-scala"                  % "3.5.0"
libraryDependencies += "io.vertx" %% "vertx-web-client-scala"                  % "3.5.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.4"

assemblyMergeStrategy in assembly := {
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case x if (x.startsWith("vertx-lang")|| x.endsWith("codegen.json")) => MergeStrategy.first
  case x if (x.startsWith("vertx-web-common")|| x.endsWith("package-info.class")) => MergeStrategy.first
  case x if x.contains("groovy") => MergeStrategy.discard
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
        
