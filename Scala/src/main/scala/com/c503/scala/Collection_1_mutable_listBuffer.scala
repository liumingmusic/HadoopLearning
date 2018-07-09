package com.c503.scala

import scala.collection.mutable.ListBuffer

/**
  * 描述 可变长度集合
  *
  * @author liumm
  * @since 2018-07-09 22:44
  */
object Collection_1_mutable_listBuffer {

  def main(args: Array[String]): Unit = {

    val list = new ListBuffer[String]()
    list += ("ABC")
    list += ("DEF")
    list += ("HIJ")
    println(list)

    println("*" * 40)

    list ++= (List("OK1", "OK2", "OK3"))
    println(list)

    println("*" * 40)

    list.append("123", "456", "789")
    println(list)

    println("*" * 40)

    list.remove(list.length - 1)
    println(list)

    println("list(0)" + list(0))
    println("list(1)" + list(1))
    println("list(2)" + list(2))

    list.clear()
    println("clear:" + list)

  }

}
