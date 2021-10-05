package models;

import DAOs.AccountDAO;
import utility.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;

public class AccountModel {
    private int account_id;
    private String account_type;
    private double balance;

    public AccountModel()  {

    }

    public AccountModel(int account_id, String account_type, double balance) {
        this.account_id = account_id;
        this.account_type = account_type;
        this.balance = balance;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
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
        return this.account_id + " - " + this.account_type + " - " + this.balance;
        }


    }
