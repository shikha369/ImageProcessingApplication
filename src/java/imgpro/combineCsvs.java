/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Shikha
 */
public class combineCsvs {
    
    public void CombineAndDownload(String inputPath,String outputPath) throws IOException
    {
    File[] fileList = new File(inputPath).listFiles();
        FileWriter fileWriter = new FileWriter(outputPath+"\\TrainingFeatures.csv");
        BufferedReader fileReader;
        String line ;
        boolean first=true;
        for (File image : fileList) {
            System.out.println(image.getAbsolutePath());
            fileReader = new BufferedReader(new FileReader(image.getAbsolutePath()));
            
           // int x, y, count = 0;
            //ArrayList<Integer> width=new ArrayList<Integer>();
            //ArrayList<Integer> min_Width=new ArrayList<Integer>();
            line = fileReader.readLine();
            if(first){
            fileWriter.write(line);
            fileWriter.write(System.lineSeparator());
            first=false;
            }
            while ((line = fileReader.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.write(System.lineSeparator());
            }
            fileReader.close();
        }
        fileWriter.flush();
        fileWriter.close();
        
        
        
    }
}
