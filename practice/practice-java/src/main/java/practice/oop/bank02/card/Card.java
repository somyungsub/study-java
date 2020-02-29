package practice.oop.bank02.card;

import practice.oop.bank02.BankWork;
import practice.oop.bank02.Customer;

import java.util.Scanner;


public abstract class Card implements BankWork {

    abstract public Card createCard(Customer customer); // 카드 개설

    abstract public void cancelCard(Customer customer); // 카드 해지

    abstract public void showTransactionHistory();      // 거래 내역 출력

    abstract public void showMenu();                    // 해당 업무 메뉴 출력

    abstract public void addMenu();                     // 공통외 추가업무들

    @Override
    public void executeBankWork(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        showMenu();
        int selectNum = scanner.nextInt();
        if (selectNum == 1) {
            Card card = createCard(customer);
            customer.getCards().add(card);
        } else if (selectNum == 2) {
            cancelCard(customer);
        } else if (selectNum == 3) {
            showTransactionHistory();
        } else {
            addMenu();
        }
    }
}

