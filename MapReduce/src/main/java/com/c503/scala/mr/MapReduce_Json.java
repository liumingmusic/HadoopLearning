package com.c503.scala.mr;

import com.alibaba.fastjson.JSONArray;
import com.c503.entity.City;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 描述: 第一个
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-13 20:33
 */
public class MapReduce_Json {

    /**
     * @method
     * @description 书写map方法
     * @date: 18/6/15 19:08
     * @author: liumm
     * @return
     */
    public static class MyMapper extends Mapper<LongWritable, Text, LongWritable, City> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1、转换为对象，同时动态补齐areaID
            City city = JSONArray.parseObject(value.toString(), City.class);
            //2、组装rowkey
            //3、组装Hbase put对象
            //4、写入到Hbase中
            System.out.println("<" + city.getAreaId() + "|" + city.getParentId());
        }

    }

    public static void main(String[] args) {
        String format = String.format("%0" + 6 + "d", Integer.parseInt("0") + 1);
        System.out.println(format);
    }

}
