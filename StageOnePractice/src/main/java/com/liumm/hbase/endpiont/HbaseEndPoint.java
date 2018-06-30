package com.liumm.hbase.endpiont;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.CoprocessorException;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.regionserver.RegionScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 终端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-30 18:24
 */
@Slf4j
public class HbaseEndPoint extends HbaseProtocol.C503HbaseQueryService implements Coprocessor,
        CoprocessorService {

    private RegionCoprocessorEnvironment environment = null;

    @Override
    public void query(RpcController controller, HbaseProtocol.C503HbaseRequest request, RpcCallback<HbaseProtocol.C503HbaseResponse> done) {
        Long rowCount = 0L;
        try {
            rowCount = this.getRowCount(request.getJsonStr());
            String result = String.valueOf(rowCount);
            HbaseProtocol.C503HbaseResponse response = HbaseProtocol.C503HbaseResponse.newBuilder().setResult(result).build();
            done.run(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(CoprocessorEnvironment evn) throws IOException {
        log.info("HbaseEndPoint.start...");
        if (evn instanceof RegionCoprocessorEnvironment) {
            this.environment = (RegionCoprocessorEnvironment) evn;

        } else {
            throw new CoprocessorException("Must be loaded on a table region!");
        }

    }

    @Override
    public void stop(CoprocessorEnvironment coprocessorEnvironment) throws IOException {
        log.info("HbaseEndPoint.stop...");
    }

    /**
     * @param jsonStr
     * @return java.lang.Long
     * @method getRowCount
     * @description 获取条数
     * @date: 18/6/30 18:33
     * @author: liumm
     */
    public Long getRowCount(String jsonStr) throws IOException {
        Long rowCount = 0L;
        Filter filter = new KeyOnlyFilter();
        Scan scan = new Scan();
        scan.setFilter(filter);
        RegionScanner scanner = this.environment.getRegion().getScanner(scan);
        List<Cell> cells = new ArrayList<>();
        boolean hasMore;
        do {
            hasMore = scanner.next(cells);
            if (cells.size() > 0) {
                rowCount += 1;
            }
        }
        while (hasMore);
        return rowCount;
    }

    // rpc服务，返回本身即可，因为此类实例就是一个服务实现
    @Override
    public Service getService() {
        return this;
    }


}
