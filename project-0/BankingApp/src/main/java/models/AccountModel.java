package models;

import DAOs.AccountDAO;
import utility.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;

public class AccountModel {
    private int account_id;
    private String accountType;
    private double balance;
    private AccountDAO AccountDao;

    public AccountModel() throws SQLException, IOException {
        this.AccountDao= new AccountDAO(ConnectionManager.getConnection());
    }

    public AccountModel(int account_id, String accountType, double balance) {
        this.account_id = account_id;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return this.account_id + " - " + this.accountType + " - " + this.balance;
        }


    }
