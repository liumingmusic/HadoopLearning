package com.c503.scala

/**
  * 元组匹配
  *
  * @author liumm
  * @since 2018-07-18 22:49
  */
object PatternMatching_6_baseOn_Tuple {

  def main(args: Array[String]): Unit = {

    matchTuple(("A", 1))
    matchTuple(("B", 2))
    matchTuple((1, 1))

  }

  def matchTuple(tuple: (Any, Any)) = {
    tuple match {
      case ("A", _) => println("(A, *)")
      case (x, 2) => println("(x, 1) = " + x)
      case (x, y) => println("(x, y) = " + x + "," + y)
      case (_: Int, _) => println("(x, y)")
      case _ => println("Not match")
    }
  }

}
