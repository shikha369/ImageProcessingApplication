
package imgpro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewCoordinatesSystem {
    private FileWriter fileWriter;
    
    
    /*
   1. centroid calculated while labelling ... using arrayList used to store x coordinates and y coordinates bn divide by size 
    */
    
    public void translateCoordinates(int k,int startX,int startY,String root,String rand,int Nimage) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException
    {
       // String root=ProcessImages.rootPath;
    //StartX and StartY can be retrieved from table corresponding to each object
    //k denotes kth component
        /*
        0.retrieve startX and startY of component k from table
        1.read any RPerimeterCsv file to translate coordinates.. 
        2.read x val and y val ... 
        3.modify as follows - newxval=xval-startX
                              newyval=yval-startY
                              start=0,0
        these modifications are done in array itself
        4.modify and rewrite csvs
                                 
        */
        
     //   Database db=new Database();
     //   Connection con=db.connect();
        
      //  Statement stmt = ProcessImages.con.createStatement();
        ResultSet rs; 
        int sx = 0,sy = 0,x,y;
        
          String readPath=root+"//root"+rand+"\\RPerimeterCsv\\Image"+Nimage+"_component";
          String writePath=root+"//root"+rand+"\\ModPerimeterCsv\\Image"+Nimage+"_component";
          String COMMA_DELIMITER = ",";
          String NEW_LINE_SEPARATOR = "\n";
      /*  
           String sqlr="Select STARTX,STARTY from imageanalysis where COMPONENTNO='"+driver.Nimage+"_"+k+"'";
           rs=stmt.executeQuery(sqlr);
           if(rs.next())
           {
           sx=rs.getInt(1);
           sy=rs.getInt(2);
           }
           rs.close();
         
              */// only perimeter csv has coordinates representaion .. :(
           sx=startX;
           sy=startY;
           fileWriter = new FileWriter(writePath+k + ".csv"); 
           BufferedReader fileReader = new BufferedReader(new FileReader(readPath+k+".csv"));
           String line ;
           
           while ((line = fileReader.readLine()) != null) {          
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) 
             { 
                 if("".equals(tokens[0]))
                 fileWriter.append(NEW_LINE_SEPARATOR);
                 else
                 {
                   x=Integer.parseInt(tokens[0]);
                   y=Integer.parseInt(tokens[1]);
                 // 2. modify val    
                     x=x-sx;
                     y=y-sy;
                 // 3. write val 
                    fileWriter.append(String.valueOf(x));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(y));
                    fileWriter.append(NEW_LINE_SEPARATOR);
                 }}}
         fileWriter.flush();
         fileWriter.close();
         fileReader.close();
         //con.close();
    }
}
