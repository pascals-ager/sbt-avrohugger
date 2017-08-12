name := "sbt-avrohugger"
organization := "com.julianpeeters"
description := "Sbt plugin for compiling Avro to Scala"

version := "0.17.0-SNAPSHOT"

sbtPlugin := true

scalaVersion := appConfiguration.value.provider.scalaProvider.version
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-value-discard")

libraryDependencies ++= Seq(
  "com.julianpeeters" %% "avrohugger-core" % "0.16.0",
  "com.julianpeeters" %% "avrohugger-filesorter" % "0.16.0",
  "io.spray" %%  "spray-json" % "1.3.2",
  "org.specs2" %% "specs2-core" % "3.6.4" % "test")

publishMavenStyle := true
publishArtifact in Test := false
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
pomIncludeRepository := { _ => false }
licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/julianpeeters/sbt-avrohugger"))
pomExtra := (
  <scm>
    <url>git://github.com/julianpeeters/sbt-avrohugger.git</url>
    <connection>scm:git://github.com/julianpeeters/sbt-avrohugger.git</connection>
  </scm>
  <developers>
    <developer>
      <id>julianpeeters</id>
      <name>Julian Peeters</name>
      <url>http://github.com/julianpeeters</url>
    </developer>
  </developers>)

ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false