package chapter_03;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class BankStatementProcessor {
  private final List<BankTransaction> bankTransactions;

  public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }

  public SummaryStatistics summarizeTransactions() {

    final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
      .mapToDouble(BankTransaction::getAmount)
      .summaryStatistics();

    return new SummaryStatistics(doubleSummaryStatistics.getSum(),
      doubleSummaryStatistics.getMax(),
      doubleSummaryStatistics.getMin(),
      doubleSummaryStatistics.getAverage());
  }

  public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
    double result = 0;
    for (final BankTransaction bankTransaction : bankTransactions) {
      result = bankTransactionSummarizer.summarize(result, bankTransaction);
    }
    return result;
  }

  public double calculateTotalInMonth(final Month month) {
    return summarizeTransactions((acc, bankTransaction) ->
      bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
  }

  public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
    return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
  }

  public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
    final List<BankTransaction> result = new ArrayList<>();
    for (final BankTransaction bankTransaction : bankTransactions) {
      if (bankTransactionFilter.test(bankTransaction)) {
        result.add(bankTransaction);
      }
    }
    return result;
  }

//  public double calculateTotalAmount() {
//    return bankTransactions.stream()
//      .mapToDouble(BankTransaction::getAmount)
//      .sum();
//  }

//  public double calculateTotalInMonth(final Month month) {
//    return bankTransactions.stream()
//      .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
//      .mapToDouble(BankTransaction::getAmount)
//      .sum();
//  }

//  public double calculateTotalForCategory(final String category) {
//    return bankTransactions.stream()
//      .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
//      .mapToDouble(BankTransaction::getAmount)
//      .sum();
//  }

//  public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
//    return bankTransactions.stream()
//      .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
//      .collect(toList());
//  }
//
//  public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
//    return bankTransactions.stream()
//      .filter(bankTransactionFilter::test)
//      .collect(toList());
//  }

}
