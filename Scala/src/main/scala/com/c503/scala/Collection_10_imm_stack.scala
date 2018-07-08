package com.c503.scala

import scala.collection.immutable.Stack

/**
  * 描述 栈
  *
  * @author liumm
  * @since 2018-07-08 21:04
  */
object Collection_10_imm_stack {

  def main(args: Array[String]): Unit = {

    val stack = Stack(1, 2)
    println(stack)

    val stack1 = stack.push(3)
    println(stack)
    println(stack1)
    println(stack == stack1)

    println("*" * 40)

    println(stack.pop)
    println(stack)
    println(stack1.pop)
    println(stack1)

    println("*" * 40)

    val stack2 = collection.mutable.Stack(1, 2)
    println(stack2)

    val stack3 = stack2.push(3)
    println(stack2)
    println(stack3)
    println(stack == stack1)
    println(stack2.pop)
    println(stack2)
    println(stack3.pop)
    println(stack3)

    println("*" * 40)

    val stackA = Stack("A", "B", "C", "D")
    val stackB = Stack(1, 2, 3, 4)
    println(stackA.union(stackB))
    println(stackA.++(stackB))

    println("*" * 40)

    println(stackA.map(_ + "-OK"))
    println(stackB.reduce(_ + _))

    println("*" * 40)

    println(stackA.zip(stackB))
    println(stackA.zipWithIndex)

    println("*" * 40)

    println(stackA.count(_.equals("A")))
    println(stackA.filter(_.equals("A")))

  }

}
