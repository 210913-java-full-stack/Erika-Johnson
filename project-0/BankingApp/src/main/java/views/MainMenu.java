package views;

import models.TestModel;
import models.UserModel;

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
        //Write I/O here, also the navigation based on processing input
        System.out.println("=====Welcome to Union Bank=====\n Please Select from the Menu:\n\n1)Login\n2)Not a member? Register\n3)Logout");
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
