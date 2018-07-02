package com.liumm.base;

import com.liumm.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

/**
 * 描述:
 * hdfs操作基础类
 *
 * @author liumm
 * @date 2018-06-09 17:57
 */
@Slf4j
public class HdfsBase {

    //链接地址
    public static final String default_url = "hdfs://hthx205:8020/";

    //访问用户
    public static final String user = "root";

    //副本系数
    public static final String replication = "1";

    //文件系统操作对象
    public static FileSystem fileSystem = null;

    private static HdfsBase single = null;

    private HdfsBase() {
    }

    /**
     * @param
     * @return com.liumm.hdfs.HdfsBase
     * @method getInstance
     * @description 单例模式
     * @date: 18/6/9 20:28
     * @author: liumm
     */
    public static HdfsBase getInstance() {
        if (null == fileSystem) {
            single = new HdfsBase();
        }
        return single;
    }

    /**
     * @Description: 初始化hdfs链接对象
     * @param: url 链接的地址
     * @return: org.apache.hadoop.fs.FileSystem
     * @author: liumm
     * @Date: 18/6/9 18:36
     */
    public FileSystem getHdfsConnection(String... url) throws Exception {
        Configuration conf = new Configuration();
        conf.set("dfs.replication", replication);
        if (url.length == 0) {
            fileSystem = FileSystem.get(new URI(default_url), conf, user);
        } else if (url.length == 1) {
            fileSystem = FileSystem.get(new URI(url[0]), conf, user);
        } else {
            throw new CustomException("现在不支持集群方式链接");
        }
        log.info("HDFS连接成功");
        return fileSystem;
    }


}
