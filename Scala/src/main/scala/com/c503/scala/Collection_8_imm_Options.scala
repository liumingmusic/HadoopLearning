package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-07 19:21
  */
object Collection_8_imm_Options {

  def main(args: Array[String]): Unit = {

    var username_1: Option[String] = None
    var username_2 = Some("www")
    println(username_1.getOrElse("xx"))
    println(username_2.getOrElse("xx"))

    var username_3: String = null
    var username_4 = "xxx"

    println(username_3)

    println("*" * 40)

    var optOk: Option[String] = None
    if (optOk.isDefined) {
      println(optOk.get)
    } else {
      println("xxxxxxxx")
    }

    optOk = Some("xx")
    println(optOk.get)

    println("*" * 40)

    var optOk1: Option[String] = Some("abcd")
    println("orElse=" + optOk1.orElse(Some("newValue")))
    optOk1 = None
    println("orElse=" + optOk1.orElse(Some("newValue")))
    println("orElse=" + optOk1.getOrElse("xxxxx"))

    println("*" * 40)

    var optOk2: Option[String] = None
    println("orNull = " + optOk2.orNull)
    optOk2 = Some("case")
    println("orNull" + optOk2.orNull)

    println("*" * 40)

    var opt: Option[String] = None
    println("defaultValue=" + opt.getOrElse("defaulyValue"))
    opt = Some("xxx")
    println("defaultValue=" + opt.getOrElse("defaulyValue"))

  }

}
