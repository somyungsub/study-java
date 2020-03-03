package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.processor.TimestampExtractor;
 
public class StreamingExample2 {
  public static void main(final String[] args) throws Exception {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "streaming-example-2");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    Deserializer<JsonNode> jsonDeserializer = new JsonDeserializer();
    Serializer<JsonNode> jsonSerializer = new JsonSerializer();
    Serde<JsonNode> jsonSerde = Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    ObjectMapper mapper = new ObjectMapper();

    StreamsBuilder builder = new StreamsBuilder();
    KStream<String, JsonNode> metrics =
        builder.stream("kafka.metrics",
                       Consumed.with(Serdes.String(), jsonSerde)
                               .withTimestampExtractor(new MetricsTimeExtractor()));
    metrics.filter((host, root) -> root.has("value") && root.has("hostname") && root.has("timestamp"))
           .flatMapValues(wrap(root -> {
               ObjectNode newroot = mapper.createObjectNode();
               newroot.put("hostname", root.get("hostname"));
               newroot.put("timestamp", root.get("timestamp"));
               newroot.put("BytesIn",
                           root.get("value")
                               .get("kafka.server:name=BytesInPerSec,type=BrokerTopicMetrics")
                               .get("Count"));
               return newroot;
             }))
           .groupByKey()
           .windowedBy(TimeWindows.of(70000).advanceBy(10000))
           .aggregate(
             () -> mapper.createObjectNode(),
             (k, v, agg) -> {
                 ObjectNode o = (ObjectNode)agg;
                 if (!agg.has("timestamp_0")) o.put("timestamp_0", v.get("timestamp").asLong());
                 if (!agg.has("first")) o.put("first", v.get("BytesIn").asLong());
                 o.put("last", v.get("BytesIn").asLong());
                 o.put("hostname", k);
                 o.put("timestamp", v.get("timestamp").asLong());
                 o.put("width", o.get("timestamp").asLong() - o.get("timestamp_0").asLong());
                 return o;
               },
             Materialized.with(Serdes.String(), jsonSerde))
           .toStream()
           .filter((k, v) -> v.get("width").asLong() >= 60)
           .selectKey((k, v) -> k.key())
           .flatMapValues(wrap((agg -> {
               ObjectNode o = (ObjectNode)agg;
               o.put("BytesIn_avg_1m",
                     (o.get("last").asLong() - o.get("first").asLong()) / o.get("width").asLong());
               o.remove("timestamp_0");
               return mapper.writeValueAsString(o);
             })))
           .to("kafka.metrics.example2",
               Produced.with(Serdes.String(), Serdes.String()));

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();
  }

  public static class MetricsTimeExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {
      JsonNode root = (JsonNode) record.value();
      if (root != null && root.has("timestamp")) {
        return root.get("timestamp").asLong() * 1000L;
      }
      else {
        return previousTimestamp;
      }
    }
  }

  @FunctionalInterface
  private interface FunctionWithException<T, R, E extends Exception> {
    R apply(T t) throws E;
  }

  private static <V, VR, E extends Exception>
      ValueMapper<V, Iterable<VR>> wrap(FunctionWithException<V, VR, E> f) {
    return new ValueMapper<V, Iterable<VR>>() {
      public Iterable<VR> apply(V v) {
        try {
          return Arrays.asList(f.apply(v));
        } catch (Exception e) {
          e.printStackTrace();
          return Arrays.asList();
        }
      }
    };
  }
}
