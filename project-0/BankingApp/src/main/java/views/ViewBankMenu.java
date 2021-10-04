package views;

import java.util.Scanner;

public class ViewBankMenu extends View{
    public ViewBankMenu(Scanner scanner) {
        super(scanner, "ViewBankMenu");
    }

    /**
     * Write I/O here, also the navigation based on processing input
     */

    @Override
    public void renderView() {
        System.out.println("=====Welcome to Union Bank=====\n Please Enter Selection:\n\n1)Create a Bank Account\n2)Make a deposit" +
                "\n3)Make a withdrawal\n4)View your bank account\n5)Logout");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                viewManager.navigate("CreateBankAccount");

                return;
            case "2":
                viewManager.navigate("MakeADeposit");
                return;

            case "3":
                viewManager.navigate("MakeAWithdrawal");
                return;


            case "4":
                viewManager.navigate("ViewYourBankAccount");
                return;

            case "5":
                viewManager.setRunning(false);

        }

    }
}
