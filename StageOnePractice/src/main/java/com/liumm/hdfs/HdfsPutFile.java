package com.liumm.hdfs;

import com.liumm.base.HdfsBasic;
import com.liumm.exception.CustomException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;

/**
 * 描述: 上传地区JSON文件到HDFS
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-30 12:42
 */
public class HdfsPutFile {

    private static FileSystem fileSystem = null;

    /**
     * @param src  原路径
     * @param dest 目的路径
     * @return void
     * @method putFileToHDFS
     * @description 本地文件上传到HDFS
     * @date: 18/6/30 12:55
     * @author: liumm
     */
    public static void putFileToHDFS(String src, String dest) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        //1、地址对象
        Path srcPath = new Path(src);
        Path destPath = new Path(dest);
        //2、判断上传的文件路径和目的地路径是否存在
        //FIXME 需要考虑上传的文件就在HDFS中
        File srcExists = new File(src);
        boolean destExists = fileSystem.exists(srcPath);
        if (!srcExists.exists()) {
            throw new CustomException("上传的原路径不存在");
        }
        if (!destExists) {
            throw new CustomException("上传的目的路径不存在");
        }
        //3、上传文件
        fileSystem.copyFromLocalFile(srcPath, destPath);
    }

}
