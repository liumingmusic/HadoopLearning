package com.c503.sparksql

import com.c503.utils.SparkSqlUtils
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-16 15:45
  */
object Spark_SQL_2 {

  def main(args: Array[String]): Unit = {

    SparkSqlUtils.offLogger()




  }

  def rddToDF_two(): Unit = {
    val spark = SparkSqlUtils.newSparkSession("Spark_SQL_2")
    val lines = spark.sparkContext.textFile(SparkSqlUtils.getPathByName("person.txt"))

    //定义一个模式字符串
    val schemaString = "name age"

    //根据模式字符串生成模式
    val fields = schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, nullable = true))

    //从上面信息可以看出，schema描述了模式信息，模式中包含name和age两个字段
    //对peopleRDD 这个RDD中的每一行元素都进行解析val peopleDF = spark.read.format("json").load("examples/src/main/resources/people.json")
    val schema = StructType(fields)

    val rowRDD = lines.map(_.split(",")).map(attributes => Row(attributes(0), attributes(1).trim))

    val peopleDF = spark.createDataFrame(rowRDD, schema)

    //必须注册为临时表才能供下面查询使用
    peopleDF.createOrReplaceTempView("people")

    val results = spark.sql("SELECT name,age FROM people")

    results.show()
  }

  def rddToDF_one(): Unit = {
    val spark = SparkSqlUtils.newSparkSession("Spark_SQL_2")
    val lines = spark.sparkContext.textFile(SparkSqlUtils.getPathByName("person.txt"))

    //隐式导入
    import spark.implicits._
    val peopleDF = lines.map(_.split(",")).map(arr => {
      Person(arr(0), arr(1).trim.toInt)
    }).toDF()

    //必须注册为临时表才能供下面的查询使用
    peopleDF.createOrReplaceTempView("person")

    spark.sql("select name, age from person where age > 20").show()
  }

}


case class Person(name: String, age: Long)
