package com.citybike.data.loader

import org.apache.kafka.clients.producer._
import java.util.Properties


//Kakfa message publisher to publish message to a kafka topic.
object MsgPublisher {

  val producer = new KafkaProducer[String, String](kafkaProperties())

  private def kafkaProperties(): Properties = {
    val kafkaConfigProperties = new Properties()
    kafkaConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.kafkaBootStrapServers)
    kafkaConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    kafkaConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    kafkaConfigProperties
  }

  def publishMessage(payLoad: String) = {
    val kafkaMessage = new ProducerRecord[String, String](Config.stationStatusTopicName, payLoad)
    val kafkaMsgFuture = producer.send(kafkaMessage)
    val data: RecordMetadata = kafkaMsgFuture.get()
  }

}
