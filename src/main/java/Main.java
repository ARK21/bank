import models.Bank;
import models.TransferThread;
import org.apache.log4j.Logger;

/**
 * Class start application
 */
public class Main {

    /**
     * Instance of Logger, allow write logs
     */
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Bank bank = new Bank();
        // initialize 2 thread and start
        TransferThread threadOne = new TransferThread("Thread #1", bank);
        TransferThread threadTwo = new TransferThread("Thread #2", bank);
        threadOne.start();
        threadTwo.start();
        // wait threads stopping and show total count of transaction
        try {
            threadOne.join();
        } catch (InterruptedException e) {
            // if already was interrupted
            log.error("Application exception", e);
        }
        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            // if already was interrupted
            log.error("Application exception", e);
        }
        log.info("The application terminates because total transaction = " + bank.getTransactionCounter());
    }
}
