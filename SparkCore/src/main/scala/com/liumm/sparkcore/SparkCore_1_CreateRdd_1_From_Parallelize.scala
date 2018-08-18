package com.liumm.sparkcore

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

    //第一种生成方式，默认分区数
    val parallelize_1 = sc.parallelize(1 to 100)
    parallelize_1.foreach(println(_))
    println("parallelize创建rdd类型：" + parallelize_1 + ", 默认分区数：" + parallelize_1.partitions.length)

    println("*" * 30)

    //第二种生成方式，显示指定分区数
    val parallelize_2 = sc.parallelize(1 to 100, 3)
    parallelize_2.foreach(println(_))
    println("parallelize创建rdd类型：" + parallelize_2 + ", 显示指定分区数：" + parallelize_2.partitions.length)

    println("*" * 30)

    //第三种生成方式，使用集合list指定
    val parallelize_3 = sc.parallelize(List("hdfs", "hadoop", "hbase", "kafka", "hive"))
    parallelize_3.foreach(println(_))
    println("parallelize创建rdd类型：" + parallelize_3 + ", 默认分区数：" + parallelize_3.partitions.length)

    println("*" * 30)

    //第四种生成方式，使用集合list指定,且有分区数
    val parallelize_4 = sc.parallelize(List("hdfs", "hadoop", "hbase", "kafka", "hive"), 3)
    parallelize_4.foreach(println(_))
    println("parallelize创建rdd类型：" + parallelize_4 + ", 显示指定分区数：" + parallelize_4.partitions.length)

    sc.stop()

  }

}
