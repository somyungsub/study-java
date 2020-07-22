package chap04_코드는한번만작성;

public class SavingsAccount extends Account {
    CheckingAccount registeredCounterAccount;

    @Override
    public Transfer makeTransfer(String counterAccount, Money amount)
        throws BusinessException {
        Transfer result = super.makeTransfer(counterAccount, amount);
        if (result.getCounterAccount().equals(this.registeredCounterAccount)) {
            return result;
        } else {
            throw new BusinessException("Counter-account not registered!");
        }
    }
}