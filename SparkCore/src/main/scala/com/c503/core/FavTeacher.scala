package com.c503.core

import java.net.URL
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-09-25 21:19
  */
object FavTeacher {

  val path: String = "/Users/liuxm/A_study/idea_ws/HadoopLearning/SparkCore/src/main/resources/teacher.log"

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("FavTeacher").setMaster("local")

    val sc: SparkContext = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile(path)

    val subjectAndTeacher: RDD[(String, String)] = lines.map(line => {
      val url: URL = new URL(line)
      val host: String = url.getHost
      val teacher: String = url.getPath.substring(1)
      val subject: String = host.substring(0, host.indexOf("."))
      (subject, teacher)
    })

    val subjectAndTeacherCount: RDD[((String, String), Int)] = subjectAndTeacher.map((_, 1)).reduceByKey(_ + _)

    val subjectAndTeacherGroupBy: RDD[(String, Iterable[((String, String), Int)])] = subjectAndTeacherCount.groupBy(_._1._1)

    val result: RDD[(String, List[((String, String), Int)])] = subjectAndTeacherGroupBy.mapValues(_.toList.sortBy(_._2).reverse.take(2))

    result.foreach(println)


  }

}
