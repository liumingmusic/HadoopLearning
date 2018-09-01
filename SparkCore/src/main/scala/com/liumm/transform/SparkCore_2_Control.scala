package com.liumm.transform

import org.apache.spark.{SparkConf, SparkContext}


/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-01 15:07
  */
object SparkCore_2_Control {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_2_Control").setMaster("local")
    val sc = new SparkContext(conf)

    val dataPath = this.getClass.getClassLoader.getResource("log4j.properties").getPath()
    //使用cache函数进行数据持久化，把RDD持久化放在内存中
    val rdd1 = sc.textFile(dataPath)
    //缓存这些数据
    rdd1.cache()
    //第一次读取会从磁盘
    println(rdd1.count())
    //第二次读取会从内存
    println(rdd1.count())

    //persist函数也是进行持久化操作，cache函数是他的特例，在persist可以指定一个storageLevel，当storageLevel为memory_only时，默认persist就是memory_only
    //使用cache函数进行持久化，把rdd持久化到内存中
    val rdd2 = sc.textFile(dataPath)
    //缓存这些数据
    rdd2.persist()
    //第一次读取在磁盘，并放在内存
    println(rdd2.count())
    //第二次读取在内存
    println(rdd2.count())

    sc.stop()

  }

}
