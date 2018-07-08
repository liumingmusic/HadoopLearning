package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-07 17:32
  */
object Collection_4_imm_Maps {

  def main(args: Array[String]): Unit = {

    var a: Map[Char, Int] = Map()
    val b: Map[Any, Any] = Map()
    val c = scala.collection.mutable.Map[String, Int]()
    val color = Map("red" -> "#ff0000", "black" -> "000000")

    println(a.getClass.getName)
    println(c.getClass.getName)
    println("a:Map=" + a)
    println("b:Map=" + b)
    a += ('I' -> 1)
    a += ('J' -> 5)
    a += ('K' -> 10, 'l' -> 15)
    println("a:Map=" + a)

    c += ("case1" -> 503)
    c += ("case2" -> 503)
    c += ("case3" -> 503)
    println("c:Map=" + c)

    println("*" * 20)

    val colors_1 = Map("red" -> "#ff0000", "yellow" -> "#0000ff", "blue" -> "#00ff00")
    println(colors_1.keys)
    println(colors_1.values)
    println(colors_1.isEmpty)

    println("*" * 20)

    val colors1 = Map("red" -> "#ff0000", "yellow" -> "#0000ff", "blue" -> "#00ff00")
    val colors2 = Map("blue" -> "#00ff00")

    /*var colors3 = colors1.++ colors2*/
    var colors3 = colors1 ++ colors2

    println(colors3)

    println("*" * 20)

    val colors4 = Map("red" -> "#ff0000", "yellow" -> "#0000ff", "blue" -> "#00ff00")
    colors4.keys.foreach(x => {
      println("key = " + x + "value = " + colors4.get(x).getOrElse("xx"))
    })

    println("*" * 20)

    colors4.foreach(e => {
      println("key=" + e._1 + ", value=" + e._2)
    })

    println("*" * 20)

    colors4.map(e => {
      val new_key = "key_" + e._1
      val new_value = "key_" + e._2
      (new_key, new_value)
    }).foreach(println)

    println("*" * 20)

    val colors5 = Map("red" -> "#ff0000", "yellow" -> "#0000ff", "blue" -> "#00ff00")

    if (colors5.contains("red")) {
      println("red key exists with value : " + colors5("red"))
    } else {
      println("red key does not exist")
    }

    if (colors5.contains("xxx")) {
      println("red key exists with value : " + colors5("xxx"))
    } else {
      println("red key does not exist")
    }

    println("*" * 20)

    val colorA = Map("red" -> "#ff0000")
    val colorB = Map("yellow" -> "#0000ff")
    val colorC = Map("blue" -> "#00ff00")

    println(colorA.+("blue" -> "#sdh"))
    println(colorA)
    println(colorA.-("red"))
    println(colorA)
    println(colorA.++(colorB).++(colorC))
    println(colorA.size)
    println(colorA.filterKeys(_.equals("red")))
  }

}
