package com.c503.kafka.example;

/**
 * 描述: kafka调用执行程序
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-07-28 19:53
 */
public class KafkaConsumerProducer {

    public static void main(String[] args) {

        Producer producerThread = new Producer(KafkaProperties.TOPIC, false);

        producerThread.start();

        //Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);

        //consumerThread.start();

    }
}
