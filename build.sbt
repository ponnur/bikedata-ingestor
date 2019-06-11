import sbt.Keys._
import sbt._

name := "bike-data"

scalaVersion := "2.12.1"

assemblyJarName in assembly := "bike-data.jar"

libraryDependencies ++= Seq(
  "com.typesafe"                    % "config"                        % "1.2.0",
  "commons-io"                      % "commons-io"                    % "2.3",
  "com.fasterxml.jackson.module"    %% "jackson-module-scala"         % "2.9.9",
  "org.apache.kafka"                % "kafka-clients"                 % "2.2.1"

)
