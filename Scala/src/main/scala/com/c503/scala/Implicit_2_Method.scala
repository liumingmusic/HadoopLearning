package com.c503.scala

import java.io.File

import com.c503.scala.Implicit_1_Class.Files

/**
  * 瘾式方法
  *
  * @author liumm
  * @since 2018-07-19 22:35
  */
object Implicit_2_Method {

  implicit def file2List(file: File): List[String] = file.lines

  def foreachFile[T](source: List[T])(fn: T => Unit) = {
    source.foreach(fn)
  }

  implicit def intToString(x: Int) = x.toString

  def printMsg(msg: String) = {
    println(msg)
  }

  def main(args: Array[String]): Unit = {

    val fileA = new File("")

    fileA.lines.foreach(e => {
      println(e)
    })

    foreachFile(fileA)(println(_))

    //当方法中的参数与目标类型不一致时转换
    printMsg(1000)

  }

}
