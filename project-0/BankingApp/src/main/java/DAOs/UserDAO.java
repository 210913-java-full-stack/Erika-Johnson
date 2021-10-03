package DAOs;

import exceptions.BadUserException;
import models.UserModel;
import utility.datastructures.MyArrayList;
import java.sql.*;

public class UserDAO implements UserCrud {
    //maintain a connection

    private Connection conn;

    /****** name public constructor same as class, and no return value because it returns a
     new object when invoked with the new keyword & our param list will have a
     connection obj called conn ******/
    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    /**Grabbing the account_id to allow user to create a bank account*/
    public int newAccountId;



    /**Grabbing the user_id to allow user to register for an account*/
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

    //param starts counting at 1
//    @Override
    public void save (UserModel row) throws SQLException, BadUserException{
        getAccountKey();
        getUserKey();
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
           prepAcctEntryStmt.setString(2, "savings");
           prepAcctEntryStmt.setInt(3, 0);
           prepAcctEntryStmt.executeUpdate();

       }
    }



//        String query = "INSERT INTO users (user_id, username, email, password) VALUES (?,?,?,?)";
//        PreparedStatement pstmt = conn.prepareStatement(query);
//        pstmt.setInt(1, row.getUser_id());
//        pstmt.setString(2, row.getUsername());
//        pstmt.setString(3, row.getEmail());
//        pstmt.setString(4, row.getPassword());
//        //auto-incremented data stored in pstmt, now have to retrieve it
//        pstmt.executeUpdate();
//
//        ResultSet rs = pstmt.getGeneratedKeys();
//        rs.next();
//        row.setUser_id(rs.getInt("user_id"));



    @Override
    public MyArrayList<UserModel> getQuery() throws SQLException {
        String sql = "SELECT username, email, password FROM users";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        return null;
    }


    //User Registration should be able to save users info to db not returning any data
    //Maybe use boolean to return true or false if user registered
    //Must create customer accounts first before creating a user due to the junction table
    @Override
    public boolean insert(UserModel row) throws SQLException {
        String insertStmt = "INSERT INTO users(user_id, username, email, password) VALUES =(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertStmt);
        pstmt.setInt(1, row.getUser_id());
        pstmt.setString(2, row.getUsername());
        pstmt.setString(3, row.getEmail());
        pstmt.setString(4, row.getPassword());
        pstmt.executeUpdate();
        {
            return true;
        }

    }

    //User Login maybe a boolean if user logged in return true or false
    //use getString() not setString() because retrieving data from db
    @Override
    public boolean authenticate (String log, String pass) throws SQLException {

            String user;
            String password;


            String sql = "SELECT username, password FROM users";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);

            while(rs.next()) {
             user = rs.getString("username");
             password = rs.getString("password");
             if(user.equals(log) && (password.equals(pass))){
                 return true;
                }
         }
            return false;
        }

        public void Create(UserModel user) throws SQLException, BadUserException {
            String query = "INSERT INTO users (user_id, username, email, password) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, user.getUser_id());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate(query);

            throw new BadUserException();

        }

    @Override
    public UserModel getItemByID(int user_id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        ResultSet rs = pstmt.executeQuery();

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

        //Implement Array List here
        MyArrayList<UserModel> resultList = new MyArrayList<>();
        while(rs.next()) {
           UserModel newItem = new UserModel(rs.getInt("user_id"), rs.getString("username"),rs.getString("email"),
                    rs.getString("password"));
           resultList.add(newItem);
        }

        return resultList;
    }

    @Override
    public void deleteById(int user_id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);

        pstmt.executeUpdate();
    }

}
