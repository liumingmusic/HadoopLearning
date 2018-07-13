package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-13 15:23
  */
object Custom_Class_6_Nested {

  def main(args: Array[String]): Unit = {
    println("--------------第一次实例化---------------")
    val myNested = new Outer("liumm", 12)
    println("--------------第二次实例化---------------")
    val mySubNested1 = new myNested.Inner(34)
    println("--------------第三次实例化---------------")
    val mySubNested2 = new myNested.Inner(35)
    println("--------------第四次实例化---------------")
    val mySubNested3 = new myNested.Inner(36)
    println("--------------第五次实例化---------------")

    println("----------------")
    myNested.px()
    println("----------------")
    mySubNested1.px()
    println("----------------")
    mySubNested2.px()
    println("----------------")
    mySubNested3.px()
    println("----------------")

    val myNested1 = new Outer("liuxm", 21)
    val myNested2 = new Outer("liudm", 22)

    val list = List(new myNested1.Inner(100), new myNested2.Inner(200))
    list.foreach(e => {
      println(e.getClass.getName)
    })

    println("*" * 40)

    val list1 = List[Outer#Inner](new myNested1.Inner(100), new myNested2.Inner(200))
    list1.foreach(e => {
      println(e.getClass.getName)
      e.px()
    })

  }

}

class Outer(name: String, age: Int) {
  outer =>
  println("MyNested_age" + age)

  class Inner(age: Int) {

    outer.px()
    Outer.this.px()
    println("MySubNested_age=" + age)

    def px() = {
      println(name)
    }

  }

  def px(): Unit = {
    println("name=" + name + ",age=" + age)
  }

}
