package com.c503.kafka.example;

/**
 * 描述: kafka配置参数
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-07-28 19:52
 */
public class KafkaProperties {

    public static final String TOPIC = "i57_ndsa_result";

    public static final String KAFKA_SERVER_URL = "hdp01";

    public static final int KAFKA_SERVER_PORT = 9092;

    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;

    public static final int CONNECTION_TIMEOUT = 100000;

    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties() {
    }
}
