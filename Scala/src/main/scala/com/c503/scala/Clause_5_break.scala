package com.c503.scala

import scala.util.control.Breaks

/**
  * 描述 跳出循环
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Clause_5_break {

  def main(args: Array[String]): Unit = {

    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val loop = new Breaks
    loop.breakable(
      for (a <- numList) {
        println("value of a : " + a)
        if (a == 4) {
          loop.break
        }
      }
    )

  }

}
