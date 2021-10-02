package DAOs;

import models.TestModel;
import models.UserModel;
import utility.datastructures.MyArrayList;

import java.sql.*;

public class TestDAO implements TestCRUD{

    private Connection conn;

    public TestDAO(Connection conn) {
            this.conn = conn;
    }
    //Registration and if you don't want to use generated keys you can query the db for the greatest number,
    //right after inserting it and will receive the id number
    @Override
    public void save(TestModel row) throws SQLException {
        String query = "INSERT INTO test_table (name, string) VALUES (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, row.getName());
        pstmt.setString(2, row.getString());
        //auto-incremented data stored in pstmt, now have to retrieve it
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        //rs.next advances the cursor to the next index
        //assign column to
        rs.next();
           row.setId(rs.getInt("id")); //can refer to it by column number also

    }

    /** WORKS  would use save method **/
    @Override
    public void insert(TestModel row) throws SQLException {
        String query = "INSERT INTO test_table (name, string) VALUES (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, row.getName());
        pstmt.setString(2, row.getString());
        pstmt.executeUpdate();

    }

    @Override
    public TestModel getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM test_table WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            return new TestModel(rs.getInt("id"), rs.getString("name"),rs.getString("string"));
        } else {
            return null;
        }
    }

    @Override
    public MyArrayList getAllItems() throws SQLException {
        String sql = "SELECT * FROM test_table";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        //Implement Array List here
        MyArrayList<TestModel> resultList = new MyArrayList<>();
        while(rs.next()) {
            TestModel models = new TestModel(rs.getInt("id"), rs.getString("name"),rs.getString("string"));
            resultList.add(models);
        }

        return resultList;
    }


    @Override
    public void deleteById(int account_id) {

    }
}
