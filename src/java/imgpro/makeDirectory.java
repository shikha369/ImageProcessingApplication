package imgpro;

import java.io.File;

public class makeDirectory {

    public void make(String rootPath, String rand) {
        new File(rootPath + "//root" + rand + "//UploadedDocs").mkdirs();
        new File(rootPath + "//root" + rand + "//BoundingRectCsv").mkdirs();
        new File(rootPath + "//root" + rand + "//ComponentLabelCsv").mkdirs();
        //new File(rootPath + "//root" + rand + "//Default").mkdirs();
        new File(rootPath + "//root" + rand + "//Distance").mkdirs();
        new File(rootPath + "//root" + rand + "//Encoding").mkdirs();
        new File(rootPath + "//root" + rand + "//ML").mkdirs();
        new File(rootPath + "//root" + rand + "//ModPerimeterCsv").mkdirs();
        new File(rootPath + "//root" + rand + "//output").mkdirs();
        new File(rootPath + "//root" + rand + "//RPerimeterCsv").mkdirs();
        new File(rootPath + "//root" + rand + "//TenPercentCsv").mkdirs();
        new File(rootPath + "//root" + rand + "//UploadedClassifier").mkdirs();
        new File(rootPath + "//root" + rand + "//UploadedParameter").mkdirs();
        new File(rootPath + "//root" + rand + "//UploadedTrainingFeature").mkdirs();
        new File(rootPath + "//root" + rand + "//zippedBeforeClassificationML").mkdirs();
        new File(rootPath + "//root" + rand + "//zippedML").mkdirs();
      //  new File(rootPath + "//root" + rand + "//UploadedDocs").mkdir();

    }
}
