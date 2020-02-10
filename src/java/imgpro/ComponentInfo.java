

package imgpro;

import java.util.ArrayList;

/**
 *
 * @author Shikha
 */


public class ComponentInfo {
    
   
   int topx,topy,botx,boty;
   // ArrayList<Boundary> boundary; //to be added
    public String name;
    String path;
    ArrayList<Pix> pixelArray;
    public String CharClass;
    public String LabelFromFileName;
    int centroidX;
    int centroidY;
    int startX;
    int startY;
    int height;
    int width;
    int perimeter;  
    int boundingRectangleArea; 
    float ratio;
    int label;
    String bmatrix;
    
    String LREncoding;
    String DistanceEncoding;
    String AngleEncoding;
    String allLREncoding;
    String allDistanceEncoding;
    String allAngleEncoding;
    String centAvgEnc;
    
    public int deadEnds;
    //int concCorners;
    //int convCorners;
    public int inwardCorners;
    public int sharpInwardCorners;
    public int outwardCorners;
    public int nBoundaries;int B0;int B1;int B2; // B0 outer  ........ B1,B2 inner 
    public int cone;
    public int bucket;
    public String cornerEnc;
    public String pointStrokeEnc;
    public String coneEnc;
    public String bucketEnc;
    
    String velocity;
    String acceleration;
    String gapVelocity;
    String gapAcceleration;
    public String recognisedAs;
    public int wordType; /*0 - english && 1 - Devnagari*/
    public boolean upperModifier;
    public boolean lowerModifier;
    public int longestVertical;
    public int strokeWidth;
}

