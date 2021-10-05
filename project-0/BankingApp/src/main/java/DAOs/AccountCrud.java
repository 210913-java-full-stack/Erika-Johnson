package DAOs;

import exceptions.BadUserException;
import models.AccountModel;
import utility.datastructures.MyArrayList;
import java.sql.SQLException;


public interface AccountCrud<E> {
    public int getBankAccountKey() throws SQLException;
    public void CreateBankAcct(String account_type, int user_id) throws SQLException, BadUserException;
    public AccountModel getItemById(int account_id) throws SQLException;
    public MyArrayList<E> getUserAccount(int user_id) throws SQLException;
}
