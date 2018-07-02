package com.liumm.hbase.endpiont;

import com.liumm.base.HbaseBase;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.ipc.BlockingRpcCallback;
import org.apache.hadoop.hbase.ipc.ServerRpcController;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 描述: 协处理器终端
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-30 18:18
 */
public class HbaseMultipleRegionClient {

    public void callCoprocessor(Table table, String... parentIds) throws Exception {

        Batch.Call<HbaseProtocol.C503HbaseQueryService, HbaseProtocol.C503HbaseResponse> call =
                new Batch.Call<HbaseProtocol.C503HbaseQueryService, HbaseProtocol.C503HbaseResponse>() {
                    ServerRpcController controller = new ServerRpcController();
                    BlockingRpcCallback<HbaseProtocol.C503HbaseResponse> rpcCallback = new BlockingRpcCallback<>();

                    @Override
                    public HbaseProtocol.C503HbaseResponse call(HbaseProtocol.C503HbaseQueryService c503HbaseQueryService) throws IOException {
                        HbaseProtocol.C503HbaseRequest request = HbaseProtocol.C503HbaseRequest.newBuilder().setJsonStr("JsonStr").build();
                        c503HbaseQueryService.query(controller, request, rpcCallback);
                        return rpcCallback.get();
                    }
                };

        try {
            Map<byte[], HbaseProtocol.C503HbaseResponse> map = table.coprocessorService(
                    HbaseProtocol.C503HbaseQueryService.class, Bytes.toBytes(parentIds[0]), Bytes.toBytes(parentIds[1]), call);
            Collection<HbaseProtocol.C503HbaseResponse> values = map.values();
            for (HbaseProtocol.C503HbaseResponse r : values) {
                System.out.println(r.getResult());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
