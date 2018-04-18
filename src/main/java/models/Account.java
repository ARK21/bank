package models;

import java.util.concurrent.ThreadLocalRandom;

public class Account {

    private final String ID;
    private int money;


    public Account() {
        this.ID = generateID();
        this.money = 10000;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getID() {
        return ID;
    }


    private String generateID() {
        return "Account #" +  ThreadLocalRandom.current().nextInt( 1, 100 + 1);
    }
}
