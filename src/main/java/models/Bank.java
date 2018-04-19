package models;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.log4j.Logger;

/**
 * Class transfers money between accounts
 */
public class Bank implements BankBase {

    /**
     * Instance of logger to write logs
     */
    private static final Logger log = Logger.getLogger(Bank.class);
    /**
     * Accounts to transfer
     */
    private Account account1;
    private Account account2;
    /**
     *  amount of one money transaction
     */
    private int amount;
    /**
     * counter of transaction
     */
    private int transactionCounter = 0;
    /**
     * Flag. If transactionCounter < 30, doWork = true. If  transactionCounter == 30, doWork = false.
     */
    private boolean doWork = true;

    /**
     * Constructor with account initialisation
     */
    public Bank() {
        this.account1 = new Account();
        this.account2 = new Account();
    }

    /**
     * Transfer generated amount
     */
    public synchronized void transfer() {
        if (transactionCounter < 30) {
            // number to choose case 1 or case 2.
            int number = generateNumber();
            switch (number) {
                // from account1 to account2
                case 1:
                    // is enough money of account
                    if (isEnoughMoney(account1)) {
                        // transfer money
                        account1.setMoney(account1.getMoney() - amount);
                        account2.setMoney(account2.getMoney() + amount);
                        // increase transactionCounter
                        transactionCounter++;
                        // write results in log file
                        String info = String.format("%d $ was transferred FROM %s TO %s.", amount, account1.getID(), account2.getID());
                        log.info(info);
                        log.info(account1.getID() + ". Money: " + account1.getMoney());
                        log.info(account2.getID() + ". Money: " + account2.getMoney());
                    } else {
                        // throw exception
                        String message = String.format("Transfer error. There is no %s $ on %s", amount, account1.getID());
                        try {
                            throw new TransferException(message);
                        } catch (TransferException e) {
                            // write in error log file
                            log.error(message);
                        }
                    }
                    break;
                case 2:
                    // from account2 to account1
                    if (isEnoughMoney(account2)) {
                        account2.setMoney(account2.getMoney() - amount);
                        account1.setMoney(account1.getMoney() + amount);
                        transactionCounter++;
                        String info = String.format("%d $ was transferred FROM %s TO %s.", amount, account2.getID(), account1.getID());
                        log.info(info);
                        log.info(account1.getID() + ". Money: " + account1.getMoney());
                        log.info(account2.getID() + ". Money: " + account2.getMoney());
                    } else {
                        String message = String.format("Transfer error. There is no %s $ on %s", amount, account1.getID());
                        try {
                            throw new TransferException(message);
                        } catch (TransferException e) {
                            log.error(message);
                        }
                    }
                    break;
            }
        }
        // if transactionCounter became == 30. doWord = false.
        else doWork = false;
    }

    /**
     * Check is enough money for transfer
     * @param out account instance
     * @return true if enough
     */
    public boolean isEnoughMoney(Account out) {
        return out.getMoney() - amount >= 0;
    }

    /**
     * Generate amount to transfer
     */
    public void generateSum() {
        this.amount = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    }

    /**
     * Generate 1 or 2. To choose between two switchers
     * @return number
     */
    private int generateNumber() {
        return ThreadLocalRandom.current().nextInt(1, 2 + 1);
    }

    /**
     * Check if bank works
     * @return
     */
    public boolean isWork() {
        return doWork;
    }

    /**
     * get transactionCounter
     * @return number ot counter
     */
    public int getTransactionCounter() {
        return transactionCounter;
    }
}
