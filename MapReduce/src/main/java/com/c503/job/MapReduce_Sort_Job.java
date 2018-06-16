package com.c503.job;

import com.c503.mr.MapReduce_Sort;
import com.c503.utils.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFilter;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

/**
 * 描述: 第一个单词计数mr启动端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-15 20:25
 */
public class MapReduce_Sort_Job {

    private static String inputPath = Constant.PATH_URL + "mapreduce/inputFile";

    private static String tempPath = Constant.PATH_URL + "mapreduce/tempFile";

    private static String outputPath = Constant.PATH_URL + "mapreduce/outputFile";

    static {
        new File(tempPath).delete();
        new File(outputPath).delete();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        /***********************************/
        /**               先计数           **/
        /***********************************/

        // 创建job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "wordcount");
        job.setJarByClass(MapReduce_Sort.class);

        // 设置map相关参数
        job.setMapperClass(MapReduce_Sort.MyMapper.class);

        // 设置reduce相关参数
        job.setReducerClass(MapReduce_Sort.MyReduce.class);

        //设置输出的格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        // 设置作业的输入路径
        FileInputFormat.addInputPath(job, new Path(inputPath));

        // 设置作业输出路径
        FileOutputFormat.setOutputPath(job, new Path(tempPath));

        // 提交作业
        job.waitForCompletion(true);

        /***********************************/
        /**       后排序(没有Reduce阶段)    **/
        /***********************************/

        Job sortJob = Job.getInstance();
        sortJob.setJobName("wordcount_sort");

        // 设置map相关参数
        sortJob.setMapperClass(InverseMapper.class);

        // 设置作业的输入路径和输入的规则
        FileInputFormat.addInputPath(sortJob, new Path(tempPath));
        sortJob.setInputFormatClass(SequenceFileInputFilter.class);

        // 设置作业输出路径和设置输出的格式
        FileOutputFormat.setOutputPath(sortJob, new Path(outputPath));
        sortJob.setOutputKeyClass(LongWritable.class);
        sortJob.setOutputValueClass(Text.class);

        //设置比较规则
        sortJob.setSortComparatorClass(MapReduce_Sort.LongWritableDecrasingComparator.class);

        // 提交作业
        sortJob.waitForCompletion(true);

        //推出程序
        System.exit(sortJob.waitForCompletion(true) ? 1 : 0);
    }

}
