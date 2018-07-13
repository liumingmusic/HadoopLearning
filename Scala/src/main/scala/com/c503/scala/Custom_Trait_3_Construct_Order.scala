package com.c503.scala

/**
  * 描述 特征类的构造顺序
  *
  * 首先调用超类构造器，之后类构造器执行
  * 特征类是左到右执行
  * 每个特征类中，父特征类先被构造，且只有一次
  * 第一个特征前用extends，后面的用with链接
  *
  * @author liumm
  * @since 2018-07-13 16:57
  */
object Custom_Trait_3_Construct_Order {

  trait LogsFather {
    println("LogsFather...")
  }

  class AccountFather {
    println("AccountFather...")
  }

  class Account extends AccountFather {
    var name = "ddtinone"
    var age = 18
    println("Account...")

    def this(name: String) {
      this()
      this.name = name
    }

    def this(name: String, age: Int) {
      this(name)
      this.age = age
    }
  }

  trait Logger extends LogsFather {
    def log(msg: String): Unit = {
      println("Logger.log...")
    }

    println("Logger...")
  }

  trait FileLogger extends Logger {
    println("FileLogger...")

    override def log(msg: String): Unit = {
      println("FileLogger.log...")
    }
  }

  trait ShortLogger extends Logger {
    println("ShortLogger...")

    override def log(msg: String): Unit = {
      println("ShortLogger.log...")
    }
  }

  //Account with FileLogger with ShortLogger是一个整体，然后再由类扩展
  class SavingsAccount extends Account with FileLogger with ShortLogger {
    println("SavingsAccount...")
  }

  def main(args: Array[String]) {
    val sa = new SavingsAccount()
    sa.log("")
  }


}
