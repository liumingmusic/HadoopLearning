package com.c503.scala

/**
  * scala特征的方法
  * scala可以有抽象方法和具体的实现
  *
  * @author liumm
  * @since 2018-07-13 16:11
  */
object Custom_Trait_1_Method {

  trait Logger {

    def printLog(msg: String)

    def log(msg: String) = {
      printLog("Logger.log")
    }

  }

  class FileLogger extends Logger {

    override def printLog(msg: String): Unit = {

      println(this.getClass.getSimpleName + "=" + msg)

    }

  }

  object FileLogger extends FileLogger

  def main(args: Array[String]): Unit = {

    val fl_1 = new FileLogger()
    fl_1.printLog("show log")
    println(fl_1.hashCode())
    val fl_2 = FileLogger
    fl_2.printLog("show msg")
    FileLogger.printLog("ds")
    println(FileLogger.hashCode())


  }

}
