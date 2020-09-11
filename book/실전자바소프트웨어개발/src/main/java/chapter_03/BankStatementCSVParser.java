package chapter_03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BankStatementCSVParser implements BankStatementParser {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  private BankTransaction parseFromCSV(final String line) {
    final String[] columns = line.split(",");

    final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
    final double amount = Double.parseDouble(columns[1]);
    final String description = columns[2];

    return new BankTransaction(date, amount, description);
  }

  public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
    return lines.stream()
      .map(this::parseFromCSV)
      .collect(toList());
  }

  @Override
  public BankTransaction parseFrom(String line) {
    return parseFromCSV(line);
  }

  @Override
  public List<BankTransaction> parseLinesFrom(List<String> lines) {
    return parseLinesFromCSV(lines);
  }
}
