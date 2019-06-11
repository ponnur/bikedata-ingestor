package com.citybike.data.loader

import com.typesafe.config.ConfigFactory


object Config {

  val appConfiguration = ConfigFactory.load()

  val kafkaBootStrapServers = appConfiguration.getString("kafka.bootstrapservers")
  val stationStatusTopicName = appConfiguration.getString("kafka.stationstatustopicname")
  val stationDataUrl = appConfiguration.getString("stationdata.url")

  override def toString: String = {
    s"""
      |KafaBootStrapServers : ${kafkaBootStrapServers}
      |StationDataTopicName : ${stationStatusTopicName}
      |StationDataUrl       : ${stationDataUrl}
    """.stripMargin
  }
}
