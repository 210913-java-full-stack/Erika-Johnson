package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Logged-in User is able to create multiple bank accounts
 */

public class CreateBankAccount extends View{
    public CreateBankAccount(Scanner scanner) {
        super(scanner, "CreateBankAccount");
    }
    @Override
    public void renderView() throws SQLException, IOException {
     AccountDAO accountdao = new AccountDAO(viewManager.getConn());
     AccountModel bankNewModel = new AccountModel();
     UserModel user =  viewManager.getCurrentUser();

     System.out.print("Please enter savings or checking for account type(case sensitive):");
     String account_type = scanner.nextLine();
     if(account_type.equals("checking") || (account_type.equals("savings"))) {
         System.out.println(" ");
         System.out.println("Thank you for choosing an account");
         bankNewModel.setAccount_type(account_type);
         bankNewModel.setAccount_id(accountdao.newAccountId);
         accountdao.trackAcctId();
         accountdao.CreateBankAcct(bankNewModel.getAccount_type(), user.getUser_id());
         System.out.println("Your" + " " + account_type + " " + "has been created");
         System.out.println("The account id assigned to this account is" + " " + accountdao.newAccountId);
         System.out.println(" ");
         viewManager.navigate("ViewBankMenu");
     } else {
         System.out.println("Invalid Input");
         viewManager.navigate("CreateBankAccount");
        }
    }
}
