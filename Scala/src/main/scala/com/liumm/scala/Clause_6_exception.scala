package com.liumm.scala

import java.io.{FileNotFoundException, FileReader, IOException}


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
