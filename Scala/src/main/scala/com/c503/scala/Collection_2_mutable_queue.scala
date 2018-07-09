package com.c503.scala

import scala.collection.mutable

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-09 23:22
  */
object Collection_2_mutable_queue {

  def main(args: Array[String]): Unit = {

    val queue = new mutable.Queue[String]()

    queue += ("1a")
    queue += ("2B")
    queue += ("3C")
    queue += ("4D")

    println(queue)

    println("*" * 40)

    queue ++= (mutable.Queue("2e", "3r", "4t"))

    println(queue)

    println("*" * 40)

    queue.enqueue("3H")

    println(queue)

    println("*" * 40)

    queue.dequeue()

    println(queue)

    println("*" * 40)

    println(queue.dequeueFirst(_.startsWith("2")))
    println(queue.dequeueAll(_.startsWith("2")))

    println(queue)

    println("*" * 40)

    println(queue.get(0))
    println(queue.get(0).getOrElse("default"))

    println("*" * 40)

    queue.clear()

    println(queue)

  }

}
