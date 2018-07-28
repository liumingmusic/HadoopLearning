package com.utils

import java.util.regex.Pattern

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.mutable.HashMap

/**
  * 描述 Spark Streaming 配置
  *
  * @author liumm
  * @since 2018-07-27 20:27
  */
object ConfManager {

  /**
    * 每次入库最大记录数量
    */
  val maxRecords = 1000

  /**
    * 配置Kafka
    *
    * @param streamConf
    * @return
    */
  def kafkaParam(streamConf: StreamConf): (Map[String, Object], Pattern) = {
    (getConsumerConfig(streamConf.brokers, streamConf.groupId), Pattern.compile(streamConf.topics))
  }

  def kafkaParamForMetadata(streamConf: StreamConf): Map[String, String] = {
    val kafkaParams = new HashMap[String, String]()
    kafkaParams += (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> streamConf.brokers)
    kafkaParams += ("metadata.broker.list" -> streamConf.brokers)
    kafkaParams += (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "smallest")
    kafkaParams += (ConsumerConfig.GROUP_ID_CONFIG -> streamConf.groupId)
    kafkaParams.toMap
  }

  /**
    * 生成Kafka的Consumer配置信息
    *
    * @return Kafka的Consumer配置信息
    */
  private def getConsumerConfig(brokers: String, groupId: String): Map[String, Object] = {
    val kafkaParams = new HashMap[String, Object]()

    kafkaParams += (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers)
    kafkaParams += (ConsumerConfig.GROUP_ID_CONFIG -> groupId)
    kafkaParams += (ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])
    kafkaParams += (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])

    kafkaParams += (ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG -> new Integer(3 * 1024 * 1024))
    kafkaParams += (ConsumerConfig.MAX_POLL_RECORDS_CONFIG -> new Integer(100))

    kafkaParams += (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "latest")
    //关闭kafka自动提交offset方式
    kafkaParams += (ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> (false: java.lang.Boolean))

    kafkaParams.toMap
  }

  def newStreamConf() = {
    val conf = new StreamConf()
    conf.zkUrl = "hdp01:2181"
    conf.brokers = "hdp01:9092"
    conf.groupId = "liumm_group"
    conf.topics = "i57_.*"
    conf
  }

}
