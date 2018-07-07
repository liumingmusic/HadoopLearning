package com.liumm.scala

object Function_2_InvokeByVar_Test {

  def main(args: Array[String]): Unit = {

    printStrings("1", "2", "ddddd", "dddsdsfdgfdgfg", "sfka")

  }

  def printStrings(temp: String, d: String, args: String*): Unit = {

    var i: Int = 0
    println(i)
    println(temp)
    println(d)
    for (arg <- args) {
      println("Arg value[" + i + "] = " + arg)
      i = i + i
    }

  }

}
