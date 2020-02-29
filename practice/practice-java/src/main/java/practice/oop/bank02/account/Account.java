package practice.oop.bank02.account;

import practice.oop.bank02.BankWork;
import practice.oop.bank02.Customer;

public interface Account extends BankWork {

    Account createCard(Customer customer);  // 계좌 개설

    void cancelCard(Customer customer);     // 계좌 해지

    void showTransactionHistory();          // 거래 내역 출력

    void showMenu();                        // 해당 업무 메뉴 출력
}
