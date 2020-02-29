package practice.oop.bank02;

import practice.oop.bank02.account.DepositAccount;
import practice.oop.bank02.account.LoanAccount;
import practice.oop.bank02.account.SavingAccount;
import practice.oop.bank02.card.CheckCard;
import practice.oop.bank02.card.CreditCard;

public enum BankWorkType {
    BANK_WORK{
        @Override
        public void showMenu() {
            System.out.println("1. 예금");
            System.out.println("2. 대출");
            System.out.println("3. 적금");
        }

        @Override
        public BankWork getBankWork(int selectNumber) {
            if (selectNumber == 1) {
                return new DepositAccount();    // 예금 관련 업무
            } else if (selectNumber == 2) {
                return new LoanAccount();       // 대출 관련 업무
            } else if (selectNumber == 3) {
                return new SavingAccount();     // 적금 관련 업무
            } else {
                return null;
            }
        }
    },

    CARD_WORK{
        @Override
        public void showMenu() {
            System.out.println("1. 신용카드");
            System.out.println("2. 체크카드");
        }

        @Override
        public BankWork getBankWork(int selectNumber) {
            if (selectNumber == 1) {
                return new CreditCard();        // 신용카드 관련 업무
            } else if (selectNumber == 2) {
                return new CheckCard();         // 체크카드 관련 업무
            } else {
                return null;
            }

        }
    }
    ;

    // 업무별 메뉴 보여주기
    abstract public void showMenu();

    // 업무별 하위 클래스 생성(얻기)
    abstract public BankWork getBankWork(int selectNumber);
}
