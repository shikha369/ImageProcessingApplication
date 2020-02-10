package imgpro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Distance {

    String content = "";
    BufferedReader fileReader;
    BufferedWriter fileWriter;
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";

    public ArrayList<Pix> BoundaryList = new ArrayList<Pix>();
    public ArrayList<Float> distanceList = new ArrayList<Float>();

    public double getDistance(Pix V0, Pix V1, Pix V2) {

        double dis;
        dis = (Math.pow((Math.pow((V1.x - V2.x), 2) + Math.pow((V1.y - V2.y), 2)), .5));

        return dis;
        // return cosTheta;
    }

    public String prepareDistanceList(String readPath) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        content = "";
        int x, y;
        fileReader = new BufferedReader(new FileReader(readPath));
        String line;
        Pix TraversePix;

        while ((line = fileReader.readLine()) != null) {

            String[] tokens = line.split(COMMA_DELIMITER);

            if (tokens.length > 0) {
                
                
                if("".equals(tokens[0]))
                {
                content=startEncoding(content);
                content=content+"# ";
                BoundaryList.clear();
                continue;
                }
                
                x = Integer.parseInt(tokens[0]);
                y = -1 * Integer.parseInt(tokens[1]);
                TraversePix = new Pix();
                TraversePix.x = x;
                TraversePix.y = y;
                BoundaryList.add(TraversePix);
            }
        }
        content=startEncoding(content);
        fileReader.close();
        return content;
    }
   //startEncoding();
         public String startEncoding(String content) throws IOException
     {
        double theta;
        Pix V0, V1, V2;
        for (int i = 0; i < BoundaryList.size(); i++) {
            V0 = BoundaryList.get(i);
            if (i == 0) {
                V1 = BoundaryList.get(BoundaryList.size() - 1);
            } else {
                V1 = BoundaryList.get(i - 1);
            }
            if (i == BoundaryList.size() - 1) {
                V2 = BoundaryList.get(0);
            } else {
                V2 = BoundaryList.get(i + 1);
            }

            theta = getDistance(V0, V1, V2);
            distanceList.add((float) theta);
            content = content + Float.toString((float) theta) + " ";
        }
        return content;
        
    }

    public String encode(int k,String rand,String root,int Nimage) throws IOException, ClassNotFoundException, SQLException // public static void main(String [] args) throws FileNotFoundException, IOException
    {
        String distanceEncoding = "";
        String content0, content1, content2, content3, content4;
        String readPath = root+"//root"+rand+"\\TenPercentCsv\\Image" + Nimage + "_component" + k + ".csv";
        String writePath = root+"//root"+rand+"\\Distance\\Image" + Nimage + "_component" + k + ".txt";
        fileWriter = new BufferedWriter(new FileWriter(writePath));
        content0 = prepareDistanceList(readPath);
        distanceList.clear();
        BoundaryList.clear();
        /*     readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv1\\Image"+driver.Nimage+"_component"+k + ".csv";
         content1=prepareDistanceList(readPath);distanceList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv2\\Image"+driver.Nimage+"_component"+k + ".csv";
         content2=prepareDistanceList(readPath);distanceList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv3\\Image"+driver.Nimage+"_component"+k + ".csv";
         content3=prepareDistanceList(readPath);distanceList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv4\\Image"+driver.Nimage+"_component"+k + ".csv";
         content4=prepareDistanceList(readPath);distanceList.clear();BoundaryList.clear();
        
         distanceEncoding=generalise(content0,content1,content2,content3,content4);
     
         return distanceEncoding;*/
        fileWriter.write(content0);
        fileWriter.flush();
        fileWriter.close();
        return content0;
        //return null;
        //fileReader.close();
    }
    /*
     public String generalise(String content0,String content1,String content2,String content3,String content4)
     {
     String encLR="";
     String[] enc0=content0.split(" ");String[] enc1=content1.split(" ");String[] enc2=content2.split(" ");
     String[] enc3=content3.split(" ");String[] enc4=content4.split(" ");
     double meanDist;
         
        
        
     for(int i=0;i<enc0.length;i++){
     meanDist=(Double.parseDouble(enc0[i])+Double.parseDouble(enc1[i])+Double.parseDouble(enc2[i])
     +Double.parseDouble(enc3[i])+Double.parseDouble(enc4[i]))/5;
     encLR=encLR+Double.toString(meanDist)+" ";
     }
     return encLR;
     }
    
     */
}
