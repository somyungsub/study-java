package chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple2 {
  private static final String RESOURCES = "src/main/resources/bank-data-simple.csv";

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES, args[0]);
    List<String> lines = Files.readAllLines(path);

    double total = 0;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (String line : lines) {
      String[] columns = line.split(",");
      LocalDateTime date = LocalDateTime.parse(columns[0], dateTimeFormatter);
      if (date.getMonth() == Month.JANUARY) {
        double amount = Double.parseDouble(columns[1]);
        total += amount;
      }
    }

    System.out.println("January total = " + total);
  }
}
