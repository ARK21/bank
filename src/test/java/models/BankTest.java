package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class check if methods works correct
 */
public class BankTest {

    @Test
    public void isTransferWorksCorrect() {
        Bank bank = new Bank();
        bank.transfer();
        // check if account money get more or less on generated sum
        assertTrue(bank.getAccount1().getMoney() == 10000 + bank.getAmount() ||
                bank.getAccount2().getMoney() == 10000 + bank.getAmount());
        assertTrue(bank.getAccount1().getMoney() == 10000 - bank.getAmount() ||
                bank.getAccount2().getMoney() == 10000 - bank.getAmount());
    }

}