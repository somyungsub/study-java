package chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

  private static final String RESOURCES = "book/실전자바소프트웨어개발/src/main/resources/";
  private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES, "bank-data-simple.csv");
    List<String> lines = Files.readAllLines(path);
    List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
    BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

    collectSummary(bankStatementProcessor);
  }

  private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
    System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
    System.out.println("The total for all transactions January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
    System.out.println("The total for all transactions February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
    System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));
  }

}
