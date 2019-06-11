package com.citybike.data.loader

import com.fasterxml.jackson.annotation.JsonProperty


case class StationData(stations: Seq[Map[String,Any]])

case class DataFeed(@JsonProperty("last_updated") lastUpdated: Long, @JsonProperty("ttl") ttl: Int,
                  @JsonProperty("data") stationData: StationData)

