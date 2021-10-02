package models;

public class CustomerAccountsModel {
    private int junction_id;
    private int user_id;
    private int account_id;

    public CustomerAccountsModel() {
    }

    public CustomerAccountsModel(int junction_id, int user_id, int account_id) {
        this.junction_id = junction_id;
        this.user_id = user_id;
        this.account_id = account_id;
    }

    public int getJunction_id() {
        return junction_id;
    }

    public void setJunction_id(int junction_id) {
        this.junction_id = junction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String toString() {
        return this.user_id + " - " + this.account_id;
    }
}
