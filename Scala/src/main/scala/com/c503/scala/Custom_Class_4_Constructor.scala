package com.c503.scala

/**
  * 描述 简单描述方法的作用
  * 辅助构造器
  * 辅助构造器名称为this
  *
  * @author liumm
  * @since 2018-07-13 14:41
  */
object Custom_Class_4_Constructor {

  def main(args: Array[String]): Unit = {

    val myClass = new MyConstructor("DD")

    println(myClass.name)
    println(myClass.age)

    val myClass_1 = new MyConstructor(name = "lmm", age = 20)


  }

}


class MyConstructor {

  var name = "nothing"

  var age = 0

  def this(name: String) {
    this
    this.name = name
    println("构造器:" + this.name)
  }

  def this(name: String, age: Int, ages: Int*) {
    this(name)
    println("2")
  }

}