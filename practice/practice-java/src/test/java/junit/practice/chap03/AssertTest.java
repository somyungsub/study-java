package junit.practice.chap03;

import org.junit.Before;
import org.junit.Test;

public class AssertTest {

  private Account account;

  @Before
  public void createAccount() {
    account = new Account("an account name");
  }

  @Test(expected=InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch() {
    account.withdraw(100);
  }

  @Test(expected=InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch2() {
    account.deposit(200);
    account.withdraw(100);
  }

}
