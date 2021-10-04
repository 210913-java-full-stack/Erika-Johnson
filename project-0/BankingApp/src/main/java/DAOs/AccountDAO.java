package DAOs;

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

    @Override
    public void save(AccountModel row) throws SQLException {
        String sql = "SELECT account_id FROM accounts WHERE account_id =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, row.getAccount_id());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("Account_id taken");
        } else {
            String userAcct = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?,?,?)";
            PreparedStatement prepState = conn.prepareStatement(userAcct);
            prepState.setInt(1, row.getAccount_id());
            prepState.setString(2, row.getAccountType());
            prepState.setDouble(3, row.getBalance());
            prepState.executeUpdate();
        }
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

    @Override
    public MyArrayList<AccountModel> getAllItems() throws SQLException {
        String sql = "SELECT * FROM accounts";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        MyArrayList<AccountModel> resultList = new MyArrayList();
        while (rs.next()) {
            AccountModel newItem = new AccountModel(rs.getInt("account_id"),
                    rs.getString("account_type"), rs.getInt("balance"));
            resultList.add(newItem);
        }
        return resultList;
    }

    @Override
    public void deleteById(int account_id) {

    }
}