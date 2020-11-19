name := """musicFrontend"""
organization := "com.example"

version := "1.0-SNAPSHOT"
val akkaVersion = "2.5.26"
lazy val root = (project in file(".")).enablePlugins(play.sbt.PlayScala).settings(
  scalaVersion := "2.13.3",
libraryDependencies ++= Seq(guice, ws)
)

val akkaHttpVersion = "10.1.11"
libraryDependencies ++= Seq(
  // akka streams
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  // akka http
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
)