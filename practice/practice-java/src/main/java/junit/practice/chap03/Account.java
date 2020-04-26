package junit.practice.chap03;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

  int balance;
  String name;

  Account(String name) {
    this.name = name;
  }

  void deposit(int dollars) {
    balance += dollars;
  }

  void withdraw(int dollars) {
    if (balance < dollars) {
      throw new InsufficientFundsException("balance only " + balance);
    }
    balance -= dollars;
  }

  public boolean hasPositiveBalance() {
    return balance > 0;
  }
}
