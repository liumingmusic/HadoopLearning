package com.c503.scala

import scala.beans.BeanProperty

/**
  * 描述 简单描述方法的作用
  * 使用@BeanProperty标注字段
  *
  * @author liumm
  * @since 2018-07-13 14:37
  */
object Custom_Class_3_BeanProperty {

  def main(args: Array[String]): Unit = {

    val myBean = new Bean()

    myBean.setName("刘大明")
    println(myBean.getName)
    myBean.name = "xx"
    println(myBean.getName)
    myBean.name_=("222")
    println(myBean.getName)


  }

}


class Bean {

  @BeanProperty var name = "Liumm"

}