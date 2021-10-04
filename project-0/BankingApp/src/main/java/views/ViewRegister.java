package views;

import DAOs.UserDAO;
import exceptions.BadUserException;
import models.UserModel;
import java.sql.SQLException;
import java.util.Scanner;


/** Line 22 A constructor, and you invoke a constructor with the new keyword the result of this operation
 is a new object ( the results from the constructor) and it gets assigned to the reference we are creating*/


public class ViewRegister extends View{
    public ViewRegister(Scanner scanner) {
        super(scanner, "ViewRegister");
    }


    @Override
    public void renderView() throws SQLException, BadUserException {
        UserDAO userdao = new UserDAO(viewManager.getConn());
        UserModel uModel = new UserModel();

        System.out.print("Create a Username:");
        String username = scanner.nextLine();

        System.out.print("Enter an Email:");
        String email = scanner.nextLine();


        System.out.print("Create a Password");
        String pass = scanner.nextLine();
//        System.out.println("A");


//        System.out.println("B");
        uModel.setUsername(username);

        uModel.setEmail(email);
        uModel.setPassword(pass);
//        System.out.println("C");

        userdao.save(uModel);
        System.out.println(passWordValidate(pass));
        System.out.println("Thank's for joining our bank " + username+ " " + "as a gift we have set " +
                "you with your personal checking account");

        viewManager.navigate("ViewBankMenu");
}

    /**
     * Password validation for registration
     * @param password
     */

    private boolean passWordValidate(String password) {
        if(password.length() > 7) {
            if(checkPassword(password))
            return true;
        }else{
            return false;
        }
        System.out.println("Invalid Password, not long enough");
        return false;
    }

    /**
     * Checks to see if password has at least 1 Uppercase,1 Lowercase & 1 number & 1 special character
     * Will loop through every single character within the string password
     * Assign c to the new character we will be looping through
     * Loop through password checking to see if it meets the requirements
     * If all 3 requirements are met password is valid
     * @param password
     * @return
     */
    private boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;

        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            } else if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            if (hasCap && hasLow && hasNum) {
                return true;
            }
        }
        return false;
    }
}
