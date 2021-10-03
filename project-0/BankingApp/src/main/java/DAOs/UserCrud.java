package DAOs;

import exceptions.BadUserException;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.List;

public interface UserCrud<E> {

    /**query data from db, fill in empty object model*/

        public MyArrayList<E> getQuery() throws SQLException;
        public boolean authenticate (String log, String pass)throws SQLException;
        public int getAccountKey() throws SQLException;
        public int getUserKey() throws SQLException;
        public void save(UserModel row) throws SQLException, BadUserException;
        public UserModel getItemByID(int user_id) throws SQLException;
        public MyArrayList<E> getAllItems() throws SQLException;
        public boolean insert (UserModel row) throws SQLException;
        public void deleteById(int user_id) throws SQLException;
        //public UserModel getItemBykeyword(String keyword); //SELECT * FROM items WHERE firstname LIKE "KEYWORD%"

}
