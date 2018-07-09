package com.c503.scala

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-09 19:26
  */
object Collection_2_imm_List_exts {

  def main(args: Array[String]): Unit = {

    val left: List[Int] = List(1, 2, 3)
    val right: List[Int] = List(4, 5, 6)

    print("++=")
    println(left ++ right)
    print(".++=")
    println(left.++(right))

    println("*" * 40)

    val temp = left :: right
    print("::=")
    println(temp)
    print("::=")
    println(temp.getClass.getSimpleName)
    temp.foreach(println)

    println("*" * 40)

    print(".::  ")
    println(left.::(right))
    print(":+  ")
    println(left :+ (right))
    print(".+:  ")
    println(left.+:(right))
    print("++:  ")
    println(left ++: (right))
    print(".++:  ")
    println(left.++(right))
    print(":::  ")
    println(left ::: (right))
    print(".:::  ")
    println(left.:::(right))

    println("*" * 40)

    val nums = List(1, 2, 3)
    val square = (x: Int) => x * x
    val squareNmu1 = nums.map(nums => nums * nums)
    val squareNmu2 = nums.map(math.pow(_, 2))
    val squareNmu3 = nums.map(square)
    println(nums)
    println(squareNmu1)
    println(squareNmu2)
    println(squareNmu3)
    nums.map(_ * 100).foreach(println)

    println("*" * 40)

    val text = List("HomeWay,25,Male", "XsDYM,23,Female")
    val userList = text.map(_.split(",")(0))
    val userWithAgeList = text.map(line => {
      val fieds = line.split(",")
      val user = fieds(0)
      val age = fieds(1).toInt
      (user, age)
    })
    println(userList)
    println(userWithAgeList)
    userWithAgeList.foreach(x => {
      println(x._1 + "=" + x._2)
    })

    println("*" * 40)

    val text_1 = List("A,B,C", "D,E,F")
    val textMapped = text_1.map(_.split(",").toList)
    val textFlatted = textMapped.flatten
    val textFlatMapped = text_1.flatMap(_.split(",").toList)
    println(textMapped)
    println(textFlatted)
    println(textFlatMapped)

    println("*" * 40)

    val num1 = List(100, 10, 80, 5)
    val sum1 = num1.reduce(_ + _)
    val sum2 = num1.sum
    println(sum1)
    println(sum2)

    println("*" * 40)

    val num2 = List(2.0, 2.0, 3.0)
    val result = num2.reduce(Math.pow)
    val resultLeftReduce = num2.reduceLeft(math.pow)
    val resultRightReduce = num2.reduceRight(math.pow)

    println(result)
    println(resultLeftReduce)
    println(resultRightReduce)

    val numRight = List(1, 2, 3, 4, 5, 6)
    val okRight = numRight.reduceRight((a, b) => {
      val x = a - b
      println("a=" + a + ",  b=" + b)
      x
    })
    println(okRight)

    println("*" * 40)

    val numbers = List(5, 4, 8, 6, 2)
    val tn1 = numbers.fold(100)((a, b) => {
      val x = a - b
      println("a=" + a + ",  b=" + b)
      x
    })
    println(tn1)

    val tn2 = numbers.foldLeft(100)((a, b) => {
      val x = a - b
      println("a=" + a + ",  b=" + b)
      x
    })
    println(tn2)

    println("*" * 40)

    val nums4 = List(1, 2, 3, 4, 1)
    val sort4 = nums4.sorted
    val users = List(("hello", 25), ("world", 33))
    val usersAge = users.sortBy(e => e._2)
    val userAge1 = users.sortBy({
      case (user, age) => age
    })
    val sortWith = users.sortWith(_._1 > _._1)

    println(sort4)
    println(usersAge)
    println(userAge1)
    println(sortWith)

    println("*" * 40)

    val num5 = List(1, 2, 3, 4, 5, 6, 7)
    val ood = num5.filter(e => e % 2 == 0)
    val even = num5.filterNot(_ % 2 == 0)
    println(ood)
    println(even)

    println("*" * 40)

    val num6 = List(-1, -2, 0, 1, 2)

    val plusCnt1 = num6.count(_ > 0)
    val plusCnt2 = num6.filter(_ > 0).length
    println(plusCnt1)
    println(plusCnt2)

    println("*" * 40)

    val num7 = List(1, 2, 3)
    val num8 = List(2, 3, 4)
    val diff1 = num7 diff (num8)
    val union1 = num7 union (num8)
    val intersect1 = num7 intersect (num8)

    println(diff1)
    println(union1)
    println(intersect1)

    println("*" * 40)

    val list = List("A", "B", "A", "D", "B")
    val list1 = List(List("A"), List("B"), List("A"), List("B"), List("E"))
    val distincted1 = list1.distinct
    val distincted2 = list1.distinct

    println(distincted1)
    println(distincted2)

    println("*" * 40)

    val data = List(("Hello", "Famale"), ("liumm", "Male"), ("xux", "Male"))
    val group1 = data.groupBy(_._2)
    val group2 = data.groupBy({ case (name, sex) => sex })
    val fixGroup = data.grouped(2).toList

    println(group1)
    println(group2)
    println(fixGroup)

    println("*" * 40)

    val num9 = List(1, 1, 1, 1, 1, 4, 5, 5, 6, 1)
    val left_1 = num9.take(3)
    val left_2 = num9.takeRight(5)
    val left_3 = num9.takeWhile(_ == num9.head)

    println(left_1)
    println(left_2)
    println(left_3)

    println("*" * 40)

    val num10 = List(1, 1, 1, 1, 4, 5, 1, 1)
    val left2 = num10.drop(4)
    val right2 = num10.dropRight(2)
    val tailNum = num10.dropWhile(_ == num10.head)

    println(left2)
    println(right2)
    println(tailNum)

    println("*" * 40)

    val num11 = List(1, 1, 1, 2, 1, 1, 1, 4, 6, 78, 1)
    val r_1 = num11.span(_ == 1)
    val r_2 = num11.splitAt(4)
    val r_3 = num11.partition(_ == 1)

    println(r_1)
    println(r_1._1)
    println(r_2)
    println(r_3)

    println("*" * 40)

    //扩充长度
    val num12 = List(1, 2, 3)
    val padded = num12.padTo(8, 2)
    println(padded)

    println("*" * 40)

    val num13 = List(1, 1, 2, 4)
    val comb1 = num13.combinations(3).toList
    val per = num13.permutations.toList

    println(comb1)
    println(per)

    println("*" * 40)

    val alphabet = List("A", "B", "C")
    val num14 = List(1, 2, 3)
    val zipped = alphabet.zip(num14)
    val zipedAll = alphabet.zipAll(num14, 1, 2)
    val zippeIndex = alphabet.zipWithIndex
    val (list1ok, list2ok) = zipped.unzip

    println(zipped)
    println(zipedAll)
    println(zippeIndex)
    println(list1ok)
    println(list2ok)

    println("*" * 40)

    val nums15 = List(1, 2, 3, 4, 5)
    val sliced = nums15.slice(2, 4)

    println(sliced)

    println("*" * 40)

    val num16 = List(1, 2, 3, 4, 5, 6, 7, 8)
    val groupStep1 = num16.sliding(2, 3).toList
    val groupStep2 = num16.sliding(2).toList

    println(groupStep1)
    println(groupStep2)

    println("*" * 40)

    val num17 = List(1, 2, 3, 0, 5)
    val fixd = num17.updated(3, 4)
    println(fixd)

    println("*" * 40)


  }

}
