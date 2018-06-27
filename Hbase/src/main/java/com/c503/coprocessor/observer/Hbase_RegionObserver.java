package com.c503.coprocessor.observer;

import com.c503.base.Hbase_Base;
import com.c503.exception.CustomException;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 描述: 协处理器
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-27 19:35
 */
@Slf4j
public class Hbase_RegionObserver extends BaseRegionObserver {

    private Connection connection;

    private final String LOG_TABLE = "logs";

    private final String FAMILY = "info";

    private final String COLUMN_NAME = "table";

    private final String COLUMN_TYPE = "type";

    private final String COLUMN_TIME = "time";

    @Override
    public void start(CoprocessorEnvironment e) throws IOException {
        log.info("Hbase_RegionObserver.start ....");
        if (e instanceof RegionCoprocessorEnvironment) {
            Hbase_Base hbase_base = new Hbase_Base();
            try {
                connection = hbase_base.connectToHbase("hthx205", 2181, "/hbase-unsecure");
            } catch (CustomException e1) {
                log.error(e1.getMessage());
                e1.printStackTrace();
            }
        } else {
            try {
                throw new CustomException("初始化报错");
            } catch (CustomException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void stop(CoprocessorEnvironment e) throws IOException {
        log.info("Hbase_RegionObserver.stop ...");
    }

    @Override
    public void postOpen(ObserverContext<RegionCoprocessorEnvironment> e) {
        this.putLog(e, "P");
    }

    @Override
    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        this.putLog(e, "D");
    }

    /***
     * @method putLog
     * @description 存储日志到hbase
     * @date: 18/6/27 20:43
     * @author: liumm
     * @param e
     * @param p
     * @return void
     */
    private void putLog(ObserverContext<RegionCoprocessorEnvironment> e, String p) {
        RegionCoprocessorEnvironment evn = e.getEnvironment();
        TableName tableName = evn.getRegion().getTableDesc().getTableName();
        if (!"hbase".equalsIgnoreCase(tableName.getNamespaceAsString()) &&
                !LOG_TABLE.equalsIgnoreCase(tableName.getNameWithNamespaceInclAsString())) {
            long regionId = evn.getRegion().getRegionInfo().getRegionId();
            if ("P".equalsIgnoreCase(p)) {
                log.info("Hbase_RegionObserver.putLog:" + regionId + "... postPut");
            } else if ("D".equalsIgnoreCase(p)) {
                log.info("Hbase_RegionObserver.putLog:" + regionId + "... postDelete");
            }
            String table = tableName.getNameAsString();
            long millis = System.currentTimeMillis();
            String rowkey = table + ":" + p + ":" + (-millis);
            Put logPut = new Put(Bytes.toBytes(rowkey));
            logPut.addColumn(Bytes.toBytes(FAMILY), Bytes.toBytes(COLUMN_NAME), Bytes.toBytes(table));
            logPut.addColumn(Bytes.toBytes(FAMILY), Bytes.toBytes(COLUMN_TYPE), Bytes.toBytes(p));
            logPut.addColumn(Bytes.toBytes(FAMILY), Bytes.toBytes(COLUMN_TIME), Bytes.toBytes(millis));
            Table logTable = null;
            try {
                logTable = connection.getTable(TableName.valueOf(LOG_TABLE));
                logTable.put(logPut);
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    logTable.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}
