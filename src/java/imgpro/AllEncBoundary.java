
package imgpro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;


public class AllEncBoundary {
    
   BufferedReader fileReader;
   BufferedWriter fileWriter;
   String COMMA_DELIMITER = ",";
   String NEW_LINE_SEPARATOR = "\n";
 
   public String findDirection(int x0,int y0,int x1,int y1){
        String content=null;
        if(x0<x1 && y0==y1)
                    content="0";
                  else if(x0>x1 && y0==y1)
                    content="4";
                  else if(x0==x1 && y0>y1)
                    content="6";
                  else if(x0==x1 && y0<y1)
                    content="2";
                  else if(x0<x1 && y0>y1)
                    content="7";
                  else if(x0>x1 && y0<y1)
                    content="3";
                  else if(x0<x1 && y0<y1)
                    content="1";
                  else
                    content="5";
        return content;
   }
  
    public String encode(int k,String rand,String root,int Nimage) throws IOException, ClassNotFoundException, SQLException
    //public static void main(String [] args) throws FileNotFoundException, IOException
    {
        
        String encLR,content0,content1,content2,content3,content4;
        String readPath=root+"//root"+rand+"\\ModPerimeterCsv\\Image"+Nimage+"_component"+k + ".csv";
        content0=encodeOnce(readPath,rand,root);
   /*     readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv1\\Image"+driver.Nimage+"_component"+k + ".csv";
        content1=encodeOnce(readPath);
        readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv2\\Image"+driver.Nimage+"_component"+k + ".csv";
        content2=encodeOnce(readPath);
        readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv3\\Image"+driver.Nimage+"_component"+k + ".csv";
        content3=encodeOnce(readPath);
        readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv4\\Image"+driver.Nimage+"_component"+k + ".csv";
        content4=encodeOnce(readPath);
        encLR=generalise(content0,content1,content2,content3,content4);
        
        return encLR;*/
        content0=generalise(content0);
        return content0;
    }
    
    
    public String encodeOnce(String readPath,String rand,String root) throws IOException, ClassNotFoundException, SQLException
    {
        String encLR;
        EncodeLeftRight eLR=new EncodeLeftRight();
        
        String writePath=root+"//root"+rand+"\\EncBoundary.txt";
        File file =new File(readPath);
       // fileWriter=new BufferedWriter(new FileWriter(writePath));
        //int NoOfFiles=new File("C:\\Users\\Shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv").listFiles().length;
        //System.out.println(NoOfFiles);
         String content;
         int startx = 0,starty = 0,x0 = 0,y0 = 0,x1 = 0,y1 = 0,x,y;
         boolean startFlag;
    
         startFlag=true;
         fileWriter=new BufferedWriter(new FileWriter(writePath,true));
      //   fileWriter.append(file.getName()+" ");
         fileReader = new BufferedReader(new FileReader(readPath));
         String line;
         content="";      
           while ((line = fileReader.readLine()) != null) 
           {
            String[] tokens = line.split(COMMA_DELIMITER);
                  if (tokens.length > 0) {
                      
                      
                     if("".equals(tokens[0]))
                      {
                          x0=x1;
                          y0=y1;
                          x1=startx;
                          y1=starty;
                  
                          content=content+findDirection(x0, y0, x1, y1);
                          content=content+" # ";
                          startFlag=true;
                          continue;
                      }
                 
                   x=Integer.parseInt(tokens[0]);
                   y=Integer.parseInt(tokens[1]); // re-negate y coordinates                 
                  if (startFlag==true)
                  {
                  startFlag=false;   //startPixel
                  startx=x;
                  starty=y;
                  x0=x;
                  y0=y;
                  x1=x;
                  y1=y;
                  continue;
                  }
                  else
                  {
                  x0=x1;
                  y0=y1;
                  x1=x;
                  y1=y;
                  
                  content=content+findDirection(x0, y0, x1, y1);
                  content=content+" ";
                  }
           }
           }
            if(x0!=startx && y0!=starty)
            {  x0=x1;
                  y0=y1;
                  x1=startx;
                  y1=starty;
                  
                  content=content+findDirection(x0, y0, x1, y1);
            }
                 fileWriter.append(content);
                 fileWriter.append(System.getProperty("line.separator"));
                 fileWriter.flush();

                fileWriter.close();
                fileReader.close();
                
                //call encode left right here and dont write to txt file hus insert into db
                encLR=eLR.encode(content);
                return encLR;
               
    }
  /*  
    public String generalise(String content0,String content1,String content2,String content3,String content4)
    {
       String encLR="";
        String[] enc0=content0.split(" ");String[] enc1=content1.split(" ");String[] enc2=content2.split(" ");
        String[] enc3=content3.split(" ");String[] enc4=content4.split(" ");
        int countL,countR,countS,countA;
        
        for(int i=0;i<enc0.length;i++){
            countL=0;countR=0;countS=0;countA=0;
           if("L".equals(enc0[i]))
               countL++;
           else if("R".equals(enc0[i]))
               countR++;
           else if("S".equals(enc0[i]))
               countS++;
           else if("A".equals(enc0[i]))
               countA++;
           
           if("L".equals(enc1[i]))
               countL++;
           else if("R".equals(enc1[i]))
               countR++;
           else if("S".equals(enc1[i]))
               countS++;
           else if("A".equals(enc1[i]))
               countA++;
           
           if("L".equals(enc2[i]))
               countL++;
           else if("R".equals(enc2[i]))
               countR++;
           else if("S".equals(enc2[i]))
               countS++;
           else if("A".equals(enc2[i]))
               countA++;
           
           if("L".equals(enc3[i]))
               countL++;
           else if("R".equals(enc3[i]))
               countR++;
           else if("S".equals(enc3[i]))
               countS++;
           else if("A".equals(enc3[i]))
               countA++;
           
           if("L".equals(enc4[i]))
               countL++;
           else if("R".equals(enc4[i]))
               countR++;
           else if("S".equals(enc4[i]))
               countS++;
           else if("A".equals(enc4[i]))
               countA++;
           
        int max=Math.max(countL,countR);
        max=Math.max(max,countA);
        max=Math.max(max,countS);
        if(max==countL)
            encLR=encLR+"L ";
        else if(max==countR)
            encLR=encLR+"R ";
        else if(max==countA)
            encLR=encLR+"A ";
        else if(max==countS)
            encLR=encLR+"S ";
        
            
        }
        return encLR;
    }
    
    */
    public String generalise(String content0)
    {
    String encLR="";
    String[] enc0=content0.split(" ");
    for(int i=1;i<enc0.length;i++){
        //code to seggregate S to LS and RS
        if(("L".equals(enc0[i-1])&&"S".equals(enc0[i]))||(("LS".equals(enc0[i-1])&&"S".equals(enc0[i]))))
            enc0[i]="LS";
        if(("R".equals(enc0[i-1])&&"S".equals(enc0[i]))||(("RS".equals(enc0[i-1])&&"S".equals(enc0[i]))))
            enc0[i]="RS";
        if("#".equals(enc0[i]))
            enc0[i]="#";
    
    }
     for(int i=0;i<enc0.length;i++){
      encLR=encLR+enc0[i]+" ";   
     }
     
    
    return encLR;
    }
} 
