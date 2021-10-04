package models;

public class UserModel {
    private int user_id;
    private String username;
    private String email;
    private String password;

    public UserModel() {

    }

    public UserModel(int user_id, String username, String email, String password) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return this.user_id + " - " + this.username + " - " + this.email;
    }

    /**
     * Password validation for registration
     * @param password
     */
    public void passwordValidate(String password) {
        if(password.length() > 7) {
            if(checkPassword(password))
            System.out.println("Password Valid");
        }else{
            System.out.println("Invalid Password, not long enough");
        }
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
    public boolean checkPassword(String password) {
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

