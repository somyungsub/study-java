package practice.oop.bank01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transacitonDate; // 거래일자
    private String transactionTime; // 거래시간
    private int money;              // 거래금액
    private String transType;       // 입금,출금
    private final String bankName = "TSIS 은행";

    public Transaction(int money, String transType) {
        LocalDateTime time = LocalDateTime.now();
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String format2 = time.format(DateTimeFormatter.ofPattern("HH시 mm분 ss초"));

        this.transacitonDate = format;
        this.transactionTime = format2;
        this.money = money;
        this.transType = transType;
    }

    public String getTransacitonDate() {
        return transacitonDate;
    }

    public void setTransacitonDate(String transacitonDate) {
        this.transacitonDate = transacitonDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getBankName() {
        return bankName;
    }
}
