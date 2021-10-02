package views;

import DAOs.TestDAO;
import models.TestModel;

import java.sql.SQLException;
import java.util.Scanner;

public class TestView extends View{
    public TestView(Scanner scanner, String viewName) {
        super(scanner, viewName);
    }

    @Override
    public void renderView() throws SQLException {


        //  System.out.print("Enter name:");
//        String name = sc.nextLine();
//       System.out.print("Enter message:");
//      String string = sc.nextLine();
//      TestModel yo = new TestModel(name, string);
//       test.insert(yo);

//      System.out.println(test.getAllItems().toString()); Array of all the items

        TestDAO test = new TestDAO(viewManager.getConn());
        //Message not returning to console only name and id
        System.out.print("Enter name:");
        String name = scanner.nextLine();
        System.out.print("Enter message:");
        String string = scanner.nextLine();
        TestModel newModel = new TestModel();
        newModel.setName(name);
        newModel.setString(string);
        test.save(newModel);
        System.out.println("The id assigned to you " + newModel.getName() + " is:" + newModel.getId() +
                " " + "Your message is: " + newModel.getString());

//                        System.out.println("Enter id to view all messages(messages not showing when user inputs only
//                        when created in sql");
        System.out.print("Input ID: ");
        int num = scanner.nextInt();
        TestModel model = new TestModel();
        test.getItemById(newModel.getId());
        newModel.setId(num);
        System.out.println("Here are your messages: " + model.getId());
        System.out.println(test.getItemById(num));


    }
}
//              System.out.println(dd.);
//           System.out.println(dd.getItemByID(1));
//            System.out.println(dd.getAllItems().toString());

//            String sql = "SElECT * FROM test_table";
//            Statement stmt = conn.createStatement();

////////EXAMPLE OF HOW TO GET INFO FROM SQL DB//////////////////////////////////////

//            ResultSet rs = stmt.executeQuery(sql);
//
//            List<TestModel> resultList = new ArrayList<>();
//            while(rs.next()) {
//                TestModel temp = new TestModel();
//                temp.setId(rs.getInt("id"));
//                temp.setName(rs.getString("name"));
//                temp.setString(rs.getString("string"));
//                resultList.add(temp);
//     }
//            for(TestModel tm : resultList){
//                System.out.println(tm);
//            }