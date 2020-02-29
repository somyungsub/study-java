package practice.oop.bank01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Bank {
    private List<Account> accounts;
    private static Scanner scanner = new Scanner(System.in);

    // 생성자 초기화
    public Bank() {
        this(new ArrayList<>());
    }

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    // 1. 계좌개설
    public void addAccount() {
        System.out.println("이름과 계좌를 입력해주세요");
        accounts.add(new Account(scanner.next(), scanner.next()));
    }


    // 2. 계좌찾기 - 이름, 번호
    public void searchAccount(BankWorkType bankWorkType) {
        System.out.println("계좌를 찾습니다 - " + bankWorkType);
        Account account = getAccount(bankWorkType);

        System.out.println("세부메뉴를 보겠습니까? (Y/N)");
        if (!"Y".equalsIgnoreCase(scanner.next())) { return; }

        while (true) {
            showDetailMenu();
            int selectNumber = scanner.nextInt();
            if (selectNumber == 5) {
                System.out.println("세부메뉴 업무 종료!!");
                return;
            }
            executeTransaction(account, selectNumber);
        }

    }

    private void executeTransaction(Account account, int selectNumber) {
        if (selectNumber < 1 || selectNumber > 5) {
            System.out.println("잘못 입력했습니다 (유효범위 1~5) - 입력한 숫자는 => " + selectNumber); return;
        }

        TransactionWorkType transactionWorkType = Stream.of(TransactionWorkType.values())
                .filter(type -> type.ordinal() == (selectNumber - 1))
                .findFirst().get();

        switch (transactionWorkType) {
            case DEPOSIT:
                account.deposit();
                break;
            case WITHDRAW:
                account.withdraw();
                break;
            case CHECK_BALANCE:
                account.checkBalance();
                break;
            case TRANSACTION_HISTORY:
                account.showTransactionHistory();
                break;
        }
    }

    private Account getAccount(BankWorkType bankWorkType) {
        Predicate<Account> predicate;
        String next = scanner.next();
        switch (bankWorkType) {
            case SEARCH_NAME:
                predicate = account -> account.getName().equals(next);
                break;
            case SEARCH_ACCOUNT:
                predicate = account -> account.getAccountNo().equals(next);
                break;
            default:
                return new Account();
        }
        return accounts.stream().filter(predicate).findFirst().get();
    }

    private void showDetailMenu() {
        System.out.println("=============MENU=============");
        System.out.println("==\t\t1. 입금 \t\t==");
        System.out.println("==\t\t2. 출금 \t\t==");
        System.out.println("==\t\t3. 잔액확인 \t\t==");
        System.out.println("==\t\t4. 거래내역 \t\t==");
        System.out.println("==\t\t5. 나가기 \t\t==");
        System.out.println("==============================");
    }

    // 4. 계좌목록
    public void showAccountList() {
        System.out.println("============== ACCOUNT LIST ==============");
        System.out.println("No.\t\t Name\t\t AC no.\t\t Bal");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + " " + account.getName() + "\t\t\t" + account.getAccountNo() + "\t\t" + account.getBalance());
        }
    }
}
