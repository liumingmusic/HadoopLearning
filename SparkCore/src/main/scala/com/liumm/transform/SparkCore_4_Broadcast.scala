package com.liumm.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 广播变量
  * 将drive端设置的变量广播到执行任务的各个executor中，这样executor中的任务可以直接获取到该变量
  * 注：广播的变量在executor中执行的任务为可读
  *
  * @author liumm
  * @since 2018-09-01 19:12
  */
object SparkCore_4_Broadcast {

  class Student(name: String, age: Int, school: String) extends Serializable {
    override def toString: String = {
      name + " , " + age + " , " + school
    }
  }

  object Student {
    def apply(name: String, age: Int, school: String): Student = new Student(name, age, school)
  }

  case class Student1(name: String, age: Int, school: String)

  def main(args: Array[String]): Unit = {

    val student = Student("liumm", 22, "xianwenlixueyuan")

    val conf = new SparkConf().setAppName("SparkCore_4_Broadcast").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize((1 to 10), 3)

    val studentBroadcast = sc.broadcast(student)

    rdd.foreachPartition(partition => {
      val student = studentBroadcast.value
      println(student)
    })

  }

}
