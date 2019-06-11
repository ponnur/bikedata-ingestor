package com.citybike.data.loader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

/**
  * Created by ponnulingam on 6/8/19.
  */
object JsonObjectMapper {
  val module = new SimpleModule

  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.registerModule(module)

  def toJson(value: Any): String = {
    mapper.writeValueAsString(value)
  }

}
