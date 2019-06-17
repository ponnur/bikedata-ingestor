import sbt.Keys._
import sbt._

name := "bikedata-ingestor"

scalaVersion := "2.12.1"

assemblyJarName in assembly := "bikedata-ingestor.jar"

libraryDependencies ++= Seq(
  "com.typesafe"                    % "config"                        % "1.2.0",
  "commons-io"                      % "commons-io"                    % "2.3",
  "com.fasterxml.jackson.module"    %% "jackson-module-scala"         % "2.9.9",
  "org.apache.kafka"                % "kafka-clients"                 % "2.2.1"

)


//Test dependencies
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0-SNAP5" % Test
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % Test
