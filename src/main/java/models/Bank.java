package models;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;

public class Bank implements BankBase {

    private static final Logger log = Logger.getLogger(Bank.class);
    private Account account1;
    private Account account2;
    private int amount;
    private int transactionCounter = 0;
    private boolean doWork = true;


    public Bank() {
        this.account1 = new Account();
        this.account2 = new Account();

    }

    public synchronized void transfer() {
        if (transactionCounter < 30) {
            int number = generateNumber();
            switch (number) {
                case 1:
                    if (valid(account1)) {
                        account1.setMoney(account1.getMoney() - amount);
                        account2.setMoney(account2.getMoney() + amount);
                        transactionCounter++;
                        String info = String.format("%d $ was transferred FROM %s TO %s.", amount, account1.getID(), account2.getID());
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
                case 2:
                    if (valid(account2)) {
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
        } else doWork = false;
    }

    public boolean valid(Account out) {
        return out.getMoney() - amount >= 0;
    }

    public void generateSum() {
        this.amount = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    }

    private int generateNumber() {
        return ThreadLocalRandom.current().nextInt(1, 2 + 1);
    }

    public boolean isWork() {
        return doWork;
    }

    public int getTransactionCounter() {
        return transactionCounter;
    }
}
