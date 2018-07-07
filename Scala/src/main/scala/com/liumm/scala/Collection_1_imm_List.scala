package com.liumm.scala

/**
  * 描述 list集合，标准的linked list
  * 不可变的数组链表
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Collection_1_imm_List {

  def main(args: Array[String]): Unit = {

    val fruit = List("apples", "oranges", "pears")
    val nums: List[Int] = List(1, 2, 3, 4, 5, 6)
    val empty: List[Nothing] = List()
    val dim: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))

    println(fruit)
    println(nums)
    println(empty)
    println(dim)

    println("*" * 20)

    val fruit1 = "apples" :: ("oranges" :: ("pears" :: Nil))
    val num1 = 1 :: 2 :: 3 :: 4 :: Nil
    val empty1 = Nil
    val dim1 = (1 :: (0 :: (0 :: Nil))) :: (0 :: (1 :: (0 :: Nil))) :: (0 :: (0 :: (1 :: Nil))) :: Nil

    println(fruit1)
    println(num1)
    println(empty1)
    println(dim1)

    println("*" * 20)

    val fruit2 = List("apples", "oranges", "pears")
    val nums2 = Nil

    println("Init of fruit : " + fruit2.init)
    println("Head of fruit : " + fruit2.head)
    println("Last of fruit : " + fruit2.last)
    println("Tail of fruit : " + fruit2.tail)
    println("Check if fruit is empty : " + fruit2.isEmpty)
    println("Check if nums is empty : " + nums2.isEmpty)

    println("*" * 20)

    val furit01 = List("apples", "oranges", "pears")
    val furit02 = List("mangoes", "banana")
    val furit03 = List("ff3", "ff4")
    var furit04 = furit01 ::: furit02 ::: furit03
    println("furit03 ::: furit02 ::: furit03 : " + furit04)
    furit04 = furit01.:::(furit02).:::(furit03)
    println("furit01.:::(furit02).:::(furit03) : " + furit04)
    furit04 = List.concat(furit01, furit02, furit03)
    println("List.concat(furit01, furit02, furit03) : " + furit04)

    println("*" * 20)

    val fruit0 = List.fill(3)("apples")
    println(fruit0)

    val num0 = List.fill(10)(2)
    println(num0)

    println("*" * 20)

    val squares = List.tabulate(6)(n => n * n)
    val mul = List.tabulate(6, 2)(_ * _)

    println("squares " + squares)
    println("mul " + mul)

    println("*" * 20)

    val fruita = "apple" :: ("oranges" :: ("pears" :: Nil))
    println("before reverse fruit : " + fruita)
    println("after reverse fruit : " + fruita.reverse)

    println("*" * 20)

    val otherA: List[String] = List("apples", "oranges", "pears")
    val otherB = List("mangoes", "banana")

    println(otherA.+("first"))
    println(otherA.:+("first"))
    println(otherA.+:("first"))
    println(otherA.++("first"))
    println(otherA.++(otherB))
    println(List.concat(otherA, otherB))
    println(otherA.::("first"))
    println(otherA.:::(otherB))

    println("*" * 20)

    val otherC: List[String] = List("apples", "oranges", "pears")
    println(otherC.mkString)
    println(otherC.mkString(","))

    println(otherA ++ otherB)

  }

}
