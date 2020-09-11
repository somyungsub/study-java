package chapter_03;

import java.io.IOException;

public class MainApplication {

  public static void main(String[] args) throws IOException {
    final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
    BankStatementParser bankStatementParser = new BankStatementCSVParser();
    bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);
  }

}
