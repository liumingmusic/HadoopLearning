package com.c503.hbase;

import com.c503.base.Hbase_Base;
import com.c503.exception.CustomException;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * 描述: 操作与Tables相关的信息
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-25 19:36
 */
public class Hbase_Tables extends Hbase_Base {

    public static void main(String[] args) throws IOException, CustomException {

        Hbase_Tables hbase_tables = new Hbase_Tables();
        Connection connection = hbase_tables.connectToHbase(hbaseMasterIPS, hbaseMasterPort, hbaseZnode);
        Admin admin = connection.getAdmin();

        //列出表
        //hbase_tables.listTables(admin);

        //创建表
        //hbase_tables.createTable(admin);

        //禁用表
        //hbase_tables.disableTable(admin);

        //开启表
        //hbase_tables.enableTable(admin);

        //修改表
        //hbase_tables.modifyTable(admin);

        //查看表
        //hbase_tables.descriptor(admin);

        //删除表
        hbase_tables.deleteTable(admin);
    }

    /**
     * @param admin
     * @return void
     * @method listTables
     * @description 列出所有表
     * @date: 18/6/25 20:50
     * @author: liumm
     */
    private void listTables(Admin admin) throws IOException {
        TableName[] tableNames = admin.listTableNames();
        for (TableName table : tableNames) {
            System.out.println(table.getNameAsString() + ":" + table.getNamespaceAsString());
        }
    }

    /**
     * @param admin
     * @return void
     * @method createTable
     * @description 创建分区表
     * @date: 18/6/26 14:18
     * @author: liumm
     */
    private void createTable(Admin admin) throws IOException {

        //创建表
        HTableDescriptor htable = new HTableDescriptor(TableName.valueOf("i57_ndsa_session"));

        //添加列簇
        htable.addFamily(new HColumnDescriptor("cfn"));

        //创建分区(第一种方式)
        String[] splitKeys = {"liumm_01", "liumm_50", "liumm_100"};
        byte[][] splitKeyBytes = new byte[splitKeys.length][];
        for (int i = 0; i < splitKeyBytes.length; i++) {
            String str = splitKeys[i];
            if (!StringUtils.isBlank(str)) {
                splitKeyBytes[i] = Bytes.toBytes(str);
            }
        }

        //创建分区(第二种方式 hashcode)
        //admin.createTable(htable, Bytes.toBytes("liumm_01"), Bytes.toBytes("liumm_20"), 5);

        admin.createTable(htable, splitKeyBytes);
    }

    /**
     * @param admin
     * @return void
     * @method disableTable
     * @description 禁用表
     * @date: 18/6/26 14:41
     * @author: liumm
     */
    private void disableTable(Admin admin) throws IOException {
        admin.disableTable(TableName.valueOf("i57_ndsa_session"));
    }

    /**
     * @param admin
     * @return void
     * @method enableTable
     * @description 启用表
     * @date: 18/6/26 14:42
     * @author: liumm
     */
    private void enableTable(Admin admin) throws IOException {
        admin.enableTable(TableName.valueOf("i57_ndsa_session"));
    }

    /**
     * @param admin
     * @return void
     * @method modifyTable
     * @description 修改表
     * @date: 18/6/26 14:42
     * @author: liumm
     */
    private void modifyTable(Admin admin) throws IOException {

        TableName i57_ndsa_session = TableName.valueOf("i57_ndsa_session");

        //创建表
        HTableDescriptor desc = new HTableDescriptor(i57_ndsa_session);

        //添加列簇
        desc.addFamily(new HColumnDescriptor("cfn"));
        desc.addFamily(new HColumnDescriptor("cfni").setMinVersions(3));

        admin.modifyTable(i57_ndsa_session, desc);
    }

    /**
     * @param admin
     * @return void
     * @method descriptor
     * @description 表信息查看
     * @date: 18/6/26 15:22
     * @author: liumm
     */
    private void descriptor(Admin admin) throws IOException {

        HTableDescriptor i57_ndsa_session = admin.getTableDescriptor(TableName.valueOf("i57_ndsa_session"));

        HColumnDescriptor[] columnFamilies = i57_ndsa_session.getColumnFamilies();

        int regionSplitPolicyClassName = i57_ndsa_session.getRegionReplication();

        Set<byte[]> familiesKeys = i57_ndsa_session.getFamiliesKeys();

        for (byte[] keys : familiesKeys) {
            System.out.println(Bytes.toString(keys));
        }

        System.out.println(regionSplitPolicyClassName);

        System.out.println("----------------------------------");

        for (HColumnDescriptor col : columnFamilies) {
            System.out.println(col.getNameAsString() + ":" + col.getBlocksize() + ":" + col.getDataBlockEncoding().name());
        }

    }

    /**
     * @param admin
     * @return void
     * @method deleteTable
     * @description 删除表
     * @date: 18/6/26 15:29
     * @author: liumm
     */
    private void deleteTable(Admin admin) throws IOException {

        admin.disableTable(TableName.valueOf("i57_ndsa_session"));
        admin.deleteTable(TableName.valueOf("i57_ndsa_session"));

    }


}
