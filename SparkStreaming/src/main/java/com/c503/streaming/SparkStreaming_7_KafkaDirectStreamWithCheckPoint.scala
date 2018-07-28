package com.c503.streaming

import com.utils.{ConfManager, SparkConf}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 19:03
  */
object SparkStreaming_7_KafkaDirectStreamWithCheckPoint {

  case class Record(key: String, value: String, offset: Long, keySize: Long, valueSize: Long)

  def main(args: Array[String]): Unit = {

    //检查点存放的数据地方
    val checkPointDirectory = "/Users/liuxm/A_study/idea_ws/mapreduce"

    //整体需要添加的driver端的checkpoint，需要包含整体
    val createStreamingContext = () => {

      val sparkConf = new SparkConf("local[2]", "SparkStreaming_7_KafkaDirectStreamWithCheckPoint")

      val patchDuration = 1

      val context = new StreamingContext(sparkConf, Seconds(patchDuration))

      val kafkaParam = ConfManager.kafkaParam(ConfManager.newStreamConf())

      val dataStream = KafkaUtils.createDirectStream(
        context,
        LocationStrategies.PreferConsistent,
        ConsumerStrategies.SubscribePattern[String, String](kafkaParam._2, kafkaParam._1)
      )

      //检查点
      context.checkpoint(checkPointDirectory)

      dataStream.foreachRDD(rdd => {

        rdd.map(consumerRecord => {
          val key = consumerRecord.key()
          val value = consumerRecord.value()
          val offset = consumerRecord.offset()
          val keySize = consumerRecord.serializedKeySize()
          val valueSize = consumerRecord.serializedValueSize()
          Record(key, value, offset, keySize, valueSize)
        }).foreach(partition => {
          println(partition)
        })

        //手动管理kafka的offset
        dataStream.asInstanceOf[CanCommitOffsets].commitAsync(rdd.asInstanceOf[HasOffsetRanges].offsetRanges)

        //持久checkpoint
        rdd.checkpoint()
      })
      context
    }: StreamingContext

    val sc = StreamingContext.getActiveOrCreate(checkPointDirectory, createStreamingContext)

    sc.start()
    sc.awaitTermination()

  }

}
