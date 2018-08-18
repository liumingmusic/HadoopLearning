package com.liumm.sparkcore

import org.apache.spark.{SparkConf, SparkContext}


/**
  * makeRdd使用，用法和parallelize一样，该方法可以指定每一个分区的首选位置
  *
  * @author liumm
  * @since 2018-07-28 20:35
  */
object SparkCore_1_CreateRdd_2_From_MakeRdd {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkCore_1_CreateRdd_2_From_MakeRdd").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    //第一种
    val makeRDD1 = sparkContext.makeRDD(1 to 100)
    makeRDD1.foreach(println(_))
    println("makeRDD方法创建RDD类型：" + makeRDD1 + ",默认分区数：" + makeRDD1.partitions.length)

    //第二种
    val makeRDD2 = sparkContext.makeRDD(1 to 100, 3)
    makeRDD2.foreach(println(_))
    println("makeRDD方法创建RDD类型：" + makeRDD2 + ",显示指定分区数：" + makeRDD2.partitions.length)

    //第三种
    val makeRDD3 = sparkContext.makeRDD(List("hive", "spark", "kafka", "hbase"))
    makeRDD3.foreach(println(_))
    println("makeRDD方法创建RDD类型：" + makeRDD3 + ",默认分区数：" + makeRDD3.partitions.length)

    //第四种
    val makeRDD4 = sparkContext.makeRDD(List("hive", "spark", "kafka", "hbase"), 4)
    makeRDD4.foreach(println(_))
    println("makeRDD方法创建RDD类型：" + makeRDD4 + ",显示指定分区数：" + makeRDD4.partitions.length)

    //第五种，显示指定首选的rdd位置
    val makeRDD5 = sparkContext.makeRDD(
      Seq(
        (1 to 3, Seq("master", "slave1")),
        (4 to 8, Seq("slave2")),
        (8 to 9, Seq("slave3"))
      )
    )
    makeRDD5.foreach(println(_))

  }

}
