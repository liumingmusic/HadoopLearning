package com.c503.scala

/**
  * 变量绑定
  * 除了独立的变量模式之外，你还可以吧任何其他模式绑定到变量
  * 只需要简单写上变量名、一个@符号，以及这个模式
  *
  * @author liumm
  * @since 2018-07-18 17:22
  */
object PatternMatching_2_baseOn_Binding_Variable {

  def main(args: Array[String]): Unit = {

    println(matchBigData("Hbase"))

  }

  def matchBigData[T](bigData: T): String = {
    bigData match {
      case x@"Hadoop" => "Hadoop=" + x
      case x@"Hbase" => "Hbase=" + x
      case x@"Hive" => "Hive=" + x
      case x@"Scala" => "Scala=" + x
      case x@_ => "UNKNOW BIG DATA" + x
    }
  }

}
