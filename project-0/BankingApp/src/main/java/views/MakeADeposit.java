package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;

import java.sql.SQLException;
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
        System.out.println("B");
        //if statement
        acctModel.setAccount_id(accountId);
        acctModel.setBalance(balance);
        acctdao.depositAcct(accountId, balance);
        System.out.println("C");

        System.out.println("Your deposit was successful" + " " + "you deposited" + " " +  balance);


        viewManager.navigate("ViewBankMenu");
    }
}
