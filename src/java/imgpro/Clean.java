/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author Shikha
 */
public class Clean {
    public void delete(String root) throws IOException
    {
//    String root=ProcessImages.rootPath; 
    File Docdir=new File(root+"\\UploadedDocs");
    File Adir=new File(root+"\\BoundingRectCsv");
    File Ldir=new File(root+"\\ComponentLabelCsv");
    File Edir=new File(root+"\\Encoding");
    File Odir=new File(root+"\\output");
    File Pdir=new File(root+"\\ModPerimeterCsv");
    File Fdir=new File(root+"\\RPerimeterCsv");
    File Tdir=new File(root+"\\TenPercentCsv");
    File MLdir=new File(root+"\\ML");
    File Dist=new File(root+"\\Distance");
    File UpClassifierDir=new File(root+"\\UploadedClassifier");
    File UpTrainingDir=new File(root+"\\UploadedTrainingFeature");
    
    File enc=new File(root+"\\LeftRight.txt");
    File encB=new File(root+"\\EncBoundary.txt");
    File encA=new File(root+"\\EncAngles.txt");
    File encD=new File(root+"\\distance.txt");
    File cornerCsv=new File(root+"\\Corners.csv");
    File classifier=new File(root+"\\Classifier.R");
    File training=new File(root+"\\trainedFeatures.csv");
    //delete recognisedAsCsv  and TestFeatures
    File test=new File(root+"\\tesFeatures.csv");
   File recChar=new File(root+"\\recognisedCharacters.csv");
   // File parFile=new File(imgpro.ProcessImages.ParameterPath+"/classes/config.properties");
    File param=new File(root+"\\Parameter.txt");
    
    //FileUtils.cleanDirectory(Imgdir);
    FileUtils.cleanDirectory(Docdir);
    FileUtils.cleanDirectory(Adir);
    FileUtils.cleanDirectory(Ldir);
    FileUtils.cleanDirectory(Edir);
    FileUtils.cleanDirectory(Odir);
    FileUtils.cleanDirectory(Pdir);
    FileUtils.cleanDirectory(Fdir);
    FileUtils.cleanDirectory(Tdir);
    FileUtils.cleanDirectory(MLdir);
    FileUtils.cleanDirectory(Dist);
    FileUtils.cleanDirectory(UpClassifierDir);
    FileUtils.cleanDirectory(UpTrainingDir);
    
    enc.delete();
    encB.delete();
    encA.delete();
    encD.delete();
    cornerCsv.delete();
    classifier.delete();
    training.delete();
    test.delete();
    recChar.delete();
    param.delete();
    }
    
}
