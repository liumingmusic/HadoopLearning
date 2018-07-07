package com.liumm.scala

/**
  * 描述 局部函数，内部函数
  *
  * @author liumm
  * @since 2018-07-07 12:19
  */
object Function_5_InvokeByNested_Test {

  def main(args: Array[String]): Unit = {

    println(factorial(0))
    println(factorial(1))
    println(factorial(2))
    println(factorial(3))
    println(factorial(4))

  }

  /**
    * 递归函数
    *
    * @param i
    * @return
    */
  def factorial(i: Int): Int = {

    val temp = 1000

    def fact(acc: Int): Int = {
      if (i <= 1) {
        temp * 1
      }
      else {
        temp * 2
      }
    }

    fact(1)
  }

}
