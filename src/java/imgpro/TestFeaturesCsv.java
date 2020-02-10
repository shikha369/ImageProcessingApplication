package imgpro;

//import static imgpro.ProcessImages.CharacterList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestFeaturesCsv {

    public void writeCSV(ArrayList<ComponentInfo> CharacterList, String root, String rand, int source) throws IOException {
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        String writePath;
        if (source == 0) {
            writePath = root + "//root" + rand + "\\downloadTestFeatures.csv";
        } else {
            writePath = root + "//root" + rand + "\\downloadTestFeaturesAfterCorrection.csv";
        }
        FileWriter fileWriter = new FileWriter(writePath);
        ComponentInfo ci;
        fileWriter.write("cname,path,wordType,UpperModifier,LowerModifier,filename,Label,TLX,TLY,TRX,TRY,BLX,BLY,BRX,BRY,width,height,BinaryMatrix,"
                + "singularPointEncoding,PointStrokeEncoding,deadends,inwardCorner,sharpInwardCorner,outwardCorner"
                + ",cone,bucket,coneEnc,buckEnc,ratio_p_by_a,boundaries,encoding,angleencoding,distance,allLREncoding,allAngleEncoding,"
                + "allDistanceEncoding,velocity,acceleration,gapvelocity,gapacceleration,centeredAverageangleEnc");
        
        fileWriter.write(NEW_LINE_SEPARATOR);

        for (int n = 0; n < CharacterList.size(); n++) {
            ci = CharacterList.get(n);

            fileWriter.write(ci.name);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.path);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Integer.toString(ci.wordType));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Boolean.toString(ci.upperModifier));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Boolean.toString(ci.lowerModifier));
            fileWriter.write(COMMA_DELIMITER);

            fileWriter.write(ci.LabelFromFileName + "," + ci.CharClass + "," + Integer.toString(ci.topx) + "," + Integer.toString(ci.topy) + ","
                    + Integer.toString(ci.botx) + "," + Integer.toString(ci.topy) + "," + Integer.toString(ci.topx) + ","
                    + Integer.toString(ci.boty) + "," + Integer.toString(ci.botx) + "," + Integer.toString(ci.boty) + ","
                    + Integer.toString(ci.width) + "," + Integer.toString(ci.height) + "," + ci.bmatrix);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.cornerEnc + "," + ci.pointStrokeEnc + ","+ Integer.toString(ci.deadEnds) + "," + Integer.toString(ci.inwardCorners) + ","
                    + Integer.toString(ci.sharpInwardCorners) + "," + Integer.toString(ci.outwardCorners));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Integer.toString(ci.cone) + "," + Integer.toString(ci.bucket) + ","
                    + ci.coneEnc + "," + ci.bucketEnc);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Double.toString(ci.ratio));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(Integer.toString(ci.nBoundaries));
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.LREncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.AngleEncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.DistanceEncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.allLREncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.allAngleEncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.allDistanceEncoding);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.velocity);
           // System.out.println("from FeaturecLASS");
            // System.out.println("velocity");
            // System.out.println(ci.velocity);
            fileWriter.write(COMMA_DELIMITER);
            // System.out.println("acceleration");
            fileWriter.write(ci.acceleration);
            //  System.out.println(ci.acceleration);
            fileWriter.write(COMMA_DELIMITER);
           // System.out.println("gapvelo");
            fileWriter.write(ci.gapVelocity);
            fileWriter.write(COMMA_DELIMITER);
          //  System.out.println("hello");
            fileWriter.write(ci.gapAcceleration);
            fileWriter.write(COMMA_DELIMITER);
            fileWriter.write(ci.centAvgEnc);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        }

        fileWriter.flush();
        fileWriter.close();
    }

}
