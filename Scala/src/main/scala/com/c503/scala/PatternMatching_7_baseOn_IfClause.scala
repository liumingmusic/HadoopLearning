package com.c503.scala

/**
  * 守卫模式匹配
  * 模式守卫接在模式之后，开始于if，相当于一个判断语句
  * 守卫可以使任意的引用模式中变量的布尔表达式
  * 如果存在模式守卫，只有在守卫返回true的时候匹配才算成功
  *
  * @author liumm
  * @since 2018-07-18 22:53
  */
object PatternMatching_7_baseOn_IfClause {

  def main(args: Array[String]): Unit = {

    println(acceptAnything(1))
    println(acceptAnything(2))
    println(acceptAnything(3))
    println(acceptAnything(4))

  }

  def acceptAnything(num: Int): String = {
    num match {
      case i if i == 1 => "one"
      case i if i == 2 => "two"
      case i if i == 3 => "three"
      case _ => "未知数字"
    }
  }

}
