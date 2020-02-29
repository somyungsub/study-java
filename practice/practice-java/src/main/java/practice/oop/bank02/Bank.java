package practice.oop.bank02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank implements BankWork{

    private List<Customer> customers;


    public Bank() {
        customers = new ArrayList<>();
    }

    // 업무 실행
    @Override
    public void executeBankWork(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        // 데이터에서 고객 찾기
        Customer customer2 = customers.stream()
                .filter(customer1 -> customer1.hashCode() == customer.hashCode())
                .findFirst().get();

        // 작업타입 얻기
        BankWorkType bankWorkType = customer2.getBankWorkType();

        // 타입에 따른 메뉴 보여주기
        bankWorkType.showMenu();

        // 메뉴에 따른 업무처리
        bankWorkType.getBankWork(scanner.nextInt())
                    .executeBankWork(customer);
    }

    // 고객생성 및 저장
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
