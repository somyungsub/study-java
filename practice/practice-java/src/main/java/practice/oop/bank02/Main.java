package practice.oop.bank02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        // 1. 고객대기 (여기서는 고객 생성 및 저장)
        while (true) {
            System.out.println("===== 고객 생성 =====");
            Customer customer = createCustomer();
            bank.addCustomer(customer);

            showWork();

            int selectNumber = scanner.nextInt();
            if (selectNumber >= 3 || selectNumber <= 0) {
                System.out.println("업무종료!!");
                scanner.close();
                return;
            }

            // 업무 실행
            BankWorkType type = Arrays.stream(BankWorkType.values())
                                    .filter(bankWorkType -> bankWorkType.ordinal() == (selectNumber - 1))
                                    .findFirst().get();
            customer.setBankWorkType(type);
            bank.executeBankWork(customer);
        }

    }

    private static Customer createCustomer() {
        Customer customer = new Customer();
        System.out.println("**** 이름 입력 -> ");
        customer.setName(scanner.next());
        return customer;
    }

    private static void showWork() {
        System.out.println("===== 업무 선택 =====");
        System.out.println("1. 계좌업무 (예금, 대출, 적금)");
        System.out.println("2. 카드업무 (신용카드, 체크카드)");
        System.out.println("3. 업무종료");
    }

}
