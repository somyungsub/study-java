package io.ssosso;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class FirstAppProducer {
  private static String topicName = "first-app";

  public static void main(String[] args) {
    // 1. KafkaProducer에 필요한 설정
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "kafka:9092");  // 브로커로 보낼 <호스트명:포트번호>
    properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
    properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    // 2. 카프카 클러스터에 메시지를 송신(produce)하는 객체 생성
    Producer<Integer, String> producer = new KafkaProducer<>(properties);

    int key;
    String value;

    for (int i = 1; i <= 100; i++) {
      key = i;
      value = String.valueOf(i);

      // 3. 송신 메시지 생성
      ProducerRecord<Integer, String> record = new ProducerRecord<>(topicName, key, value);

      // 4. 메시지 송신 후 Ack 받았을 때, 실행할 작업 (CallBack메서드) 등록
      producer.send(record, (recordMetadata, e) -> {
        if (recordMetadata != null) {
          // 송신에 성공한 경우 처리
          String infoString = String.format("Success partition:%d, offset:%d", recordMetadata.partition(), recordMetadata.offset());
          System.out.println("infoString = " + infoString);
        } else {
          System.err.println(String.format("Fail : %s", e.getMessage()));
        }
      });
    }
    // 5. KafkaProducer 닫고 종료
    producer.close();
  }
}
