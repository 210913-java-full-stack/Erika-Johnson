package views;

import DAOs.AccountDAO;
import DAOs.UserDAO;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;

import java.sql.SQLException;
import java.util.Scanner;


public class ViewLogin extends View{

    public ViewLogin(Scanner scanner) {
        super(scanner, "ViewLogin");
    }

    @Override
    public void renderView() throws SQLException {
        UserDAO userdao = new UserDAO(viewManager.getConn());

        System.out.print("Enter Username:");
        String user = scanner.nextLine();
        System.out.print("Enter Password:");
        String password = scanner.nextLine();
        if (userdao.authenticate(user, password)) {
            System.out.println("Welcome " + user );
//            System.out.println( ViewManager.getViewManager().getCurrentUser());
            viewManager.navigate("ViewBankMenu");
    } else {
          System.out.println("Username and Password do not match");
          viewManager.navigate("MainMenu");
        }

    }
}
