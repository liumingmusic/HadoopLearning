package com.c503.scala

/**
  * 特征字段
  *
  * @author liumm
  * @since 2018-07-13 16:49
  */
object Custom_Trait_2_Field {

  trait Account {
    var loginName = "liumm"
    var loginTimes: Int

    def printX() = {
      doPrint("loginName=" + loginName + ",loginTimes=" + loginTimes)
    }

    def doPrint(msg: String): Unit
  }

  class AdminAccount extends Account {

    override var loginTimes: Int = 18

    override def doPrint(msg: String): Unit = {

      println("loginName=" + loginName + ",loginTimes=" + loginTimes)

    }

  }

  object AdminAccount extends AdminAccount

  def main(args: Array[String]): Unit = {

    val aa_1 = new AdminAccount()
    val aa_2 = AdminAccount
    aa_1.printX()
    aa_2.printX()
    AdminAccount.printX()

  }

}
