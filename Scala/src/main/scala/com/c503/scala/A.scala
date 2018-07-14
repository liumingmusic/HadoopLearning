/**
  * 描述 包作用域规则
  * 源文件的目录和包之间没有实质的关联关系
  * 包语句可以访问上层作用域的名称而不用引入
  * 包语句可以包含一个"串"或路径区域，这样的包限定了可见性成员
  * 可以在文件顶部使用package，而不用带括号
  *
  * @author liumm
  * @since 2018-07-13 23:16
  */
package com {

  class A {
    val name = "com.A"

    def p = {
      val a = new A()
      println("self:" + name)
      println("A:" + a.name)
    }
  }

  package liumm {

    class B {
      val name = "com.liumm.B"

      def p = {
        val a = new A()
        val b = new B()
        println("self:" + name)
        println("A:" + a.name)
        println("B:" + b.name)
      }
    }

    package scala {

      class C {
        val name = "com.liumm.scala.C"

        def p = {
          val a = new A()
          val b = new B()
          val c = new C()
          println("self:" + name)
          println("A:" + a.name)
          println("B:" + b.name)
          println("C:" + c.name)
        }
      }

    }

  }

}