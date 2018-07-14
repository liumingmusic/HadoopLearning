/**
  * 包含语句可以包含一个串行或路径区域，这样包就可以限定了成员的可见性
  *
  * @author liumm
  * @since 2018-07-13 23:24
  */
package com.liumm {

  package scala {

    object SerPackage {

      def main(args: Array[String]): Unit = {

        val b = new B()
        val c = new C()

        println(b.p)
        println(c.p)

      }

    }

  }

}
