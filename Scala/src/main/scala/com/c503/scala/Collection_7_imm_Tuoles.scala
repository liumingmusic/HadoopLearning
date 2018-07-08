package com.c503.scala

import java.util.Date

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-07 18:59
  */
object Collection_7_imm_Tuoles {

  def main(args: Array[String]): Unit = {

    val tuples_1 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22)
    val tuples_2 = Tuple3[Int, String, Int](1, "dd", 2)
    val tuples_3 = (1, 2, 3)
    val tuples_4 = Tuple3(1, "hello", Console)

    println(tuples_1)
    println(tuples_2)
    println(tuples_3)
    println(tuples_4)

    println("*" * 40)

    val tupleDefault = ("Scala", "Scala", "Spark", 120, 12, 34, new Date())
    println(tupleDefault._1)
    println(tupleDefault._2)
    println(tupleDefault._3)
    println(tupleDefault._4)
    println(tupleDefault._5)
    println(tupleDefault._6)
    println(tupleDefault._7)

    println("*" * 40)

    val t = Tuple2("Scala", "Hello")
    println("Tuple:" + t)
    println("swapped Tuple:" + t.swap)

    println("*" * 40)

    val t1 = (4, 3, 2, 1)
    t1.productIterator.foreach(println)

    val arrkey = Array(1, 3, 5)
    val arrValue = Array("a", "b", "c", "d")
    val tupleArr = arrkey.zipAll(arrValue, 1, 2)
    val map = tupleArr.toMap
    println(map)
    println(tupleArr.getClass.getName)

    println("*" * 40)

    val tuples_A_1 = ("A")
    val tuples_A_2 = Tuple1[String]("A")
    val tuples_A_3 = Tuple1[Int](1)
    val tuple_B = ("A", "B")
    val tuple_C = ("A", "B", "C")
    val tuple_D = ("A", "B", "C", "D")
    println(tuples_A_1.getClass.getSimpleName)
    println(tuples_A_2.getClass.getSimpleName)
    println(tuples_A_3.getClass.getSimpleName)
    println(tuple_B.getClass.getSimpleName)
    println(tuple_C.getClass.getSimpleName)
    println(tuple_D.getClass.getSimpleName)

  }

}
