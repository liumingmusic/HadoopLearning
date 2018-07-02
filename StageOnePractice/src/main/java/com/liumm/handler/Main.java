package com.liumm.handler;

import com.liumm.entity.City;
import com.liumm.hbase.HbaseSearch;
import com.liumm.hdfs.HdfsPutFile;
import com.liumm.mr.MapReduceJsonJob;

import java.util.List;
import java.util.Map;

/**
 * 描述: 所有程序的入口
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-30 17:18
 */
public class Main {

    private static HdfsPutFile hdfsPutFile = new HdfsPutFile();

    private static HbaseSearch hbaseSearch = new HbaseSearch();

    private static MapReduceJsonJob mapReduceJsonJob = new MapReduceJsonJob();

    /**
     * @param args
     * @return void
     * @method main
     * @description 主程序入口
     * @date: 18/6/30 17:50
     * @author: liumm
     */
    public static void main(String[] args) throws Exception {
        //1、hdfs上传文件
        if (args[0].equalsIgnoreCase("hdfs")) {
            if (args.length == 3) {
                hdfsPutFile.putFileToHDFS(args[1], args[2]);
                System.out.println("上传成功");
            } else {
                System.out.println("输入的参数有误");
            }
        }
        //2、写入数据到hbase
        else if (args[0].equalsIgnoreCase("mr")) {
            mapReduceJsonJob.run(args[1], args[2]);
        }
        //3、查询hbase数据
        else if (args[0].equalsIgnoreCase("hbase")) {
            //3.1、查询单个的省下面的信息
            if (args.length == 2) {
                List<City> cityList = hbaseSearch.getCityList(args[1]);
                for (City c : cityList) {
                    System.out.println(c);
                }
            }
            //3.2、查询多个的和
            else {
                Map<String, Long> count = hbaseSearch.getCountByParent(Main.deleteFirst(args));
                for (Map.Entry<String, Long> map : count.entrySet()) {
                    System.out.println("地区编号：" + map.getKey() + ";地区个数：" + map.getValue());
                }
            }
        } else {
            System.out.println("输入有误");
        }
    }

    /**
     * @param arr
     * @return java.lang.String[]
     * @method deleteFirst
     * @description 删除第一个元素
     * @date: 18/6/30 20:37
     * @author: liumm
     */
    public static String[] deleteFirst(String[] arr) {
        String[] temp = new String[arr.length - 1];
        System.arraycopy(arr, 1, temp, 0, temp.length);
        return temp;
    }
}
