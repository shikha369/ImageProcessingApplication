
package imgpro;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shikha
 */
public class PrepareCsv {
    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    Database db=new Database();
    Connection con;
    Statement stmt;
    
    String sign,angle;
    
    
    FileWriter fileWriter;
    
    public void write(String character) throws SQLException, IOException
    {
    
    String sql="SELECT ENCODING,ANGLEENCODING FROM TESTIMAGEANALYSIS where CHARCLASS like '"+character+"%'";
    ResultSet rs=stmt.executeQuery(sql);
    while(rs.next())
    {
    sign=rs.getString(1);
    angle=rs.getString(2);
    fileWriter.append(sign);
    fileWriter.append(angle);
    fileWriter.append(COMMA_DELIMITER);
    }
    rs.close();
    fileWriter.append(NEW_LINE_SEPARATOR);
    }
    
    public void make(String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
        String writePath=root+"//root"+rand+"\\LeftRight.csv" ;
        con=db.connect();
        stmt=con.createStatement();
        fileWriter=new FileWriter(writePath);
        write("zero");
        write("one");
        write("two");
        write("three");
        write("four");
        write("five");
        write("six");
        write("seven");
        write("eight");
        write("nine");
        write("A");
        write("B");
        write("C");write("D");
        write("E");
        write("F");
        write("G");
        write("H");
        write("I");
        write("J");
        write("K");
        write("L");write("M");write("N");write("O");write("P");write("Q");write("R");
        write("S");write("T");write("U");write("V");write("W");write("X");write("Y");
        write("Z");
        /*small letters*/
        write("aa");write("bb");write("cc");write("dd");write("ee");write("ff");write("gg");
        write("hh");write("ii");write("jj");write("kk");write("ll");write("mm");write("nn");
        write("oo");write("pp");write("qq");write("rr");write("ss");write("tt");write("uu");write("vv");
        write("ww");write("xx");write("yy");write("zz");
        
        
        fileWriter.flush();
        fileWriter.close();
        
        
       // con.close();
    }
}
