package com.liumm.sparkcore

import java.io.{ByteArrayInputStream, ObjectInputStream}

import org.apache.hadoop.hbase.client.{Put, Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableOutputFormat}
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{mapreduce, util, _}
import org.apache.hadoop.io._
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 基于HDFS进行创建
  *
  * @author liumm
  * @since 2018-08-18 14:10
  */
object SparkCore_1_CreateRdd_6_From_ByHdfs {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SparkCore_1_CreateRdd_6_From_ByHdfs").setMaster("local")
    //防止序列化器不存在
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val sc = new SparkContext(conf)

    //hdfs创建rdd
    val rdd1 = sc.textFile("hdfs://hthx205:8020/apps/hbase/data/hbase.id")
    println("======================hdfs文件创建")
    println("textFile方法从hdfs文件创建RDD类型：" + rdd1 + ",默认分区数是：" + rdd1.partitions.length)
    rdd1.foreach(println(_))

    val rdd2 = sc.textFile("hdfs://hthx205:8020/apps/hbase/data/hbase.id", 4)
    println("======================hdfs文件创建")
    println("textFile方法从hdfs文件创建RDD类型：" + rdd2 + ",默认分区数是：" + rdd2.partitions.length)
    rdd2.foreach(println(_))

    //整个目录创建
    val rdd3 = sc.wholeTextFiles("hdfs://hthx205:8020/apps/hbase/data/")
    println("======================hdfs文件夹创建")
    println("wholeTextFiles方法从hdfs文件夹创建RDD类型：" + rdd3 + ",默认分区数是：" + rdd3.partitions.length)
    rdd3.foreach(println(_))

    //基于sequence创建
    val rdd4 = sc.makeRDD((1 to 10))
    rdd4.saveAsObjectFile("hdfs://hthx205:8020/apps/hbase/data/hbase.version")
    rdd4.foreach(println(_))

    val rdd5 = sc.sequenceFile[NullWritable, BytesWritable]("hdfs://hthx205:8020/apps/hbase/data/hbase.version")

    println("=================基于sequenceFile创建")

    rdd5.foreach(kv => {
      println("----")
      val ois = new ObjectInputStream(new ByteArrayInputStream(kv._2.getBytes))
      val arr = ois.readObject().asInstanceOf[Array[Int]]
      arr.foreach(println(_))
      ois.close()
    })

    //基于hadoopfile创建
    //1、首先在hdfs上面创建hadoop文件
    val rdd6 = sc.makeRDD(Array(("a", 1), ("b", 2), ("c", 3)))
    rdd6.saveAsNewAPIHadoopFile("hdfs://hdp01:8020/liumm", classOf[Text], classOf[IntWritable], classOf[TextOutputFormat[Text, IntWritable]])

    //2、使用newAPIHadoop方法上面加载hadoop文件，该方法属于底层方法
    val rdd7 = sc.newAPIHadoopFile("hdfs://hdp01:8020/liumm", classOf[TextInputFormat], classOf[LongWritable], classOf[Text])
    println("=========通过newAPIHadoopFile创建")
    rdd7.foreach(kv => {
      println(kv._1 + "->" + kv._2)
    })

    //基于Hbase创建
    val that = sc.hadoopConfiguration
    that.set("hbase.rpc.protection", "privacy")
    that.set("hbase.zookeeper.quorum", "hthx205:2181,hthx206:2181,hthx207:2181")
    that.set("hbase.zookeeper.property.clientPort", "2181")
    that.set("zookeeper.znode.parent", "/hbase-unsecure")
    that.set("hbase.rpc.timeout", "3000")
    that.set("zookeeper.session.timeout", "3000")

    //1、使用原生api创建habse表
    val connection = client.ConnectionFactory.createConnection(that)
    val admin = connection.getAdmin

    if (!admin.tableExists(TableName.valueOf("i57_ndsa_result"))) {
      admin.createTable(new HTableDescriptor(TableName.valueOf("i57_ndsa_result")).addFamily(new HColumnDescriptor("cfn")))
      println("i57_ndsa_result表创建成功")
    }

    //2、使用spark的saveHadoopData保存数据
    that.set(TableOutputFormat.OUTPUT_TABLE, "i57_ndsa_result")
    val job = Job.getInstance(that)
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Result])
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    val rdd22 = sc.makeRDD(
      Array(("rowkey-5", "cfn", "name", "xinww")).map(data => {
        val put = new Put(Bytes.toBytes(data._1))
        put.addColumn(Bytes.toBytes(data._2), Bytes.toBytes(data._3), Bytes.toBytes(data._4))
        (new ImmutableBytesWritable, put)
      })
    )

    rdd22.saveAsNewAPIHadoopDataset(job.getConfiguration)

    //3、使用spark的newApiHadoop从Hbase中读取数据
    that.set(mapreduce.TableInputFormat.INPUT_TABLE, "i57_ndsa_result")
    that.set(mapreduce.TableInputFormat.SCAN, util.Base64.encodeBytes(ProtobufUtil.toScan(new Scan()).toByteArray))
    val rdd23 = sc.newAPIHadoopRDD(that, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
    println("Spark读取Hbase数据")
    rdd23.foreach(x => {
      val result = x._2
      val v = Bytes.toString(result.getValue(Bytes.toBytes("cfn"), Bytes.toBytes("name")))
      println(v)
    })
  }

}
