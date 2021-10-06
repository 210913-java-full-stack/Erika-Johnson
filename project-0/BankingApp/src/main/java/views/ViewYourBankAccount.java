package views;
import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


/**
 * Displays all the accounts associated with the current user
 */
public class ViewYourBankAccount extends View{
    public ViewYourBankAccount(Scanner scanner) {
        super(scanner, "ViewYourBankAccount");
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO acctDao = new AccountDAO(viewManager.getConn());
        UserModel user =  viewManager.getCurrentUser();

        MyArrayList<AccountModel> bankList;
        bankList = acctDao.getUserAccount(user.getUser_id());
        System.out.println("======Your Bank Accounts======");
        System.out.println(" ");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        for(int i = 0; i < bankList.size(); i++){
            System.out.println(bankList.get(i).getAccount_id() + " - " + bankList.get(i).getAccount_type() +
                   " " + formatter.format(bankList.get(i).getBalance()));
            System.out.println(" ");

        }



        viewManager.navigate("ViewBankMenu");
    }


}
