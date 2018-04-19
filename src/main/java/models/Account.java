package models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class contains account information
 */
public class Account {

    /**
     * account ID
     */
    private final String ID;
    /**
     * account money
     */
    private int money;

    /**
     * Constructor
     */
    public Account() {
        this.ID = generateID();
        this.money = 10000;
    }

    /**
     * get money
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * set money
     * @param money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * get account ID
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Generate account ID
     * @return generated string
     */
    private String generateID() {
        return "Account #" +  ThreadLocalRandom.current().nextInt( 1, 100 + 1);
    }
}
