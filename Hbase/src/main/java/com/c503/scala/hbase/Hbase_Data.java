package com.c503.scala.hbase;

import com.c503.base.Hbase_Base;
import com.c503.exception.CustomException;
import com.c503.scala.base.Hbase_Base;
import com.c503.scala.exception.CustomException;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述: 操作与Hbase中数据相关的API
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-25 19:37
 */
public class Hbase_Data extends Hbase_Base {

    public static void main(String[] args) throws IOException, CustomException {

        Hbase_Data hbase_data = new Hbase_Data();

        Connection connection = hbase_data.connectToHbase(hbaseMasterIPS, hbaseMasterPort, hbaseZnode);

        //新增数据
        //hbase_data.put(connection);

        //修改数据
        //hbase_data.modify(connection);

        //获得数据
        //hbase_data.get(connection);

        //删除数据
        //hbase_data.delete(connection);

        //添加多条
        //hbase_data.puts(connection);

        //扫描数据
        hbase_data.scan(connection);

        System.out.println("执行完成");
    }

    /**
     * @param connection
     * @return void
     * @method put
     * @description 添加数据
     * @date: 18/6/26 17:00
     * @author: liumm
     */
    private void put(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        Put put = new Put(Bytes.toBytes("rowkey-001"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("name"), new Date().getTime(), Bytes.toBytes("liux"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("age"), new Date().getTime(), Bytes.toBytes("20"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("address"), new Date().getTime(), Bytes.toBytes("guizhou"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("sex"), new Date().getTime(), Bytes.toBytes("man"));
        i57_ndsa_session.put(put);
    }

    /**
     * @param connection
     * @return void
     * @method modify
     * @description 修改数据
     * @date: 18/6/26 17:01
     * @author: liumm
     */
    private void modify(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        Put put = new Put(Bytes.toBytes("rowkey-001"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("name"), new Date().getTime(), Bytes.toBytes("liuxm"));
        put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("school"), new Date().getTime(), Bytes.toBytes("xi'an"));
        i57_ndsa_session.put(put);
    }

    /**
     * @param connection
     * @return void
     * @method get
     * @description 得到数据
     * @date: 18/6/26 17:01
     * @author: liumm
     */
    private void get(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        Get get = new Get(Bytes.toBytes("rowkey-001"));
        Result result = i57_ndsa_session.get(get);
        printResult(result);
    }

    /**
     * @param connection
     * @return void
     * @method delete
     * @description 删除数据
     * @date: 18/6/26 17:01
     * @author: liumm
     */
    private void delete(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        Delete delete = new Delete(Bytes.toBytes("rowkey-001"));
        delete.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("name"));
        i57_ndsa_session.delete(delete);


    }

    /**
     * @param connection
     * @return void
     * @method puts
     * @description 添加多条数据
     * @date: 18/6/26 17:01
     * @author: liumm≤
     */
    private void puts(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        List<Put> list = new ArrayList<Put>();
        for (int i = 2; i < 10; i++) {
            Put put = new Put(Bytes.toBytes("rowkey-00" + i));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("name"), new Date().getTime(), Bytes.toBytes("liux" + i));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("age"), new Date().getTime(), Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("address"), new Date().getTime(), Bytes.toBytes("guizhou" + i));
            put.addColumn(Bytes.toBytes("cfn"), Bytes.toBytes("sex"), new Date().getTime(), Bytes.toBytes("man" + i));
            list.add(put);
        }
        //批量添加
        i57_ndsa_session.put(list);
    }

    /**
     * @param connection
     * @return void
     * @method scan
     * @description 扫描数据
     * @date: 18/6/26 17:02
     * @author: liumm
     */
    private void scan(Connection connection) throws IOException {
        Table i57_ndsa_session = connection.getTable(TableName.valueOf("i57_ndsa_session"));
        String startRowkey = "rowkey-001";
        String endRowkey = "rowkey-006";
        String cfn = "cfn";
        String qualifier = "name";
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRowkey));
        scan.setStopRow(Bytes.toBytes(endRowkey));
        scan.addFamily(Bytes.toBytes(cfn));
        //scan.addColumn(Bytes.toBytes(cfn),Bytes.toBytes(qualifier));
        //scan.setReversed(true);
        //scan.setMaxVersions(3);
        ResultScanner scanner = i57_ndsa_session.getScanner(scan);
        for (Result r = scanner.next(); r != null; r = scanner.next()) {
            printResult(r);
        }
    }

    /**
     * @param result
     * @return void
     * @method printResult
     * @description 日志消息打印
     * @date: 18/6/27 18:55
     * @author: liumm
     */
    private void printResult(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(result.getRow()) + ":" + Bytes.toString(CellUtil.cloneFamily(cell)) + ":" +
                    Bytes.toString(CellUtil.cloneQualifier(cell)) + ":" + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

}
