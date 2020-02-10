

package imgpro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import static org.apache.commons.io.FileUtils.copyFile;

public class ZipBeforeClassification {
 public String randomFileName;
 
    List<String> filesListInDir = new ArrayList<String>();
    public void ProcedureZipAndDownload(String root,String rand) throws IOException {
        
        
        //java.util.Date date= new java.util.Date();
       // String rand=TimeStamp.rand;
        //String root=ProcessImages.rootPath;
        String source1 = root+"//root"+rand+"\\ComponentLabelCsv";
        String source2 = root+"//root"+rand+"\\output";
        File srcDir1 = new File(source1);
        File srcDir2 = new File(source2);
        File sourceMLDataCsv = new File(root+"//root"+rand+"\\downloadTestFeatures.csv");
        File destinationMLDataCsv = new File(root+"//root"+rand+"\\BeforeClassificationML"+rand+"\\downloadTestFeatures.csv");

        String destination = root+"//root"+rand+"\\BeforeClassificationML"+rand;
        File destDir = new File(destination);

        try {

            // Copy source directories into destination directory
            FileUtils.copyDirectory(srcDir1, destDir);
            FileUtils.copyDirectory(srcDir2, destDir);
          // Copy ML data csv to Destination Directory

            File dir = new File(root+"//root"+rand+"\\BeforeClassificationML"+rand);
            String zipDirName = root+"//root"+rand+"\\zippedBeforeClassificationML\\BeforeClassificationML"+rand+".zip";

            copyFile(sourceMLDataCsv, destinationMLDataCsv);

            ZipBeforeClassification zipFiles = new ZipBeforeClassification();
            zipFiles.zipDirectory(dir, zipDirName);

            //rename zipped File acc. to timeStamp ..  
            File oldDir=new File(zipDirName);
            randomFileName=root+"//root"+rand+"\\zippedBeforeClassificationML\\BeforeClassificationML"+rand+".zip";
            File timestamped_zipDir = new File(randomFileName);
            oldDir.renameTo(timestamped_zipDir);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zipDirectory(File dir, String zipDirName) {
        try {
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String filePath : filesListInDir) {
                //System.out.println("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                filesListInDir.add(file.getAbsolutePath());
            } else {
                populateFilesList(file);
            }
        }
    }
}
