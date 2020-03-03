package com.example.chapter10;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.KeyValue;

public class KafkaEnrichmentSample {

    private static String sensordataTopicName = "mqtt-source";
    private static String masterdataTopicName = "device-master";
    private static String enrichedTopicName = "mqtt-enriched";

    public static void main(final String[] args) throws Exception {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-enrichment");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker01:9092,kafka-broker02:9092,kafka-broker03:9092");

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> mqttSource = builder.stream(sensordataTopicName,
                Consumed.with(Serdes.String(), Serdes.String()));
        GlobalKTable<String, String> deviceMasterTable = builder.globalTable(masterdataTopicName,
                Consumed.with(Serdes.String(), Serdes.String()));

        mqttSource
                .map((key, value) -> KeyValue.pair(value.split(",")[0], value))
                .leftJoin(deviceMasterTable,
                        (sensorKey,sensorValue) -> sensorKey,
                        (sensorValue,masterValue) -> SensorDataJoiner(sensorValue,masterValue))
                .to(enrichedTopicName,
                        Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }

    private static String SensorDataJoiner(String sensorValue, String masterValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(sensorValue.split(",",2)[1]);
        sb.append(",");
        sb.append(masterValue);
        return new String(sb);
    }
}
