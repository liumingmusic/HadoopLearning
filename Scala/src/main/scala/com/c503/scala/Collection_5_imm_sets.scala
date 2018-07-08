package com.c503.scala

/**
  * 描述 set集合
  * 可以包含不同类型的不同元素
  * 集合中没有重复元素
  * 可变集合和不可变集合
  * scala.collection.mutable.Set
  *
  * @author liumm
  * @since 2018-07-07 18:15
  */
object Collection_5_imm_sets {

  def main(args: Array[String]): Unit = {

    val s1: Set[Int] = Set()
    val s2 = Set(1, 2, 3, 4, 5, 6, "d")
    val s3 = Set(1, 1, 1, 4, 5, "d", "d")

    println(s1)
    println(s2)
    println(s3)

    s3.foreach(x => {
      println(x.getClass.getName)
    })

    println("*" * 40)

    val fruit = Set("apples", "oranges", "pears")
    val nums: Set[Int] = Set()

    println("Head of fruit :  " + fruit.head)
    println("Last of fruit :  " + fruit.last)
    println("Tail of fruit :  " + fruit.tail)
    println("Check if fruit is empty :  " + fruit.isEmpty)
    println("Check if nums is empty :  " + fruit.head)

    println("*" * 40)

    val fruit1 = Set("apples", "oranges", "pears")
    val fruit2 = Set("mangoes", "banana")
    val fruit3 = fruit1 ++ fruit2
    var fruit4 = Set("ddd")
    println("fruit1 ++ fruit2 = " + fruit3)
    fruit4 = fruit1.++(fruit2)
    println("fruit1.++(fruit2) = " + fruit4)

    println("*" * 40)

    val num = Set(5, 3, 4, 6, 7, 9, 0)
    println("min Set(5, 3, 4, 6, 7, 9, 0) = " + num.min)
    println("max Set(5, 3, 4, 6, 7, 9, 0) = " + num.max)

    println("*" * 40)

    val num1 = Set(1, 2, 3, 4, 5)
    val num2 = Set(50, 40, 30, 10)
    println("num1 | num2 " + (num1 | num2))
    println("num1 ++ num2 " + (num1 ++ num2))
    println("num1 union num2 " + (num1 union num2))
    println("num1 & num2 " + (num1 & num2))
    println("num1 intersect num2 " + (num1 intersect num2))
    println("num1 -- num2 " + (num1 -- num2))
    println("num1 diff num2 " + (num1 diff num2))

    println("*" * 40)

    val fruit11 = Set("apples", "oranges", "pears")
    val fruit12 = Set("ok")

    println(fruit11.+("add"))
    println(fruit11.-("add"))
    println("fruit apples " + fruit11.contains("apples"))
    println("fruit apples " + fruit11.exists(_.startsWith("a")))
    println(fruit11.count(_.startsWith("a")))
    println(fruit11.++(fruit12))

  }

}
