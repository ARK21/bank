package models;

import org.apache.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class-thread to transfer money between accounts
 */
public class TransferThread extends Thread {

    /**
     * Instance of Logger, allow write logs
     */
    private static final Logger log = Logger.getLogger(TransferThread.class);
    /**
     * Instance of bank, to get access to bank's methods
     */
    private Bank bank;

    /**
     * Constructor
     * @param name
     * @param bank
     */
    public TransferThread(String name, Bank bank) {
        super(name);
        this.bank = bank;
    }

    /**
     * Works after using start() method
     */
    @Override
    public void run() {
        // repeat till bank transfers. (transactionCounter < 30)
        while (bank.isWork()) {
            try {
                // thread sleeps 1000...2000 ms
                sleep(sleepTime());
            } catch (InterruptedException e) {
               log.error("Application exception", e);
            }
            // generate transfer amount
           bank.transfer();
        }
    }

    /**
     * generate number of ms to sleep
     * @return number of ms
     */
    private int sleepTime() {
        return ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
    }
}
