package practice.oop.bank02.card;


import practice.oop.bank02.Customer;

import java.util.Scanner;

public class CheckCard extends Card {

    @Override
    public Card createCard(Customer customer) {
        return null;
    }

    @Override
    public void cancelCard(Customer customer) {

    }

    @Override
    public void showTransactionHistory() {

    }

//    @Override
//    public void executeBankWork(Customer customer) {
//
//        Scanner scanner = new Scanner(System.in);
//        showMenu();
//        int selectNum = scanner.nextInt();
//        if (selectNum == 1) {
//            Card card = createCard(customer);
//            customer.getCards().add(card);
//        } else if (selectNum == 2) {
//            cancelCard(customer);
//        } else if (selectNum == 3) {
//            showTransactionHistory();
//        } else if (selectNum == 4) {
//            // 출금
//        } else if (selectNum == 5) {
//            // 입금
//        }
//    }

    @Override
    public void showMenu() {
        System.out.println("1. 계좌개설");
        System.out.println("2. 해지");
        System.out.println("3. 거래내역");
        System.out.println("4. 출금");
        System.out.println("5. 입금");
    }


    @Override
    public void addMenu() {
        System.out.println("입금업무");
        System.out.println("출금업무");
    }
}

