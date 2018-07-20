package com.liumm

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-20 20:35
  */
object SparkCore_1_CreateRdd_1_From_Parallelize {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkCore_1_CreateRdd_1_From_Parallelize").setMaster("local")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")

    val parallelize_1 = sc.parallelize(1 to 100)
    parallelize_1.foreach(println(_))
    println("parallelize创建rdd类型：" + parallelize_1 + ", 默认分区数：" + parallelize_1.partitions.length)


  }

}
