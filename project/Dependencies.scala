import sbt._

object Dependencies {
  lazy val common: Seq[ModuleID] = Seq(
  )

  lazy val http: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-http"   % "10.1.10",
    "com.typesafe.akka" %% "akka-stream" % "2.5.23",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10"
  )
}
