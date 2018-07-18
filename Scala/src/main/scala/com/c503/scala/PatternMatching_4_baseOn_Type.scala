package com.c503.scala

/**
  * 类型模式匹配
  * 可以把类型模式当做类型测试和类型转换的简易代替
  *
  * @author liumm
  * @since 2018-07-18 18:06
  */
object PatternMatching_4_baseOn_Type {

  def main(args: Array[String]): Unit = {

    println(acceptAnything(1))
    println(acceptAnything("xxx"))
    println(acceptAnything(30))
    println(acceptAnything(1.1d))

  }

  def acceptAnything[T](x: T): String = {
    x match {
      case i: String => "String " + i
      case i: Int => "Int " + i
      case i: Double => "Double " + i
      case i: Int if (i < 20) => "Int and less than 20  " + i
      case _ => "i don't know what that is!"
    }
  }

}
