package com.c503.scala

/**
  * 描述 自身类型
  * 他确保只能被混入指定类型的子类
  * 相当于对继承的子类做限制
  *
  * @author liumm
  * @since 2018-07-13 17:02
  */
object Custom_Trait_4_SelfType {

  /** *******************************************/

  trait LogsFather {

    this: AccountFather =>

    def log

  }

  class AccountFather {
    println("AccountFather...")
  }

  class Account extends AccountFather with LogsFather {
    override def log: Unit = {
      println("Account log...")
    }
  }

  /** *******************************************/

  trait LogsFather1 {

    //继承的类必须要有一个p方法
    this: {def p(msg: String): Unit} =>

    def log
  }

  class AccountFather1 {

    def p(msg: String) = {
      println(msg)
    }

    def p1(msg: String) = {
      println(msg)
    }

  }

  class Account1 extends AccountFather1 with LogsFather1 {
    override def log: Unit = {
      println("Account1 log...")
    }
  }

  def main(args: Array[String]): Unit = {

    val account = new Account
    account.log

    val account1 = new Account1
    account.log

  }


}
