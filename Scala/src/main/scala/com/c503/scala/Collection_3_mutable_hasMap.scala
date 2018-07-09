package com.c503.scala

import scala.collection.mutable

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-09 23:17
  */
object Collection_3_mutable_hasMap {

  def main(args: Array[String]): Unit = {

    val map = new mutable.HashMap[String, Int]()

    map += ("key1" -> 100)
    map += ("key2" -> 200)
    map += ("key3" -> 300)
    map += ("key4" -> 400)

    println(map)

    println("*" * 40)

    map ++= (Map("key4" -> 400, "key5" -> 500, "key6" -> 600))

    println(map)

    map -= ("key6")

    println(map)

    map.clear()
    println("clear:" + map)

  }

}
