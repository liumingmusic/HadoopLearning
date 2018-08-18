package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-08-18 11:30
  */
object SparkCore_1_CreateRdd_3_From_OtherRdd {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_1_CreateRdd_3_From_OtherRdd").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List("Hadoop", "Hbase", "Hive", "Scala", "Spark"))

    val newRdd = rdd.map(x => {(x, "big data")})

    newRdd.foreach(println(_))

    sc.stop()


  }

}
