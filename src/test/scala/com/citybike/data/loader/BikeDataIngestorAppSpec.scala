package com.citybike.data.loader


import java.net.URL

import com.citybike.data.loader.BikeDataIngestorApp.JsonData
import org.scalatest.{FunSpec, Matchers}

class BikeDataIngestorAppSpec extends FunSpec {

  val stationDataUrl: URL = getClass().getClassLoader().getResource("data.json")
  println("URL"+stationDataUrl)

  describe("bike data ingestor"){
    it("should filter out the invalid station data "){
      val jsonData: Option[JsonData] = BikeDataIngestorApp.getStationData(stationDataUrl)
      jsonData match {
        case Some(data) => assert(data.size ==1)
        case _ => fail("Invalid size")
      }
    }
    it("should include the mandatory station data fields"){
      val jsonData: Option[JsonData] = BikeDataIngestorApp.getStationData(stationDataUrl)
      val resultData = jsonData.get.head
      assert(resultData.get("station_id") == Some("3092"))
      assert(resultData.get("num_bikes_available") == Some(3))
      assert(resultData.get("num_docks_available") == Some(24))

    }

  }

}
