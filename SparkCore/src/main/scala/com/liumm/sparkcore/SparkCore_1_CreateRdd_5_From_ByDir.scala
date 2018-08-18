package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 整个目录创建rdd
  *
  * @author liumm
  * @since 2018-08-18 13:59
  */
object SparkCore_1_CreateRdd_5_From_ByDir {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_1_CreateRdd_5_From_ByDir").setMaster("local")

    val sc = new SparkContext(conf)

    val path = this.getClass.getClassLoader.getResource("spark_core").getPath

    //如果是目录，会将每一行数据作为RDD元素，RDD[String]
    val textFile = sc.textFile(path)

    textFile.foreach(println(_))

    println("*" * 40)

    //可以把指定目录下面的文件读取出来，形成RDD[(String),(String)],其中元素类型为元组
    val smallfiles = sc.wholeTextFiles(path)

    smallfiles.foreach(println(_))
  }

}
