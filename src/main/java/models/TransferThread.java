package models;

import org.apache.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class TransferThread extends Thread {

    private static final Logger log = Logger.getLogger(TransferThread.class);
    private Bank bank;

    public TransferThread(String name, Bank bank) {
        super(name);
        this.bank = bank;
    }

    @Override
    public void run() {
        while (bank.isWork()) {
            try {
                sleep(sleepTime());
            } catch (InterruptedException e) {
               log.error("Application exception", e);
            }
            bank.generateSum();
            bank.transfer();
        }
    }

    private int sleepTime() {
        return ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
    }
}
