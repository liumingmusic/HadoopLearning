package com.c503.utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-15 14:07
  */
object SparkSqlUtils {

  /**
    * 关闭日志消息
    *
    * @param toggle 是否关闭开关，默认为true
    */
  def offLogger(toggle: Boolean = true): Unit = {
    if (toggle) {
      Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
      Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
      Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.WARN)
      Logger.getLogger("org.spark_project").setLevel(Level.WARN)
    }
  }

  /**
    * 通过文件名称返回文件路径
    *
    * @param fileName 文件名称
    * @return
    */
  def getPathByName(fileName: String): String = {
    this.getClass.getClassLoader.getResource(fileName).getPath
  }

  /**
    * 创建SparkSession对象
    *
    * @param appName
    * @param isHive
    * @return
    */
  def newSparkSession(appName: String, isHive: Boolean = false) = {
    val builder = SparkSession.builder().appName(appName).master("local")
    if (isHive) {
      builder.enableHiveSupport()
    }
    builder.getOrCreate()

  }

}
