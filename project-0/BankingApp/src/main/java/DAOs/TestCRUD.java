package DAOs;



import models.TestModel;
import utility.datastructures.MyArrayList;

import java.sql.SQLException;

public interface TestCRUD<E> {
    public void save(TestModel row) throws SQLException;
    public void insert(TestModel row) throws SQLException;
    public TestModel getItemById(int account_id) throws SQLException;
    public MyArrayList<E> getAllItems() throws SQLException;
    public void deleteById(int account_id);
}
