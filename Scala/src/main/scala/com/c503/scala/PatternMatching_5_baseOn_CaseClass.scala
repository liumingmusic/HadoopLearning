package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-18 22:45
  */
object PatternMatching_5_baseOn_CaseClass {

  case class Hadoop(name: String, ver: String)

  case class Hbase(name: String, ver: String)

  case class Hive(name: String, ver: String)

  case class Scala(name: String, ver: String)

  case class Spark(name: String, ver: String)

  def main(args: Array[String]): Unit = {

    println(matchCase(Hadoop("Hadoop", "2.6.3")))
    println(matchCase(Hadoop("Hbase", "1.0.0")))
    println(matchCase(Hadoop("Hive", "4.1.1")))
    println(matchCase(Hadoop("Scala", "2.0.0")))
    println(matchCase(Hadoop("Spark", "2.1.1")))

  }

  def matchCase(transport: Any): String = {
    transport match {
      case x: Hadoop => "Hadoop:" + x.name + x.ver
      case x: Hbase => "Hbase:" + x.name + x.ver
      case x: Hive => "Hive:" + x.name + x.ver
      case x: Scala => "Scala:" + x.name + x.ver
      case x: Spark => "Spark:" + x.name + x.ver
      case _ => "UNKNWON"
    }
  }

}
