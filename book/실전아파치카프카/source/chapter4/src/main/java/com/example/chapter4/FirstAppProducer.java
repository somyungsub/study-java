package com.example.chapter4;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class FirstAppProducer {

    private static String topicName = "first-app";

    public static void main(String[] args) {

        // 1. KafkaProducer에 필요한 설정
        Properties conf = new Properties();
        conf.setProperty("bootstrap.servers", "kafka-broker01:9092,kafka-broker02:9092,kafka-broker03:9092");
        conf.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        conf.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 2. Kafka 클러스터에 메시지를 송신(produce)하는 객체를 생성
        Producer<Integer, String> producer = new KafkaProducer<>(conf);

        int key;
        String value;
        for(int i = 1; i <= 100; i++) {
            key = i;
            value = String.valueOf(i);

            // 3. 송신할 메시지를 생성
            ProducerRecord<Integer, String> record = new ProducerRecord<>(topicName, key, value);

            // 4. 메시지를 송신하고, Ack을 받았을 때에 실행할 작업(Callback)을 등록한다
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if( metadata != null) {
                        // 송신에 성공한 경우의 처리
                        String infoString = String.format("Success partition:%d, offset:%d", metadata.partition(), metadata.offset());
                        System.out.println(infoString);
                    } else {
                        // 송신에 실패한 경우의 처리
                        String infoString = String.format("Failed:%s", e.getMessage());
                        System.err.println(infoString);
                    }
                }
            });
        }

        // 5. KafkaProducer를 클로즈하여 종료
        producer.close();
    }
}

