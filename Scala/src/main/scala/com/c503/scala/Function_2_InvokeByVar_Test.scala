package com.c503.scala

/**
  * 描述 可传名称方法
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
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
