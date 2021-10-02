
import utility.ConnectionManager;
import utility.ViewManager;
import java.sql.*;


public class Driver {
    public static void main(String[] args){

        //viewManager
        ViewManager viewManager = ViewManager.getViewManager();

        //A constructor and you invoke a constructor with the new keyword the result of this operation
        //is a new object ( the results from the constructor) and it gets assigned to the reference we are creating

//            AccountDAO accountdao = new AccountDAO(conn);

        /**invoke render method on every loop (goToNextView). This is the main loop, it keeps
         * running until something sets the viewManager "running" flag = false.
         * With every loop invoked, the viewManager singleton's goToNextView() method
         */
        viewManager.navigate("MainMenu");
        while (viewManager.isRunning()) {
            try {
                viewManager.goToNextView();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

