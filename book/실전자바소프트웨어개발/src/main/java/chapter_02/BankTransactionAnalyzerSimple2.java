package chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple2 {
  private static final String RESOURCES = "book/실전자바소프트웨어개발/src/main/resources/";

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES, "bank-data-simple.csv");
    List<String> lines = Files.readAllLines(path);

    double total = 0;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (String line : lines) {
      String[] columns = line.split(",");
      LocalDate date = LocalDate.parse(columns[0], dateTimeFormatter);
      if (date.getMonth() == Month.JANUARY) {
        double amount = Double.parseDouble(columns[1]);
        total += amount;
      }
    }

    System.out.println("January total = " + total);
  }
}
