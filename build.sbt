name := "app-hackatrix-back"

version := "0.1"

scalaVersion := "2.12.10"

scalacOptions += "-Ypartial-unification"
lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  fork in Test := true,
  name := "back",
  organization:= "co.tnt",
  libraryDependencies ++= Dependencies.common,
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
)



lazy val core = project
  .configs()
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.common,
    name += "-core",
  )


lazy val http = project
  .configs()
  .settings(
      commonSettings,
      libraryDependencies ++=  Dependencies.http,
      name += "-http",
      mainClass in Compile := Some("co.tnt.hackatrix")
  ).dependsOn(core)
