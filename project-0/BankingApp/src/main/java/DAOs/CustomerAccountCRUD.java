package DAOs;

import models.AccountModel;
import models.CustomerAccountsModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;

public interface CustomerAccountCRUD <E>{
    public void save(CustomerAccountsModel row);
    public void insert(CustomerAccountsModel  row) throws SQLException;
    public CustomerAccountsModel  getItemById( int user_id, int account_id) throws SQLException;
    public MyArrayList<E> getAllItems() throws SQLException;
    public void deleteById(int user_id, int account_id);
}
