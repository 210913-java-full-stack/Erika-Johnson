
import exceptions.BadUserException;
import utility.ViewManager;

import java.io.IOException;
import java.sql.*;


public class Driver {
    public static void main(String[] args){

        //viewManager
        ViewManager viewManager = ViewManager.getViewManager();

        /**invoke render method on every loop (goToNextView). This is the main loop, it keeps
         * running until something sets the viewManager "running" flag = false.
         * With every loop invoked, the viewManager singleton's goToNextView() method
         */
        viewManager.navigate("MainMenu");
        while (viewManager.isRunning()) {
            try {
                viewManager.goToNextView();
            } catch (SQLException | BadUserException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

