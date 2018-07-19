package com.c503.scala

/**
  * 隐式对象
  * implicit object 指定隐式对象，通过继承一个限定的类型对象，该类型对象会带有泛型
  * 通常转换时，会根据哦按段的泛型类型来转换或指定的类型对象
  *
  * @author liumm
  * @since 2018-07-19 23:00
  */
object Implicit_5_Object {

  trait CustomTypeTrait[T] {
    def add(param: T): T
  }

  object CustomTypeTraitInt extends CustomTypeTrait[Int] {
    override def add(param: Int): Int = param + 1
  }

  implicit object CustomTypeTraitDouble extends CustomTypeTrait[Double] {
    override def add(param: Double): Double = param * 100
  }

  implicit object CustomTypeTraitString extends CustomTypeTrait[String] {
    override def add(param: String): String = param * 3
  }

  def addAny[T: CustomTypeTrait](list: List[T]) = {
    val customTypeTrait = implicitly[CustomTypeTrait[T]]
    println("1")
    list.map(customTypeTrait.add).foreach(println(_))
    println("------")
  }

  def main(args: Array[String]): Unit = {

    addAny(List(100, 100, 100))
    addAny(List(100.1, 100.1, 100.2))
    addAny(List("1", "2", "3"))

  }

}
