package com.c503.scala

/**
  * 描述 默认参数
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Function_3_InvokeByDefault_Test {
  def main(args: Array[String]): Unit = {

    val i = addInt()

    println(i)

  }

  def addInt(a: String = "ok", b: Int = 9): Int = {
    println("a = " + a)
    println("b = " + b)
    0
  }
}
