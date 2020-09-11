package chapter_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzerSRP {
  private static final String RESOURCES = "book/실전자바소프트웨어개발/src/main/resources/";

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES, "bank-data-simple.csv");
    List<String> lines = Files.readAllLines(path);
    final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
    List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);

    double totalAmount = calculateTotalAmount(bankTransactions);
    System.out.println("totalAmount = " + totalAmount);

    double januaryAmount = selectInMonth(bankTransactions, Month.JANUARY);
    System.out.println("januaryAmount = " + januaryAmount);

  }

  private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
    return bankTransactions.stream()
      .mapToDouble(BankTransaction::getAmount)
      .sum();
  }

  private static double selectInMonth(List<BankTransaction> bankTransactions, Month month) {
    return bankTransactions.stream()
      .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
      .mapToDouble(BankTransaction::getAmount)
      .sum();
  }
}
