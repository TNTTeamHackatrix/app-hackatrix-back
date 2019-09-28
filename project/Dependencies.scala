import sbt._

object Dependencies {
  lazy val common: Seq[ModuleID] = Seq(
    "com.github.pureconfig" %% "pureconfig" % "0.12.0",
    // Start with this one
    "org.tpolecat" %% "doobie-core"      % "0.8.2",

    // And add any of these as needed
    "org.tpolecat" %% "doobie-h2"        % "0.8.2",          // H2 driver 1.4.199 + type mappings.
    "org.tpolecat" %% "doobie-hikari"    % "0.8.2",          // HikariCP transactor.
    "org.tpolecat" %% "doobie-postgres"  % "0.8.2",          // Postgres driver 42.2.8 + type mappings.
    "org.tpolecat" %% "doobie-quill"     % "0.8.2",          // Support for Quill 3.4.4
    "org.tpolecat" %% "doobie-specs2"    % "0.8.2" % "test", // Specs2 support for typechecking statements.
    "org.tpolecat" %% "doobie-scalatest" % "0.8.2" % "test"  // ScalaTest support for typechecking statements.
  )

  lazy val http: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-http"   % "10.1.10",
    "com.typesafe.akka" %% "akka-stream" % "2.5.23"
  )
}
