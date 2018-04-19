package models;

/**
 * Base interface for Bank
 */
public interface BankBase {

    public void transfer();

    public boolean isEnoughMoney(Account out);
}

