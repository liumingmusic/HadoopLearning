package com.liumm;

import com.liumm.handler.Main;
import com.liumm.hbase.HbaseSearch;
import com.liumm.hdfs.HdfsPutFile;
import com.liumm.mr.MapReduceJsonJob;
import org.junit.Test;

/**
 * 描述: 单元测试
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-07-02 11:37
 */
public class MainTest {

    private static HdfsPutFile hdfsPutFile = new HdfsPutFile();

    private static HbaseSearch hbaseSearch = new HbaseSearch();

    private static MapReduceJsonJob mapReduceJsonJob = new MapReduceJsonJob();

    @Test
    public void hbaseTest() throws Exception {

        String[] str = new String[]{"hbase", "654300,000000"};

        Main.main(str);

    }

    @Test
    public void mrTest() throws Exception {

        String[] str = new String[]{"mr", "/Users/liuxm/A_study/idea_ws/mapreduce/jsonInputFile", "/Users/liuxm/A_study/idea_ws/mapreduce/jsonOutputFile"};

        Main.main(str);
    }


}
