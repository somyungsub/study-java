package com.example.chapter9

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{count, get_json_object, to_timestamp, window}
import org.apache.spark.sql.streaming.{StreamingQuery, Trigger}

import joptsimple.{OptionParser, OptionSet}

class TweetLangTimeWindowCount {

  private val optParser = new OptionParser
  private val bootstrapServersOpt = optParser.accepts("bootstrap-servers").withRequiredArg
  private val checkpointLocOpt = optParser.accepts("checkpoint-location").withRequiredArg
  private val outputModeOpt = optParser.accepts("output-mode").withRequiredArg
  private val triggerDurationOpt = optParser.accepts("trigger").withRequiredArg
  private val watermarkOpt = optParser.accepts("watermark").withRequiredArg
  private val windowWidthOpt = optParser.accepts("window-width").withRequiredArg
  private val slidingDurationOpt = optParser.accepts("sliding-duration").withRequiredArg

  def run(args: Array[String]) {

    val options = optParser.parse(args: _*)
    val spark = SparkSession.builder.appName("TweetLangTimeWindowCount").getOrCreate
    import spark.implicits._

    // Dataset의 생성
    val bootstrapServers = options.valueOf(bootstrapServersOpt)
    val langDS = 
      spark.readStream.format("kafka")
        .option("subscribe", "tweet")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .load()
        .selectExpr("CAST(value AS string) as value")
        .selectExpr("""to_timestamp(
                         get_json_object(value, '$.created_at'), 'EEE MMM d HH:mm:ss Z yyyy') AS created_at""",
                      "get_json_object(value, '$.lang') as lang")


    // 쿼리의 기술
    val watermarkDuration = options.valueOf(watermarkOpt)
    val windowWidth = options.valueOf(windowWidthOpt)
    val slidingDuration = options.valueOf(slidingDurationOpt)
    val groupedDS = 
      langDS
        .withWatermark("created_at", watermarkDuration)
        .groupBy(window($"created_at", windowWidth, slidingDuration), $"lang")
        .agg(count("*") as "count")

    val resultDS = groupedDS.selectExpr(s"CONCAT(window.start, ' - ', window.end, ': lang=', lang, ' count=', count) AS value")

    // Dataset의 내용을 출력
    val checkpointLocation = options.valueOf(checkpointLocOpt)
    val triggerDuration = options.valueOf(triggerDurationOpt)
    val outputMode = options.valueOf(outputModeOpt)
    val streamingQuery = 
      resultDS.writeStream.format("kafka")
        .option("topic", "processed_tweet")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("checkpointLocation", checkpointLocation)
        .trigger(Trigger.ProcessingTime(triggerDuration))
        .outputMode(outputMode)
        .start()

    // JVM 정지 시에 실행되는 클린업 스레드의 셋업
    sys.ShutdownHookThread {
      println("Application stopping...")
      streamingQuery.stop()
      spark.stop()
    }

    // 스트립 처리의 종료를 기다린다
    streamingQuery.awaitTermination()
  }
}

object TweetLangTimeWindowCount {

  def main(args: Array[String]) {
    new TweetLangTimeWindowCount().run(args)
  }
}
