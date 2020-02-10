package imgpro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Shikha
 */
public class EncAlpha {

    BufferedReader fileReader;
    BufferedWriter fileWriter;
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";

    public ArrayList<Pix> BoundaryList = new ArrayList<Pix>();
    public ArrayList<Float> angleList = new ArrayList<Float>();

    public double dotProd(int[] a, int[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    /*  public double getAngle(Pix V0, Pix V1, Pix V2) {

     double cosTheta, mod, theta, Nr;
     int[] xVal = new int[2];
     int[] yVal = new int[2];
     xVal[0] = V1.x - V0.x;
     xVal[1] = V2.x - V0.x;
     yVal[0] = V1.y - V0.y;
     yVal[1] = V2.y - V0.y;
     int[] a = {xVal[0], yVal[0]};
     int[] b = {xVal[1], yVal[1]};

     mod = (Math.pow((Math.pow(xVal[0], 2) + Math.pow(yVal[0], 2)), .5)) * (Math.pow((Math.pow(xVal[1], 2) + Math.pow(yVal[1], 2)), .5));
     Nr = dotProd(a, b);
     cosTheta = Nr / (Math.round(mod * 100.0) / 100.0);
     if (cosTheta == -1) {
     theta = 180;
     } else {
     theta = Math.ceil(Math.toDegrees(Math.acos(cosTheta)));
     }
     return theta;
     // return cosTheta;
     }
     */
    public double getAngle(Pix V0, Pix V1, Pix V2) {

        int direction;
        double cosTheta, mod, theta, Nr;
        int[] xVal = new int[2];
        int[] yVal = new int[2];
        xVal[0] = V1.x - V0.x;
        xVal[1] = V2.x - V0.x;
        yVal[0] = V1.y - V0.y;
        yVal[1] = V2.y - V0.y;
        //vector a and b
        int[] a = {xVal[0], yVal[0]};
        int[] b = {xVal[1], yVal[1]};

        //get direction by cross product and magnitude by dot product
        direction = (int) Math.signum(a[0] * b[1] - a[1] * b[0]);
        mod = (Math.pow((Math.pow(xVal[0], 2) + Math.pow(yVal[0], 2)), .5)) * (Math.pow((Math.pow(xVal[1], 2) + Math.pow(yVal[1], 2)), .5));
        Nr = dotProd(a, b);
        cosTheta = Nr / (Math.round(mod * 100.0) / 100.0);
        if (cosTheta == -1) {
            theta = 180;
        } else {
            theta = Math.ceil(Math.toDegrees(Math.acos(cosTheta)));
        }
        
        /*convention reversed coz of negative y axis*/
        if (direction ==-1) {
            return theta;
        } else {
            return 360 - theta;
        }
        // return cosTheta;
    }

    public String prepareAngleList(String path) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        boolean startFlag;
        int x, y;
        fileReader = new BufferedReader(new FileReader(path));
        String line = "";
        Pix TraversePix;
        String content = "";
        int startx, starty, lenList;
        while ((line = fileReader.readLine()) != null) {

            String[] tokens = line.split(COMMA_DELIMITER);

            if (tokens.length > 0) {

                if ("".equals(tokens[0])) {
                    content = startEncoding(content);
                    content = content + "# ";
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

        content = startEncoding(content);
        fileReader.close();
        return content;
    }

    public String startEncoding(String content) throws IOException {
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

            theta = getAngle(V0, V1, V2);
            angleList.add((float) theta);
            content = content + Float.toString((float) theta) + " ";

        }
        return content;
    }

    public String encode(int k, String rand, String root, int Nimage) throws IOException, ClassNotFoundException, SQLException //  public void main(String [] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException
    {
        String angleEncoding = "";
        String content0, content1, content2, content3, content4;
        String readPath = root + "//root" + rand + "\\TenPercentCsv\\Image" + Nimage + "_component" + k + ".csv";
        content0 = prepareAngleList(readPath);
        angleList.clear();
        BoundaryList.clear();
        /*     readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv1\\Image"+driver.Nimage+"_component"+k + ".csv";
         content1=prepareAngleList(readPath);angleList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv2\\Image"+driver.Nimage+"_component"+k + ".csv";
         content2=prepareAngleList(readPath);angleList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv3\\Image"+driver.Nimage+"_component"+k + ".csv";
         content3=prepareAngleList(readPath);angleList.clear();BoundaryList.clear();
         readPath="C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\TenPercentCsv4\\Image"+driver.Nimage+"_component"+k + ".csv";
         content4=prepareAngleList(readPath);angleList.clear();BoundaryList.clear();
         angleEncoding=generalise(content0,content1,content2,content3,content4);
        
     
         return angleEncoding;
         */
        content0 = generalise(content0);
        return content0;
    }

    // public String generalise(String content0,String content1,String content2,String content3,String content4)
    public String generalise(String content0) {
        String encLR = "";
        String[] enc0 = content0.split(" ");
        /*String[] enc1=content1.split(" ");String[] enc2=content2.split(" ");
         String[] enc3=content3.split(" ");String[] enc4=content4.split(" ");
         int countV,countS,countX; 
         /* V : Less than 115
         * X : 115 to 150
         * S : >150
         */

        /*    for(int i=0;i<enc0.length;i++){
         countV=0;countS=0;countX=0;
         if(Double.parseDouble(enc0[i])<=115)
         countV++;
         else if(Double.parseDouble(enc0[i])<=150)
         countX++;
         else
         countS++;
           
           
         if(Double.parseDouble(enc1[i])<=115)
         countV++;
         else if(Double.parseDouble(enc1[i])<=150)
         countX++;
         else
         countS++;
           
         if(Double.parseDouble(enc2[i])<=115)
         countV++;
         else if(Double.parseDouble(enc2[i])<=150)
         countX++;
         else
         countS++;
           
           
         if(Double.parseDouble(enc3[i])<=115)
         countV++;
         else if(Double.parseDouble(enc3[i])<=150)
         countX++;
         else
         countS++;
           
           
         if(Double.parseDouble(enc4[i])<=115)
         countV++;
         else if(Double.parseDouble(enc4[i])<=150)
         countX++;
         else
         countS++;
           
           
         int max=Math.max(countV,countX);
         max=Math.max(max,countS);
         if(max==countV)
         encLR=encLR+"V ";
         else if(max==countX)
         encLR=encLR+"X ";
         else if(max==countS)
         encLR=encLR+"S ";
         }
            
         */
        for (int i = 0; i < enc0.length; i++) {
            /*
             if("#".equals(enc0[i]))
             encLR = encLR+"# " ; 
             else{
             if (Double.parseDouble(enc0[i]) <= 115) {
             encLR = encLR + "V ";
             } else if (Double.parseDouble(enc0[i]) <= 150) {
             encLR = encLR + "X ";
             } else if((Double.parseDouble(enc0[i]) > 150)) {
             encLR = encLR + "S ";
             }
             }
             */
            if ("#".equals(enc0[i])) {
                encLR = encLR + "# ";
            } else {
                encLR = encLR + enc0[i] + " ";
                //encLR = encLR + "S ";
            }

        }
        return encLR;
    }

}
