package com.liumm.transform

import org.apache.hadoop.hbase.{HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Spark Action操作
  *
  * @author liumm
  * @since 2018-09-01 15:31
  */
object SparkCore_3_Action {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_3_Action").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(1 to 100)

    val rddKV = sc.makeRDD(List(
      ("apache" -> "Hadoop"),
      ("apache" -> "Hbase"),
      ("apache" -> "Hive"),
      ("apache" -> "Pig"),
      ("redis" -> "Redis"),
      ("db" -> "Mysql")
    ))

    //foreach用于遍历RDD中的每个元素，并将函数应用到每个元素
    //注意：foreach只在executor端有效，而不是在dirvie端
    println("-------------------foreach start------------------------")
    rdd.foreach(elem => {
      println(elem)
    })
    println("-------------------foreach end------------------------\n")

    //foreachPartition和foreach类似，只是函数应用于每个分区
    val rdd_1 = sc.makeRDD(Array(
      ("a", 1),
      ("b", 2),
      ("c", 3),
      ("d", 4),
      ("f", 5)
    ), 2)
    println("-------------------foreachPartition start------------------------")
    rdd_1.foreachPartition(elem => {
      println("----------")
      while (elem.hasNext) {
        println("\t" + elem.next())
      }
    })
    println("-------------------foreachPartition end------------------------\n")


    //reduce(func)
    //通过函数func聚集数据集中的所有元素，func函数接受两个参数，返回一个值
    //这个函数必须是关联性的，确保可以被正确的并发执行
    println("-------------------reduce start------------------------")
    val rdd_2 = sc.makeRDD(1 to 6, 3)
    val rdd_reduce = rdd_2.reduce((x, y) => {
      println("x=" + x + " , y=" + y)
      x + y
    })
    println(rdd_reduce)
    println("-------------------reduce end------------------------\n")

    //collect()
    //在drive的程序中，以数组的形式，返回数据集的所有元素，通常会使用filter或者其他操作后，返回一个足够小的数据子集再使用
    //直接通过RDD集collect返回，很可能会让drive端oom
    println("-------------------collect start------------------------")
    rddKV.collect().foreach(println)
    println("-------------------collect end------------------------\n")

    //count 返回数据集元素个数
    println("-------------------count start------------------------")
    println(rdd.count())
    println("-------------------count end------------------------\n")

    //take(n)
    //返回一个数组，由数据集合前面的元素组成，注意，这个操作目前并非在多个节点上并行执行
    //而drive程序的=所在的机器，单计算所有的元素(getway的内存压力会增大)
    val rdd_take = rddKV.take(2)
    println("-------------------take start------------------------")
    rdd_take.foreach(println)
    println("-------------------take end------------------------\n")

    //top函数操作将rdd中，默认是降序或者指定的排序规则，返回前num个元素
    val arr = rdd.top(2)
    println("-------------------take start------------------------")
    println("arr.length " + arr.length)
    arr.foreach(println)
    println("-------------------take end------------------------\n")

    //takeOrderd和top类似，只是顺序相反
    val arr1 = rdd.takeOrdered(2)
    println("-------------------take start------------------------")
    println("arr1.length " + arr1.length)
    arr1.foreach(println)
    println("-------------------take end------------------------\n")

    //first()
    //类似take(1)
    val arr_first = rdd.first()
    println("-------------------first start------------------------")
    println("arr_first " + arr_first)
    println("-------------------first end------------------------\n")

    //lookup操作用于(K,V),类型RDD，指定K，返回RDD该对K的所有V值
    val add1 = sc.makeRDD(Array(
      ("a", 1),
      ("b", 2),
      ("c", 3),
      ("d", 4),
      ("e", 5),
      ("f", 6),
      ("a", 7)
    ))
    println("-------------------lookup start------------------------")
    add1.lookup("a").foreach(println)
    println("-------------------lookup end------------------------\n")

    //countByKey用于统计(K,V)中每个K的数量
    println("-------------------countByKey start------------------------")
    rddKV.countByKey().foreach(kv => {
      println("key:" + kv._1 + " , size:" + kv._2)
    })
    println("-------------------countByKey end------------------------\n")

    //countByValues用于统计相同元素的个数
    val rdd2 = sc.makeRDD(Array(1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 7, 7, 3, 3, 8, 0, 9, 8, 66, 6, 5, 4), 2)
    println("-------------------countByValue start------------------------")
    rdd2.countByValue().foreach(kv => {
      println("key: " + kv._1 + "\t value: " + kv._2)
    })
    println("-------------------countByValue end------------------------\n")

    //reduceByKeyLocally和reduceByKey类似，不同的是redueBykeyLocally的运算结果映射到Map[K, V]中而不是RDD[K, V]
    val rdd3 = sc.makeRDD(Array(
      ("a", "Hello World"),
      ("b", "It is"),
      ("c", "show time"),
      ("d", "are"),
      ("e", "you ready for"),
      ("f", "showing"),
      ("g", "yourself now")
    ))
    println("-------------------reduceByKeyLocally start------------------------")
    rdd3.reduceByKeyLocally(_ + _).foreach(println)
    println("-------------------reduceByKeyLocally end------------------------\n")

    //注意，fold中zeroval、temp结果类型的必须和元素类型相同
    val rdd4 = sc.makeRDD(1 to 10, 3)
    val zeroVal = 100
    val op = (temp: Int, elem: Int) => {
      println("temp = " + temp + " , elem = " + elem)
      temp + elem
    }
    println("-------------------fold start------------------------")
    println(rdd4.fold(zeroVal)(op))
    println("-------------------fold end------------------------\n")

    println("-------------------saveAsTextFile start------------------------")
    rdd.saveAsTextFile("/Users/liumm/Desktop/" + System.currentTimeMillis())
    println("-------------------saveAsTextFile end------------------------\n")

    println("-------------------saveAsObjectFile start------------------------")
    rdd.saveAsObjectFile("/Users/liumm/Desktop/" + System.currentTimeMillis())
    println("-------------------saveAsObjectFile end------------------------\n")

    println("-------------------saveAsHadoopFile start------------------------")
    val rdd6 = sc.makeRDD(List(
      ("a", 1),
      ("b", 2),
      ("c", 3),
      ("d", 4),
      ("e", 5)
    ))
    rdd6.saveAsHadoopFile(
      "hdfs://master2.i57.com:8020/sparkOutput",
      classOf[Text],
      classOf[IntWritable],
      classOf[TextOutputFormat[Text, IntWritable]]
    )
    println("-------------------saveAsHadoopFile end------------------------\n")

    println("-------------------saveAsNewAPIHadoopDataset start------------------------")
    val that = sc.hadoopConfiguration
    that.set("hbase.rpc.protection", "privacy")
    that.set("hbase.zookeeper.quorum", "master1.i57.com,master2.i57.com,worker1.i57.com")
    that.set("hbase.zookeeper.property.clientPort", "2181")
    that.set("zookeeper.znode.parent", "/hbase-unsecure")
    that.set("hbase.rpc.timeout", "3000")
    that.set("zookeeper.session.timeout", "3000")
    //1、使用原生API创建Hbase表
    val connection = ConnectionFactory.createConnection(that)
    val admin = connection.getAdmin
    if (!admin.tableExists(TableName.valueOf("liumm_spark_test"))) {
      val liumm_spark_test = new HTableDescriptor(TableName.valueOf("liumm_spark_test"))
      liumm_spark_test.addFamily(new HColumnDescriptor("cfn"))
      admin.createTable(liumm_spark_test)
    }
    //2、使用spark的saveAsHadoopDataset写入Hbase数据
    that.set(TableOutputFormat.OUTPUT_TABLE, "liumm_spark_test")
    val job = Job.getInstance(that)
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Result])
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    //构造数据
    val rdd22 = sc.makeRDD(Array(
      ("r1", "cfn", "name", "liumm"),
      ("r2", "cfn", "age", "22"),
      ("r3", "cfn", "school", "xiandaxue"),
      ("r4", "cfn", "addr", "zunyi"),
      ("r5", "cfn", "city", "chengdu")
    )).map(e => {
      val put = new Put(Bytes.toBytes(e._1))
      put.addColumn(Bytes.toBytes(e._2), Bytes.toBytes(e._3), Bytes.toBytes(e._4))
      (new ImmutableBytesWritable, put)
    })
    rdd22.saveAsNewAPIHadoopDataset(job.getConfiguration)
    println("-------------------saveAsNewAPIHadoopDataset end------------------------\n")

    sc.stop()
  }


}
