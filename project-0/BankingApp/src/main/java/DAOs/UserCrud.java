package DAOs;

import exceptions.BadUserException;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;

public interface UserCrud<E> {

    /**Query data from db, fill in empty object model*/

        public int trackUserId() throws SQLException;
        public MyArrayList<E> getQuery() throws SQLException;
        public boolean authenticate (String log, String pass)throws SQLException;
        public int getAccountKey() throws SQLException;
        public int getUserKey() throws SQLException;
        public void save(UserModel row) throws SQLException, BadUserException;
        public UserModel getItemByID(int user_id) throws SQLException;
        public MyArrayList<E> getAllItems() throws SQLException;
}
