package com.c503.scala

import com.c503.scala.Implicit_4_ValOrVar._

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-19 22:42
  */
object Implicit_3_Parameter {

  def printX(generalParam: String)(implicit implicitStrParamName: String, implicitStrBoolName: Boolean): Unit = {

    println("welcome，" + generalParam + ". The System is ready. ")

    println(implicitStrParamName)
    println(implicitStrBoolName)

  }

  def main(args: Array[String]): Unit = {

    printX("x")

  }

}
