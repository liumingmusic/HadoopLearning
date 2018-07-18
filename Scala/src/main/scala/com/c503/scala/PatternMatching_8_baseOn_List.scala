package com.c503.scala

/**
  * 匹配list
  *
  * @author liumm
  * @since 2018-07-18 22:58
  */
object PatternMatching_8_baseOn_List {

  def main(args: Array[String]): Unit = {

    matchList(List(1))
    matchList(List(1, 2))
    matchList(List(1, 2, "A"))
    matchList(List(1, 2, 3, "A"))
    matchList(List("A", 2, 3, "B"))
    matchList(List(1, List("A")))
    matchList(List("A", 2, 3, 4))
    matchList(List("A", "2", "3", "4"))

  }

  def matchList(list: List[Any]) = {

    list match {
      case 1 :: Nil => println("List(1)")
      case 1 :: 2 :: Nil => println("List(1, 2)")
      case 1 :: 2 :: _ :: Nil => println("List(1, 2, *)")
      case List(1, a) => println("a = " + a)
      case head :: tail => println("tail = " + tail)
      case head :: 2 :: 3 :: _ :: Nil => println("head = " + head)
      case List(1, List(a)) => println("List(1, List(*)), * = " + a)
      case List("A", _*) => println("List(A, ......)")
      case _ => println("Not Match")
    }

  }

}
