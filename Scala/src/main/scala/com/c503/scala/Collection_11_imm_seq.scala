package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-08 21:04
  */
object Collection_11_imm_seq {

  def main(args: Array[String]): Unit = {

    val seqA = Seq("a", "b", "c", "d")
    val seqB = Seq("1", "2", "3", "4")

    println("*" * 20)

    println(seqA.union(seqB))
    println(seqA.++(seqB))

    println("*" * 20)

    println(seqA.map(_ + "-ok"))
    println(seqA.reduce(_ + _))
    println(seqA.mkString("-"))

    println("*" * 20)

    println(seqA.zip(seqB))
    println(seqA.zipWithIndex)

    println("*" * 20)

    println(seqA.count(_.equals("A")))
    println(seqA.filter(_.equals("A")))


  }

}
