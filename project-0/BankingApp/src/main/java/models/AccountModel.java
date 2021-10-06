package models;

import java.text.NumberFormat;
import java.util.Locale;

public class AccountModel {
    private int account_id;
    private String account_type;
    private double balance;

    public AccountModel() {

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

    public void withdraw(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyString = formatter.format(balance);
        double checkBalance = balance;
        if (amount > 0) {
            checkBalance -= amount;
            if (checkBalance >= 0) {
                balance -= amount;
                System.out.println("Your withdraw was successful" + " " + "you withdrew" + " " +  moneyString);
            }
        } else {
            System.out.println("Invalid amount, please enter a positive number");
        }
    }

    public void deposit(double amount){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyString = formatter.format(balance);
        if(amount > 0){
            balance += amount;
            System.out.println("Your deposit was successful" + " " + "you deposited" + " " +  moneyString);
        }else{
            System.out.println("Invalid amount, please enter a positive number");
        }

    }

    public String toString() {
        return this.account_id + " - " + this.account_type + " - " + this.balance;
        }


    }
