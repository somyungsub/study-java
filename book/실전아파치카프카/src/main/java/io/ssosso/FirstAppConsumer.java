package io.ssosso;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class FirstAppConsumer {

  private static String topicName = "first-app";

  public static void main(String[] args) {
    // 1. KafkaConsumer 필요한 설정
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "kafka:9092");
    properties.setProperty("group.id", "FirstAppConsumerGroup");
    properties.setProperty("enable.auto.commit", "false");
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    // 2. 카프카 클러스터에서 메시지를 수신 하는 객체 생성
    Consumer<Integer, String> consumer = new KafkaConsumer<>(properties);

    // 3. 구독(subscribe) 하는 Topic 등록
    consumer.subscribe(Collections.singletonList(topicName));

    for (int i = 0; i < 300; i++) {

      // 4. 메시지를 수신하여 콘솔에 표시
      ConsumerRecords<Integer, String> records = consumer.poll(1); //  1개씩 땡겨감
      for (ConsumerRecord<Integer, String> record : records) {
        String msgString = String.format("key : %d, value: %s", record.key(), record.value());
        System.out.println(msgString);

        // 5. 처리가 완료된 메시지의 오프셋을 커밋
        TopicPartition tp = new TopicPartition(record.topic(), record.partition());
        OffsetAndMetadata metadata = new OffsetAndMetadata(record.offset() + 1);
        final Map<TopicPartition, OffsetAndMetadata> commitInfo = Collections.singletonMap(tp, metadata);
        consumer.commitSync(commitInfo);
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // 6. KafkaConsumer를 닫고 종
    consumer.close();
  }
}
