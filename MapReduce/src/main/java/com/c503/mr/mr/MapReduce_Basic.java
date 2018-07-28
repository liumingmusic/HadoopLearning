package com.c503.mr.mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 描述: 第一个
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-13 20:33
 */
public class MapReduce_Basic {

    /**
     * @method
     * @description 书写map方法
     * @date: 18/6/15 19:08
     * @author: liumm
     * @return
     */
    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        LongWritable one = new LongWritable(1);

        Text text = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] strings = value.toString().split(" ");
            for (String str : strings) {
                text.set(str);
                context.write(text, one);
            }

        }
    }

    /**
     * @method
     * @description 书写reduce方法
     * @date: 18/6/15 19:09
     * @author: liumm
     * @return
     */
    public static class MyReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            Long sum = 0L;

            for (LongWritable count : values) {
                sum += count.get();
            }

            context.write(key, new LongWritable(sum));

        }
    }

}
