package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-08-18 13:37
  */
object SparkCore_1_CreateRdd_4_From_ByFile_2_Json {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_1_CreateRdd_4_From_ByFile_2_Json").setMaster("local")

    val context = new SparkContext(conf)

    val path = this.getClass.getClassLoader.getResource("spark_core/spark_core.json").getPath

    val rdd = context.textFile(path)

    rdd.foreach(println(_))

    context.stop()


  }

}
