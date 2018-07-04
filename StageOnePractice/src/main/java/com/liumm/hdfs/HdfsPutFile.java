package com.liumm.hdfs;

import com.liumm.base.HdfsBase;
import com.liumm.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class HdfsPutFile {

    private static FileSystem fileSystem = null;

    private HdfsBase instance = HdfsBase.getInstance();

    /**
     * @param src  原路径
     * @param dest 目的路径
     * @return void
     * @method putFileToHDFS
     * @description 本地文件上传到HDFS
     * @date: 18/6/30 12:55
     * @author: liumm
     */
    public void putFileToHDFS(String src, String dest) throws Exception {
        fileSystem = instance.getHdfsConnection();
        //1、地址对象
        Path srcPath = new Path(src);
        //FIXME 动态修改
        Path destPath = new Path(dest + "area.json");
        //2、判断上传的文件路径和目的地路径是否存在
        //FIXME 需要考虑上传的文件就在HDFS中
        File srcExists = new File(src);
        if (!srcExists.exists()) {
            throw new CustomException("上传的原路径不存在");
        }
        if (fileSystem.exists(destPath)) {
            //重复上传删除
            boolean delete = fileSystem.delete(destPath, true);
            if (delete) {
                log.info("删除文件重复文件成功");
            }
        }
        //3、上传文件
        fileSystem.copyFromLocalFile(srcPath, destPath);
    }

}
