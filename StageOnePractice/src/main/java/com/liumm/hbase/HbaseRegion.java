package com.liumm.hbase;

import com.liumm.base.HbaseBase;
import com.liumm.exception.CustomException;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 描述: 创建表和分区
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-30 13:14
 */
public class HbaseRegion extends HbaseBase {

    /***
     * @method createTables
     * @description 创建分区
     * @date: 18/6/30 13:18
     * @author: liumm
     * @param tableName
     * @return void
     */
    public static void createTables(String tableName) throws IOException, CustomException {
        Connection connection = HbaseBase.connectToHbase(hbaseMasterIPS, hbaseMasterPort, hbaseZnode);
        Admin admin = connection.getAdmin();
        //创建表
        HTableDescriptor htable = new HTableDescriptor(TableName.valueOf(tableName));
        //添加列簇
        htable.addFamily(new HColumnDescriptor("cfn"));

        String[] splitKeys = {"11", "12", "13", "14", "15", "21",
                "22", "23", "31", "32", "33",
                "34", "35", "36", "37", "41",
                "42", "43", "44", "45", "46",
                "50", "51", "52", "53", "54",
                "61", "62", "63", "64"};
        byte[][] splitKeyBytes = new byte[splitKeys.length][];
        for (int i = 0; i < splitKeyBytes.length; i++) {
            String str = splitKeys[i];
            if (!StringUtils.isBlank(str)) {
                splitKeyBytes[i] = Bytes.toBytes(str);
            }
        }

        //创建分区
        admin.createTable(htable, splitKeyBytes);
    }

    public static void main(String[] args) throws IOException, CustomException {
        HbaseRegion.createTables("city");
    }

}
