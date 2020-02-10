
package imgpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Shikha
 */
public class Database {
     
    public Connection connect() throws ClassNotFoundException, SQLException
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
       // Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.8:1522:orcl11g","MCS25","mcs25");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","system","sys");
      //Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.8:1522:orcl11g","MCS25","mcs25");
      return con;
        
    }
}
