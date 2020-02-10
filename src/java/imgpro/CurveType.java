package imgpro;

public class CurveType {
 
 public int getCurveType(Pix CurrentSingularPoint,Pix NextPoint,Pix NextSingularPoint )
 {
 int typeOfCurve=0;
 int dirCurrentSingularPoint_NextPoint,dirCurrentSingularPoint_NextSingularPoint; //convert to int please :/
 EncAlpha computingAngle=new EncAlpha();
 EncBoundary computingDirection=new EncBoundary();
 double angle;
 angle=computingAngle.getAngle(CurrentSingularPoint, NextPoint, NextSingularPoint);

 /*take INPUT 3 pix objects
  CurrentSingularPoint , NextPoint , NextSingularPoint
  vector V1(CurrentSingularPoint,NextPoint) and vector V2(CurrentSingularPoint,NextSingularPoint)
  find angle between V1 and V2
  if angle=0-5 :straight line
     angle>5 : curve ------  direction clockwise -> convex corner
                     ------  direction anti clockwise -> concave corner
             getting directions : direction combination from 8 connectivity for clockwise and counterclockwise
            
OUTPUT : int 0--- Straight Line
             1--- Concave Curve
             2--- Convex Curve
 */
 
 
 /*if(angle>5)
 {
  //when curve is not straightLine
  dirCurrentSingularPoint_NextPoint=Integer.parseInt(computingDirection.findDirection(CurrentSingularPoint.x,CurrentSingularPoint.y,NextPoint.x,NextPoint.y));
  dirCurrentSingularPoint_NextSingularPoint=Integer.parseInt(computingDirection.findDirection(CurrentSingularPoint.x,CurrentSingularPoint.y,NextSingularPoint.x,NextSingularPoint.y));
  typeOfCurve=findCurveDirection(dirCurrentSingularPoint_NextPoint, dirCurrentSingularPoint_NextSingularPoint);
  
 }
 */
 if(angle>15)
     typeOfCurve=1;
 
 return typeOfCurve;
 }
  /*
 public int findCurveDirection(int d1,int d2)
 {
  int type;
   /*counterclockwise dir combinations for concave corner *
 
  if((d1==0&&d1==1)||(d1==0&&d1==2)||(d1==0&&d1==3)||(d1==0&&d1==4)||(d1==0&&d1==5)||(d1==0&&d1==6)||(d1==0&&d1==7)
    ||(d1==1&&d1==2)||(d1==1&&d1==3)||(d1==1&&d1==4)||(d1==1&&d1==5)||(d1==1&&d1==6)||(d1==1&&d1==7)||(d1==1&&d1==0)
    ||(d1==2&&d1==3)||(d1==2&&d1==4)||(d1==2&&d1==5)||(d1==2&&d1==6)||(d1==2&&d1==7)||(d1==2&&d1==0)||(d1==2&&d1==1)
    ||(d1==3&&d1==4)||(d1==3&&d1==5)||(d1==3&&d1==6)||(d1==3&&d1==7)||(d1==3&&d1==0)||(d1==3&&d1==1)||(d1==3&&d1==2)
    ||(d1==4&&d1==5)||(d1==4&&d1==6)||(d1==4&&d1==7)||(d1==3&&d1==7)||(d1==3&&d1==0)||(d1==3&&d1==1)||(d1==3&&d1==2)    
    ||(d1==5&&d1==6)||(d1==5&&d1==7)||(d1==5&&d1==0)||(d1==5&&d1==1)||(d1==5&&d1==2)||(d1==5&&d1==3)||(d1==5&&d1==4)
    ||(d1==6&&d1==7)||(d1==6&&d1==0)||(d1==6&&d1==1)||(d1==6&&d1==2)||(d1==6&&d1==3)||(d1==6&&d1==4)||(d1==6&&d1==5)
    ||(d1==0&&d1==1)||(d1==0&&d1==2)||(d1==0&&d1==3)||(d1==0&&d1==4)||(d1==0&&d1==5)||(d1==0&&d1==6 )||(d1==0&&d1==7))
      type=1;
  else if((d1==0&&d1==0)||(d1==1&&d1==1)||(d1==2&&d1==2)||(d1==3&&d1==3)||(d1==4&&d1==4)||(d1==5&&d1==5)||(d1==6&&d1==6)||((d1==7&&d1==7)))
     type=0;
  else
      type=2;
  return type;
 }

    public int getCurveType(Pix CurrentSingularPoint,Pix NextPoint,Pix NextSingularPoint )
    {
    int type=0;
    return type;
    
    }
 */
 public String findCurveLabel(int type,double length,double mean,double std)
 {
 String Label="";
 if(type==0 && length>=mean)
     Label="a";
 else if(type==0 && length<mean)
     Label="b";
 else if(type==1 && length>=mean)
     Label="c";
 else if(type==1 && length<mean)
     Label="d";
 else if(type==2 && length>=mean+std)
     Label="e";
 else if(type==2 && length<mean+std)
     Label="f";
 
 return Label;
 }
 
}
