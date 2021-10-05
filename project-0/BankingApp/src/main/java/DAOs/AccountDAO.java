package DAOs;

import exceptions.BadUserException;
import models.AccountModel;
import utility.datastructures.MyArrayList;
import java.sql.*;

public class AccountDAO implements AccountCrud {

  private Connection conn;

    public AccountDAO(Connection conn) {
        this.conn = conn;

    }


//To create another bank account need the user_id
// need to perhaps populate the junction table first with a new account

    public int newAccountId;
    @Override
  public int getBankAccountKey() throws SQLException {
      String sql ="SELECT * FROM customer_accounts";
      PreparedStatement getAccountId = conn.prepareStatement(sql);
      ResultSet resultSet = getAccountId.executeQuery();

      while(resultSet.next()) {
          newAccountId = resultSet.getInt("account_id");
      }
      return newAccountId;
  }
    //We need to maintain the account ID that we create new rows with, so far we track it with newAccountId, which
    //increments. Now we just need to make sure we resume that count when we restart the app.
    //I would suggest: Query the accounts table in the constructor, looking for the current greatest ID,
    // then increment it once.
    //Also, if we need to do the same sort of tracking of IDs for user, just re-use very similar code as this.
    @Override
    public void trackAcctId() throws SQLException {
        getBankAccountKey();
        String trackAcctISql = "SELECT account_id FROM accounts ORDER BY account_id DESC LIMIT 1";
        PreparedStatement storeAcctId = conn.prepareStatement(trackAcctISql);
        storeAcctId.setInt(1, newAccountId);
        ResultSet resultSet = storeAcctId.executeQuery();
        //...write the JDBC stuff to use this statement
        if(resultSet.next()) {
            resultSet.getInt("account_id");
            newAccountId++;
            //increment the number once and store it.
        } else {
           newAccountId = 0;
            //if no results came back. then the table is empty, start with 0;
        }
    }


//    String caUsers= "SELECT ca.user_id FROM customer_accounts ca " +
//            "JOIN users u ON ca.user_id = u.user_id " +
//            "JOIN accounts a ON ca.account_id = a.account_id WHERE ca.user_id = ?";
    @Override
    public void CreateBankAcct(String account_type, int user_id) throws SQLException {
      getBankAccountKey();
      trackAcctId();
//        String caUsers =  "SELECT ca.user_id FROM customer_accounts ca WHERE user_id = ?";
//        PreparedStatement stmt = conn.prepareStatement(caUsers);
//        stmt.setInt(1, user_id);
//        ResultSet rs = stmt.executeQuery();
//        if(rs.next()) {
//            System.out.println(rs.getInt("user_id"));
//            if (rs.getInt("user_id") == user_id) {

                String insertStmt = "INSERT INTO customer_accounts (user_id, account_id) VALUES (?, ?)";
                PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStmt);
                preparedInsertStatement.setInt(1, user_id );
                newAccountId++;
                preparedInsertStatement.setInt(2, newAccountId);
                preparedInsertStatement.executeUpdate();

                String bankAcct = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?,?,?)";
                PreparedStatement bankAcctStmt = conn.prepareStatement(bankAcct);
                bankAcctStmt.setInt(1, newAccountId);
                bankAcctStmt.setString(2, account_type);
                bankAcctStmt.setDouble(3, 0);
                bankAcctStmt.executeUpdate();
            }


    @Override
    public MyArrayList<AccountModel> getUserAccount(int user_id) throws SQLException {
        String userAcctSql = "SELECT * FROM accounts a JOIN customer_accounts ca ON a.account_id = ca.account_id WHERE user_id = ?";
        PreparedStatement prepState = conn.prepareStatement(userAcctSql);
        prepState.setInt(1, user_id);
        ResultSet rs = prepState.executeQuery();

        MyArrayList<AccountModel> resultList = new MyArrayList();

        while (rs.next()) {
            AccountModel newAcctItem = new AccountModel(rs.getInt("account_id"),
                    rs.getString("account_type"), rs.getDouble("balance"));
            resultList.add(newAcctItem);
        }
        return resultList;
    }

    @Override
    public boolean updateAcct(int account_id, double balance) throws SQLException {
        String updateSql = "UPDATE accounts a SET balance = ? WHERE account_id = ?";
        PreparedStatement updateAcctBalance = conn.prepareStatement(updateSql);
        updateAcctBalance.setInt(1, account_id);
        updateAcctBalance.setDouble(2, balance);
        updateAcctBalance.executeUpdate();
        return true;
    }

    /**
     * First check the account_id's within the accounts table.
     *Next check to see if the account_id matches the account_id the user wants
     * If so, update account to the amount the user wants to deposit or withdraw.
     * Else if account_id doesn't match before depositing throw user a response stating account_id is invalid.
     * @param account_id
     * @param balance
     * @return
     * @throws SQLException
     */

    @Override
    public boolean withdrawAcct(int account_id, double balance) throws SQLException {
        String sql =  "SELECT a.account_id FROM accounts a WHERE account_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            System.out.println(rs.getInt("account_id"));
           if(rs.getInt("account_id") == account_id) {
               String withdrawSql = "UPDATE accounts a SET a.balance = (a.balance - ?) WHERE account_id = ?";
               PreparedStatement withdrawBal = conn.prepareStatement(withdrawSql);
               withdrawBal.setDouble(1, balance);
               withdrawBal.setInt(2, account_id);
               withdrawBal.executeUpdate();
               return true;
           }
        }else {
            System.out.println("Account_id is invalid");
        }
        return false;
    }

    @Override
    public boolean depositAcct(int account_id, double balance) throws SQLException {
        String sql =  "SELECT a.account_id FROM accounts a WHERE account_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            System.out.println(rs.getInt("account_id"));
            if(rs.getInt("account_id") == account_id) {
                String depositSql = "UPDATE accounts a SET a.balance = (a.balance + ?) WHERE account_id = ?";
                PreparedStatement depositBal = conn.prepareStatement(depositSql);
                depositBal.setDouble(1, balance);
                depositBal.setInt(2, account_id);
                depositBal.executeUpdate();
                return true;
            }
        }else{
            System.out.println("Account_id is invalid");
        }
     return false;
    }

    @Override
    public AccountModel getItemById(int account_id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            return new AccountModel(rs.getInt("account_id"), (rs.getString("account_type")),
                    (rs.getDouble("balance")));
        }else {
            return null;
        }

    }

}