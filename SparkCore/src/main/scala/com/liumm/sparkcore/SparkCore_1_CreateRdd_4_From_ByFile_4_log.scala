package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-08-18 13:48
  */
object SparkCore_1_CreateRdd_4_From_ByFile_4_log {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("").setMaster("local")

    val sc = new SparkContext(conf)

    val path = this.getClass.getClassLoader.getResource("spark_core/spark_core.log").getPath

    val rdd = sc.textFile(path)

    rdd.foreach(println(_))

    sc.stop()


  }

}
