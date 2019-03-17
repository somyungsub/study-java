package practice.oop.bank02;

import practice.oop.bank01.Account;
import practice.oop.bank02.card.Card;

import java.util.List;

public class Customer {

    private String name;            // 고객 개인정보 -> 여기서는 간단히 이름만 (식별값)
    private List<Account> accounts; // 소지한 계좌
    private List<Card> cards;       // 소지한 카드
    private BankWorkType bankWorkType;  // 업무타입 설정



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public BankWorkType getBankWorkType() {
        return bankWorkType;
    }

    public void setBankWorkType(BankWorkType bankWorkType) {
        this.bankWorkType = bankWorkType;
    }
}
