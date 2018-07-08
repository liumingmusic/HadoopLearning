package com.c503.scala

/**
  * 描述 递归
  *
  * @author liumm
  * @since 2018-07-07 13:26
  */
object Function_6_InvokeByRecursion_Test {

  def main(args: Array[String]): Unit = {

    println(factorial(10))

  }

  def factorial(n: BigInt): BigInt = {
    if (n <= 1) {
      1
    } else {
      n * factorial(n - 1)
    }
  }
}
