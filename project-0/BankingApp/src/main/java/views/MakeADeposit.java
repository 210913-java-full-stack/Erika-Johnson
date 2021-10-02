package views;

import java.sql.SQLException;
import java.util.Scanner;

public class MakeADeposit extends View{
    public MakeADeposit(Scanner scanner) {
        super(scanner, "MakeADeposit");
    }

    @Override
    public void renderView() throws SQLException {


        viewManager.navigate("ViewBankMenu");
    }
}
