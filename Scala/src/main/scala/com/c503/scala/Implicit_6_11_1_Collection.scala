package com.c503.scala

import com.c503.scala.Implicit_1_Class._

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-19 23:11
  */
object Implicit_6_11_1_Collection {

  def main(args: Array[String]): Unit = {

    val seq = Seq("Hbase", "Hadoop", "Hive", "Scala")

    println("customMember=" + seq.customMember)

    println("showString=" + seq.showString)

  }

}
