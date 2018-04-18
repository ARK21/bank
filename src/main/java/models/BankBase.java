package models;

public interface BankBase {

    public void transfer();

    public boolean valid(Account out);
}
