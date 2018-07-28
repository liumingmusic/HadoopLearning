package com.c503.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 09:51
  */
object SparkStreaming_3_Socket_SparkIsClient {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkStreaming_3_Socket_SparkIsClient").setMaster("local[2]")
    val context = new StreamingContext(sparkConf, Seconds(1))

    val lines = context.socketTextStream("localhost", 8880)
    println(lines)
    val words = lines.flatMap(_.split(" "))
    val paris = words.map((_, 1))
    val wordCount = paris.reduceByKey(_ + _)

    wordCount.print()

    context.start()
    context.awaitTermination()

  }

}
