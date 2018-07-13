package com.c503.scala

/**
  * 描述
  * scala与java一样没有多重继承
  * Scala特征非常相似Java的抽象类
  * Scala特征构造器初始化字段与其他特征中的语句构成
  * Scala类可以同时扩展多个特征
  *
  * @author liumm
  * @since 2018-07-13 16:01
  */
object Custom_Trait {

  trait SparkSomeLanague {
    val lanague: String
  }

  class Genius(val name: String, var age: Int) extends SparkSomeLanague {

    val lanague: String = "汉语"

    val university = "北京大学"

    def speak = {
      println(university + "-" + lanague)
    }

  }

  class Worker(name: String, age: Int, val salary: Long) extends Genius(name, age) {

    override val lanague: String = "英语"

    def sleep = "8小时"

  }

  object Worker extends Worker("liumm", 28, 1000)


  def main(args: Array[String]): Unit = {
    val genius = new Genius("xx", 23)
    genius.speak
    val worker = new Worker("xxx", 22, 100)
    worker.speak
    println(Worker.university)
    println(Worker.lanague)
    println(Worker.sleep)
  }

}
