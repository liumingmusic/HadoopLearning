package com.c503.scala

/**
  * 描述 样离类
  * 当一个类被定义为case类之后，scala就会自动帮你创建一个伴生对象并帮你实例化好一些列方法
  * 实现了apply方法，不用new关键字创建
  * 实现了unapply方法，可以通过模式匹配来获取类属性
  * 实现了类构造的getter方法
  * 实现了toString.equal,copy和hashCode方法
  *
  * @author liumm
  * @since 2018-07-13 15:41
  */
object Custom_Class_7_CaseClass {

  def main(args: Array[String]): Unit = {

    val caseClass = C503_Custom_Class_7_CaseClass("LIUMM_", 34, 1)

    println(caseClass)
    println(caseClass.name + "-" + caseClass.age + "-" + caseClass.sex)
    caseClass.sex = 0
    println(caseClass.sex)
    caseClass.desc = "just Test case class"
    println(caseClass.desc)

    println("*" * 40)

    caseClass match {
      case C503_Custom_Class_7_CaseClass("LIUMM_", _, _, desc) => {
        println("name is c503 and desc is " + desc)
      }
      case C503_Custom_Class_7_CaseClass(n, a, s, d) => {
        println(n + "," + a + "," + s + "," + d)
      }
      case _ => {
        println("Not Match")
      }
    }

    println("*" * 40)

    val C503_Custom_Class_7_CaseClass(nm, _, _, _) = caseClass
    println(nm)
    val C503_Custom_Class_7_CaseClass(n1, a, s, d) = caseClass
    println(n1)
  }

}


case class C503_Custom_Class_7_CaseClass(name: String, age: Int, var sex: Int, var desc: String = "")