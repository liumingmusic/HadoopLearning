package com.c503.scala

import java.io.{BufferedReader, File, FileReader}

import scala.collection.mutable.ListBuffer

/**
  * 隐式类
  * 在scala中提供了隐式类，可以使用implicit声明类，但是需要注意
  * 1.其所在的构造参数有且只能有一个
  * 2.隐式类必须被定义在类，伴生对象和包对象中
  * 3.隐式类不能是case class
  * 4.作用域内不能有与之相同的标识符
  *
  * @author liumm
  * @since 2018-07-19 22:08
  */
object Implicit_1_Class {

  implicit class CustomTraversable(once: Traversable[Any]) {
    //扩展，成员变量
    var customMember = once

    //扩展，成员方法
    def showString: String = {
      once.mkString(", ")
    }
  }

  /**
    * 扩展ava的file类方法
    *
    * @param file
    */
  implicit class Files(file: File) {
    def lines: List[String] = {
      val fileReader = new FileReader(file)
      val reader = new BufferedReader(fileReader)
      try {
        var lines = ListBuffer[String]()
        var line = reader.readLine()
        while (line != null) {
          lines = lines :+ line
          line = reader.readLine()
        }
        lines.toList
      } finally {
        if (fileReader != null) {
          fileReader.close()
        }
        if (reader != null) {
          reader.close()
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {

    val file = new File("")
    file.lines.foreach(e => {
      println(e)
    })

  }

}
