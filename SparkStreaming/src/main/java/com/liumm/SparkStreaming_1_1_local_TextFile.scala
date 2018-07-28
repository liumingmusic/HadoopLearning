package com.liumm

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * dataDirectory中的文件需要满足以下的约束
  * 1、文件格式必须相同
  * 2、这些文件在目录dataDirectory中的创建形式比较特殊
  * 必须以原子方式"移动"或者"重命名"至目录dataDirectory中
  * 3、一旦文件被"移动"或者"重命名"至目录dataDirectory中，文件将不会被改变
  *
  * fileStream的实现原理比较简单
  * 以固定的时间段（duration）不断地探测目录
  * 每次探测时间段（now-duration,now]内写入的文件
  *
  * @author liumm
  * @since 2018-07-27 20:10
  */
object SparkStreaming_1_1_local_TextFile {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkStreaming_1_1_local_TextFile").setMaster("local[2]")
    val sc = new StreamingContext(sparkConf, Seconds(1))

    val lines = sc.textFileStream("/Users/liuxm/A_study/idea_ws/mapreduce/")
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map((_, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.foreachRDD(rdd => {
      println("*" * 30)
      rdd.sortBy(x => x._2, false).foreach(e => {
        println(e)
      })
    })

    sc.start()
    sc.awaitTermination()
  }

}
