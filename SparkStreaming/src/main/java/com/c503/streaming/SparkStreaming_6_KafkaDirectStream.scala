package com.c503.streaming

import com.utils.{ConfManager, SparkConf}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * sparkstream 从kafka中读取数据进行消费
  *
  * @author liumm
  * @since 2018-07-28 14:38
  */
object SparkStreaming_6_KafkaDirectStream {

  def main(args: Array[String]): Unit = {

    //初始化conf
    val sparkConf = new SparkConf("local[2]", "SparkStreaming_6_KafkaDirectStream")
    val context = new StreamingContext(sparkConf, Seconds(1))

    //组装kafka配置
    val kafkaParams = ConfManager.kafkaParam(ConfManager.newStreamConf())

    //1、链接kafka,使用正则模式匹配topic
    val dataStream = KafkaUtils.createDirectStream(
      context,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.SubscribePattern[String, String](kafkaParams._2, kafkaParams._1)
    )

    //2、链接kafka,使用topic全名称，为一个集合
    /*
    val dataStream = KafkaUtils.createDirectStream(
      context,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](List("i57_ndsa_result", "i57_ndsa_data"), kafkaParams._1)
    )
    */

    //3、链接kafka,力度更细，对应topic的分区
    /*
    val dataStream = KafkaUtils.createDirectStream(
      context,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Assign[String, String](
        List(
          new TopicPartition("i57_ndsa_result", 1),
          new TopicPartition("i57_ndsa_data", 1)
        ),
        kafkaParams._1
      )
    )
    */

    //执行数据
    dataStream.foreachRDD(rdd => {
      rdd.foreach(partition => {
        var msg = "topic=" + partition.topic() + "\n"
        msg += "partition=" + partition.partition() + "\n"
        msg += "offset=" + partition.offset() + "\n"
        msg += "timestamp=" + partition.timestamp() + "\n"
        msg += "checksum=" + partition.checksum() + "\n"
        msg += "key=" + partition.key() + "\n"
        msg += "value=" + partition.value() + "\n"
        println(msg)
      })
      //手动管理kafka的offset
      dataStream.asInstanceOf[CanCommitOffsets].commitAsync(rdd.asInstanceOf[HasOffsetRanges].offsetRanges)
    })

    context.start()
    context.awaitTermination()
  }

}
