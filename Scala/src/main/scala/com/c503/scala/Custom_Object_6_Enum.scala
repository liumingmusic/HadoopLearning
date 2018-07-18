package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-18 16:38
  */
object Custom_Object_6_Enum {

  object MyEnum extends Enumeration {
    type MyEnum = Value
    val hadoop, scala, spark = Value
    val kylin = Value
    val hive = Value(4, "MyEnum")
  }

  def p(enum: MyEnum.Value) = {
    print(enum)
  }

  def p1(enum: MyEnum.MyEnum) = {
    print(enum)
  }

  def main(args: Array[String]): Unit = {

    print(MyEnum.hadoop.id)
    print(MyEnum.hadoop)
    print(MyEnum.kylin.id)
    print(MyEnum.kylin)
    print(MyEnum.hive.id)
    print(MyEnum.hive)

  }

}
