package views;

import DAOs.UserDAO;
import exceptions.BadUserException;
import models.UserModel;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**User Registration*/
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
        uModel.setUsername(username);
        uModel.setEmail(email);
        uModel.setPassword(pass);
        userdao.trackUserId();
        userdao.save(uModel);

        if(passWordValidate(pass) && (emailValidate(email) && (usernameValidate(username)))){
            System.out.println("Thanks for joining our bank " + username);
            System.out.println("As a gift we have set you with your own personal checking account");
            System.out.println("You will be redirected to the login screen");

            viewManager.navigate("ViewLogin");
        } else{
            System.out.println("Invalid password and email");
            viewManager.navigate("MainMenu");
        }
}

/**
 * Username validation. In sql a unique constraint is placed on username in users table
 */

private boolean usernameValidate(String username) {
    if(username.length() >= 5){
        return true;
    } else {
        System.out.println("Username must be at least 5 characters and can consist of numbers");
    }
    return false;
}

/**
 * Email Validation for registration
 * emailValidate Method will print true or false and is called on email (Whatever the user inputs)
 * Set emailRegex to the regex pattern to validate email
 * Then create a pattern object and are able to compile the regex
 * We are then able to match the object with the user input to make sure it is meets the requirements/valid
 * matcher.find() validates if the string(email) matches the pattern of the regex
 * Email must contain normal characters before and after the @ symbol and .com
 */

    private boolean emailValidate(String email) {
            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
            Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = emailPat.matcher(email);
            return matcher.find();
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
        System.out.println("Password must be at least 8 characters containing 1 Uppercase, a Lowercase and a number");
        return false;
    }

    /**
     * Checks to see if password has at least 1 Uppercase,1 Lowercase & 1 number
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
