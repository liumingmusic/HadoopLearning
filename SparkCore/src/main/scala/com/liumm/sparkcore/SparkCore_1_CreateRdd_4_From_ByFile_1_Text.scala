package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 基于文本文件创建RDD
  *
  * @author liumm
  * @since 2018-08-18 11:37
  */
object SparkCore_1_CreateRdd_4_From_ByFile_1_Text {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_1_CreateRdd_4_From_ByFile_1_Text").setMaster("local")
    val sc = new SparkContext(conf)

    val dataPath = this.getClass.getClassLoader.getResource("spark_core/spark_core.txt").getPath

    val textRdd = sc.textFile(dataPath)

    textRdd.foreach(println(_))

    sc.stop()

  }

}
