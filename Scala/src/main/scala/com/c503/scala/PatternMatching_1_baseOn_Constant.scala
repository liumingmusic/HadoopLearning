package com.c503.scala

/**
  * 常量模式
  * 匹配自身，任何字面量都可以作为常量
  * 外部变量在模式匹配是也可以被常量使用
  *
  * @author liumm
  * @since 2018-07-18 17:02
  */
object PatternMatching_1_baseOn_Constant {

  def main(args: Array[String]): Unit = {

    println(matchBigData("A"))

  }

  def matchBigData[T](bigData: T): String = {
    bigData match {
      case "A" => "A=" + bigData
      case "B" => "B" + bigData
      case "C" => "C" + bigData
      case _ => "UNKWOWN = " + bigData
    }
  }

}
