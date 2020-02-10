package imgpro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class driver {

    public ComponentInfo character;
    public ArrayList<ComponentInfo> CharList = new ArrayList<ComponentInfo>();
    public int Ncomponents, Nimage;
    public String fileName;
    BufferedImage original, grayscale, binarized, compImage;
    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";

    void writeImage(String output) throws IOException {
        File file = new File(output + ".jpg");
        ImageIO.write(binarized, "jpg", file);
    }

    String convertMatrixToString(int[][] bmatrix) {
        String matrixString = "";
        for (int r = 0; r < bmatrix.length; r++) {
            for (int c = 0; c < bmatrix[0].length; c++) {
                if (bmatrix[r][c] == 0) {
                    matrixString = matrixString + "0";
                } else {
                    matrixString = matrixString + "1";
                }
            }
            matrixString = matrixString + "#";
        }
        return matrixString;
    }

    @SuppressWarnings("empty-statement")
    public EncapsulatedResult processImage(String path, String imgName, int Nimages, String rootpath, String rand,
            ArrayList<ComponentRecognisedAs> RecognisedCharacetrList,
            ArrayList<ComponentInfo> CharacterList, ArrayList<Double> DistanceBetweenCharacetrs,
            double meanDistancebetweenCharacters) throws IOException, Exception {

        String root = rootpath;
        int index;
        Nimage = Nimages;
        index = imgName.indexOf(".");
        fileName = imgName.substring(0, index);   //this charclass will b usd as long as we are working on characters
        int cp[][], nr = 0, nc, a = 0;
        File componentFile;
        FileWriter fileWriter = null;
        File original_f = new File(path);
        original = ImageIO.read(original_f);

        OtsuBinarize ob = new OtsuBinarize();
        grayscale = ob.toGray(original);
        binarized = ob.binarize(grayscale);
        writeImage(root + "//root" + rand + "\\UploadedDocs\\bin.jpg");

        TraceBoundary1 tb = new TraceBoundary1();
        LabelImage Li = new LabelImage();
        SortingModule sortObj = new SortingModule(); /*invoke sortObj.SortCharacetrs(CharList); @end @after all processing has been done*/

        WordsArrangement wordArrObj = new WordsArrangement();

        //labelling to 0 and 1
        int pix[][] = label.labelImg(binarized);

        CharList = Li.labelImage(pix, binarized, root, rand, Nimages, Ncomponents);

        String cpath;
        int area, width, height;

        for (int k = 1; k <= CharList.size(); k++) {

            character = new ComponentInfo();
            character = CharList.get(k - 1); // component no start from 1 to k ... index in arraylist start from 0 to k-1
            System.out.println(character.boundingRectangleArea);
            cpath = root + "//root" + rand + "\\output\\Image" + Nimage + "_component" + k + ".jpg";
            //insert sr.no ,cpath in table here
            componentFile = new File(cpath);
            compImage = ImageIO.read(componentFile);
            int cPix[][] = label.labelImg(compImage);
            character.bmatrix = convertMatrixToString(cPix);
            height = cPix.length;
            width = cPix[0].length;
            area = width * height;
            character.path = cpath;
            character.height = height;
            character.width = width;
            character.LabelFromFileName = fileName;
            // String sql = "UPDATE IMAGEANALYSIS SET PATH='"+cpath+"',AREABOUNDINGRECT="+area+",WIDTH="+width+",HEIGHT="+height+" WHERE COMPONENTNO='Image"+driver.Nimage+"_component"+k+"'";
            // stmtt.executeUpdate(sql);
            CharList.set(k - 1, character);

            try {
                fileWriter = new FileWriter(root + "//root" + rand + "\\BoundingRectCsv\\Image" + Nimage + "_component" + k + ".csv");
                //csv is created with row col interchange ... to read as buffered image do appropriate conversion
                for (int i = 0; i < cPix.length; i++) {
                    for (int j = 0; j < cPix[0].length; j++) {
                        fileWriter.append(String.valueOf(cPix[i][j]));
                        fileWriter.append(COMMA_DELIMITER);
                    }
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fileWriter.flush();
                fileWriter.close();
                cPix = null;
            }
        }
        NewCoordinatesSystem trans = new NewCoordinatesSystem();
       // TenPercent calcTen=new TenPercent();

        // code to calculate perimeter from csv generated for corressponding components using contour tracing algorithm in 8 connectivity 
        // neighbourhood ......
        CountBoundaryPixel calcTen = new CountBoundaryPixel();
        EncBoundary encode = new EncBoundary();
        EncAlpha encodeAngles = new EncAlpha();
        Distance objDist = new Distance();
        AllEncBoundary allencode = new AllEncBoundary();
        AllEncAlpha allencodeAngles = new AllEncAlpha();
        AllDistance allobjDist = new AllDistance();
        angleVelocity vel = new angleVelocity();
        angleAcceleration acc = new angleAcceleration();
        gapAngleVelocity gav = new gapAngleVelocity();
        gapAngleAcceleration gac = new gapAngleAcceleration();
        FindSingularPoints fsp = new FindSingularPoints();
        Segregate seg = new Segregate();
        String[] bEnc;
        EncapsulatePointStrokeResult enps;
        bucketClass bc=new bucketClass();
        coneClass cc=new coneClass();
        centeredAverage cAvg=new centeredAverage();

        for (int k = 1; k <= CharList.size(); k++) {
            System.out.println("component number: " + k);
            character = new ComponentInfo();
            character = CharList.get(k - 1);
            character = tb.traceComponentBoundary(k, character, root, rand, Nimage);
            CharList.set(k - 1, character);
            //calculate ratio of p/a ...... retrieve 'a' here 
          /*
             String sqlr="Select AREABOUNDINGRECT,PERIMETER from imageanalysis where componentno='Image"+driver.Nimage+"_component"+k+"'";
             rs=stmt.executeQuery(sqlr);
             if(rs.next())
             {
             a=rs.getInt(1);
             nr=rs.getInt(2);
             }
             rs.close();
             */
            a = character.boundingRectangleArea;
            nr = character.perimeter;
            float r = (float) nr / (float) a;

            //   String sqlp = "UPDATE IMAGEANALYSIS SET PERIMETER="+nr+",RATIO_P_BY_A="+r+" WHERE COMPONENTNO='Image"+driver.Nimage+"_component"+k+"'";
            //   stmtt.executeUpdate(sqlp);
            //translate coordinates..
            character.ratio = r;

            trans.translateCoordinates(k, character.startX, character.startY, root, rand, Nimages);
            character.strokeWidth=calcTen.calculateTenPercent(k, character.B0, character.B1, character.B2, root, rand, Nimages);
            character.LREncoding = encode.encode(k, rand, root, Nimages);
            character.AngleEncoding = encodeAngles.encode(k, rand, root, Nimages);
            character.DistanceEncoding = objDist.encode(k, rand, root, Nimages);
            character.allLREncoding = allencode.encode(k, rand, root, Nimages);
            character.allAngleEncoding = allencodeAngles.encode(k, rand, root, Nimages);
            character.allDistanceEncoding = allobjDist.encode(k, rand, root, Nimages);
            character.velocity = vel.GetVelocity(character.AngleEncoding);
            character.acceleration = acc.GetAcceleration(character.velocity);
            character.gapVelocity = gav.GetVelocity(character.allAngleEncoding);
            character.gapAcceleration = gac.GetAcceleration(character.gapVelocity);
           // bEnc = character.allAngleEncoding.split("#");
            bEnc = character.allAngleEncoding.split("#");
            fsp = new FindSingularPoints();
            character.centAvgEnc=cAvg.getCenteredAverage(bEnc[0], character.strokeWidth);
            enps = fsp.find(character.centAvgEnc, root, rand, Nimages, k); /*pass only outter boundary*/;
            character.cornerEnc = enps.pointEnc;
            character.pointStrokeEnc = enps.pointStrokeEnc;
            character.deadEnds = seg.doSegregate(character.cornerEnc, "0",true); //boolean space allowed in strings
            character.inwardCorners = seg.doSegregate(character.cornerEnc, "2",true);
            character.sharpInwardCorners = seg.doSegregate(character.cornerEnc, "1",true);
            character.outwardCorners = seg.doSegregate(character.cornerEnc, "3",true);
            character.bucketEnc=bc.findBucketStructure(character.pointStrokeEnc);
            character.coneEnc=cc.findBucketStructure(character.pointStrokeEnc);
            character.bucket=seg.doSegregate(character.bucketEnc,"#",false);
            character.cone=seg.doSegregate(character.coneEnc,"*",false);
            
            

        }
        for (int k = 0; k < CharList.size(); k++) {
            CharacterList.add(CharList.get(k));

        }

        CharacterList = sortObj.SortCharacetrs(CharacterList);

        /*   for (int k = 0; k < CharacterList.size(); k++) {
         System.out.println(CharacterList.get(k).path);
         }
         */
        EncapsulatedDistanceResult edr;
        edr = wordArrObj.makeWords(CharacterList, DistanceBetweenCharacetrs); //not req at all ... jus return charList
        meanDistancebetweenCharacters = edr.meanDistBwChar;           
        DistanceBetweenCharacetrs = edr.DistanceBetweenCharacetrs;

        EncapsulatedResult er = new EncapsulatedResult();
        er.CharacterList = CharacterList;
        er.DistanceBetweenCharacetrs = DistanceBetweenCharacetrs;
        er.meanDistancebetweenCharacters = meanDistancebetweenCharacters;
      
        /*for (int i = 0; i < er.CharacterList.size(); i++) {
            System.out.println(er.CharacterList.get(i).path);
        }
                */
        return er;
    }
}
