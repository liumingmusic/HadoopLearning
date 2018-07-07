package com.liumm.scala

/**
  * 描述 柯里函数
  *
  * @author liumm
  * @since 2018-07-07 13:31
  */
object Function_8_InvokeByCurrying_Test {

  def main(args: Array[String]): Unit = {

    val str1: String = "hello"
    val str2: String = "scala"

    println(strcat_1(str1, str2))
    println(strcat_2(str1)(str2))

  }

  def strcat_1(s1: String, s2: String): String = {
    s1 + s2
  }

  def strcat_2(s1: String)(s2: String): String = {
    s1 + s2
  }


}
