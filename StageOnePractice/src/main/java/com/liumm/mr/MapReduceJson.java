package com.liumm.mr;

import com.alibaba.fastjson.JSONArray;
import com.liumm.base.HbaseBase;
import com.liumm.entity.City;
import com.liumm.exception.CustomException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
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
public class MapReduceJson {

    /**
     * @method
     * @description 书写map方法
     * @date: 18/6/15 19:08
     * @author: liumm
     * @return
     */
    public static class JSONMapper extends Mapper<LongWritable, Text, LongWritable, City> {

        private HbaseBase hbaseBase = HbaseBase.getInstance();
        private Connection connection = null;
        private Table table;


        @Override
        protected void setup(Context context) throws IOException {
            try {
                connection = hbaseBase.connectionDefault();
                table = connection.getTable(TableName.valueOf("city"));
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1、转换为对象，同时动态补齐areaID
            City city = JSONArray.parseObject(value.toString(), City.class);
            //2、组装rowkey
            String rowkey = city.getParentId() + "|" + city.getAreaId();
            //3、组装Hbase put对象
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("areaId"), Bytes.toBytes(city.getAreaId()));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("parentId"), Bytes.toBytes(city.getParentId()));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("areaName"), Bytes.toBytes(city.getAreaName()));
            //4、写入到Hbase中
            //FIXME 可以修改成批量写入，而不是每次解析都写入
            table.put(put);
        }

        @Override
        protected void cleanup(Context context) throws IOException {
            table.close();
            connection.close();
        }
    }

}
