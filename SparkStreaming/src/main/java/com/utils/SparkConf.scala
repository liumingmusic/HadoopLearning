package com.utils

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 11:26
  */
class SparkConf extends org.apache.spark.SparkConf {

  /**
    * 构造函数传入应用内核数量和应用平台运行名称
    *
    * @param master  内核数
    * @param appName 应用名称
    * @param maxRate 速率
    */
  def this(master: String, appName: String, maxRate: String = "100") {
    this()
    this.setMaster(master)
    this.setAppName(appName)
    this.setMaxRatePerPartition(maxRate)
    this.allowDynamicAllocation(false)
    this.setUiPort(4800)
  }

  def setMaxRatePerPartition(maxRate: String): Unit = {
    //限制吞吐，默认无限制
    this.set("spark.streaming.kafka.maxRatePerPartition", maxRate)
  }

  def allowDynamicAllocation(boolean: Boolean): Unit = {
    //资源动态分配，避免高低峰数据吞吐差异导致资源浪费
    this.set("spark.dynamicAllocation.enabled", boolean.toString)
  }


  def setUiPort(port: Int): Unit = {
    //设置spark内置web端口
    this.set("spark.ui.port", port.toString)
  }

}
