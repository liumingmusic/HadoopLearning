package com.c503.mr.job;

import com.c503.mr.mr.MapReduce_Basic;
import com.c503.mr.mr.MapReduce_Multi;
import com.c503.mr.utils.Constant;
import org.apache.hadoop.conf.Configuration;
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
public class MapReduce_Multi_Job {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        String inputPath = Constant.PATH_URL + "mapreduce/inputFile";
        String outputPath = Constant.PATH_URL + "mapreduce/outputFile";

        // 创建job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "wordcount");
        job.setJarByClass(MapReduce_Basic.class);

        // 设置map相关参数
        job.setMapperClass(MapReduce_Multi.MyMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 设置reduce相关参数
        job.setReducerClass(MapReduce_Multi.MyReduce.class);

        // 设置作业的输入路径
        FileInputFormat.addInputPath(job, new Path(inputPath));

        // 设置作业输出路径
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // 提交作业
        job.waitForCompletion(true);

        //推出程序
        System.exit(job.waitForCompletion(true) ? 1 : 0);
    }

}
