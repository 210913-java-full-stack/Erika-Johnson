package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateBankAccount extends View{
    public CreateBankAccount(Scanner scanner) {
        super(scanner, "CreateBankAccount");
    }
    @Override
    public void renderView() throws SQLException, IOException {
     AccountDAO accountdao = new AccountDAO(viewManager.getConn());
     AccountModel bankNewModel = new AccountModel();
     UserModel user =  viewManager.getCurrentUser();

     System.out.print("Please enter savings or checking for account type:");
     String account_type = scanner.nextLine();
     System.out.println("C");
     bankNewModel.setAccount_type(account_type);
     bankNewModel.setAccount_id(accountdao.newAccountId);
     accountdao.trackAcctId();
     System.out.println("E");
     accountdao.CreateBankAcct(bankNewModel.getAccount_type(), accountdao.newAccountId);
     System.out.println("D");
     System.out.println("Your" + " " + account_type + " " + "has been created");
     System.out.println("The account id assigned to this account is" + " " + accountdao.newAccountId);

     viewManager.navigate("ViewBankMenu");
    }
}
