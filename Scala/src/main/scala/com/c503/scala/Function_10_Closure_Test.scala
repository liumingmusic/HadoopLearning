package com.c503.scala

/**
  * 描述 闭包
  *
  * @author liumm
  * @since 2018-07-07 15:19
  */
object Function_10_Closure_Test {

  def main(args: Array[String]): Unit = {

    println("muliplier(1) value = " + multiplier)

    factor = factor + 3

    println("muliplier(1) value = " + multiplier)

  }

  var factor = 3

  val multiplier = () => {
    val i = 2
    i * factor
  }

}
