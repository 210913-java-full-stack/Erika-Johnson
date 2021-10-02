package DAOs;

import exceptions.BadUserException;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;
import java.util.List;

public interface UserCrud<E> {
    //will need a connection

    //create
        //save object to db method

    //read
    //query data from db, fill in empty object model
        public MyArrayList<E> getQuery() throws SQLException;
         public boolean authenticate (String log, String pass)throws SQLException;

        public UserModel getItemByID(int user_id) throws SQLException;
        public MyArrayList<E> getAllItems() throws SQLException;

        //public UserModel getItemBykeyword(String keyword); //SELECT * FROM items WHERE firstname LIKE "KEYWORD%"

     //update

        //will use save() method for updates

        public void save(UserModel row) throws SQLException, BadUserException;
        public boolean insert (UserModel row) throws SQLException;


    //delete

        //remove by ID
        public void deleteById(int user_id) throws SQLException;

}
