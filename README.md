### 1、基础教程
#### 1.1、VM虚拟机和Centos系统安装
#### 1.2、linux常用命令讲解
#### 1.3、shell脚本编写入门
#### 1.4、maven企业级配置讲解

### 2、大数据教程
#### 2.1、HDFS入门、深入、Shell访问、Java API操作
#### 2.2、MapReduce入门、深入、编程基础、编程进阶、实战分析和训练
#### 2.3、Yarn入门、原理剖解和应用场景
#### 2.4、Hbase存储原理、RowKey设计、协处理、Shell访问、Java API访问和Hbase性能调优
#### 2.5、ElasticSearch入门、概念基础、基础原理、索引、映射、搜索、聚合和性能优化
#### 2.6、Scala入门、函数、类、对象、特征、模式匹配、常用类和方法、异常处理、编程实战
#### 2.7、Kafka入门、原理剖解、删除数据、消费数据、Shell访问和Java API访问
#### 2.8、SparkCore技术原理、b编程模型、检查点、广播和累加器
#### 2.9、sparkSQL简介、运行原理、程序开发、数据源、分布式引擎、数据类型、性能优化、实战训练
#### 3.0、SparkStreaming简介、运行原理、数据源、性能、容错、实战操作

### 3、综合实战训练项目

-------------------------------

### 1、多模块构建

**mvn install**

初始化需要进行下载plugins相关的信息，后续可以过滤相关的下载以及测试代码

**mvn -o install** (将会离线打包)

**mvn -DskipTests install** (打包不执行测试用例)

**mvn clean package -pb** (打包使用不用环境下面的配置文件)

### 2、jar包运行(没有pom文件配置assembly)
**java -cp hello_world-1.0-SNAPSHOT.jar com.liumm.hello.App**

### 3、私服搭建
下载私服ß
[私服下载地址](https://www.sonatype.com/download-oss-sonatype)
解压进入目录
/Users/liuxm/E_lib/nexus-2.14.8-01-bundle/nexus-2.14.8-01/bin

执行命令 ./nexus start

关闭私服 ./nexus stop
