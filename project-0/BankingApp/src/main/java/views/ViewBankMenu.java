package views;

import java.util.Scanner;

public class ViewBankMenu extends View{
    public ViewBankMenu(Scanner scanner) {
        super(scanner, "ViewBankMenu");
    }


    @Override
    public void renderView() {
        //Write I/O here, also the navigation based on processing input
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

//       MyArrayInterface<String> BankMenu = new MyArrayList<>();
//     BankMenu.add("1)Create an account");
//     BankMenu.add("2)Make a deposit");
//     BankMenu.add("3)Make a withdrawal");
//     BankMenu.add("4)View your accounts");
//     BankMenu.add("5)Logout");
//     for (int i = 0; i < BankMenu.size(); i++) {
//     System.out.println(BankMenu.get(i));
//
    }
}
