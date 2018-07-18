package com.c503.scala

/**
  * unapply匹配
  *
  * @author liumm
  * @since 2018-07-18 23:06
  */
object PatternMatching_9_baseOn_unapply {

  def main(args: Array[String]): Unit = {

    val list = (1 to 9).toList
    println(list)
    list match {
      case Append(x, 9) => println(x)
    }
    list match {
      case Append(Append(Append(x, 7), 8), 9) => println(x)
    }
    list match {
      case Append(Append(_, 8), 9) => println("OK")
    }
    val strSplits = "a,b,c,d"
    strSplits match {
      case Splits(a, b, c, d) => println(s"a:$a,b=$b,c=$c,d=$d")
    }
  }

  object Append {
    def unapply[A](list: List[A]): Option[(List[A], A)] = {
      Some((list.init, list.last))
    }
  }

  object Splits {
    def unapplySeq(str: String): Option[Seq[String]] = {
      Some(str.split(","))
    }
  }

}
