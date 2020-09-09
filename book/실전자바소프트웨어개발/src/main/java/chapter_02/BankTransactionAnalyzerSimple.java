package chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {

  private static final String RESOURCES = "book/실전자바소프트웨어개발/src/main/resources/";

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES, "bank-data-simple.csv");
    List<String> lines = Files.readAllLines(path);

    double total = 0;
    for (String line : lines) {
      String[] columns = line.split(",");
      double amount = Double.parseDouble(columns[1]);
      total += amount;
    }

    System.out.println("total = " + total);
  }
}
