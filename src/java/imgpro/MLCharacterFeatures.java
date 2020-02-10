/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MLCharacterFeatures {
    
    public void modifyTestDB(ArrayList<Updates> ListMLChar,String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
        Database db=new Database();
        Connection con=db.connect();
        Statement Stmt=con.createStatement();
        String cname = null,modify,rec = null,clas = null;
        
        
        
        for(int i=0;i<ListMLChar.size()-1;i++)
        {
        cname=ListMLChar.get(i).ComponentName;
        clas=ListMLChar.get(i).CorrectTo;
        modify="update testimageanalysis set class='"+clas+"' where componentno='"+cname+"'";
        Stmt.executeUpdate(modify);
        }
        Stmt.close();
        //createMLCsv(con);
        createMLCsv(root,rand);
        
    }
    
    public void createMLCsv(String root,String rand) throws IOException, SQLException
        //    public void createMLCsv(Connection con,String root,String rand) throws IOException, SQLException
    {
    
    String writePath=root+"//root"+rand+"\\MLCharacterFeaturesCsv.csv";
    FileWriter fileWriter=new FileWriter(writePath);
    String sql="SELECT componentno,path,recognisedas,class,boundaries,deadends,concave_corner,convex_corner,"
            + "encoding,angleencoding,distance,cornerenc,allpointdirenc,allpointangenc,allpointdistenc FROM TESTIMAGEANALYSIS";
        /*Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery(sql);
    fileWriter.append("componentno");fileWriter.append(",");
    fileWriter.append("path");fileWriter.append(",");
    fileWriter.append("recognisedas");fileWriter.append(",");
    fileWriter.append("class");fileWriter.append(",");
    fileWriter.append("boundaries");fileWriter.append(",");
    fileWriter.append("deadends");fileWriter.append(",");
    fileWriter.append("concave_corner");fileWriter.append(",");
    fileWriter.append("convex_corner");fileWriter.append(",");
    fileWriter.append("encoding");fileWriter.append(",");
    fileWriter.append("angleencoding");fileWriter.append(",");
    fileWriter.append("distance");fileWriter.append(",");
    fileWriter.append("cornerenc");fileWriter.append(",");
    fileWriter.append("allpointdirenc");fileWriter.append(",");
    fileWriter.append("allpointangenc");fileWriter.append(",");
    fileWriter.append("allpointdistenc");
    fileWriter.append(System.lineSeparator());
   
    while(rs.next())
    {
    fileWriter.append(rs.getString(1));fileWriter.append(",");
    fileWriter.append(rs.getString(2));fileWriter.append(",");
    fileWriter.append(rs.getString(3));fileWriter.append(",");
    fileWriter.append(rs.getString(4));fileWriter.append(",");
    fileWriter.append(rs.getString(5));fileWriter.append(",");
    fileWriter.append(rs.getString(6));fileWriter.append(",");
    fileWriter.append(rs.getString(7));fileWriter.append(",");
    fileWriter.append(rs.getString(8));fileWriter.append(",");
    fileWriter.append(rs.getString(9));fileWriter.append(",");
    fileWriter.append(rs.getString(10));fileWriter.append(",");
    fileWriter.append(rs.getString(11));fileWriter.append(",");
    fileWriter.append(rs.getString(12));fileWriter.append(",");
    fileWriter.append(rs.getString(13));fileWriter.append(",");
    fileWriter.append(rs.getString(14));fileWriter.append(",");
    fileWriter.append(rs.getString(15));fileWriter.append(",");
    
    fileWriter.append(System.lineSeparator());
    }
    rs.close();
    fileWriter.flush();
    fileWriter.close();*/
    }
    
    }

