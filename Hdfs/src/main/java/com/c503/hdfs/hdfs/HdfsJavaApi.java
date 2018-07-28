package com.c503.hdfs.hdfs;

import com.c503.hdfs.utils.HdfsUnit;
import com.c503.hdfs.utils.HdfsUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 描述:
 * 第一个操作HDFS的JAVA API
 *
 * @author liumm
 * @date 2018-06-09 15:35
 */
@Slf4j
public class HdfsJavaApi {

    FileSystem fileSystem = null;

    /**
     * @Description: 初始化参数
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 16:40
     */
    @Before
    public void setBefore() throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
    }

    /**
     * @Description: 打印文件系统状态
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 18:31
     */
    @Test
    public void getFSTest() throws Exception {
        FsStatus status = fileSystem.getStatus();
        long capacity = status.getCapacity();
        long remaining = status.getRemaining();
        long used = status.getUsed();
        //打印结果出来
        System.out.println("存储空间大小：" + HdfsUnit.formatUnit(capacity, HdfsUnit.G));
        System.out.println("剩余空间大小：" + HdfsUnit.formatUnit(remaining, HdfsUnit.G));
        System.out.println("已经使用大小：" + HdfsUnit.formatUnit(used, HdfsUnit.M));
    }

    /**
     * @Description: 测试copyFromLocalFile
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 16:28
     */
    @Test
    public void putTest() throws Exception {
        Path path = new Path("/Users/liuxm/A_study/idea_ws/HadoopLearning/README.MD");
        fileSystem.copyFromLocalFile(path, new Path("/"));
    }

    /**
     * @Description: 创建文件夹
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 16:39
     */
    @Test
    public void mkdirTest() throws Exception {
        boolean mkdirs = fileSystem.mkdirs(new Path("/javaAPI"));
        if (mkdirs) {
            log.info("创建目录成功");
        } else {
            log.error("创建目录失败");
        }
    }

    /**
     * @Description: 拷贝远程文件到本地
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 16:40
     */
    @Test
    public void getTest() throws Exception {
        fileSystem.copyToLocalFile(new Path("/jdk.rpm"), new Path("/Users/liumm"));
    }

    /**
     * @Description: 列出所有的文件
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 16:41
     */
    @Test
    public void listTest() throws Exception {
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);
        //迭代遍历
        while (listFiles.hasNext()) {
            LocatedFileStatus file = listFiles.next();
            long blockSize = file.getBlockSize();
            String group = file.getGroup();
            long len = file.getLen();
            String owner = file.getOwner();
            short replication = file.getReplication();
            BlockLocation[] blockLocations = file.getBlockLocations();
            //打印信息
            System.out.println("块的大小：" + blockSize);
            System.out.println("组名：" + group);
            System.out.println("文件大小：" + len);
            System.out.println("拥有者：" + owner);
            System.out.println("副本数：" + replication);
            System.out.println("块的位置");
            for (BlockLocation bl : blockLocations) {
                String[] names = bl.getNames();
                for (String str : names) {
                    System.out.println("\t位置：" + str);
                }
            }
            System.out.println("*****************************************************************************");
        }
    }

    /**
     * @Description: 显示文件信息
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 17:06
     */
    @Test
    public void catTest() throws Exception {
        FSDataInputStream open = fileSystem.open(new Path("/README.MD"));
        IOUtils.copyBytes(open, System.out, 1024);
    }

    /**
     * @Description: 删除文件信息
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 17:10
     */
    @Test
    public void rmTest() throws Exception {
        boolean delete = fileSystem.delete(new Path("/com/hdfs.txt"), true);
        if (delete) {
            System.out.println("删除成功");
        }

    }

    /**
     * @param
     * @return void
     * @method getSize
     * @description 获取文件的基本信息
     * @date: 18/6/9 19:23
     * @author: liumm
     */
    @Test
    public void getFileStatus() throws Exception {
        FileStatus status = fileSystem.getFileStatus(new Path("/com/hadoop-2.7.3.tar.gz"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long accessTime = status.getAccessTime();
        long blockSize = status.getBlockSize();
        String group = status.getGroup();
        long len = status.getLen();
        long modificationTime = status.getModificationTime();
        Path path = status.getPath();
        System.out.println("文件创建时间：" + format.format(new Date(accessTime)));
        System.out.println("块的大小：" + HdfsUnit.formatUnit(blockSize, HdfsUnit.M));
        System.out.println("文件组：" + group);
        System.out.println("文件大小：" + HdfsUnit.formatUnit(len, HdfsUnit.M));
        System.out.println("文件修改时间：" + format.format(new Date(modificationTime)));
        System.out.println("文件路径：" + path.getParent().getName());
    }

    /**
     * @param
     * @return void
     * @method moveFile
     * @description 修改文件名称
     * @date: 18/6/9 19:30
     * @author: liumm
     */
    @Test
    public void renameFile() throws Exception {
        boolean rename = fileSystem.rename(new Path("/jdk.rpm"), new Path("/rename_jdk.rpm"));
        if (rename) {
            System.out.println("文件名称修改成功");
        } else {
            System.out.println("文件名称修改失败");
        }
    }

    /**
     * @param
     * @return void
     * @method moveFile
     * @description 移动文件的路径
     * @date: 18/6/9 19:41
     * @author: liumm
     */
    @Test
    public void moveFile() throws Exception {
        boolean rename = fileSystem.rename(new Path("/README.MD"), new Path("/com/README.MD"));
        System.out.println(rename);
    }

    /**
     * @param
     * @return void
     * @method isServerNormalTest
     * @description 得到scheme
     * @date: 18/6/9 19:56
     * @author: liumm
     */
    @Test
    public void isServerNormalTest() throws Exception {
        String scheme = fileSystem.getScheme();
        System.out.println(scheme);
    }

    /**
     * @Description: 最后处理程序
     * @param: []
     * @return: void
     * @author: liumm
     * @Date: 18/6/9 17:09
     */
    @After
    public void after() throws IOException {
        fileSystem.close();
        log.info("测试完成");
    }

}
