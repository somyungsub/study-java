package practice.oop.bank01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String name;
    private String accountNo;
    private int balance;
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String name, String accountNo) {
        this.name = name;
        this.accountNo = accountNo;
        this.transactions = new ArrayList<>();
    }

    // 1. 입금
    public void deposit() {
        System.out.println(name + "고객님의 계좌 : " + accountNo + "에 입금시킬 금액 입력 : ");
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        if (money < 0) {
            System.out.println("입금액은 음수일 수 없습니다"); return;
        }
        balance += money;
        System.out.println("계좌에 " + money + "원이 입금되었습니다.");

        transactions.add(new Transaction(money, "입금"));
    }

    // 2. 출금
    public void withdraw() {
        System.out.println(name + "고객님의 계좌 : " + accountNo + "에 출금시킬 금액 입력 : ");
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        if (balance - money < 0) {
            System.out.println("잔액이 부족하여 출금할 수 없습니다"); return;
        }
        balance -= money;
        System.out.println("계좌에서 " + money + "원이 출금되었습니다.");

        transactions.add(new Transaction(money, "출금"));
    }

    // 3. 잔액확인
    public void checkBalance() {
        System.out.println("고객님의 계좌의 잔액은 " + balance + "원 입니다.");
    }

    // 4. 거래내역
    public void showTransactionHistory() {
        StringBuffer sb = new StringBuffer();
        transactions.stream().forEach(transaction -> {
            sb.append("은행 : ").append(transaction.getBankName()).append("\t")
              .append("업무 : ").append(transaction.getTransType())
              .append("(").append(transaction.getMoney()).append("원)\t")
              .append("거래일자 : ").append(transaction.getTransacitonDate()).append("\t")
              .append("거래시간 : ").append(transaction.getTransactionTime()).append(System.lineSeparator());

        });
        System.out.println(sb.toString());
    }

    public String getName() {
        return name;
    }


    public String getAccountNo() {
        return accountNo;
    }


    public int getBalance() {
        return balance;
    }
}
