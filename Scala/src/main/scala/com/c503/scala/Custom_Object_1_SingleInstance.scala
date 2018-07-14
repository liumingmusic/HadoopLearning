package com.c503.scala

/**
  * 描述 单例对象
  * scala没有静态方法
  * scala没有静态字段
  * 可以使用object来实现
  * object定义了某个类的单例对象
  * object的构造器在该对象第一次被使用时调用
  * object拥有除了不能提供的构造器参数以外的所有特性
  * 存放工具函数、常量的地方
  * 高效的共享单例不能变实例
  * 需要使用单个实例来协助调用某个服务
  *
  * @author liumm
  * @since 2018-07-13 23:31
  */
object Custom_Object_1_SingleInstance {

  var name = "liumm"

  def newName(name: String) = {
    this.name = name
  }

  object MyObj {
    val abc = 100
    println("init")

    def p() = {
      println("abcd")
    }

    val temp = "1000"

    println("ddd")

  }

  def main(args: Array[String]): Unit = {

    println(MyObj.hashCode())
    println(name)

    println("*" * 40)

    newName("new-liumm")
    println(name)

    println("*" * 40)

    Custom_Object_1_SingleInstance.newName("new-liumm1")
    println(Custom_Object_1_SingleInstance.name)
    println(MyObj.temp)
    println("hashCode=" + MyObj.hashCode())
    MyObj.p()
    println("hashCode=" + MyObj.hashCode())


  }

}
