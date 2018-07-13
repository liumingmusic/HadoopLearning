package com.c503.scala

/**
  * 描述 第一个简单类使用
  * 简单类和无参构造
  * 声明为public无法编译，但是可以声明为private和protected
  * scala源文件可以包含多个类，所有的这些类都具有公共性
  * 调用无参构造，可以写 ()，也可以不写()
  * 类的字段必须初始化
  *
  * @author liumm
  * @since 2018-07-13 14:13
  */
object Custom_Class_1_Simple {

  def main(args: Array[String]): Unit = {

    val myCounter = new Counter("D")
    println(myCounter.hashCode())
    val myCounter_1 = new Counter("A")
    println(myCounter_1.hashCode())

    println(myCounter.isDef(myCounter_1))
    println(myCounter.inc())
    println(myCounter.dec)
    println(myCounter.current)
    println(myCounter.pubValue)
  }

}

class Counter {
  var pubValue = 123
  private var value = 100

  def inc(): Int = {
    value += 1
    value
  }

  def dec: Int = {
    value -= 1
    value
  }

  def current = value

  def this(name: String) {
    this()
  }

  def isDef(counter: Counter) = counter.value > this.pubValue
}


