package com.c503.impl;

import com.c503.exception.CustomException;
import com.c503.hdfs.HdfsBasic;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.math.BigDecimal;

/**
 * 描述: 实现Hdfs客户端接口
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-09 20:09
 */
@Slf4j
public class HdfsClientImpl implements IHdfsClient {

    public static FileSystem fileSystem = null;

    /**
     * @param
     * @return org.apache.hadoop.fs.FileSystem
     * @method getFS
     * @description 获取HDFS对象
     * @date: 18/6/9 20:22
     * @author: liumm
     */
    @Override
    public FileSystem getFS() throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        return fileSystem;
    }

    /**
     * @param pathOnHdfs 创建的目录名称
     * @return void
     * @method mkDir
     * @description 创建目录
     * @date: 18/6/9 20:45
     * @author: liumm
     */
    @Override
    public void mkDir(String pathOnHdfs) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        if (StringUtils.isEmpty(pathOnHdfs)) {
            throw new CustomException("输入的创建的目录为空");
        } else if (fileSystem.exists(new Path(pathOnHdfs))) {
            throw new CustomException("当前创建的目录已经存在了");
        } else {
            fileSystem.mkdirs(new Path(pathOnHdfs), FsPermission.getDefault());
        }
    }

    /**
     * @param localPathAndFileName
     * @param pathOnHdfs
     * @return void
     * @method copyToHdfs
     * @description 从本地上传文件到服务器
     * @date: 18/6/9 20:45
     * @author: liumm
     */
    @Override
    public void copyToHdfs(String localPathAndFileName, String pathOnHdfs) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        File local_path = new File(localPathAndFileName);
        Path hdfs_path = new Path(pathOnHdfs);
        if (StringUtils.isEmpty(localPathAndFileName) || StringUtils.isEmpty(pathOnHdfs)) {
            throw new CustomException("输入的创建的目录为空");
        } else if (!local_path.exists()) {
            throw new CustomException("上传的文件不存在");
        } else if (fileSystem.exists(hdfs_path)) {
            throw new CustomException("上传的HDFS已经存在当前目录文件");
        } else {
            fileSystem.copyToLocalFile(new Path(localPathAndFileName), hdfs_path);
        }
    }

    /**
     * @param localPathAndFileName
     * @param pathOnHdfs
     * @return void
     * @method moveToHdfs
     * @description 移动本地文件到hdfs
     * @date: 18/6/9 20:47
     * @author: liumm
     */
    @Override
    public void moveToHdfs(String localPathAndFileName, String pathOnHdfs) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        File local_path = new File(localPathAndFileName);
        Path hdfs_path = new Path(pathOnHdfs);
        if (StringUtils.isEmpty(localPathAndFileName) || StringUtils.isEmpty(pathOnHdfs)) {
            throw new CustomException("输入的创建的目录为空");
        } else if (!local_path.exists()) {
            throw new CustomException("上传的文件不存在");
        } else if (fileSystem.exists(hdfs_path)) {
            throw new CustomException("上传的HDFS已经存在当前目录文件");
        } else {
            fileSystem.moveToLocalFile(new Path(localPathAndFileName), hdfs_path);
        }
    }

    /**
     * @param pathAndFileNameOnHdfs
     * @param outputStream
     * @return void
     * @method downToOutputStream
     * @description 以流的方式进行下载文件
     * @date: 18/6/9 20:52
     * @author: liumm
     */
    @Override
    public void downToOutputStream(String pathAndFileNameOnHdfs, OutputStream outputStream) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        Path hdfs_path = new Path(pathAndFileNameOnHdfs);
        if (StringUtils.isEmpty(pathAndFileNameOnHdfs)) {
            throw new CustomException("输入下载的文件名称为空");
        } else if (!fileSystem.exists(hdfs_path)) {
            throw new CustomException("下载的文件不存在");
        } else {
            FSDataInputStream open = fileSystem.open(new Path(pathAndFileNameOnHdfs));
            IOUtils.copyBytes(open, outputStream, 1024);
        }
    }

    /**
     * @param pathAndFileNameOnHdfs
     * @return java.io.InputStream
     * @method downToInputStream
     * @description 下载文件获取输入流
     * @date: 18/6/9 20:54
     * @author: liumm
     */
    @Override
    public InputStream downToInputStream(String pathAndFileNameOnHdfs) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        Path hdfs_path = new Path(pathAndFileNameOnHdfs);
        FSDataInputStream open;
        if (StringUtils.isEmpty(pathAndFileNameOnHdfs)) {
            throw new CustomException("输入下载的文件名称为空");
        } else if (!fileSystem.exists(hdfs_path)) {
            throw new CustomException("下载的文件不存在");
        } else {
            open = fileSystem.open(new Path(pathAndFileNameOnHdfs));
        }
        return open;
    }

    /**
     * @param pathOrPathAndFileNameOnHdfs
     * @param localPath
     * @return void
     * @method downFromHdfs
     * @description 下载文件到本地
     * @date: 18/6/9 20:58
     * @author: liumm
     */
    @Override
    public void downFromHdfs(String pathOrPathAndFileNameOnHdfs, String localPath) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        if (StringUtils.isEmpty(pathOrPathAndFileNameOnHdfs) || StringUtils.isEmpty(localPath)) {
            throw new CustomException("输入的路径有问题");
        } else if (!fileSystem.exists(new Path(pathOrPathAndFileNameOnHdfs))) {
            throw new CustomException("下载的文件不存在");
        } else {
            fileSystem.copyToLocalFile(new Path(pathOrPathAndFileNameOnHdfs), new Path(localPath));
        }
    }


    /**
     * @param pathAndFileNameOnHdfs
     * @param newFileName
     * @return void
     * @method rename
     * @description 修改文件名称
     * @date: 18/6/9 21:01
     * @author: liumm
     */
    @Override
    public void rename(String pathAndFileNameOnHdfs, String newFileName) throws Exception {
        HdfsBasic instance = HdfsBasic.getInstance();
        fileSystem = instance.getHdfsConnection();
        Path old = new Path(pathAndFileNameOnHdfs);
        Path newFile = new Path(newFileName);
        if (!fileSystem.exists(old)) {
            throw new CustomException("修改的文件不存在");
        } else if (fileSystem.exists(newFile)) {
            throw new CustomException("被修改的文件已存在");
        } else {
            fileSystem.rename(old, newFile);
        }
    }

    @Override
    public String getFileModifyTime(String pathOrpathAndFileNameOnHdfs) throws IOException, CustomException {
        return null;
    }

    @Override
    public long getSize(String pathOrPathAndFileNameOnHdfs) throws IOException, CustomException {
        return 0;
    }

    @Override
    public void moveFile(String pathAndFileNameOnHdfs, String newPath) throws IOException, CustomException {

    }

    @Override
    public void delete(String pathOrpathAndFileNameOnHdfs) throws IOException, CustomException {

    }

    @Override
    public long getUsed() throws IOException, CustomException {
        return 0;
    }

    @Override
    public long getUsed(String pathOnHdfs) throws IOException, CustomException {
        return 0;
    }

    @Override
    public long getHdfsCapacity() throws IOException, CustomException {
        return 0;
    }

    @Override
    public String list(String pathOnHdfs) throws IOException, CustomException {
        return null;
    }

    @Override
    public boolean isServerNormal() throws CustomException {
        return false;
    }

    /**
     * @param size
     * @return java.lang.String
     * @method formatSize
     * @description 格式化文件大小
     * @date: 18/6/9 21:03
     * @author: liumm
     */
    @Override
    public String formatSize(long size) {
        BigDecimal a = new BigDecimal(size);
        String s = a.divide(new BigDecimal(1024), 2).toString();
        return s;
    }

    @Override
    public void close() throws IOException {
        fileSystem.close();
        fileSystem = null;
    }
}
