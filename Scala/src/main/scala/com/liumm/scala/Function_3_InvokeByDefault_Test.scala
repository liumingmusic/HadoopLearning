package com.liumm.scala

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
