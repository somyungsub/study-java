package chap04_코드는한번만작성;

public class Account {
    public Transfer makeTransfer(String counterAccount, Money amount)
        throws BusinessException {
        if (isValid(counterAccount)) {
            CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
            return new Transfer(this, acct, amount);
        } else {
            throw new BusinessException("Invalid account number!");
        }
    }

    private boolean isValid(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            sum = sum + (9 - i) * Character.getNumericValue(number.charAt(i));
        }
        return sum % 11 == 0;
    }
}
