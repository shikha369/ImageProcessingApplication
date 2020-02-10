/*
 * To create feature vector CSV
 */

package imgpro;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FeatureVector {
    
    public void generateFeatureVectorCsv(ArrayList<ComponentInfo> CharacterList,String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
    int nBoundaries = 0,deadends = 0;
    String encoding = null;
    Database db=new Database();
    Connection con=db.connect();
    Statement stmt=con.createStatement();
    String sql;
    ResultSet rs;
    String sortedCharName;
    //trainedFeatures created statically
    String writePath=root+"//root"+rand+"\\testFeatures.csv" ;
    FileWriter fileWriter=new FileWriter(writePath);
    fileWriter.append("cname");
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append("boundaries");
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append("deadends");
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append("cornerenc");
    fileWriter.append(NEW_LINE_SEPARATOR);
    for(int i=0;i<CharacterList.size();i++)
    {
        /*should be adaptive according to OCR 
         * 1/14/2016 -- feature vector comprise of [No of boundaries,No of deadends,Encoding]
         */
        
        /*
         *use it to update ComponentInfo as well
         */
    sortedCharName=CharacterList.get(i).name;
    sql="SELECT BOUNDARIES,DEADENDS,CORNERENC FROM TESTIMAGEANALYSIS where componentno='"+sortedCharName+"'";
    rs=stmt.executeQuery(sql);
    if(rs.next())
    {
        nBoundaries=rs.getInt(1);
        deadends=rs.getInt(2);
        encoding=rs.getString(3);
    }
    CharacterList.get(i).nBoundaries=nBoundaries;
    CharacterList.get(i).deadEnds=deadends;
    CharacterList.get(i).cornerEnc=encoding;
    fileWriter.append(sortedCharName);
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append(Integer.toString(nBoundaries));
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append(Integer.toString(deadends));
    fileWriter.append(COMMA_DELIMITER);
    fileWriter.append(encoding);
    fileWriter.append(NEW_LINE_SEPARATOR);
    }
    con.close();
    fileWriter.flush();
    fileWriter.close();
    }
    
}
