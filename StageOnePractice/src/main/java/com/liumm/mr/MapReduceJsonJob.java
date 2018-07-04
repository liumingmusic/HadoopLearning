package com.liumm.mr;

import com.liumm.base.HdfsBase;
import com.liumm.exception.CustomException;
import com.liumm.hbase.HbaseRegion;
import com.liumm.utils.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 描述: 第一个单词计数mr启动端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-15 20:25
 */
public class MapReduceJsonJob {

    private HbaseRegion hbaseRegion = new HbaseRegion();

    private static FileSystem fileSystem = null;

    private HdfsBase instance = HdfsBase.getInstance();

    /**
     * @param src
     * @param dest
     * @return void
     * @method run
     * @description 启动任务mr文件json写入数据到hbase
     * @date: 18/7/2 11:19
     * @author: liumm
     */
    public void run(String src, String dest) throws Exception {

        fileSystem = instance.getHdfsConnection();

        // 创建表
        hbaseRegion.createTables("city");

        // 创建job
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(configuration, "CityHbase");
        job.setJarByClass(MapReduceJson.class);

        // 设置map相关参数
        job.setMapperClass(MapReduceJson.JSONMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 设置作业的输入路径
        FileInputFormat.addInputPath(job, new Path(src));

        //输出的路径存在，删除之后执行
        Path destPath = new Path(dest);
        if (fileSystem.isDirectory(destPath)) {
            fileSystem.delete(destPath, true);
        }

        //设置输出路径
        FileOutputFormat.setOutputPath(job, destPath);

        // 提交作业
        job.waitForCompletion(true);

        // 推出程序
        System.exit(job.waitForCompletion(true) ? 1 : 0);
    }

}
