import models.Bank;
import models.TransferThread;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Bank bank = new Bank();
        TransferThread threadOne = new TransferThread("Thread #1", bank);
        TransferThread threadTwo = new TransferThread("Thread #2", bank);
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            log.error("Application exception" , e);
        }
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            log.error("Application exception" , e);
        }
        log.info("Total transaction: " + bank.getTransactionCounter());
    }
}
