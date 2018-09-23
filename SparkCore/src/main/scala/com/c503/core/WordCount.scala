package com.c503.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-23 16:52
  */
object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("WordCount")

    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile(args(0))

    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordsAndOne: RDD[(String, Int)] = words.map((_, 1))

    val reduce: RDD[(String, Int)] = wordsAndOne.reduceByKey(_ + _)

    val sorted: RDD[(String, Int)] = reduce.sortBy(_._2)

    sorted.saveAsTextFile(args(1))

    sc.stop()


  }

}
