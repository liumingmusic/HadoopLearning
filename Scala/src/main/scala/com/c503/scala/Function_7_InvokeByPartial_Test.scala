package com.c503.scala

import java.util.Date

/**
  * 描述 部分应用函数
  *
  * @author liumm
  * @since 2018-07-07 13:26
  */
object Function_7_InvokeByPartial_Test {

  def main(args: Array[String]): Unit = {

    val date = new Date()
    val logWithDataBound = log(date, _: String)

    logWithDataBound("message1")
    logWithDataBound("message2")
    logWithDataBound("message3")

    log(new Date(), "M-1")
    log(new Date(), "M-2")
    log(new Date(), "M-3")
  }

  def log(date: Date, message: String): Unit = {
    println(date + "---" + message)
  }

}
