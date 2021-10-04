package views;

import DAOs.AccountDAO;
import models.AccountModel;

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
     AccountModel newModel = new AccountModel();

     System.out.print("Please enter savings or checking for type of account:");
     String accountType = scanner.nextLine();
     System.out.println("C");
     newModel.setAccountType(accountType);
     accountdao.save(newModel);
        System.out.println("D");


        viewManager.navigate("ViewBankMenu");
    }
}
