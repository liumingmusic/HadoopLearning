package com.c503.scala

/**
  * 扩展类或特质的对象
  * object可以扩展一个类
  * object可以扩展一个或者多个特质
  *
  * @author liumm
  * @since 2018-07-14 12:15
  */
object Custom_Object_3_Extend_form_ObjectOrTrait {

  abstract class Animal {
    println("Animal")
    val name = "liumm"

    def voice(): Unit

    def haveLife() = {
      println("haveLife")
    }

  }

  trait ABD {
    print("ABD")
    val aaa = 1

    def fff

    def adasa
  }

  trait TOP {
    print("Top")
  }

  trait Motion extends TOP {
    println("Motion")

    def ddd

    def motion() = {
      println("motion")
    }
  }

  trait RUN extends TOP {
    val rr = 330
    println("RUN")

    def run() = {
      println("run")
    }
  }

  object Dog extends Animal with Motion with RUN {

    println("dog")

    override def voice(): Unit = {
      println("voice")
    }

    override def ddd: Unit = {

    }
  }

  def main(args: Array[String]): Unit = {

    Dog.voice()
    Dog.motion()
    Dog.run()
    Dog.haveLife()
    println(Dog.name)

  }

}
