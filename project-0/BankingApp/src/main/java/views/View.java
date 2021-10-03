package views;

import exceptions.BadUserException;
import utility.ViewManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/** Abstract class can not be instantiated and can only be inherited similar to an interface.
 * This is a framework for a menu is a prompt of output. Accepts the input and from the view, invoke the methods to act on the input
 * */
public abstract class View {
    protected Scanner scanner;
    protected String viewName;
    protected ViewManager viewManager;

    public View(Scanner scanner, String viewName) {
        this.scanner = scanner;
        this.viewName = viewName; //
        this.viewManager = ViewManager.getViewManager(); //getting the view manager from the getViewManager method
    }

    public String getViewName() {
        return viewName;
    }
    /**abstract method with no implementations
     * Invoke the renderView method to help users navigate to different throughout the application
     * which is based on their input. For example. if a user wants to check their balance or login would
     * invoke the renderView method to that screen. Using the proper logic. Can also invoke the DAO methods
    */
    public abstract void renderView() throws SQLException, BadUserException, IOException;
}
