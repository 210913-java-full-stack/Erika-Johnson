package views;

import java.sql.SQLException;
import java.util.Scanner;

public class MakeAWithdrawal extends View{
    public MakeAWithdrawal(Scanner scanner) {
        super(scanner, "MakeAWithdrawal");
    }

    @Override
    public void renderView() throws SQLException {
        System.out.print("Make a withdrawal:");

        System.out.println("Withdrawal successful");

        viewManager.navigate("ViewBankMenu");
    }
}
