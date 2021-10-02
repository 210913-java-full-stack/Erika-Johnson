package views;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateBankAccount extends View{
    public CreateBankAccount(Scanner scanner) {
        super(scanner, "CreateBankAccount");

    }

    @Override
    public void renderView() throws SQLException {


        viewManager.navigate("ViewBankMenu");
    }
}
