package pft.tests;

import org.testng.annotations.Test;
import pft.model.GroupData;
import pft.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData(rs.getInt("group_id"),
                rs.getString("group_name"), null, null));
      }
      rs.close();    //обязательно закрывать все соединения!
      st.close();
      conn.close();

      System.out.println(groups );

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());

    }
  }


}


