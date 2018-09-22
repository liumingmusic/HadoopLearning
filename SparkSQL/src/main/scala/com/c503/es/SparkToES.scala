package com.c503.es

import com.c503.utils.SparkSqlUtils
import org.elasticsearch.spark.sql.sparkDataFrameFunctions

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-22 18:12
  */
object SparkToES {

  def main(args: Array[String]): Unit = {

    SparkSqlUtils.offLogger()

    val sqlContext = SparkSqlUtils.newSparkSession("SparkToES")

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
      "es.mapping.date.rich" -> "false"
    )

    val df = sqlContext.read.format("es").options(options).load("bank")

    df.createOrReplaceTempView("bank_table")

    //SQL脚本
    val show_all_sql = SparkSqlUtils.readSqlByPath("sql/show_all_sql.sql")
    val gender_stats_sql = SparkSqlUtils.readSqlByPath("sql/gender_stats_sql.sql")
    val balance_stats_sql = SparkSqlUtils.readSqlByPath("sql/balance_stats.sql")
    val gender_group_by_sql = SparkSqlUtils.readSqlByPath("sql/gender_group_by_sql.sql")

    //sql查询
    val result_all = sqlContext.sql(show_all_sql)
    val result_gender = sqlContext.sql(gender_stats_sql)
    val result_balance = sqlContext.sql(balance_stats_sql)
    val result_gender_stats = sqlContext.sql(gender_group_by_sql)

    //统计分析数据存入ES中
    result_all.saveToEs("i57_stats/all", options)
    result_gender.saveToEs("i57_stats/gender", options)
    result_balance.saveToEs("i57_stats/balance", options)
    result_gender_stats.saveToEs("i57_stats/gender_stats", options)

    //关闭回话
    sqlContext.stop()

  }

}
