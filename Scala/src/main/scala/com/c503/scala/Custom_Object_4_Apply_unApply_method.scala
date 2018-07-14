package com.c503.scala

/**
  * apply
  * 使用object的apply返回伴生类对象
  * 定义apply方法可以使代码更加简洁
  *
  * @author liumm
  * @since 2018-07-14 12:22
  */
object Custom_Object_4_Apply_unApply_method {

  class HighPerson(var name: String, var age: Int) {
    def printName() = {
      println(name)
    }

    def printAge() = {
      println(age)
    }
  }

  object HighPerson {
    def apply(name: String, age: Int): HighPerson = {
      new HighPerson(name, age)
    }

    def apply(name: String): HighPerson = {
      new HighPerson(name, 21)
    }

    def apply(age: Int): HighPerson = {
      new HighPerson("liumm", age)
    }

    def unapply(hp: HighPerson): Unit = {
      println("unapply")
      println(hp.name)
      println(hp.age)
      Some((hp.name, hp.age))
    }
  }

  def main(args: Array[String]): Unit = {

    val hp1 = HighPerson(10)
    hp1.printAge()
    hp1.printName()

    println("*" * 40)

    val hp2 = HighPerson("liuxm", 10)
    hp2.printAge()
    hp2.printName()

    println("*" * 40)

  }

}
