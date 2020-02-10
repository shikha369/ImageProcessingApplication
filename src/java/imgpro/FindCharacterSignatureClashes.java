
package imgpro;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Shikha
 */
public class FindCharacterSignatureClashes {
    ArrayList<String> charClass=new ArrayList<String>();
    ArrayList<String> Encoding=new ArrayList<String>();
    ArrayList<String> distinctEncoding=new ArrayList<String>();
       
    public boolean IfRotatedVersionOfEachOther(String str1,String str2){
    if(str1.length()!=str2.length())
        {
            return false;
        }
        else
        {
            String temp = str1 + str1;
            if(temp.contains(str2))
            {
               return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    public void GetDistinct(){   // perfectly fine
        for(int i=0;i<Encoding.size();i++){
            boolean isDistinct = false;
            for(int j=0;j<i;j++){
                if(IfRotatedVersionOfEachOther(Encoding.get(i),Encoding.get(j))){
                    isDistinct = true;
                    break;
                }
            }
            if(!isDistinct){
                distinctEncoding.add(Encoding.get(i));
            }
        }
    }

    
   public void find(String[] characters) throws ClassNotFoundException, SQLException, IOException{
     //  Database db=new Database();
     //  Connection con=db.connect();
     //  Statement stmt=ProcessImages.con.createStatement();
     //  Statement stmtInsert=ProcessImages.con.createStatement();
       ResultSet rs;
       String distinct,orig;
       int index=characters.length;
       ArrayList<CharSign> cs=new ArrayList<CharSign>();
       //int count=0;
       if (characters.length != 0) {
       for(int i=0;i<index;i++)
       {

       String sql="SELECT CHARCLASS,ENCODING FROM IMAGEANALYSIS WHERE CHARCLASS LIKE '"+characters[i]+"%'";
     /* rs=stmt.executeQuery(sql);
       while(rs.next())
       {
       charClass.add(rs.getString(1));
       Encoding.add(rs.getString(2));
       }
       rs.close();
  */     }
       }
       
       
       GetDistinct();
       String getClass;
       CharSign CharSignObj;
       charclass zero,one,two,three,four,five,six,seven,eight,nine;
       int classindex;
       for(int i=0;i<distinctEncoding.size();i++)
       {  CharSignObj =new CharSign();
          zero=new charclass();
          one=new charclass();
          two=new charclass();
          three=new charclass();
          four=new charclass();
          five=new charclass();
          six=new charclass();
          seven=new charclass();
          eight=new charclass();
          nine=new charclass();
          CharSignObj.zero=zero;CharSignObj.one=one;CharSignObj.two=two;
          CharSignObj.three=three;CharSignObj.four=four;CharSignObj.five=five;
          CharSignObj.six=six;CharSignObj.seven=seven;CharSignObj.eight=eight;
          CharSignObj.nine=nine;
          
           distinct=distinctEncoding.get(i);
           CharSignObj.enc=distinct;
           
             for(int j=0;j<Encoding.size();j++){
               orig=Encoding.get(j);
               if(IfRotatedVersionOfEachOther(distinct,orig)){
               getClass=charClass.get(j);
              
              if(getClass.contains("zero"))
               CharSignObj.zero.frequency++;
              else if(getClass.contains("one"))
               CharSignObj.one.frequency++;
              else if(getClass.contains("two"))
               CharSignObj.two.frequency++;
              else if(getClass.contains("three"))
               CharSignObj.three.frequency++;
              else if(getClass.contains("four"))
               CharSignObj.four.frequency++;
              else if(getClass.contains("five"))
               CharSignObj.five.frequency++;
              else if(getClass.contains("six"))
               CharSignObj.six.frequency++;
              else if(getClass.contains("seven"))
               CharSignObj.seven.frequency++;
              else if(getClass.contains("eight"))
               CharSignObj.eight.frequency++;
              else
               CharSignObj.nine.frequency++; 
            }
        
           }
             cs.add(CharSignObj);
       }
       
      // for(int m=0;m<charClass.size();m++)
      // System.out.println(charClass.get(m)+" "+Encoding.get(m));
       
       //System.out.println(distinctEncoding);
      
       
       //output csv
       String COMMA_DELIMITER = ",";
       String NEW_LINE_SEPARATOR = "\n";
       String writePath="C:\\Users\\Shikha\\Documents\\NetBeansProjects\\ImageProcessingApplication\\web\\CharacterEncodingClashes.csv" ;
       FileWriter fileWriter=new FileWriter(writePath);
       for(int k=0;k<cs.size();k++)
      {
          CharSignObj=new CharSign();
          CharSignObj=cs.get(k);
          fileWriter.append(CharSignObj.enc);fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.zero.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.one.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.two.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.three.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.four.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.five.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.six.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.seven.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.eight.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.nine.frequency));fileWriter.append(NEW_LINE_SEPARATOR);
          String sqlInsert="INSERT INTO SIGNATURECLASH VALUES('"
                  +CharSignObj.enc+"',"
                  +CharSignObj.zero.frequency +","
                  +CharSignObj.one.frequency +","
                  +CharSignObj.two.frequency +","
                  +CharSignObj.three.frequency+","
                  +CharSignObj.four.frequency+","
                  +CharSignObj.five.frequency+","
                  +CharSignObj.six.frequency +","
                  +CharSignObj.seven.frequency +","
                  +CharSignObj.eight.frequency +","
                  +CharSignObj.nine.frequency
                  +")";  
         // System.out.println(sqlInsert);
         // stmtInsert.executeUpdate(sqlInsert);
      
      }
       fileWriter.flush();
       fileWriter.close();
       //con.close();
   } 
}
