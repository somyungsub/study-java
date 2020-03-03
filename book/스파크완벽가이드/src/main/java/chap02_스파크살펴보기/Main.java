package chap02_스파크살펴보기;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {

  public static void main(String[] args) {
    final SparkSession spark
        = SparkSession.builder()
        .master("local[*]")
        .appName("Chap2 AgentleInforductionToSpark")
        .getOrCreate();

    final Dataset<Row> myRange = spark.range(1000).toDF("number");
    myRange.show();

    final Dataset<Row> divisBy2 = myRange.where("number % 2 =0");
    divisBy2.show();

    spark.read()
        .option("inferSchema", "true")
        .option("header", "true")
        .csv("book/스파크완벽가이드/Spark-The-Definitive-Guide/data/flight-data/csv/2015-summary.csv")
        .show();




  }
}
