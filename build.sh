#!/usr/bin/env bash

#Build and publish the jar to the server

sbt clean assembly
scp target/scala-2.12/bikedata-ingestor.jar jbit:/home/ubuntu/app

ssh jbit 'chmod +x /home/ubuntu/app/bikedata-ingestor.jar'