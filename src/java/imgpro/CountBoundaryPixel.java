
package imgpro;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class CountBoundaryPixel {
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
   // Database db=new Database();
   // Connection con;
    Statement stmt;
    String sql;
    int[] boundaryPixels=new int[3];
    int Percent,gap,number;
    private FileWriter fileWriter;
    //fileWriter1,fileWriter2,fileWriter3,fileWriter4;
    int count,x,y;
    public int calculateTenPercent(int k,int B0,int B1,int B2,String root,String rand,int Nimage) throws ClassNotFoundException, SQLException, IOException
    {
        //String root=ProcessImages.rootPath;
       
      //con=db.connect();
      /*
        stmt=ProcessImages.con.createStatement();
        sql="Select PERIMETER,INTERNAL_ONE,INTERNAL_TWO from imageanalysis where COMPONENTNO='Image"+driver.Nimage+"_component"+k+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
           {
           boundaryPixels[0]=rs.getInt(1);
           boundaryPixels[1]=rs.getInt(2);
           boundaryPixels[2]=rs.getInt(3);
           }
        rs.close();
        *
        */
        ArrayList<Integer> xc=new ArrayList<Integer>();
        ArrayList<Integer> yc=new ArrayList<Integer>();
        boundaryPixels[0]=B0;
        boundaryPixels[1]=B1;
        boundaryPixels[2]=B2;
        fileWriter = new FileWriter(root+"//root"+rand+"\\TenPercentCsv\\Image"+Nimage+"_component"+k + ".csv");
       /*
          fileWriter1 = new FileWriter("C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv1\\Image"+driver.Nimage+"_component"+k + ".csv");
          fileWriter2 = new FileWriter("C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv2\\Image"+driver.Nimage+"_component"+k + ".csv");
          fileWriter3 = new FileWriter("C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv3\\Image"+driver.Nimage+"_component"+k + ".csv");
          fileWriter4 = new FileWriter("C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv4\\Image"+driver.Nimage+"_component"+k + ".csv");
        
          */
          BufferedReader fileReader = new BufferedReader(new FileReader(root+"//root"+rand+"\\ModPerimeterCsv\\Image"+Nimage+"_component"+k + ".csv"));
           
        
           
          // Percent=; // no of pixels one greater than no of gaps ... #gaps = 5 to get 6 pixels 
          // gap=boundaryPixels[b]/Percent;
          /*
          ******************logic to calculate gap here******************
          1.calculate approximate width and suppose u need 4 points in that width
          2.calculate gap = approximate width/4
          3.window size is statically 4 while finding singular points
          */
          //gap=7;
          String setgap = "";
          int width;
          String imageName="Image"+Nimage+"_component"+k;
          ApproximateWidth ap=new ApproximateWidth();
          width=ap.getApproximateWidth(imageName,root,rand); //pass image name to get approximate width ...
         // gap=(width/3)+1; //suppose i want 4 points in one width...gap will b calculated as shown..
          //gap=(width/2)+1;
          gap=(int) Math.floor(0.75*width);
          /*Properties prop = new Properties();
	  OutputStream output = null;
          output = new FileOutputStream("config.properties");
	  String propFileName = "config.properties";
          prop.setProperty(setgap, Integer.toString(gap));
          prop.store(output, null);
          */
          String line ;
          number=0;
           while ((line = fileReader.readLine()) != null) 
           {
               String[] tokens = line.split(COMMA_DELIMITER);
               
               if (tokens.length > 0) {
               
                   if("".equals(tokens[0]))
                    //count=0;
                    break;
                   else
             {
                 x=Integer.parseInt(tokens[0]);
                 y=Integer.parseInt(tokens[1]);
                 xc.add(x);
                 yc.add(y);
             }}}
           int length=xc.size();
                   
               //if(count==0||count%Percent==0)
         //       int startx0=xc.get(0);startx0++;int starty0=yc.get(0);
                /*
                int startx1=xc.get(1);startx1++;int starty1=yc.get(1);
                int startx2=xc.get(2);startx2++;int starty2=yc.get(2);
                int startx3=xc.get(3);startx3++;int starty3=yc.get(3);
                int startx4=xc.get(4);startx4++;int starty4=yc.get(4);
                        */
                count=0;
                while(number<length)
                {
                    //x=xc.get(number%length);
                    //y=yc.get(number%length);
                if(count==0)   
                 {
                 // 2. write val 
                    fileWriter.append(String.valueOf(xc.get(number%length)));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(-1*yc.get(number%length)));
                    fileWriter.append(NEW_LINE_SEPARATOR);
             /*       
                    fileWriter1.append(String.valueOf(xc.get((number+1)%length)));
                    fileWriter1.append(COMMA_DELIMITER);
                    fileWriter1.append(String.valueOf(-1*yc.get((number+1)%length)));
                    fileWriter1.append(NEW_LINE_SEPARATOR);
                 
                    fileWriter2.append(String.valueOf(xc.get((number+2)%length)));
                    fileWriter2.append(COMMA_DELIMITER);
                    fileWriter2.append(String.valueOf(-1*yc.get((number+2)%length)));
                    fileWriter2.append(NEW_LINE_SEPARATOR);
                    
                    fileWriter3.append(String.valueOf(xc.get((number+3)%length)));
                    fileWriter3.append(COMMA_DELIMITER);
                    fileWriter3.append(String.valueOf(-1*yc.get((number+3)%length)));
                    fileWriter3.append(NEW_LINE_SEPARATOR);

                    fileWriter4.append(String.valueOf(xc.get((number+4)%length)));
                    fileWriter4.append(COMMA_DELIMITER);
                    fileWriter4.append(String.valueOf(-1*yc.get((number+4)%length)));
                    fileWriter4.append(NEW_LINE_SEPARATOR);
              */      
               }
               //if count=1,2,3,4,5 then write to different folders and repeat same  for encoding process for all
               number++;
               count=(count+1)%gap;     
               } 
        
     
           
                 //   fileWriter.append(String.valueOf(startx0));
                //    fileWriter.append(COMMA_DELIMITER);
                //    fileWriter.append(String.valueOf(-starty0));
                //    fileWriter.append(NEW_LINE_SEPARATOR);
           /*/
           
                    fileWriter1.append(String.valueOf(startx1));
                    fileWriter1.append(COMMA_DELIMITER);
                    fileWriter1.append(String.valueOf(-starty1));
                    fileWriter1.append(NEW_LINE_SEPARATOR);
           
           
                    fileWriter2.append(String.valueOf(startx2));
                    fileWriter2.append(COMMA_DELIMITER);
                    fileWriter2.append(String.valueOf(-starty2));
                    fileWriter2.append(NEW_LINE_SEPARATOR);
           
           
                    fileWriter3.append(String.valueOf(startx3));
                    fileWriter3.append(COMMA_DELIMITER);
                    fileWriter3.append(String.valueOf(-starty3));
                    fileWriter3.append(NEW_LINE_SEPARATOR);
           
           
                    fileWriter4.append(String.valueOf(startx4));
                    fileWriter4.append(COMMA_DELIMITER);
                    fileWriter4.append(String.valueOf(-starty4));
                    fileWriter4.append(NEW_LINE_SEPARATOR);
           */
          
           
           
         fileWriter.flush();
         fileWriter.close();
    /*   fileWriter1.flush();
         fileWriter1.close();
         fileWriter2.flush();
         fileWriter2.close();
         fileWriter3.flush();
         fileWriter3.close();
         fileWriter4.flush();
         fileWriter4.close();
            */
         fileReader.close();
      // con.close();
         return gap;
}  
}
