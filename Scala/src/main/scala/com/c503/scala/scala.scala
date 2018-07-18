package com.c503

/**
  * scala包对象
  * 可以有代码块、属性、方法，也可以在包对象内声明某个类型的别名
  * 定义包对象跟定义一个普通的伴生对象在写法上的区别就是object前面增加一个package
  * 实际上包对象的最重要的作用是兼容旧的类库，或者是某些类型的增强版本
  * 把工具函数或者常量添加到包而不是某个Utils对象的做法更加合理
  * 每个包都可以有一个包对象，需要在父包中定义且名称与子包一样
  *
  * @author liumm
  * @since 2018-07-18 16:46
  */
package object scala {

  val codeBlock = {
    val a = "scala"
    val b = 2
  }

  val constant = "这是一个常量"

  var variable = "设置一个变量"

  def printlnLine() = {
    print(codeBlock)
    print(constant)
    print(variable)
  }

  trait InnerTrait {
    def printHashCode = {
      print("hashcode=" + this.hashCode())
    }
  }

  class InnerObject extends InnerTrait {
    private val inVal = 100

    def printX() = {
      print("inVal=" + inVal)
    }
  }

  object InnerObject {
    def apply() = {
      new InnerObject()
    }
  }

}
