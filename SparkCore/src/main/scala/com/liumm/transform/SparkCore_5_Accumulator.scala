package com.liumm.transform

import org.apache.spark.util.{CollectionAccumulator, DoubleAccumulator, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 累加器
  * 累加器(accumulate) 是Spark中提供的一种分布式变量机制
  * 其原理类似于mapreduce，及分布式的改变，然后聚合这些改变
  * 累加器的一个常见用途是在调试时对作业执行的过程的事件进行技术
  * 官方提供了一个抽象类，accumulateV2来提供更加友好的自定义类型的累加器来实现
  *
  * @author liumm
  * @since 2018-09-01 19:29
  */
object SparkCore_5_Accumulator {

  case class Info(var success: Boolean, var msg: String, count: Int)

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_5_Accumulator").setMaster("local")
    val sc = new SparkContext(conf)

    val r = sc.parallelize(1 to 100)

    println("\n-----------------------传统方式累加------------------------")

    var total = 0
    r.foreach(num => {
      total += num
    })

    println(total)

    println("\n-----------------------注册LongAccumulator-------------------")

    //内置的累加器只支持 Long、Double、Collection
    val rdd = sc.parallelize(1 to 100, 4)

    //1、显示注册
    val longAccumulator = new LongAccumulator
    sc.register(longAccumulator)

    rdd.foreach(num => {
      longAccumulator.add(num)
    })
    println(longAccumulator.value)

    println("\n-----------------------直接使用LongAccumulator-------------------")
    //2、直接使用LongAccumulator，无需注册
    val longAccumulator1 = sc.longAccumulator
    rdd.foreach(num => {
      longAccumulator1.add(num)
    })
    println(longAccumulator1.value)

    println("\n-----------------------直接使用DoubleAccumulator-------------------")
    val rdd1 = sc.parallelize(List(1.1, 2.2, 3.3, 4.3))
    //1、显示注册
    val doubleAccumulator = new DoubleAccumulator
    sc.register(doubleAccumulator)
    rdd1.foreach(num => {
      doubleAccumulator.add(num)
    })
    println(doubleAccumulator.value)
    //2、直接使用
    val doubleAccumulator1 = sc.doubleAccumulator
    rdd1.foreach(num => {
      doubleAccumulator1.add(num)
    })
    println(doubleAccumulator1.value)

    println("\n-----------------------直接使用CollectionAccumulator-------------------")

    val rdd2 = sc.parallelize(1 to 100)
    //1、显示注册
    val collectionAccumulator = new CollectionAccumulator[Int]
    sc.register(collectionAccumulator)
    rdd2.foreach(num => {
      collectionAccumulator.add(num)
    })
    println(collectionAccumulator)

    //2、直接使用
    val collectionAccumulator1 = sc.collectionAccumulator[Int]
    rdd2.foreach(num => {
      collectionAccumulator.add(num)
    })
    println(collectionAccumulator1)

    println("\n-----------------------使用自定义累加器-------------------")
    val customAccumulator = new CustomAccumulator
    sc.register(customAccumulator)
    rdd.foreach(num => {
      customAccumulator.add(num.toString)
    })
    println(customAccumulator.value)

  }

}
