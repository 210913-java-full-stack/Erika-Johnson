package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
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
        AccountDAO acctdao = new AccountDAO(viewManager.getConn());
        AccountModel acctModel = new AccountModel();
        UserModel user = viewManager.getCurrentUser();


        System.out.print("Enter Account #:");
        int accountId = scanner.nextInt();
        System.out.print("Make a withdrawal:");
        Double balance = scanner.nextDouble();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyString = formatter.format(balance);
        acctModel.setAccount_id(accountId);
        acctModel.setBalance(balance);
        acctdao.withdrawAcct(accountId, balance);
        System.out.println("Your withdrawal was successful, you withdrew" + " " + moneyString);

        viewManager.navigate("ViewBankMenu");
    }

}

