package com.c503.job;

import com.c503.mr.MapReduce_Basic;
import com.c503.mr.MapReduce_Parameter;
import com.c503.mr.MapReduce_Split;
import com.c503.utils.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * 描述: 第一个单词计数mr启动端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-15 20:25
 */
public class MapReduce_Parameter_Job {


    private static String inputPath = Constant.PATH_URL + "mapreduce/inputFile";
    private static String outputPath = Constant.PATH_URL + "mapreduce/outputFile";

    static {
        new File(outputPath).delete();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        //传递参数
        Configuration conf = new Configuration();
        conf.set("parameter", "liumm");

        // 创建job
        Job job = Job.getInstance(conf, "wordcount");
        job.setJarByClass(MapReduce_Split.class);
        job.setNumReduceTasks(2);

        // 设置map相关参数
        job.setMapperClass(MapReduce_Split.MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 设置reduce相关参数
        job.setReducerClass(MapReduce_Split.MyReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

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
