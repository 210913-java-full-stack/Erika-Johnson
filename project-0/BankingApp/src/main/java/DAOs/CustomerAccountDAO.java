package DAOs;

import models.CustomerAccountsModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;

public class CustomerAccountDAO implements CustomerAccountCRUD{
    @Override
    public void save(CustomerAccountsModel row) {

    }

    @Override
    public void insert(CustomerAccountsModel row) throws SQLException {

    }

    @Override
    public CustomerAccountsModel getItemById(int user_id, int account_id) throws SQLException {
        return null;
    }

    @Override
    public MyArrayList getAllItems() throws SQLException {
        return null;
    }

    @Override
    public void deleteById(int user_id, int account_id) {

    }
}
