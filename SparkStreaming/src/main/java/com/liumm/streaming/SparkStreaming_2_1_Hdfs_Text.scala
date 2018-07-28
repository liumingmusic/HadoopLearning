package com.liumm

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 09:44
  */
object SparkStreaming_2_1_Hdfs_Text {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkStreaming_2_1_Hdfs_Text").setMaster("local[2]")
    val context = new StreamingContext(sparkConf, Seconds(1))

    val textFileStream = context.textFileStream("hdfs://hdp01:8082/user")
    val words = textFileStream.flatMap(_.split(" "))
    val pairs = words.map((_, 1))
    val wordCount = pairs.reduceByKey(_ + _)
    wordCount.print()

    context.start()
    context.awaitTermination()

  }

}
