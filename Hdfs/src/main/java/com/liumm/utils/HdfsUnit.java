package com.liumm.utils;

/**
 * 描述:
 * 控制HDFS文件显示的单位格式化
 *
 * @author liumm
 * @date 2018-06-09 18:57
 */
public class HdfsUnit {

    public static final long G = 1024 * 1024 * 1024;

    public static final long M = 1024 * 1024;

    /**
     * @method  formatUnit
     * @description 格式化显示单位
     * @date: 18/6/9 19:10
     * @author: liumm
     * @param len 数据长度
     * @param unit 转换的单位
     * @return long
     */
    public static long formatUnit(long len, long unit) {
        return len / unit;
    }

}
