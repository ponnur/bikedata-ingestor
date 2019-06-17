package com.citybike.data.loader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper


object JsonObjectMapper {
  val module = new SimpleModule

  val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.registerModule(module)

  //Return the json string of the given object
  def toJsonString(value: Any): String = {
    mapper.writeValueAsString(value)
  }

}
