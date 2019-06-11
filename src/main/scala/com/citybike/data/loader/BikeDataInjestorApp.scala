package com.citybike.data.loader

import java.net.URL


object BikeDataInjestorApp extends App{

  println("Starting the bike data loader applcation")
  println(Config.toString())


//  val sampleDataUrl = getClass().getClassLoader().getResource("data.json");


    val stationData = getStationData(Config.stationDataUrl)
    publishMessageToKafka(stationData)



  def publishMessageToKafka(stationData:  Seq[Map[String, Any]]): Unit ={
    stationData.map(st => {
      println(JsonObjectMapper.toJson(st))
      MsgPublisher.publishMessage(JsonObjectMapper.toJson(st))
    })
  }


  def getStationData(url: String): Seq[Map[String, Any]] = {
    val jsonUrl: URL = new URL(url)

    val dataFeed: DataFeed = JsonObjectMapper.mapper.readValue(jsonUrl, classOf[DataFeed])

    val stationData = dataFeed.stationData.stations.map(st => {
      st + ("lastUpdated" -> dataFeed.lastUpdated)
    })

    stationData
  }

}
