package DAOs;

import exceptions.BadUserException;
import models.AccountModel;
import models.UserModel;
import utility.ViewManager;
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

    @Override
    public void CreateBankAcct(String account_type, int user_id) throws SQLException {
      getBankAccountKey();

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