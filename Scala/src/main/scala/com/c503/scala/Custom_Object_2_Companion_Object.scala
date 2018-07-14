package com.c503.scala

/**
  * 伴生对象
  * scala通过类和类名相同的伴生对象达到既有实例方法又有静态方法的类的目的
  * 类与它的伴生对象必须在同一个scala源文件中
  *
  * @author liumm
  * @since 2018-07-14 12:03
  */
object Custom_Object_2_Companion_Object {

  //伴生对象的伴生类
  class Person {
    private val name = "liumm"

    def printName() = {
      println(name)
    }
  }

  //伴生类的伴生对象
  object Person {
    def printlnX() = {
      println("abcd")
    }
  }

  class Person1 {
    //作用域在scala包下面的所有对象和类可以访问
    private[scala] val name = "liumm"
    //不生成getter和setter
    private[this] val age = 25
    //私有的getter和setter
    private val sex = 1

    def printlnName() = {
      println(name)
    }

    def printSex(person1: Person1) = {
      println(person1.name)
      println(age)
    }
  }

  object Person1 extends Person1 {
    def printlnX() = {
      println("abcd.." + name)
    }
  }

  def main(args: Array[String]): Unit = {

    val p = new Person()
    p.printName()
    Person.printlnX()

    println("*" * 40)

    val p1 = new Person1()
    p1.printSex(p1)

    Person1.printlnName()
    Person1.printlnX()

  }

}
