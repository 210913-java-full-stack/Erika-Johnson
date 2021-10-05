package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;

import java.sql.SQLException;
import java.util.Scanner;

public class MakeAWithdrawal extends View{
    public MakeAWithdrawal(Scanner scanner) {
        super(scanner, "MakeAWithdrawal");
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO acctdao = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user =  viewManager.getCurrentUser();

        System.out.println("Enter Account #:");
        int accountId = scanner.nextInt();
        System.out.println("A");
        System.out.print("Make a withdrawal:");
        Double balance = scanner.nextDouble();
        System.out.println("B");
        //if statement
        acctModel.setAccount_id(accountId);
        acctModel.setBalance(balance);
        acctdao.withdrawAcct(int a)
        System.out.println("C");
        ViewManager.getViewManager().getCurrentUser();
        System.out.println("Withdrawal successful" + "you withdrew:" + balance);

        viewManager.navigate("ViewBankMenu");
    }
}
