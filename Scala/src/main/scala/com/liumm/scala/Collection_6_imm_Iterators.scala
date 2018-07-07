package com.liumm.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-07 18:50
  */
object Collection_6_imm_Iterators {

  def main(args: Array[String]): Unit = {

    val it = Iterator("aa", "bb", "cc")
    while (it.hasNext) {
      println("Iterator.X = " + it.next())
    }

    println("*" * 40)

    val it_A = Iterator("www")
    val it_B = Iterator("scala", "spark", "hadoop", "hive")
    val it_ok_1 = it_A ++ it_B
    val it_ok_2 = it_A.++(it_B)

    while (it_ok_1.hasNext) {
      println("Iterator.X = " + it_ok_1.next())
    }


    println("*" * 40)

    while (it_ok_2.hasNext) {
      println("Iterator.y = " + it_ok_2.next())
    }


  }

}
