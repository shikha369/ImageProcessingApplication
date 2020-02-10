
package imgpro;

import java.util.ArrayList;

/**
 *
 * @author Shikha
 */
  public class Stroke {
  SingularPoint start;   
  SingularPoint end;
  String Label;
  int typeOfCurve; //CurveType methods will be called to from amin module  or make a method in class itself
 /* 0--- Straight Line 1--- Concave Curve 2--- Convex Curve */
  int StrokeLength; 
  /* once u have access of both the singular points ... u can access their attributes - index to get stroke length
   * - type
   * - start and end coordiantes to get stroke type
   *start equals end in case of corners .... but in case of deadends u need to calculate mid point of start and end    
   */
   ArrayList<Pix> strokeCoordinates=new ArrayList<Pix>();
   public int ComputeStrokeLength(SingularPoint start,SingularPoint end){
       int length=0;
       length=start.index-end.index;
       if(length<0)
           length=-1*length;
       return length;
  }
   public void fillStrokeCoordinates(ArrayList<Pix> coordinates)
   {
  // int index=0;
   ArrayList<Pix> sc=new ArrayList<Pix>();
   for(int k=start.index;k<=(start.index+this.StrokeLength);k++){
   sc.add(coordinates.get(k%coordinates.size()));
   }
   this.strokeCoordinates=sc;
   }
   
   /*Labels ----
   a - Longer Stroke (> mean)
   b - Shorter Stroke (<= mean)
   c - Longer concave
   d - shorter concave
   e - Longer convex
   f - shorter convex
   
   
   
   
   */
   

}
