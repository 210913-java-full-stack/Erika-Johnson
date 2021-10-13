package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * User can deposit from an account
 */

public class MakeADeposit extends View{
    public MakeADeposit(Scanner scanner) {
        super(scanner, "MakeADeposit");
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO acct = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user =  viewManager.getCurrentUser();

        System.out.print("Enter Account id:");
        int accountId = scanner.nextInt();
        System.out.print("Make a deposit:");
        Double balance = scanner.nextDouble();
        acctModel.setAccount_id(accountId);
        acctModel.setBalance(balance);
        acctModel.setAccount_id(user.getUser_id());
        acct.depositAcct(accountId, balance);
        acctModel.deposit(balance);
        viewManager.navigate("ViewBankMenu");
    }

}


