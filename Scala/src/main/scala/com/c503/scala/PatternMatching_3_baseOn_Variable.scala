package com.c503.scala

/**
  * 变量模式
  * 类似于通配符，可以匹配任意对象
  * 不同的是匹配的对象会被绑定在变量上，之后就可以使用这个变量操作对象
  *
  * @author liumm
  * @since 2018-07-18 17:31
  */
object PatternMatching_3_baseOn_Variable {

  def main(args: Array[String]): Unit = {

    println(matchBigData(1))
    println(matchBigData("x"))
    println(matchBigData(true))
    println(matchBigData(System.currentTimeMillis()))

  }

  def matchBigData[T](bigData: T): String = {
    bigData match {
      case true => "true = " + true.getClass.getSimpleName
      case false => "false = " + false.getClass.getSimpleName
      case Int => "Int = " + Int.getClass.getSimpleName
      case "ABC" => "ABC = " + "".getClass.getSimpleName
      case x => "x = " + x.getClass.getSimpleName
    }
  }

}
