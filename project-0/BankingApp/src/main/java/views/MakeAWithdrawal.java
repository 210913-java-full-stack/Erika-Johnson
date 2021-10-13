package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import java.sql.SQLException;
import java.util.Scanner;

public class MakeAWithdrawal extends View{
    public MakeAWithdrawal(Scanner scanner) {
        super(scanner, "MakeAWithdrawal");
    }

    /**
     * User can withdraw from an account
     * @throws SQLException
     */

    @Override
    public void renderView() throws SQLException {
        AccountDAO acct = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user = viewManager.getCurrentUser();


        System.out.print("Enter Account #:");
        int accountId = scanner.nextInt();
        System.out.print("Make a withdrawal:");
        Double balance = scanner.nextDouble();
        acctModel.setAccount_id(accountId);
        acctModel.setAccount_id(user.getUser_id());
        acctModel.setBalance(balance);
        acct.withdrawAcct(accountId, balance);
        acctModel.withdraw(balance);
        viewManager.navigate("ViewBankMenu");
    }

}

