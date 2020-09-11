package chapter_02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementParserTest {

  private  BankStatementParser statementParser;

  @BeforeEach
  public void setUp() {
    statementParser = new BankStatementCSVParser();
  }

  @Test
  @DisplayName("CSV 파싱")
  public void CSV가_제대로_파싱하는지_확인한다() {
    final String line = "30-01-2017,-50,Tesco";
    final BankTransaction result = statementParser.parseFrom(line);
    final BankTransaction expected
      = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");

    final double tolerance = 0.0d;

    assertEquals(expected.getDate(), result.getDate());
    assertEquals(expected.getAmount(), result.getAmount(), tolerance);
    assertEquals(expected.getDescription(), result.getDescription());
  }

}