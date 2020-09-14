name := """musicFrontend"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(play.sbt.PlayScala).settings(
  scalaVersion := "2.13.3",
libraryDependencies ++= Seq(guice, ws)
)

