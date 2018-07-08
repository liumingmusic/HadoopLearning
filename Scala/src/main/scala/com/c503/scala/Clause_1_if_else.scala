package com.c503.scala
/**
  * 描述 if else 语法
  *
  * @author liumm
  * @since 2018-07-07 15:22
  */
object Clause_1_if_else {

  def main(args: Array[String]): Unit = {

    val x = 30
    if (x == 10) {
      printf("value of X is 10")
    } else if (x == 20) {
      printf("value of X is 20")
    } else if (x == 30) {
      printf("value of X is 30")
    } else {
      printf("This is else statement")
    }

  }

}
