package views;

import DAOs.UserDAO;

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
          System.out.println("Welcome " + user);
    } else {
          System.out.println("Username and password do not match");
        }
        //navigate to main menu
        viewManager.navigate("ViewBankMenu");


    }
}
