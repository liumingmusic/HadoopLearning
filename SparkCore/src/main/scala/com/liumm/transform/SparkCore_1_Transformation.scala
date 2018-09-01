package com.liumm.transform

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-08-18 15:58
  */
object SparkCore_1_Transformation {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("SparkCore_1_Transformation").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rddA = sc.makeRDD(List(
      ("Hadoop", "V2.X", "2.7.3", "www.apache.org"),
      ("Hbase", "V1.X", "1.1.2", "www.apache.org"),
      ("Hive", "V2.X", "1.2.1", "www.apache.org"),
      ("Scala", "V1.X", "2.11.8", "www.scala-lang.org"),
      ("Spark", "V2.X", "2.0.0", "www.redis.org")),
      3)

    val rddB = sc.parallelize(List(
      ("Elasticsearch", "www.es.org"),
      ("Kylin", "www.kylin.org"),
      ("Flume", "www.flume.org"),
      ("Hbase", "www.apache.org")))

    val rddC = sc.parallelize(List(("Elasticsearch", "V1.X"), ("Kylin", "V2.X"), ("Flume", "V3.X")))

    val rddKV = sc.makeRDD(List(
      ("apache" -> "Hadoop"),
      ("apache" -> "Hive"),
      ("apache" -> "Spark"),
      ("postgrasql" -> "PostGrasql")
    ))

    /*1、基础转换操作*/
    println("-------------------------------------1、基础转换操作-------------------------------------\n")

    //返回一个新的分布式数据集，由每个元素进过的func函数转换组成
    val rdd_map = rddA.map(x => (x._1, x._4))
    println("**************rdd_map************")
    rdd_map.foreach(x => {
      println("rdd_map" + x)
    })
    println("**************rdd_map************")
    println()

    //mapParallelize的输入函数是应用于每个分区
    val rdd_temp = sc.parallelize((1 to 10), 3)
    val mapParallelize = rdd_temp.mapPartitions(iter => {
      println("-" * 20)
      val a = ListBuffer[Int]()
      while (iter.hasNext) {
        a += iter.next() + 1
      }
      a.iterator
    })
    println("**************mapPartitions************")
    mapParallelize.foreach(println(_))
    println("**************mapPartitions************")
    println()

    //mapParallelizeWithIndex操作用法类似于mapParallelize，只是输入参数多了一个分区索引
    val rdd_temp1 = sc.parallelize((1 to 10), 3)
    val rdd_mapParallelizeWithIndex = rdd_temp1.mapPartitionsWithIndex((i, iter) => {
      println("--------------" + i + "-----------")
      val a = ListBuffer[(Int, Int)]()
      while (iter.hasNext) {
        a += ((i, iter.next() + 1))
      }
      a.iterator
    })
    println("**************mapParallelizeWithIndex************")
    rdd_mapParallelizeWithIndex.foreach(println(_))
    println("**************mapParallelizeWithIndex************")
    println()

    //笛卡尔积，在数据集T和U上面调用时，返回一个(T,U)对的数据集合，所有元素进行笛卡尔积
    val rdd_C = sc.makeRDD(List(1, 2, 3))
    val rdd_D = sc.makeRDD(List(4, 5, 6))
    val rdd_E = rdd_C.cartesian(rdd_D)
    println("**************cartesian************")
    rdd_E.foreach(println(_))
    println("**************cartesian************")
    println()

    //distinct
    val rdd_F = sc.makeRDD(List(1, 2, 3))
    val rdd_G = sc.makeRDD(List(1, 2, 3))
    val rdd_H = rdd_F ++ rdd_G
    println("**************distinct start************")
    rdd_H.foreach(println(_))
    println("**************cartesian************")
    val rdd_I = rdd_H.distinct()
    rdd_I.foreach(println(_))
    println("**************cartesian end************")
    println()

    //filter
    val rdd_filter = rddA.filter(x => x._2.equalsIgnoreCase("v1.X") && x._1.equals("Hbase"))
    println("**************filter start************")
    rdd_filter.foreach(println(_))
    println("**************filter end************")
    println()

    //filterMap
    //类似于map，但是每一个输入的元素，会被映射为0到多个元素输出
    val rdd_flatMap = rddA.flatMap(x => x.productIterator)
    println("**************filterMap start************")
    rdd_flatMap.foreach(println(_))
    println("**************filterMap end************")
    println()

    //zip操作用于将两个rdd组合成key/value形式的RDD，这里默认两个rdd的partition数目以及元素数量都相同，否者会出异常
    val rdd_1 = sc.parallelize((1 to 10), 3)
    val rdd_2 = sc.parallelize((11 to 20), 3)
    println("**************zip start************")
    rdd_1.zip(rdd_2).foreach(println(_))
    println("**************zip end************")
    println()

    //zippartition操作将多个rdd应用按照partition组合成为新的rdd，该操作需要组合成rdd需要相同的分区数，但对于分区数里面的元素没有要求
    val rdd_3 = sc.parallelize((1 to 10), 3)
    val rdd_4 = sc.parallelize((11 to 22), 3)
    println("**************zippartition start************")
    rdd_3.zipPartitions(rdd_4)((iter1, iter2) => {
      val a = ListBuffer[(Int, Int)]()
      while (iter1.hasNext && iter2.hasNext) {
        a += ((iter1.next(), iter2.next()))
      }
      a.iterator
    }).foreach(println(_))
    println("**************zippartition end************")
    println()

    //zipWithIndex操作将RDD中的元素和这个元素在RDD中的ID(索引号)组合成键值对(elem,index)
    val rdd_5 = sc.parallelize((1 to 10), 3)
    println("**************zipWithIndex start************")
    rdd_5.zipWithIndex().foreach(println(_))
    println("**************zipWithIndex end************")
    println()

    //zipWithUniqueId将操作的RDD中的元素和唯一的ID组合成键值对，唯一的生成算法如下：
    //每个分区中的第一个元素的唯一ID值为：该分区的索引号
    //每个分区中的第N个元素的唯一ID值为：前一个元素的唯一ID值+该RDD总分区数
    //总结算法，对每个分区而言，ID为从分区索引号开发，不进为分区数
    val rdd_6 = sc.parallelize((1 to 10), 3)
    println("**************zipWithUniqueId start************")
    rdd_6.zipWithUniqueId().foreach(println(_))
    println("**************zipWithUniqueId end************\n")

    //glom操作将RDD中的每一个分区所有类型为T的数据转变成类型为T的数组[Array[T]]
    println("**************glom start************")
    val rdd_7 = sc.parallelize((1 to 10), 2)
    rdd_7.glom().foreach(array => {
      println("----------")
      array.foreach(println(_))
    })
    println("**************glom end************\n")

    //union操作将两个RDD合并，返回两个RDD的并集，返回元素不去重
    val rdd_union = rdd_map.union(rddB)
    println("**************rdd_union start************")
    rdd_union.foreach(println(_))
    println("**************rdd_union end************\n")

    //intersection操作返回两个RDD交集，返回元素去重
    val rdd01 = sc.parallelize((1 to 5))
    val rdd02 = sc.parallelize((2 to 7))
    println("**************intersection start************")
    rdd01.intersection(rdd02).foreach(println(_))
    println("**************intersection end************\n")

    //subtract操作将两个RDD差集，返回元素不去重
    val rdd03 = sc.parallelize((1 to 5))
    val rdd04 = sc.parallelize((2 to 7))
    println("**************intersection start************")
    rdd03.subtract(rdd04).foreach(println(_))
    println("**************intersection end************\n")

    /*2、键值转换操作*/

    //mapValues类似于map，知识mapValues针对[KEY,VALUE]中的V进行map操作
    println("-------------------------------------2、键值转换操作-------------------------------------\n")
    val rdd05 = sc.makeRDD(Array(("a", 1), ("b", 2), ("c", 3), ("d", 4), ("e", 4), ("f", 5), ("j", 6)))
    println("**************mapValues start************")
    rdd05.mapValues(_ + 1).foreach(println)
    println("**************mapValues end************\n")

    //flatMapValues类似于flatMap，只是flatMapValues针对(K, V)中的V值进行flat操作
    val rdd06 = sc.makeRDD(Array(
      ("a", "Hello World"),
      ("b", "It is "),
      ("c", "Show time"),
      ("d", "are"),
      ("e", "showing")))
    println("**************flatMapValues start************")
    rdd06.flatMapValues(_.split("\\s+")).foreach(println)
    println("**************flatMapValues end************\n")

    //cogroup相当于SQL的全外关联，返回左右RDD记录，关联不上的为空，可传入的参数有1`3个RDD
    //数据条数为不同的key的总数
    val rdd1 = sc.makeRDD(Array(
      ("a", "Hello World"),
      ("b", "It is"),
      ("c", "Are")
    ))

    val rdd2 = sc.makeRDD(Array(
      ("b", "Show time"),
      ("d", "You ready for")
    ))

    val rdd3 = sc.makeRDD(Array(
      ("d", "showing")
    ))

    val rdd_cogroup = rdd1.cogroup(rdd2, rdd3)
    println("**************cogroup start************")
    rdd_cogroup.foreach(println(_))
    println()
    rdd_cogroup.foreach(kv => {
      val (key, (v1, v2, v3)) = kv
      println(key + " : V1")
      v1.foreach(println)
      println(key + " : V2")
      v2.foreach(println)
      println(key + " : V2")
      v3.foreach(println)

    })
    println("**************cogroup end************\n")

    //join相当于SQL的自然连接，只返回左右RDD都有的KEY
    println("**************join start************")
    rdd1.join(rdd2).foreach(println)
    println("**************join end************\n")

    //fullOuterJoin相当于SQL的全连接，与cogroup结果类似，但是不同的是会把相同的key放在一条数据里面
    val rdd6 = sc.makeRDD(Array(
      ("a", "Hello World"),
      ("b", "It is"),
      ("c", "Are")
    ))

    val rdd7 = sc.makeRDD(Array(
      ("b", "Show time"),
      ("d", "You ready for")
    ))
    println("**************fullOuterJoin start************")
    rdd6.fullOuterJoin(rdd7).foreach(kv => {
      println("--------")
      println(kv._1 + " - " + kv._2)
    })
    println("**************fullOuterJoin end************\n")

    //subtractByKey和基本操作的subtract类似，只是subtractByKey针对的是键值操作
    val rdd8 = sc.makeRDD(Array(
      ("a", "Hello World"),
      ("b", "It is"),
      ("c", "Are")
    ))
    val rdd9 = sc.makeRDD(Array(
      ("b", "Show time"),
      ("d", "You ready for")
    ))
    println("**************subtractByKey start************")
    rdd8.subtractByKey(rdd9).foreach(println(_))
    println("**************subtractByKey end************\n")

    //reduceByKey(func,[numTasks])
    //在一个(k,v)对的数据集上使用，返回一个(k,v)对的数据集
    //key相同的值，将会被指定的函数合并在一起
    //和cogroup类似，任务的个数是可以通过第二个参数来配置
    val rdd_reduceByKey = rddKV.reduceByKey((x, y) => x + "-" + y)
    println("**************reduceByKey start************")
    rdd_reduceByKey.foreach(println(_))
    println("**************reduceByKey end************\n")

    //foldByKey操作用于RDD[(K,V)]根据K将V折叠、合并处理
    val rdd14 = sc.makeRDD(Array(("a", "Hello World"), ("b", "It is"), ("b", "show time")), 3)
    println("**************foldByKey start************")
    rdd14.foldByKey("")(_ + _).foreach(println)
    println("**************foldByKey end************\n")

    //在一个由(K, V)对组成的数据集上面调用,返回一个(K,Seq[V])对的数据集
    //你可以传入numTask可选的参数，根据数据量设置不同的数目Task
    println("**************groupByKey start************")
    rddKV.groupByKey().foreach(println)
    println("**************groupByKey end************\n")

    println("**************groupBy start************")
    rddKV.groupBy(_._1).foreach(println)
    println("**************groupBy end************\n")

    println("**************groupBy Value start************")
    rddKV.groupBy(_._2).foreach(println)
    println("**************groupBy Value end************\n")

    //groupWith(OtherDataset,[NumTask])
    //在类型为(K,V)和(K,W)类型的数据集上面调用
    //返回一个数据集，组成元素为(K,Seq[V],Seq[W]) Tuples
    val rdd_K_V = sc.makeRDD(List(
      ("apache", "Hadoop"),
      ("apache", "Hive"),
      ("apache", "Spark"),
      ("Hbase", "PostgreSQL"),
      ("Redis", "Redis")
    ))

    val rdd_K_W = sc.makeRDD(List(
      ("apache", "Hadoop.com"),
      ("apache", "Hive.com"),
      ("apache", "Spark.com"),
      ("Hbase", "PostgreSQL.com"),
      ("Redis", "Redis.com")
    ))

    println("**************groupWith start************")
    rdd_K_V.groupWith(rdd_K_W).foreach(println)
    println("**************groupWith end************\n")

    val rdd_keys = rddKV.keys
    println("**************keys start************")
    rdd_keys.foreach(println)
    println("**************keys end************\n")

    println("**************values start************")
    rddKV.values.foreach(println)
    println("**************values end************\n")

    /*3、排序转换*/
    println("-------------------------------------3、排序转换-------------------------------------\n")
    val rdd91 = sc.makeRDD(1 to 10, 2)
    println("**************sortBy start************")
    rdd91.sortBy(elem => elem, true, 3).foreach(println)
    println("-------")
    val rdd92 = sc.makeRDD(Array(("a", 10), ("b", 5), ("c", 9)))
    rdd92.sortBy(elem => elem._2, true, 3).foreach(kv => println(kv._1))
    println("**************values end************")

    sc.stop()
  }

}
