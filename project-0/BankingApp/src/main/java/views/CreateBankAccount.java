package views;

import DAOs.AccountDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;
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

     System.out.print("Please enter savings or checking for type of account:");
     String account_type = scanner.nextLine();
     System.out.println("C");
     bankNewModel.setAccount_type(account_type);
     bankNewModel.setBalance(0);
     accountdao.CreateBankAcct(bankNewModel.getAccount_type(), user.getUser_id());
     System.out.println("D");
     System.out.println("Your account has been created");

     viewManager.navigate("ViewBankMenu");
    }
}
