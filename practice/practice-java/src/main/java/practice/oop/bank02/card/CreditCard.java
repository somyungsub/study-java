package practice.oop.bank02.card;


import practice.oop.bank02.Customer;

public class CreditCard extends Card {

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

    @Override
    public void showMenu() {
        System.out.println("1. 계좌개설");
        System.out.println("2. 해지");
        System.out.println("3. 거래내역");
        System.out.println("4. 대금결제");
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
//            // 대금결제
//        }
//    }

    // 추가업무 대금결제
    @Override
    public void addMenu() {
        System.out.println("대금결제 하기");
    }
}
