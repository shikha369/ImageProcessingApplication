
package imgpro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 *invoked from analysingResults.jsp if arraylist of updates in feature vector is to be added to trainedFeatures.csv
 *method : 
  updateTrainingSet(name of component,recognisedAs)
*/

public class UpdationsToTrainingData {
    /* to be appended at the end of csv */
  
  FileWriter fw;
   
  Database db=new Database();
  

 
  
  public void updateTrainingSet(ArrayList<Updates> upData,String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
        String TrainingDataFilename = root+"//root"+rand+"\\trainedFeatures.csv"; 
        fw = new FileWriter(TrainingDataFilename, true);
         BufferedWriter bw = new BufferedWriter(fw);
        Connection con=db.connect();
        Statement updStmt=con.createStatement();
        String sql;
        ResultSet rs;
        String componentName;String correctTo,charClass,encoding = null;
        int boundaries = 0,deadends = 0;
        Updates currentUpData;
        for(int i=0;i<upData.size();i++)
        {
            currentUpData=upData.get(i);
            componentName=currentUpData.ComponentName;
            correctTo=currentUpData.CorrectTo;
            charClass=getClassFromChar(correctTo);
            sql="Select BOUNDARIES,DEADENDS,CORNERENC from testimageanalysis where COMPONENTNO='"+componentName+"'";
            rs=updStmt.executeQuery(sql);
            if(rs.next())
            {
            boundaries=rs.getInt(1);
            deadends=rs.getInt(2);
            encoding=rs.getString(3);
            }
            bw.append(Integer.toString(boundaries));
            bw.append(",");
            bw.append(Integer.toString(deadends));
            bw.append(",");
            bw.append(encoding);
            bw.append(",");
            bw.append(charClass);
            bw.append(System.lineSeparator());
        }
      bw.flush();
      bw.close();
    
    }
    
    public String getClassFromChar(String character)
    {
      String charClass="";
      if(character.equals("0"))
            charClass="ZERO";
        else if(character.equals("1"))
            charClass="ONE";
        else if(character.equals("2"))
            charClass="TWO";
        else if(character.equals("3"))
            charClass="THREE";
        else if(character.equals("4"))
            charClass="FOUR";
        else if(character.equals("5"))
            charClass="FIVE";
        else if(character.equals("6"))
            charClass="SIX";
        else if(character.equals("7"))
            charClass="SEVEN";
        else if(character.equals("8"))
            charClass="EIGHT";
        else if(character.equals("9"))
            charClass="NINE";
        else
            charClass=character;
      
      return charClass;
    }
    
}
