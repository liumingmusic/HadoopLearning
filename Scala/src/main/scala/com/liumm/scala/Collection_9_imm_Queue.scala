package com.liumm.scala

import scala.collection.immutable.Queue

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-07 19:40
  */
object Collection_9_imm_Queue {

  def main(args: Array[String]): Unit = {

    val queue1 = Queue[Int](1, 2)
    val queue2 = queue1.enqueue(3, 4)
    println(queue1)
    println(queue2)
    println(queue2.dequeue)
    println(queue1)

    println("*" * 40)

    val queueA = Queue("A", "B", "C", "D")
    val queueB = Queue(1, 2, 3, 4)
    println(queueA.union(queueB))
    println(queueA.++(queueB))

    println("*" * 40)

    println(queueA.map(x => x + "-ok"))
    println(queueB.reduce(_ + _))

  }

}
