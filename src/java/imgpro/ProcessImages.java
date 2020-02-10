package imgpro;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shikha
 */
public class ProcessImages {

    public String rootPath;
    public String ParameterPath;
    public int Nimages, counter;
    public ArrayList<String> sortedChararcetrs = new ArrayList<String>();
    public Database db = new Database();
    public Connection con;
    public String rand;
    public ArrayList<ComponentRecognisedAs> RecognisedCharacetrList = new ArrayList<ComponentRecognisedAs>();
    public ArrayList<ComponentInfo> CharacterList = new ArrayList<ComponentInfo>();
    public ArrayList<Double> DistanceBetweenCharacetrs = new ArrayList<Double>();
    public double meanDistancebetweenCharacters;
    ComponentInfo ci = new ComponentInfo();

    public ProcessImages() throws ClassNotFoundException, SQLException {
        //  this.con = db.connect();

    }

    public ArrayList<ComponentInfo> ProcessImageList(String path, int source) throws Exception {
        //source 0 means invoked from addingFolder .... source 1 means invoked from addingTestFolder

        counter = 1;
        Nimages = 1; // set it dynamically to take all images from images folder
        //Database db = new Database();
        //Connection con = db.connect();
        // Statement stmtt = con.createStatement();
        // String sqld = "delete from testimageanalysis";
        //  stmtt.executeUpdate(sqld);
        // con.setAutoCommit(true);
        // stmtt.close();
        //con.close();
        EncapsulatedResult er;
        driver ImageDriver = new driver();
        File[] fileList = new File(path).listFiles();
        for (File image : fileList) {
            //System.out.println(image.getAbsolutePath());
            //System.out.println(image.getName());
            er = ImageDriver.processImage(image.getAbsolutePath(), image.getName(), Nimages++, this.rootPath, this.rand,
                    RecognisedCharacetrList, CharacterList, DistanceBetweenCharacetrs, meanDistancebetweenCharacters);
       // }

            //      Statement stmt = ProcessImages.con.createStatement();
            //RecognisedCharacetrList=er.RecognisedCharacetrList;
            CharacterList = er.CharacterList;
            meanDistancebetweenCharacters = er.meanDistancebetweenCharacters;
            DistanceBetweenCharacetrs = er.DistanceBetweenCharacetrs;

            /*   String sql;
             for (int n = 0; n < CharacterList.size(); n++) {
             ci = CharacterList.get(n);
             if (source == 0) {
             sql = "INSERT INTO TESTIMAGEANALYSIS(COMPONENTNO,PATH,AREABOUNDINGRECT,PERIMETER,RATIO_P_BY_A,WIDTH,HEIGHT,"
             + "CENTROIDX,CENTROIDY,STARTX,STARTY,BOUNDARIES,CHARCLASS,ENCODING,INTERNAL_ONE,INTERNAL_TWO,"
             + "ANGLEENCODING,DISTANCE,TOPX,TOPY,ALLPOINTDIRENC,ALLPOINTANGENC,ALLPOINTDISTENC) VALUES('" + ci.name + "','" + ci.path + "',"
             + ci.boundingRectangleArea + "," + ci.perimeter + "," + ci.ratio + "," + ci.width + ","
             + ci.height + "," + ci.centroidX + "," + ci.centroidY + "," + ci.startX + "," + ci.startY + ","
             + ci.nBoundaries + ",'" + ci.CharClass + "','" + ci.LREncoding + "'," + ci.B1 + "," + ci.B2 + ",'"
             + ci.AngleEncoding + "','" + ci.DistanceEncoding + "'," + ci.topx + "," + ci.topy + ",'" + ci.allLREncoding + "','" + ci.allAngleEncoding + "','"
             + ci.allDistanceEncoding + "')";
             } else {
             sql = "INSERT INTO TESTIMAGEANALYSIS(COMPONENTNO,PATH,AREABOUNDINGRECT,PERIMETER,RATIO_P_BY_A,WIDTH,HEIGHT,"
             + "CENTROIDX,CENTROIDY,STARTX,STARTY,BOUNDARIES,ENCODING,INTERNAL_ONE,INTERNAL_TWO,"
             + "ANGLEENCODING,DISTANCE,TOPX,TOPY,ALLPOINTDIRENC,ALLPOINTANGENC,ALLPOINTDISTENC) VALUES('" + ci.name + "','" + ci.path + "',"
             + ci.boundingRectangleArea + "," + ci.perimeter + "," + ci.ratio + "," + ci.width + ","
             + ci.height + "," + ci.centroidX + "," + ci.centroidY + "," + ci.startX + "," + ci.startY + ","
             + ci.nBoundaries + ",'" + ci.LREncoding + "'," + ci.B1 + "," + ci.B2 + ",'"
             + ci.AngleEncoding + "','" + ci.DistanceEncoding + "'," + ci.topx + "," + ci.topy + ",'" + ci.allLREncoding + "','" + ci.allAngleEncoding + "','"
             + ci.allDistanceEncoding + "')";
             }

             // System.out.println(sql);
             //   stmt.executeUpdate(sql);
             //
             }*/
        }
        // stmt.close();
        //  con.close();
        TestFeaturesCsv tfc = new TestFeaturesCsv();
        tfc.writeCSV(CharacterList, rootPath, rand, 0); //0 denotes invocation before correction
        

        /*
        here u can call generate document and write the read doc and indexed doc in root folder itself
        1.pass characterlist to classifier to get recognisedlist or clsfr.runClassifier(rand, rand) 
        2.pass recognisedlist and distancelist to documentcontent java which will return content string
        3.pass content string to writeDocumnet() and writeIndexedDocument()
        */
        
        /*with this input classifier can read training features 
        as well as test features and write prediction into csv within root folder
        ComponentRecognisedAs cra;
        
        for(int i=0;i<CharacterList.size();i++)
        {
        cra=new ComponentRecognisedAs();
        cra.cname=CharacterList.get(i).name;
        cra.recAs=cra.cname.substring(16,cra.cname.length());
        RecognisedCharacetrList.add(cra);
        }
       */
        /*
        DocumentContent dc=new DocumentContent();
        String contentString=dc.generateContentInString(RecognisedCharacetrList, DistanceBetweenCharacetrs, meanDistancebetweenCharacters);
        dc.writeDocument(rootPath,rand,contentString);
        dc.writeIndexedDocument(rootPath,rand,contentString);
        */
      // for generating jus traing features... commemt this out
        
      /*  Classifier clsfr=new Classifier();
        
        RecognisedCharacetrList=clsfr.runClassifier(rootPath, rand);
        testDocumentContent dc=new testDocumentContent();
        String contentString=dc.generateContentInString(RecognisedCharacetrList,CharacterList);
        dc.writeDocument(rootPath,rand,contentString);
        dc.writeIndexedDocument(rootPath,rand,contentString);
        for(int r=0;r<CharacterList.size();r++)
            CharacterList.get(r).recognisedAs=RecognisedCharacetrList.get(r).recAs;
             
        */    
        return CharacterList; 
        
    }

}
