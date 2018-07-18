package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-18 16:56
  */
object Custom_Object_8_Package_Runner {

  class Person {
    private[scala] val name = 1
  }

  def main(args: Array[String]): Unit = {

    val p = new Person
    print(p.name)
    printlnLine()

    val innerObject = InnerObject()
    innerObject.printX()
    innerObject.printHashCode
  }

}
