package com.c503.streaming

import com.utils.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils
import org.scalatest.time.Seconds

/**
  * sparkstream 从kafka中读取数据进行消费
  *
  * @author liumm
  * @since 2018-07-28 14:38
  */
object SparkStreaming_6_KafkaDirectStream {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf("local[2]", "SparkStreaming_6_KafkaDirectStream")
    val context = new StreamingContext(sparkConf, Seconds(1))



  }

}
