package views;

import DAOs.UserDAO;
import models.UserModel;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewRegister extends View{
    public ViewRegister(Scanner scanner) {
        super(scanner, "ViewRegister");
    }

    @Override
    public void renderView() throws SQLException {
        UserDAO userdao = new UserDAO(viewManager.getConn());
        UserModel uModel = new UserModel();

        System.out.print("Create a Username:");
        String username = scanner.nextLine();

        System.out.print("Enter an Email:");
        String email = scanner.nextLine();

        System.out.print("Create a Password");
        String pass = scanner.nextLine();
        System.out.println("A");


        System.out.println("B");
        uModel.setUsername(username);

        uModel.setEmail(email);
        uModel.setPassword(pass);
        System.out.println("C");

        userdao.save(uModel); //Error occurs here need to create an account first
        System.out.println("User created");

        viewManager.navigate("ViewBankMenu");
}
}
