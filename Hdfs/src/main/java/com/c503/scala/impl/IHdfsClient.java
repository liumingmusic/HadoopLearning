package com.c503.scala.impl;

import com.c503.exception.CustomException;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 描述: hdfs 客户端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-09 20:02
 */
public interface IHdfsClient {


    /**
     * 获取分布式文件系统
     *
     * @return 分布式文件系统
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    FileSystem getFS() throws Exception;

    /**
     * 创建目录
     * 依据输入的完整路径创建目录文件
     * 若目录名已经存在，则报异常<br>
     * 注：即将dir转化为Path再创建目录（创建的目录不存在备份处理，即没有时间戳的后缀）。<br>
     *
     * @param pathOnHdfs 云端路径（完整的绝对路径）
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void mkDir(String pathOnHdfs) throws Exception;

    /**
     * 拷贝文件到HDFS
     *
     * @param localPathAndFileName 本地路径（完整的绝对路径） + 文件名
     * @param pathOnHdfs           云端路径（完整的绝对路径）
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void copyToHdfs(String localPathAndFileName, String pathOnHdfs) throws Exception;

    /**
     * 移动文件到HDFS，删除本地源文件
     *
     * @param localPathAndFileName 本地路径（完整的绝对路径） + 文件名
     * @param pathOnHdfs           云端路径（完整的绝对路径）
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void moveToHdfs(String localPathAndFileName, String pathOnHdfs) throws Exception;

    /**
     * 下载文件
     * 从云端下载文件到输出流
     *
     * @param pathAndFileNameOnHdfs 云端路径（完整的绝对路径） + 文件名
     * @param outputStream          输出流
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void downToOutputStream(String pathAndFileNameOnHdfs, OutputStream outputStream) throws Exception;

    /**
     * 下载文件
     * 从云端下载文件，返回输入流
     *
     * @param pathAndFileNameOnHdfs 云端路径（完整的绝对路径） + 文件名
     * @return
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    InputStream downToInputStream(String pathAndFileNameOnHdfs) throws Exception;

    /**
     * 下载文件
     * 从云端下载文件到本地目录下<br>
     * 具体实现：<br>
     * 1.确定两端路径，判断本地文件的路径是否存在，不存在则创建。<br>
     * 2.先判断云端文件是否是目录，是则遍历该目录，并在本地创建相关的文件目录;不是则开始文件下载<br>
     * 3.实例化一个FileSystem进而得到云端路径下的FSDataInputStream；同时实例化本地下载到的路径下的OutputStream<br>
     * 4.使用IOUtils.copyBytes将InputStrteam 中的内容通过IOUtils的copyBytes方法复制到OutToLOCAL中<br>
     *
     * @param pathOrPathAndFileNameOnHdfs 云端路径（完整的绝对路径） 或 云端路径（完整的绝对路径） + 文件名
     * @param localPath                   本地路径（完整的绝对路径）
     * @throws java.io.FileNotFoundException
     * @throws IOException
     * @author dtinone--加米谷大数据学院
     */
    void downFromHdfs(String pathOrPathAndFileNameOnHdfs, String localPath) throws Exception;

    /**
     * 重命名文件<br>
     * 输入原文件的完整路径和要修改成的名字，修改文件名<br>
     * 1.先判断文件是否是目录<br>
     * 2.若是则检查修改后的目录名是否会重复，是则报DirectoryNameException异常，不是则更新目录名<br>
     * 3.若不是目录，则先检查修改后的文件名在原路径下是否已经存在。是则报IOException。不是就获取到file路径下同名文件的Map，再遍历Map，重命名。<br>
     *
     * @param pathAndFileNameOnHdfs 云端路径（完整的绝对路径） + 文件名
     * @param newFileName           新的文件名
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void rename(String pathAndFileNameOnHdfs, String newFileName) throws Exception;

    /**
     * 获取文件修改时间
     * 从输入的字符串路径上获取到FileStatus并规格化输出他的最后修改时间<br>
     * 通过SimpleDateFormat来规格化输出<br>
     * 规格化输出文件的最后修改时间<br>得到的结果(yyyy-MM-dd HH:mm:ss)
     *
     * @param pathOrpathAndFileNameOnHdfs 云端路径（完整的绝对路径） 或 云端路径（完整的绝对路径） + 文件名
     * @return 得到的结果(yyyy - MM - dd HH : mm : ss)
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    String getFileModifyTime(String pathOrpathAndFileNameOnHdfs) throws IOException, CustomException;

    /**
     * 获取出规格化输出的文件大小<br>
     * 具体实现：<br>
     * 判断输入的文件是否是标准文件，是就直接得到大小，不是则用递归实现累加得到大小，在转换为规格化输出的结果。<br>
     *
     * @param pathOrPathAndFileNameOnHdfs 云端路径（完整的绝对路径） 或 云端路径（完整的绝对路径） + 文件名
     * @return
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    long getSize(String pathOrPathAndFileNameOnHdfs) throws IOException, CustomException;

    /**
     * 移动文件到另一个目录
     * 移动目标文件到另一个目录中去，dst只能是目录路径<br>
     * 具体实现：<br>
     * 1.先判断dst是不是已经存在的目录路径，若是则进行下一步，否则报异常<br>
     * 2.再判断src是不是目录路径，是则是将src路径下的目录移动到dst目录下。顾还需判断dst 目录下是否已经含有同名的目录。没有就移动，有则报异常DirectoryNameException。<br>
     * 3.若src不是一个目录路径，则也需要先判断dst目录中是否含有同名的文件，没有则获取到src下同名文件组，遍历该文件组，移动。有则报异常IOException。<br>
     *
     * @param pathAndFileNameOnHdfs 云端路径（完整的绝对路径） + 文件名
     * @param newPath               新的文件名
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void moveFile(String pathAndFileNameOnHdfs, String newPath) throws IOException, CustomException;

    /**
     * 删除文件 或 删除目录
     * 删除文件（包括目录），删除全部备份文件;若目录与文件同名则优先删除目录。<br>
     *
     * @param pathOrpathAndFileNameOnHdfs 云端路径（完整的绝对路径） 或 云端路径（完整的绝对路径） + 文件名
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    void delete(String pathOrpathAndFileNameOnHdfs) throws IOException, CustomException;

    /**
     * 获取已占用的空间的值
     * 获取到已占用空间的大小（其单位为byte）。
     *
     * @return long
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    long getUsed() throws IOException, CustomException;

    /**
     * 获取相应路径下的空间大小（单位为：byte）
     *
     * @param pathOnHdfs 云端路径（完整的绝对路径）
     * @return
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    long getUsed(String pathOnHdfs) throws IOException, CustomException;

    /**
     * 获取集群空间的总大小（单位为：byte）
     *
     * @return
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    long getHdfsCapacity() throws IOException, CustomException;

    /**
     * 显示path下面一级文件（或文件夹）列表信息，是文件则只显示最新文件信息。<br>
     * 与getListTree(String path)原理一样，此处就只是只有一层的遍历。得到的数据处理方式也一样。<br>
     * 注：该路径必须为目录路径，否则报异常。<br>
     * 注：json数据的格式：filename：文件名，children：文件内的子文件，isfile:是否是文件，owner：文件属于谁，permission:权限，
     * group：文件的组，modifytime：文件的最后修改时间 ，backupnum:文件的备份数。即经处理后得到的名字。<br>
     * eg:<br>
     * [<br>
     * {<br>
     * "owner": "shuzhilian",<br>
     * "pathOnHdfs": "cbc/dir3/dir2",<br>
     * "filename": "dir2",<br>
     * "isFile": false,<br>
     * "size": ".00B",<br>
     * "modifytime": 20150305142159,<br>
     * "permission": 493,<br>
     * "group": "supergroup"<br>
     * },<br>
     * {<br>
     * "owner": "shuzhilian",<br>
     * "pathOnHdfs": "cbc/dir3/test.jar",<br>
     * "filename": "test.jar",<br>
     * "isFile": true,<br>
     * "size": "34.55M",<br>
     * "modifytime": 20150305164614,<br>
     * "permission": 420,<br>
     * "backupnum": 1,<br>
     * "group": "supergroup"<br>
     * }<br>
     * ]<br>
     *
     * @param pathOnHdfs 云端路径（完整的绝对路径）
     * @return
     * @throws IOException
     * @throws CustomException
     * @author dtinone--加米谷大数据学院
     */
    String list(String pathOnHdfs) throws IOException, CustomException;

    /**
     * 检查服务器是否正常
     * 查看文件系统连接是否正常<br>
     * 正常返回true
     * 否则返回false<br>
     *
     * @return true/false
     * @author dtinone--加米谷大数据学院
     */
    boolean isServerNormal() throws CustomException;

    /**
     * 格式化文件大小
     *
     * @param size
     * @return
     * @author dtinone--加米谷大数据学院
     */
    String formatSize(long size);

    /**
     * 关闭hdfs
     *
     * @throws IOException
     * @throws CustomException
     * @author dtinone---加米谷大数据学院
     */
    void close() throws IOException, CustomException;
}
