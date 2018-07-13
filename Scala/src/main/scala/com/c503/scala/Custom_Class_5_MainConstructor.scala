package com.c503.scala

/**
  * 描述 主构造器
  * 主构造器的参数知己放置在类的后面
  * 主构造器的参数编译成字段并生成getter和setter
  * 主构造器会执行类定义中所有的语句，如果类名后无参数，则为一个无参主构造器
  *
  * @author liumm
  * @since 2018-07-13 15:09
  */
object Custom_Class_5_MainConstructor {

  def main(args: Array[String]): Unit = {

    val mainConstructor = new MainConstructor("A", "B", 0)
    println("--------------")
    println(mainConstructor.name)
    println(mainConstructor.fa)
    mainConstructor.printX()

  }

}

class MainConstructor(var name: String, val fa: String, age: Int = 0) {

  println(name + "-start-" + age)

  var temp = 0

  def printX() = {
    println(name)
    println(age)
  }

  def this(temp: Int) {
    this("1", "2", 3)
    this.temp = temp
    printX()
  }

  println(name + "-end-" + age)

}

class SubClass(age: Int, fa: String, name: String) extends MainConstructor(name, fa, age) {

}

