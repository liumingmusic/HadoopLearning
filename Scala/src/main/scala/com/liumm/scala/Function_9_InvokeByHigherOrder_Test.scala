package com.liumm.scala

/**
  * 描述 高阶函数
  *
  * @author liumm
  * @since 2018-07-07 13:35
  */
object Function_9_InvokeByHigherOrder_Test {

  def main(args: Array[String]): Unit = {

    invokeHigherOrder
    println("*" * 20)
    println(hof[Int](layout, 120))
    println(higherOrder_2(_ * 2.0))
    println(higherOrder_2(ceil))
    println(higherOrder_1(ceil, 127.0))
    println(higherOrder_3(ceilExt, 100.0, 200.0))

  }

  def invokeHigherOrder(): Unit = {
    lazy val temp = (1 to 9).map("*" * _)

    temp.foreach(println)

    (1 to 9).filter(_ % 2 == 0).foreach(println)

    println((3 to 5).reduce(_ * _))

    "lmm xxx where".split(" ").sortWith(_.length < _.length).foreach(println)
  }

  def hof[T](f: T => String, v: T) = f(v)

  def higherOrder_1(f: (Double) => Double, x: Double) = f(x)

  def higherOrder_2(f: (Double) => Double) = f(126.0)

  def higherOrder_3(f: (Double, Double) => Double, x: Double, y: Double) = f(x, y)

  val ceil = (x: Double) => x * 100

  val ceilExt = (x: Double, y: Double) => x * y * 100

  def layout[T](x: T) = "[" + x.toString + "]"


}
