
package imgpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TenPercent {
    
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
   // Database db=new Database();
   // Connection con;
    Statement stmt;
    String sql;
    int totalPixels;
    int tenPercent,gap;
    private FileWriter fileWriter;
    int count,x,y;
    
    public void calculateTenPercent(int k,String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
       String readPath=root+"//root"+rand+"\\ModPerimeterCsv\\component";
       String writePath=root+"//root"+rand+"\\TenPercentCsv\\component" ;
       count=0;
     //  con=db.connect();
       //stmt=ProcessImages.con.createStatement();
       sql="Select PERIMETER from imageanalysis where componentno="+k;
       ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
           {
           totalPixels=rs.getInt(1); 
           }
        rs.close();
       //tenPercent=(int) (.2*totalPixels);
       //if(tenPercent==0)
       //      return;
       //gap=totalPixels/tenPercent;
       gap=1;
       fileWriter = new FileWriter(writePath+k + ".csv");
       BufferedReader fileReader = new BufferedReader(new FileReader(readPath+k+".csv"));
       String line;
       while ((line = fileReader.readLine()) != null) 
           {
               String[] tokens = line.split(COMMA_DELIMITER);
               if (tokens.length > 0) {
               if("".equals(tokens[0]))
                    //count=0;
                    break;
                   else
             {
                if(count==0)   
               {
                    x=Integer.parseInt(tokens[0]);
                    y=Integer.parseInt(tokens[1]);
                   
                 // 2. write val 
                    fileWriter.append(String.valueOf(x));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(-y));
                    fileWriter.append(NEW_LINE_SEPARATOR);
                 }
               count=(count+1)%gap;
             }}} 
         fileWriter.flush();
         fileWriter.close();
         fileReader.close();
         //con.close();
         
    }
}