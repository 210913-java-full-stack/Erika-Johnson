package DAOs;

import exceptions.BadUserException;
import models.AccountModel;
import utility.datastructures.MyArrayList;
import java.sql.SQLException;

/**
 * Crud methods for the accounts table (Create, Read, Update, Delete)
 * @param <E>
 */

public interface AccountCrud<E> {
    public int trackAcctId() throws SQLException;
    public int getBankAccountKey() throws SQLException;
    public boolean updateAcct(int account_id, double balance) throws SQLException;
    public void CreateBankAcct(String account_type, int user_id) throws SQLException, BadUserException;
    public MyArrayList<E> getUserAccount(int user_id) throws SQLException;
    public boolean withdrawAcct(int account_id, double balance) throws SQLException;
    public boolean depositAcct(int account_id, double balance) throws SQLException;
}
