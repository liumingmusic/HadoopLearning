/**
  * 描述 可以在文件顶部使用package，不带花括号
  *
  * @author liumm
  * @since 2018-07-13 23:28
  */

//文件顶部标记法，可以在文件顶部使用package
package com.liumm
package scala

object TopPackage {

  def main(args: Array[String]): Unit = {

    val b = new B()
    val c = new C()

  }

}
