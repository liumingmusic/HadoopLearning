package com.c503.scala
/**
  * 描述 do while循环
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Clause_4_do_while {

  def main(args: Array[String]): Unit = {

    var a = 1
    do {
      println("value of a : " + a)
      a = a + 1
    } while (a < 6)

  }

}
