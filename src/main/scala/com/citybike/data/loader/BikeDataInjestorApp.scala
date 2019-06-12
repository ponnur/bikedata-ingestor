package com.citybike.data.loader

import java.io.IOException
import java.net.URL

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException


//Logging. Test Cases. Design

/*
  BikeDataInjestor retrieves the city bike data from the source data feed and publishes the station data in the feed
  as individual message to a kafka topic.
 */
object BikeDataInjestorApp extends App {

  println("Starting the bike data loader applcation")
  println(Config.toString())

  type JsonData = Seq[Map[String, Any]]

  val stationData = getStationData(Config.stationDataUrl)
  stationData.map(publishMessageToKafka(_))


  //Publish the message as json to the kafka topic for each station
  def publishMessageToKafka(stationData: Seq[Map[String, Any]]): Unit = {
    stationData.map(st => {
      MsgPublisher.publishMessage(JsonObjectMapper.toJson(st))

    })
    println("Messages published to kafka  :" + stationData.size)
  }

  //Retrieve the station data feed from the given url. Filters the invalid data
  def getStationData(url: String): Option[JsonData] = {
    val jsonUrl: URL = new URL(url)

    try {
      val dataFeed: DataFeed = JsonObjectMapper.mapper.readValue(jsonUrl, classOf[DataFeed])

      println("Feed last updated Time:" + dataFeed.lastUpdated)
      println("Feed station count    :" + dataFeed.stationData.stations.size)

      val stationData = dataFeed.stationData.stations.map(st => {
        st + ("lastUpdated" -> dataFeed.lastUpdated)
      }).filter(isValidStationData(_))

      Some(stationData)

    } catch {
      case e@(_: IOException | _: JsonParseException | _: JsonMappingException) => {
        e.printStackTrace()
        None
      }
    }
  }

  //Validate the mandatory fields for data processing
  def isValidStationData(json: Map[String, Any]): Boolean = {
    if (json.contains("station_id") && json.contains("num_bikes_available") && json.contains("num_docks_available")) {
      return true
    }
    println("Missing require fields in the json")
    return false
  }

}
