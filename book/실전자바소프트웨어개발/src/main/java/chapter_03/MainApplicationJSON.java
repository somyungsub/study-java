package chapter_03;

import java.io.IOException;

public class MainApplicationJSON {

  public static void main(String[] args) throws IOException {
    final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
    final BankStatementParser bankStatementParser = new BankStatementJSONParser();
    final Exporter exporter = new JsonExporter();

    bankStatementAnalyzer.analyze("bank-data-simple.json", bankStatementParser, exporter);
  }
}
