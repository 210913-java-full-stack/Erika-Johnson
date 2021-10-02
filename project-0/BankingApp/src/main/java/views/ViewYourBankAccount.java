package views;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewYourBankAccount extends View{
    public ViewYourBankAccount(Scanner scanner) {
        super(scanner, "ViewYourBankAccount");
    }

    @Override
    public void renderView() throws SQLException {


        viewManager.navigate("ViewBankMenu");
    }
}