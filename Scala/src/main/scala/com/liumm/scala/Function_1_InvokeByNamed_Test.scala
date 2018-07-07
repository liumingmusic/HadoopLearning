package com.liumm.scala

object Function_1_InvokeByNamed_Test {

  def main(args: Array[String]): Unit = {

    printInt(b = 5, a = 3)

  }

  def printInt(a: Int, b: Int) = {
    println("Value of a " + a)
    println("Value of b " + b)
  }

}
