package views;

import DAOs.UserDAO;
import models.UserModel;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewYourBankAccount extends View{
    public ViewYourBankAccount(Scanner scanner) {
        super(scanner, "ViewYourBankAccount");
    }

    @Override
    public void renderView() throws SQLException {
        UserDAO newModel = new UserDAO(viewManager.getConn());
        UserModel userInfo = new UserModel();


        viewManager.navigate("ViewBankMenu");
    }
}
