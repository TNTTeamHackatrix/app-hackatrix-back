name := "app-hackatrix-back"

version := "0.1"

scalaVersion := "2.12.10"


lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  fork in Test := true,
  name := "authentication",
  libraryDependencies ++= Dependencies.common,
  testFrameworks += new TestFramework("minitest.runner.Framework"),
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
)



lazy val core = project
  .configs()
  .settings(
    commonSettings,
    name += "-core"
  )


lazy val http = project
  .configs()
  .settings(
    commonSettings,
    libraryDependencies ++=  Dependencies.http,
    name += "-http"
  )
