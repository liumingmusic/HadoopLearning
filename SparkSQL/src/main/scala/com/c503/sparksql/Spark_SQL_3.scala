package com.c503.sparksql


import com.c503.utils.SparkSqlUtils
import org.elasticsearch.spark.sql._

import scala.language.postfixOps


/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-22 13:55
  */
object Spark_SQL_3 {

  def main(args: Array[String]): Unit = {

    SparkSqlUtils.offLogger()

    sparkReadES()

  }

  def sparkReadES(): Unit = {

    //SparkSession
    val sqlContext = SparkSqlUtils.newSparkSession("Spark_SQL_3")

    //参数
    val options = Map(
      "pushdown" -> "true",
      "es.nodes" -> "192.168.56.100",
      "es.port" -> "9200",
      "es.nodes.wan.only" -> "true",
      "es.query" -> "?q=*",
      "es.http.timeout" -> "10s",
      "es.scroll.size" -> "5000",
      "es.read.field.as.array.include" -> "",
      "es.mapping.date.rich" -> "false",
      "es.index.auto.create" -> "true"
    )

    //构建df
    val df = sqlContext.read.format("es").options(options).load("spark_es")

    //df.select("name", "age").collect().foreach(println(_))
    //临时表
    df.createOrReplaceTempView("person")

    //根据sql查询数据
    val queryWhere = sqlContext.sql(SparkSqlUtils.readSqlByPath("sql/test.sql"))

    //sql统计数据后写入es中
    queryWhere.saveToEs("i57_stats/net", options)


  }

}