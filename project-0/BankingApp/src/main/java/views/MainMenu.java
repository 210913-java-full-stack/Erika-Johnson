package views;

import java.util.Scanner;

/**
 * When the app starts the user will be brought to this menu
 */

public class MainMenu extends View{
    public MainMenu(Scanner scanner) {
        super(scanner, "MainMenu"); //super is the parent class
    }

    /**
     * These options prompt the user for input, accept the input and navigate to the next view
     */

    @Override
    public void renderView() {
        System.out.println("=====Welcome to Union Bank=====\n Please Select from the Menu:\n\n1)Login\n2)Register for an account\n3)Logout");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                    viewManager.navigate("ViewLogin");

                return;
            case "2":
                    viewManager.navigate("ViewRegister");
                return;

            case "3":
                    viewManager.setRunning(false);

        }
    }
}
