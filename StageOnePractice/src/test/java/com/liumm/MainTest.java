package com.liumm;

import com.liumm.handler.Main;
import com.liumm.hbase.HbaseSearch;
import com.liumm.hdfs.HdfsPutFile;
import com.liumm.mr.MapReduceJsonJob;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void hdfsTest() throws Exception {

        String[] liumm = new String[]{"hdfs", "/Users/liuxm/A_study/idea_ws/HadoopLearning/StageOnePractice/src/main/resources/area.json", "/area/"};

        Main.main(liumm);

    }

    @Test
    public void hbaseTest() throws Exception {

        String[] liumm = new String[]{"hbase", "140700,110000,654200,000000,654300"};

        Main.main(liumm);

    }

    @Test
    public void mrTest() throws Exception {

        String[] str = new String[]{"mr", "/area/", "/mr_result/"};

        Main.main(str);


    }

}
