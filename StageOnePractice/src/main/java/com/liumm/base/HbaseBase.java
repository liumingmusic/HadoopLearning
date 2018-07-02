package com.liumm.base;

import com.liumm.exception.CustomException;
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
public class HbaseBase {

    public final static String hbaseMasterIPS = "hthx205";

    public final static int hbaseMasterPort = 2181;

    public final static String hbaseZnode = "/hbase-unsecure";

    private static Configuration conf;

    private static HbaseBase instance = null;

    private HbaseBase() {

    }

    public static HbaseBase getInstance() {
        if (instance == null) {
            instance = new HbaseBase();
        }
        return instance;
    }

    /**
     * @param
     * @return org.apache.hadoop.hbase.client.Connection
     * @method connectionDefault
     * @description 默认链接
     * @date: 18/7/2 11:08
     * @author: liumm
     */
    public Connection connectionDefault() throws IOException, CustomException {
        Connection connection = this.connectSingle(HbaseBase.hbaseMasterIPS, HbaseBase.hbaseMasterPort, HbaseBase.hbaseZnode);
        return connection;
    }

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
    public Connection connectCluster(ArrayList<String> zkIps, int zkPort, String znode) throws IOException, CustomException {
        String zkIp = "";
        if (zkIps != null && zkIps.size() > 0) {
            for (String ip : zkIps) {
                zkIp = zkIp + ip + ",";
            }
            if (zkIp.endsWith(",")) {
                zkIp = zkIp.substring(0, zkIp.length() - 1);
            }
        }
        return this.connectSingle(zkIp, zkPort, znode);
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
    public Connection connectSingle(String zkIps, int zkPort, String znode) throws CustomException, IOException {
        Connection connect;
        if (!"".equals(zkIps)) {
            Configuration that = new Configuration();
            that.set("hbase.rpc.protection", "privacy");
            that.set("hbase.zookeeper.quorum", zkIps);
            that.set("hbase.zookeeper.property.clientPort", String.valueOf(zkPort));
            that.set("zookeeper.znode.parent", znode);
            that.set("hbase.rpc.timeout", "3000");
            that.set("zookeeper.session.timeout", "3000");
            conf = HBaseConfiguration.create(that);
            connect = ConnectionFactory.createConnection(conf);
        } else {
            throw new CustomException("IP address is not set.");
        }
        return connect;
    }

}
