package DAOs;

import models.AccountModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.List;

public interface AccountCrud<E> {
//    public int getAccountKey() throws SQLException;
    public void save(AccountModel row) throws SQLException;
    public AccountModel getItemById(int account_id) throws SQLException;
    public MyArrayList<E> getAllItems() throws SQLException;
    public void deleteById(int account_id);
}
