package oop.bank01;

import java.util.Scanner;
import java.util.stream.Stream;

public class BankTest {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();
    public static void main(String[] args) {
        while (true) {
            showMenu();
            int selectNumber = scanner.nextInt();
            if (selectNumber == 5) {
                System.out.println("==\t\t 은행업무 종료 \t\t==");
                scanner.close();
                return;
            }
            executeBankWork(selectNumber);
        }

    }
    private static void showMenu() {
        System.out.println("안녕하세요. TSIS 은행입니다. 무엇을 도와드릴까요?");
        System.out.println("==\t\t 1. 새 계좌 개설 \t\t\t==");
        System.out.println("==\t\t 2. 계좌찾기(고객명) \t\t==");
        System.out.println("==\t\t 3. 계좌 찾기(계좌번호) \t\t==");
        System.out.println("==\t\t 4. 계좌목록 \t\t\t\t==");
        System.out.println("==\t\t 5. 나가기 \t\t\t\t==");
    }

    private static void executeBankWork(int selectNumber) {
        if (selectNumber < 1 || selectNumber > 5) {
            System.out.println("잘못 입력했습니다 (유효범위 1~5) - 입력한 숫자는 => " + selectNumber);
            return;
        }

        BankWorkType bankWorkType = Stream.of(BankWorkType.values())
                .filter(bank -> bank.ordinal() == (selectNumber - 1))
                .findFirst().get();

        switch (bankWorkType) {
            case ADD_ACCOUNT:
                bank.addAccount();
                break;
            case SEARCH_NAME:
                bank.searchAccount(bankWorkType);
                break;
            case SEARCH_ACCOUNT:
                bank.searchAccount(bankWorkType);
                break;
            case LIST_ACCOUNT:
                bank.showAccountList();
                break;
        }
    }
}
