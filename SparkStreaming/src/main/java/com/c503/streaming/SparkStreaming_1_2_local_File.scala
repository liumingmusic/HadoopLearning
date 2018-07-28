package com.c503.streaming

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 09:36
  */
object SparkStreaming_1_2_local_File {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkStreaming_1_2_local_File").setMaster("local[2]")
    val sc = new StreamingContext(sparkConf, Seconds(1))

    val textFileStream = sc.fileStream[LongWritable, Text, TextInputFormat]("")
    val lines = textFileStream.map(_._2.toString)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map((_, 1))
    val wordCount = pairs.reduceByKey(_ + _)
    wordCount.print()

    sc.start()
    sc.awaitTermination()

  }

}
