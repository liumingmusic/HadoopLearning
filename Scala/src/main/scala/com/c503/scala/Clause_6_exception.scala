package com.c503.scala

import java.io.{FileNotFoundException, FileReader, IOException}

/**
  * 描述 异常捕获
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Clause_6_exception {

  def main(args: Array[String]): Unit = {

    try {
      val f = new FileReader("input.txt")
      println(f.getClass.getName)
    } catch {
      case ex: FileNotFoundException => {
        println("Missing file exception")
      }
      case bx: IOException => {
        println("IO Exception")
      }
    } finally {
      println("Exiting finally...")
    }

  }

}
