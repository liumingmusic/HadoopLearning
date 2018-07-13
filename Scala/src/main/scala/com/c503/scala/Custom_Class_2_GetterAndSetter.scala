package com.c503.scala

/**
  * 描述 简单描述方法的作用
  * Scala对var、字段自动生成对应的getter和setter方法，但是名称是 字段名和字段名_
  * 如果不需要任何getter和setter方法，可以将字段声明为private[this]
  * 只有getter方法，字需要将字段加上val即可
  *
  * @author liumm
  * @since 2018-07-13 14:24
  */
object Custom_Class_2_GetterAndSetter {

  def main(args: Array[String]): Unit = {

    val myPerson = new Person()

    println(myPerson.age)
    myPerson.age = 100
    println(myPerson.age)
    myPerson.age_=(20)
    println(myPerson.age)
    myPerson.printSex()

  }
}

class Person {
  var age = 0
  private[this] val sex = "男"

  def printSex() {
    println(sex)
  }
}
