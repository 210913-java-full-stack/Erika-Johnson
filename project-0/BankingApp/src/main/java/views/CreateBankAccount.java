package views;

import DAOs.AccountDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateBankAccount extends View{
    public CreateBankAccount(Scanner scanner) {
        super(scanner, "CreateBankAccount");
    }
    @Override
    public void renderView() throws SQLException {
     AccountDAO accountdao = new AccountDAO(viewManager.getConn());

     System.out.print("Please enter in savings or checking for type of account");
     String accountType = scanner.nextLine();
     System.out.print("Please deposit a balance, if not just put in 0");
     Double balance = scanner.nextDouble();
     if(balance < 5){
         System.out.println("Must deposit at least $5");
     } else {
         System.out.println("Banking account & deposit successful!");
     }


        viewManager.navigate("ViewBankMenu");
    }
}
