package com.c503.scala
/**
  * 描述 方法声明
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Function_1_InvokeByNamed_Test {

  def main(args: Array[String]): Unit = {

    printInt(b = 5, a = 3)

  }

  def printInt(a: Int, b: Int) = {
    println("Value of a " + a)
    println("Value of b " + b)
  }

}
