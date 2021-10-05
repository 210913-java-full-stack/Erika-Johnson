package views;
import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
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
//        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        MyArrayList<AccountModel> bankList;
        bankList = acctDao.getUserAccount(user.getUser_id());

        for(int i = 0; i < bankList.size(); i++){
            System.out.println("======Your Bank Accounts======");
            System.out.println(" ");
            System.out.println(bankList.get(i)); //Format currency here
        }
//
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
//        System.out.println(formatter.format(amt));
        viewManager.navigate("ViewBankMenu");
    }
}
