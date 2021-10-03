package DAOs;

import models.AccountModel;
import utility.datastructures.MyArrayInterface;
import utility.datastructures.MyArrayList;

import javax.swing.plaf.nimbus.State;

import java.sql.*;
import java.util.List;

public class AccountDAO implements AccountCrud {

  private Connection conn;

  public AccountDAO(Connection conn) {
      this.conn = conn;
  }

    @Override
    public void save(AccountModel row) throws SQLException {

    }

    @Override
    public AccountModel getItemById(int account_id) throws SQLException {
      String sql = "SELECT * FROM accounts WHERE account_id = ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, account_id);
      ResultSet rs = pstmt.executeQuery();

      if(rs.next()) {
          return new AccountModel(rs.getInt("account_id"), (rs.getString("accountType")),
                  (rs.getDouble("balance")));
      }
        return null;
    }

    @Override
    public MyArrayList<AccountModel> getAllItems() throws SQLException {
        String sql = "SELECT * FROM accounts";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        MyArrayList<AccountModel> resultList = new MyArrayList();
        while (rs.next()) {
            AccountModel newItem = new AccountModel(rs.getInt("account_id"),
                    rs.getString("accountType"), rs.getInt("balance"));
            resultList.add(newItem);
        }
        return resultList;
    }

    @Override
    public void deleteById(int account_id) {

    }
}