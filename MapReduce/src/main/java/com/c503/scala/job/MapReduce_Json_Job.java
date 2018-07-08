package com.c503.scala.job;

import com.c503.mr.MapReduce_Basic;
import com.c503.mr.MapReduce_Json;
import com.c503.scala.mr.MapReduce_Json;
import com.c503.scala.utils.Constant;
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
public class MapReduce_Json_Job {

    private static String inputPath = Constant.PATH_URL + "mapreduce/jsonInputFile";
    private static String outputPath = Constant.PATH_URL + "mapreduce/jsonOutputFile";

    static {
        boolean delete = new File(outputPath).delete();
        System.out.println(delete);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 创建job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "wordcount");
        job.setJarByClass(MapReduce_Basic.class);

        // 设置map相关参数
        job.setMapperClass(MapReduce_Json.MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
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
