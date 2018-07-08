package com.c503.scala.base;

import com.c503.exception.CustomException;
import com.c503.scala.exception.CustomException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 描述: 基础的Hbase类
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-24 20:08
 */
@SuppressWarnings({"ALL", "AlibabaStringConcat"})
public class Hbase_Base {

    public final static String hbaseMasterIPS = "hthx205";

    public final static int hbaseMasterPort = 2181;

    public final static String hbaseZnode = "/hbase-unsecure";

    public final static int defaultVersion = 3;

    public final static String tableName = "liumm";

    private Configuration conf;

    /**
     * @param zkIps  Hbase所依赖的Zookeeper集群的IP
     * @param zkPort Zookeeper集群的IP
     * @param znode  对应的znode
     * @return org.apache.hadoop.hbase.client.Connection
     * @method connectToHbase
     * @description 连接到Hbase
     * @date: 18/6/25 19:58
     * @author: liumm
     */
    @SuppressWarnings("AlibabaStringConcat")
    public Connection connectToHbase(ArrayList<String> zkIps, int zkPort, String znode) throws IOException, CustomException {
        String zkIp = "";
        if (zkIps != null && zkIps.size() > 0) {
            for (String ip : zkIps) {
                //noinspection AlibabaStringConcat
                zkIp = zkIp + ip + ",";
            }
            if (zkIp.endsWith(",")) {
                zkIp = zkIp.substring(0, zkIp.length() - 1);
            }
        }
        return this.connectToHbase(zkIp, zkPort, znode);
    }

    /**
     * @param zkIps  Hbase所依赖的Zookeeper集群的IP
     * @param zkPort Zookeeper集群的IP
     * @param znode  对应的znode
     * @return org.apache.hadoop.hbase.client.Connection
     * @method connectToHbase
     * @description 描述一下方法的作用
     * @date: 18/6/25 19:59
     * @author: liumm
     */
    public Connection connectToHbase(String zkIps, int zkPort, String znode) throws CustomException, IOException {
        Connection connect;
        if (!"".equals(zkIps)) {
            Configuration that = new Configuration();
            that.set("hbase.rpc.protection", "privacy");
            that.set("hbase.zookeeper.quorum", zkIps);
            that.set("hbase.zookeeper.property.clientPort", String.valueOf(zkPort));
            that.set("zookeeper.znode.parent", znode);
            that.set("hbase.rpc.timeout", "3000");
            that.set("zookeeper.session.timeout", "3000");
            //that.set("hadoop.home.dir", "/usr/local/hadoop");
            this.conf = HBaseConfiguration.create(that);
            connect = ConnectionFactory.createConnection(conf);
        } else {
            throw new CustomException("IP address is not set.");
        }
        return connect;
    }

    /**
     * 获取Hbase集群配置
     *
     * @return Hbase集群配置
     * @author dtinone--加米谷大数据学院
     */
    public Configuration getConf() {
        return conf;
    }

    /**
     * @param obj
     * @return byte[]
     * @method objectToByte
     * @description 对象转换
     * @date: 18/6/25 20:00
     * @author: liumm
     */
    protected byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        if (obj.getClass() == String.class) {
            return ((String) obj).getBytes();
        }
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }
}
