package com.c503.utils

import java.io.{BufferedInputStream, BufferedReader, FileInputStream, InputStreamReader}
import java.nio.file.Path

import com.google.common.io.Resources
import org.apache.log4j.{Level, Logger}
import org.apache.mesos.Protos.Resource
import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-15 14:07
  */
object SparkSqlUtils {

  val wareHouse = "/Users/liuxm/A_study/idea_ws/HadoopLearning/SparkSQL/src/main/resources/warehouse"

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
    val builder = SparkSession.builder().appName(appName).master("local").config("spark.sql.warehouse.dir", wareHouse)
    if (isHive) {
      builder.enableHiveSupport()
    }
    builder.getOrCreate()
  }

  /**
    * 读取资源文件内容
    *
    * @param sqlPath
    * @return
    */
  def readSqlByPath(sqlPath: String) = {
    val buf = new StringBuilder
    val path = this.getPathByName(sqlPath)
    val file = Source.fromFile(path)
    for (line <- file.getLines) {
      buf ++= line + "\n"
    }
    file.close
    buf.toString()
  }


}
