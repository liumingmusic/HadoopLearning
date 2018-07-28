package com.c503.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 11:22
  */
object SparkStreaming_4_Socket_RddInfo {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkStreaming_4_Socket_RddInfo").setMaster("local[2]")
    val context = new StreamingContext(sparkConf, Seconds(1))

    val lines = context.socketTextStream("127.0.0.1", 8880)
    val words = lines.map(_.split(" "))
    val pairs = words.map((_, 1))
    val wordCount = pairs.reduceByKey(_ + _)

    wordCount.foreachRDD(e => {
      e.foreach(println)
    })

    context.start()
    context.awaitTermination()

  }

}
