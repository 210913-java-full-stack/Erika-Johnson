package DAOs;

import exceptions.BadUserException;
import models.UserModel;
import utility.ViewManager;
import utility.datastructures.MyArrayList;
import java.sql.*;

public class UserDAO implements UserCrud {


    private Connection conn;

    /****** name public constructor same as class, and no return value because it returns a
     new object when invoked with the new keyword & our param list will have a
     connection obj called conn ******/
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**Returning the user_id to allow user to register for an account and keeps track of the
     * user_id and increments by one to find the next available id.*/
    public int newUserId;
    @Override
    public int getUserKey() throws SQLException {
        String sql = "SELECT * FROM customer_accounts";
        PreparedStatement getUserId = conn.prepareStatement(sql);
        ResultSet rs = getUserId.executeQuery();

        while(rs.next()) {
            newUserId = rs.getInt("user_id");
        }
        return newUserId;
    }

    /**
     * The trackUserId method maintains the user_id that we create new rows with. It allows
     * us to resume that count when we restart the application.
     * Query the users table in a constructor, looking for the current greatest ID.
     * Once the highest value has been detected it is stored in newUserId (Line 54)
     * and return the highest user_id (Line 55)
     * else return 0 if user_id can not be found
     * @return
     * @throws SQLException
     */

    @Override
    public int trackUserId() throws SQLException {
        getUserKey();
        String trackAcctSql = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1";
        PreparedStatement storeUserId = conn.prepareStatement(trackAcctSql);
        ResultSet resultSet = storeUserId.executeQuery();
        if(resultSet.next()) {
            newUserId = resultSet.getInt("user_id");
           return newUserId;
        } else {
            return (newUserId = 0);
        }
    }


    /**Returning the account_id to allow user to create a bank account*/
    public int newAccountId;
    @Override
    public int getAccountKey() throws SQLException {
        String sql = "SELECT * FROM customer_accounts";
        PreparedStatement getAccountId = conn.prepareStatement(sql);
        ResultSet rs = getAccountId.executeQuery();

        while(rs.next()) {
            newAccountId = rs.getInt("account_id");
        }
        return newAccountId;
    }


    /**
     * Checks to see what user_id is available if already in use will through an exception
     * Pass in getAccountKey() and getUserKey() methods, so we don't duplicate an entry
     * If user_id key is available will insert it into the customer_accounts table, which is our junction table
     * customer_accounts table must have user_id and account_id(foreign keys) inserted first before anyone can
     * register for an account and create a bank account. As the user and account table are dependent on the customer_account table
     * When inserting the foreign keys into the customer_account table must increment the return value to receive the next available id
     * Once those values are inserted into the table and after you register you will automatically be given a bank account.
     * @return
     */
    @Override
    public void save (UserModel row) throws SQLException, BadUserException{
       getAccountKey();
       getUserKey();
       trackUserId();
       String sql =  "SELECT user_id FROM users WHERE user_id = ?";
       PreparedStatement pstmt = conn.prepareStatement(sql);
       pstmt.setInt(1, row.getUser_id());

       ResultSet rs = pstmt.executeQuery();

       if(rs.next()){
           throw new BadUserException();

       }else {
           String insertStatement = "INSERT INTO customer_accounts(user_id, account_id) VALUES (?,?)";
           PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
           newUserId++;
           preparedInsertStatement.setInt(1, newUserId);
           newAccountId++;
           preparedInsertStatement.setInt(2, newAccountId);
           preparedInsertStatement.executeUpdate();

           String userNew = "INSERT INTO users (user_id, username, email, password) VALUES (?,?,?,?)";
           PreparedStatement preparedEntryStmt = conn.prepareStatement(userNew);
           preparedEntryStmt.setInt(1, newUserId);
           preparedEntryStmt.setString(2, row.getUsername());
           preparedEntryStmt.setString(3, row.getEmail());
           preparedEntryStmt.setString(4, row.getPassword());
           preparedEntryStmt.executeUpdate();

           String userAccount = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?,?,?)";
           PreparedStatement prepAcctEntryStmt = conn.prepareStatement(userAccount);
           prepAcctEntryStmt.setInt(1, newAccountId);
           prepAcctEntryStmt.setString(2, "checking");
           prepAcctEntryStmt.setInt(3, 0);
           prepAcctEntryStmt.executeUpdate();

       }

    }

    /**User Login maybe a boolean if user logged in return true or false
     *use getString() not setString() because retrieving data from db*/
    /** Lines (120 - 124) before returning true, marshall these results into a UserModel object
     *then store that object with viewManager.setCurrentUser(user), then whenever you need to know who is logged in,
     *just get it with the getter method. UserModel user =  viewManager.getCurrentUser();
     */

    @Override
    public boolean authenticate (String log, String pass) throws SQLException {

            String user;
            String password;

            String sql = "SELECT * FROM users";//change this to * instead of username and pass select * from users
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);

        while(rs.next()) {
             user = rs.getString("username");
             password = rs.getString("password");
             if(user.equals(log) && (password.equals(pass))){
                 UserModel currentUser = new UserModel();
                 currentUser.setUser_id(rs.getInt("user_id"));
                 currentUser.setUsername(rs.getString("username"));
                 currentUser.setPassword(rs.getString("password"));
                 ViewManager.getViewManager().setCurrentUser(currentUser);

                 return true;
                }
         }
            return false;
        }

    @Override
    public MyArrayList<UserModel> getQuery() throws SQLException {
        String sql = "SELECT username, email, password FROM users";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        return null;
    }

    @Override
    public UserModel getItemByID(int user_id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        ResultSet rs = pstmt.executeQuery(sql);

        if(rs.next()) {
           return new UserModel(rs.getInt("user_id"), rs.getString("username"),rs.getString("email"),
                    rs.getString("password"));
        } else {
            return null;
        }
    }

    @Override
    public MyArrayList<UserModel> getAllItems() throws SQLException {
        String sql = "SELECT * FROM users";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        MyArrayList<UserModel> resultList = new MyArrayList<>();
        while (rs.next()) {
            UserModel newItem = new UserModel();
            newItem.setUser_id(rs.getInt("user_id"));
            newItem.setUsername(rs.getString("username"));
            newItem.setEmail(rs.getString("email"));
            resultList.add(newItem);
        }
       for (UserModel um : resultList){
           System.out.println(um);

       }
        return resultList;

    }
 }


