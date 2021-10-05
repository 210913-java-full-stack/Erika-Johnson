package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MakeADeposit extends View{
    public MakeADeposit(Scanner scanner) {
        super(scanner, "MakeADeposit");
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO acctdao = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user =  viewManager.getCurrentUser();

        System.out.print("Enter Account #:");
        int accountId = scanner.nextInt();
        System.out.println("A");
        System.out.print("Make a deposit:");
        Double balance = scanner.nextDouble();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyString = formatter.format(balance);
        System.out.println("B");
        //if statement
        acctModel.setAccount_id(accountId);
        acctModel.setBalance(balance);
        acctdao.depositAcct(accountId, balance);
        System.out.println("C");

        System.out.println("Your deposit was successful" + " " + "you deposited" + " " +  moneyString);


        viewManager.navigate("ViewBankMenu");
    }
}
