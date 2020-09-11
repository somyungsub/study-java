package chapter_03;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class BankStatementProcessor {
  private final List<BankTransaction> bankTransactions;

  public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }


  public double calculateTotalAmount() {
    return bankTransactions.stream()
      .mapToDouble(BankTransaction::getAmount)
      .sum();
  }

  public double calculateTotalInMonth(final Month month) {
    return bankTransactions.stream()
      .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
      .mapToDouble(BankTransaction::getAmount)
      .sum();
  }

  public double calculateTotalForCategory(final String category) {
    return bankTransactions.stream()
      .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
      .mapToDouble(BankTransaction::getAmount)
      .sum();
  }

  public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
    return bankTransactions.stream()
      .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
      .collect(toList());
  }

  public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
    return bankTransactions.stream()
      .filter(bankTransactionFilter::test)
      .collect(toList());
  }

}
