package utility;
import exceptions.BadUserException;
import models.UserModel;
import utility.datastructures.MyArrayList;
import views.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


/**ViewManager is designed as a singleton to handle rendering the different console Input/Output views.
 * It maintains a list of possible views, when navigate is invoked(called) with the name of the view.
 * It searches the list for that view and is queued up to be rendered in the next goToNextView() call from main
 * Note that goToNextView() calls the renderView() method (can be found in the Driver class in the try/catch block).
 */
public class ViewManager {
    private static ViewManager viewManager;
    private static View nextView;
    private MyArrayList<View> viewList;
    private boolean running;
    private Connection conn;
    private Scanner scanner;
    private UserModel currentUser;

    public ViewManager() {
       viewManager = this;
       running = true;
       conn = ConnectionManager.getConnection();
       scanner = new Scanner(System.in); //Binding Scanner to our console
       viewList = new MyArrayList<>();

        //set up views
        viewList.add(new MainMenu(scanner));
        viewList.add(new ViewLogin(scanner));
        viewList.add(new ViewRegister(scanner));
        viewList.add(new ViewBankMenu(scanner));
        viewList.add(new CreateBankAccount(scanner));
        viewList.add(new MakeADeposit(scanner));
        viewList.add(new MakeAWithdrawal(scanner));
        viewList.add(new ViewYourBankAccount(scanner));
    }

/**
 * This is the method to retrieve the singleton instance of ViewManager
 */
    public static ViewManager getViewManager() {
        if(viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }
/**
 * Searches the List for a view by name and queries that view up to be rendered
 * next time the main loop calls the goToNextView();
 */
    public void navigate(String destination) {
        for(View view : viewList) {
            if(view.getViewName().equals(destination)) {
                nextView = view;
            }
        }
    }
/**
 * Called from the main loop, and just calls the queued next view's render method
 */
    public void goToNextView() throws SQLException, BadUserException, IOException {
        nextView.renderView();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}
