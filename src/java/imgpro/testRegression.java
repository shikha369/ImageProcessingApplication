package imgpro;

import java.util.ArrayList;

public class testRegression {
float a,b,c;

public double calculateDistance(int x,int y)
{
    double distance=0;
    distance=Math.abs(a*x+b*y+c)/Math.pow((Math.pow(a, 2)+Math.pow(b, 2)), 0.5);
    return distance;
}

public void calculateParameters(int x0,int y0,int xn,int yn)
{
a=y0-yn;
b=xn-x0;
c=(x0-xn)*y0+(yn-y0)*x0;
}

//public static void main(String[] args)
public int getCurveType(ArrayList<Pix> cc)
{
//arraylist of coordinates will be passed here
    int strokeShape;
    double curveLength;
    String curveShape;
    //ArrayList<Pix> cc=new ArrayList<Pix>();
    ArrayList<Double> dist=new ArrayList<Double>();
    ArrayList<String> delta=new ArrayList<String>();
    double tempDist;
    String tempDelta;
    int counta=0,countb=0,countc=0,countd=0;
     Pix p0,pN;
     p0=cc.get(0);
     pN=cc.get(cc.size()-1);
    /*
    Pix p0=new Pix();Pix p1=new Pix();Pix p2=new Pix();Pix p3=new Pix();Pix p4=new Pix();Pix p5=new Pix();
    Pix p6=new Pix();Pix p7=new Pix();Pix p8=new Pix();Pix p9=new Pix();Pix p10=new Pix();Pix p11=new Pix();
    p0.x= 9; p0.y= -56; cc.add(p0);
    p1.x= 7; p1.y= -51; cc.add(p1);
    p2.x= 5; p2.y=-46; cc.add(p2);
    p3.x= 5; p3.y= -41; cc.add(p3);
    p4.x= 4; p4.y= -36; cc.add(p4);
    p5.x= 5; p5.y= -31; cc.add(p5);
    p6.x= 6; p6.y= -26; cc.add(p6);
    p7.x= 7; p7.y= -21; cc.add(p7);
    p8.x= 9; p8.y= -16; cc.add(p8);
    p9.x= 13; p9.y= -11; cc.add(p9);
    p10.x=17; p10.y= -6; cc.add(p10);
    */
    calculateParameters(p0.x, p0.y, pN.x, pN.y);
    /*compute distance between line joining end points and the intermediate points on curve */
    for(int i=0;i<cc.size();i++)
    {
    dist.add(calculateDistance(cc.get(i).x,cc.get(i).y ));
    }
    curveLength=Math.pow((Math.pow((pN.x-p0.x), 2)+Math.pow((pN.y-p0.y), 2)), 0.5); 
    //System.out.println(curveLength);
    /*assign labels acc to distance and count frequency of labels to determine shape of stroke*/
    for(int i=0;i<cc.size();i++)
    { 
        tempDist=dist.get(i);
        if(tempDist<=.1*curveLength)
        {
        tempDelta="a";
        counta++;
        }
        else if(tempDist<=.15*curveLength)
        {
         tempDelta="b";
         countb++;
        }
         else if(tempDist<=.5*curveLength)
         {
         tempDelta="c";
         countc++;
         }
        else
         { tempDelta="d";
         countd++;
         }
        delta.add(tempDelta);
    //  System.out.println(tempDist+" "+tempDelta);
    }
     if(counta>countb && counta>countc && counta>countd)
    //  curveShape="Line";  
        strokeShape=0;
   //  else if((counta+countb)>=countc && (counta+countb)>=countd )
   //   curveShape="Line";
     else
         strokeShape=1;
   //      curveShape="Curve";
   //  System.out.println(curveShape);
     return strokeShape;
}
}
