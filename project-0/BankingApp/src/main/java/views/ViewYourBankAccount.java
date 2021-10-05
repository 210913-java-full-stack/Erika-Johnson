package views;
import DAOs.AccountDAO;
import DAOs.UserDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;



public class ViewYourBankAccount extends View{
    public ViewYourBankAccount(Scanner scanner) {
        super(scanner, "ViewYourBankAccount");
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO acctDao = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user =  viewManager.getCurrentUser();

        MyArrayList<AccountModel> bankList;
        bankList = acctDao.getUserAccount(user.getUser_id());

        for(int i = 0; i < bankList.size(); i++){
            System.out.println("======Your Bank Accounts======");
            System.out.println(" ");
            System.out.println(bankList.get(i));
        }
        viewManager.navigate("ViewBankMenu");
    }
}
