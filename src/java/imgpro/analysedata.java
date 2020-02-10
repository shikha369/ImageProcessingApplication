
package imgpro;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class analysedata {
     FileWriter fileWriter;
     String[] words;
     String[] angles;
     String[] dist;
     double mean,std;
     int chkLen=0;
     boolean ifcorner;
   // boolean ifCorner;
     int deadEnds;
     int concaveCorners,convexCorners;
     //Connection con=ProcessImages.con;
     Connection con;
     Statement csvStmt;
     ArrayList<Pix> tenpercentCoordinates=new ArrayList<Pix>();
     BufferedReader fileReader,fileReaderDist;
     int startCorner,endCorner;
     ArrayList<SingularPoint> ListSingularPoints=new ArrayList<SingularPoint>();
     ArrayList<Stroke> ListStrokes=new ArrayList<Stroke>();
    String runLengthH;
    
    public void ExtractFeatures(Connection con,String root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
        this.con=con;
    CurveType ctype=new CurveType();
    testRegression tReg=new testRegression();
    ApproximateWidth ap=new ApproximateWidth();
    int window;
    Pix p;
    Database db=new Database();
    con=db.connect();
    Statement stmt = con.createStatement();
    //ResultSet rs;
    String path=root+"//root"+rand+"\\output";
    int index;
    //***************************************************************************************
    //for all the segmented images in output folder
    File[] fileList =new File(path).listFiles();
    
    for(File image:fileList)
    {
    //extract imageNo and count
    String imageName=image.getName();
    index=imageName.indexOf(".");
    imageName=imageName.substring(0, index);
    //window=ap.getApproximateWidth(imageName); //pass image name to get approximate width ... 
    System.out.println(imageName);
    //correect window size here according to gap //or get width/window first and make gap as a function of width/window
    // window=f(window,gap);
    //window=(window/7)*3; //where gap=7
    //********window=4;//set static ...**refer calculation of gap in countboundarypixel class
    
    CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
    window=Integer.parseInt(properties.getPropValues("window")); 
    
    System.out.println(window);
    //reset both the lists
    ListSingularPoints.clear();
    ListStrokes.clear();
    deadEnds=0;concaveCorners=0;convexCorners=0;startCorner=0;endCorner=0;
    
    String StringRLEnc="",StringRLang="",StringDist="";
    chkLen=0;
    String sql = "SELECT ENCODING,ANGLEENCODING,DISTANCE FROM TESTIMAGEANALYSIS WHERE COMPONENTNO='"+imageName
            +"'";  
    
    /*String sql = "SELECT ENCODING,ANGLEENCODING FROM IMAGEANALYSIS WHERE COMPONENTNO='"+imageName
            +"'";  */
    ResultSet rs=stmt.executeQuery(sql);
    if(rs.next())
    {
    StringRLEnc=rs.getString(1);
    StringRLang=rs.getString(2);
    StringDist=rs.getString(3);
    }
    rs.close();
    //stmt.close();
    //fileReaderDist = new BufferedReader(new FileReader("C:\\Users\\shikha\\Documents\\NetBeansProjects\\ImageProcessingApplication\\web\\Distance\\"+imageName+".txt"));
    //StringDist=fileReader.readLine();
    //read ten percent csv too and store in arraylist                 ....use name of image
    fileReader = new BufferedReader(new FileReader(root+"\\TenPercentCsv\\"+imageName+".csv"));
         String line;    
           while ((line = fileReader.readLine()) != null) 
           {
            String[] tokens = line.split(",");
                  if (tokens.length > 0) {
                       if("".equals(tokens[0]))
                           break;
                       p=new Pix();
                       p.x=Integer.parseInt(tokens[0]);
                       p.y=-1*Integer.parseInt(tokens[1]);
                       tenpercentCoordinates.add(p);    
                  }
           }
   
    words=StringRLEnc.split(" ");
    angles=StringRLang.split(" ");
    dist=StringDist.split(" ");
    
    // Get a DescriptiveStatistics instance
    DescriptiveStatistics stats = new DescriptiveStatistics();
    DescriptiveStatistics statsLine = new DescriptiveStatistics();
    DescriptiveStatistics statsCurve = new DescriptiveStatistics();
     // Compute some statistics
    for(int i=0;i<angles.length;i++)
    {
    System.out.println(i+"--->"+words[i]+"   "+angles[i]+"   "+dist[i]);
    stats.addValue(Double.parseDouble(dist[i]));
    }
    chkLen=angles.length;
    chkLen--;
    mean = stats.getMean();
    std = stats.getStandardDeviation();
    //System.out.println(count);
    System.out.println("mean------"+mean);   
    System.out.println("std-------"+std);
    System.out.println("variance------"+stats.getVariance());
    //check corners
    recurseCorner(0,window);
    
    int n=ListSingularPoints.size();
    
    if(startCorner==1&&endCorner==1)
      {
      concaveCorners=concaveCorners-2;
      deadEnds++;
      System.out.println("corners merged to form deadend");
      SingularPoint spStart,spEnd;
      SingularPoint sp=new SingularPoint();
      spStart=ListSingularPoints.get(0);
      spEnd=ListSingularPoints.get(n-1);
      sp.index=(spStart.index+spEnd.index)/2;
      sp.nextIndex=spStart.index+1;
      sp.type=0;
      sp.start=spEnd.end;
      sp.end=spStart.start;
      ListSingularPoints.remove(0);//removes element at first position
      ListSingularPoints.add(0, sp);
      ListSingularPoints.remove(n-1);
      } 
        
    /*till now all Singular Points are stored in ListSingularPoints */
    /* getting Strokes... for n singular Points .. there will be n-1 number of strokes */
     int nSingularPoints=ListSingularPoints.size();
     Stroke st;
     for(int m=0;m<nSingularPoints;m++)
     {
     st=new Stroke();
     st.start=ListSingularPoints.get(m);
     if(m==(nSingularPoints-1))
         st.end=ListSingularPoints.get(0);
     else
         st.end=ListSingularPoints.get(m+1);
     st.StrokeLength=st.ComputeStrokeLength(st.start, st.end);
     /*invoke ctype.getCurveType(Pix CurrentSingularPoint,Pix NextPoint,Pix NextSingularPoint);
     corner-corner : s/e to s/e
     corner-deadend : s/e to s
     deadend-corner : e - s/e
     deaddend - deadend : e-s
     */
       /*  Pix next;
     ***********************************************************
     
     if(st.start.type==0 && st.end.type==0 )
         next=
     else if(st.start.type==0 && st.end.type!=0 )
         next=
     else if(st.start.type!=0 && st.end.type==0 )
         next=
     else
         next= 
         **************************************************
         next=tenpercentCoordinates.get(st.start.nextIndex);
         st.typeOfCurve=ctype.getCurveType(st.start.end,next,st.end.start);
     */
     st.fillStrokeCoordinates(tenpercentCoordinates);
     st.typeOfCurve=tReg.getCurveType(st.strokeCoordinates);
         if(st.typeOfCurve==0)
             statsLine.addValue(st.StrokeLength);
         else
             statsCurve.addValue(st.StrokeLength);
         
         ListStrokes.add(st);
     }
    
    /*traverse all strokes...*/
    double meanLineLength,meanCurveLength,stdLineLength,stdCurveLength;
    
    meanLineLength=statsLine.getMean();
    stdLineLength=statsLine.getStandardDeviation();
    
    meanCurveLength=statsCurve.getMean();    
    stdCurveLength=statsCurve.getStandardDeviation();
    
    int nStrokes=ListStrokes.size();
    Stroke dummyStroke;
    for(int m=0;m<nStrokes;m++)
    {
    dummyStroke=ListStrokes.get(m);
    if(dummyStroke.typeOfCurve==0) //line
        dummyStroke.Label=ctype.findCurveLabel(dummyStroke.typeOfCurve,dummyStroke.StrokeLength,meanLineLength,stdLineLength);
    else
        dummyStroke.Label=ctype.findCurveLabel(dummyStroke.typeOfCurve,dummyStroke.StrokeLength,meanCurveLength,stdCurveLength); 
        
    ListStrokes.remove(m);
    ListStrokes.add(m,dummyStroke);
    
    }
        
    /*create encoding and store in runLengthH*
    singularPoint-stroketype
    */
    runLengthH="";
    SingularPoint encSp=new SingularPoint();
    Stroke encSt=new Stroke();
    for(n=0;n<nStrokes;n++)
    {
        encSp=ListSingularPoints.get(n);
        encSt=ListStrokes.get(n);
    runLengthH=runLengthH+Integer.toString(encSp.type)+encSt.Label;
    }
    
    
    System.out.println("Number Of DeadEnds : "+deadEnds);
    //stroke encoding in place of runLengthH, runLengthQ
  /* String sqlCornerInsert="UPDATE IMAGEANALYSIS SET DEADENDS="+deadEnds+",CONCAVE_CORNER="+concaveCorners+
            ",CONVEX_CORNER="+convexCorners+",CORNERENC='"+runLengthH+"',CORNERENCQ='"+runLengthQ+"' WHERE COMPONENTNO='"+imageName
            +"'"; // use name of image .. */
    if("".equals(runLengthH))
        runLengthH="g"; //g - a complete sphere
    String sqlCornerInsert="UPDATE TESTIMAGEANALYSIS SET DEADENDS="+deadEnds+",CONCAVE_CORNER="+concaveCorners+
            ",CONVEX_CORNER="+convexCorners+",CORNERENC='"+runLengthH+"' WHERE COMPONENTNO='"+imageName
            +"'";
    Statement CornerStatement=con.createStatement();
    CornerStatement.executeUpdate(sqlCornerInsert);
    tenpercentCoordinates.clear();
    fileReader.close();
    //fileReaderDist.close();
    }
  // prepareCsv();
  // csvStmt.close();
  // stmt.close();
   con.close();
    }
    //code to find corners on contour 
  
    
    public void recurseCorner(int startPoint,int window) throws IOException 
    {    
      /*checking if the start coordinate of perimeter is corner itself..if yes then    
        look for a corner in chklen-window ..if found .. merge them to be a deadend
        use variables ---> startCorner and endCorner
              */
      int dirIndex,angleIndex,distanceIndex,k;
      if(startPoint>chkLen){
        return;
      }    
      else
      {
          int i=startPoint;
          
          while(i<=chkLen)
          {
         //  if(("X".equals(angles[i])||"V".equals(angles[i]))&& Double.parseDouble(dist[i])<mean+std && ("LS".equals(words[i])||"L".equals(words[i])) )    
         //  {
           if((Double.parseDouble(angles[i])>0 &&Double.parseDouble(angles[i])<=150)&& Double.parseDouble(dist[i])<mean+std && ("LS".equals(words[i])||"L".equals(words[i])) )    
           {
            dirIndex=i;
            angleIndex=i;
            distanceIndex=i;
     
            ifcorner=false;
            if(window<=4){
                for( k=1;k<=window;k++)  
                    { 
                       if(dirIndex+k>chkLen||angleIndex+k>chkLen||distanceIndex+k>chkLen)
                        break;
                        //found end corner
                    //   if(("X".equals(angles[angleIndex+k])||"V".equals(angles[angleIndex+k]))&& Double.parseDouble(dist[distanceIndex+k])<mean+std && ("L".equals(words[ dirIndex+k])||"LS".equals(words[dirIndex+k])||"A".equals(words[dirIndex+k])))
                    if(((Double.parseDouble(angles[angleIndex+k])>0 &&Double.parseDouble(angles[angleIndex+k])<=150))&& Double.parseDouble(dist[distanceIndex+k])<mean+std && ("L".equals(words[ dirIndex+k])||"LS".equals(words[dirIndex+k])||"A".equals(words[dirIndex+k])))
                       {
                                 ifcorner=true;
                                 System.out.println("found deadend at "+" start at "+i+" end at "+k);
                                 SingularPoint sp=new SingularPoint();
                                 sp.index=(int) Math.ceil((i+i+k)/2);//get ceiling
                                 sp.nextIndex=i+k+1;
                                 if(sp.nextIndex>=chkLen)
                                     sp.nextIndex=0;
                                 sp.type=0;
                                 sp.start=tenpercentCoordinates.get(i);
                                 sp.end=tenpercentCoordinates.get(i+k);
                                 ListSingularPoints.add(sp);
                                 break;
                             }
                     }
            }
            else
            {
            for( k=2;k<=window;k++)   //window size ... k=1 gives extra dead ends....so starting from k=2 .. some dead ends left out 
                    {                // we need dynamic window size for each character
                       if(dirIndex+k>chkLen||angleIndex+k>chkLen||distanceIndex+k>chkLen)
                        break;
                        //found end corner
                    //   if(("X".equals(angles[angleIndex+k])||"V".equals(angles[angleIndex+k]))&& Double.parseDouble(dist[distanceIndex+k])<mean+std && ("L".equals(words[ dirIndex+k])||"LS".equals(words[dirIndex+k])||"A".equals(words[dirIndex+k])))
                      if(((Double.parseDouble(angles[angleIndex+k])>0 &&Double.parseDouble(angles[angleIndex+k])<=150))&& Double.parseDouble(dist[distanceIndex+k])<mean+std && ("L".equals(words[ dirIndex+k])||"LS".equals(words[dirIndex+k])||"A".equals(words[dirIndex+k])))
                       {
                                 ifcorner=true;
                                 System.out.println("found deadend at "+" start at "+i+" end at "+k);
                                 SingularPoint sp=new SingularPoint();
                                 sp.index=(int) Math.ceil((i+i+k)/2);//get ceiling
                                 sp.nextIndex=i+k+1;
                                  if(sp.nextIndex>=chkLen)
                                     sp.nextIndex=0;
                                 sp.type=0;
                                 sp.start=tenpercentCoordinates.get(i);
                                 sp.end=tenpercentCoordinates.get(i+k);
                                 ListSingularPoints.add(sp);
                                 break;
                             }
                     } 
            }
     //************************    
     if(ifcorner)
     {
     deadEnds++;
     if(window<=3)
      recurseCorner(i+k+2*window,window);//condition to skip more distance to eliminate redundant deadends 
     else
      recurseCorner(i+k+window,window);//condition wen window is more... u dont want to miss deadends  
     
     break;
     }
     else
     {
         //updating corner condition from angle 115 to 150
    
       //if("X".equals(angles[i])||"V".equals(angles[i]))
      if((Double.parseDouble(angles[i])>0 &&Double.parseDouble(angles[i])<=150))
         {  //to find corner in chcklen-window to trac deadend
      System.out.println("found concave corner at "+i); // to avoid the neighbouring pixel wid similar behaviour 
      //if start coordinate of perimeter is corner itself..set startCorner to 1;
      if(i==0)
      {
      startCorner=1;
      System.out.println("StartCorner found");
      }
          
      if((i)>chkLen-window)
         {
      endCorner=1;
      System.out.println("endCorner found");
      }
      
      concaveCorners++;
      SingularPoint sp=new SingularPoint();
      sp.index=i;
      sp.nextIndex=i+1;
      if(sp.nextIndex>=chkLen)
         sp.nextIndex=0;
      sp.type=1;
      sp.start=tenpercentCoordinates.get(i);
      sp.end=tenpercentCoordinates.get(i);
      ListSingularPoints.add(sp);
      if(window<=3)
       i=i+2*window; //condition to skip more distance to eliminate redundant singular points
      else
       i=i+window;  //condition wen window is more... u dont want to miss singular points
     }}
           }
           //chk for convex corner
     //else if(("X".equals(angles[i])||"V".equals(angles[i]))&& Double.parseDouble(dist[i])<mean+std &&("R".equals(words[i])) )   
      else if((Double.parseDouble(angles[i])>0 &&Double.parseDouble(angles[i])<=150)&& Double.parseDouble(dist[i])<mean+std &&("R".equals(words[i])) )   
     {
        System.out.println("Found convex corner at "+i);
       
        convexCorners++;
        SingularPoint sp=new SingularPoint();
        sp.index=i;
        sp.nextIndex=i+1;
        if(sp.nextIndex>=chkLen)
           sp.nextIndex=0;
        sp.type=2;
        sp.start=tenpercentCoordinates.get(i);
        sp.end=tenpercentCoordinates.get(i);
        ListSingularPoints.add(sp);
        if(window<=3)
       i=i+2*window; //condition to skip more distance to eliminate redundant singular points
      else
       i=i+window;  //condition wen window is more... u dont want to miss singular points
        }
        i=i+1;
    }
    //end of for
        
    }
    
          
}
    
    public void prepareCsv(String root,String rand) throws IOException, ClassNotFoundException, SQLException
    {
    
    Statement stmt; 
    String writePath=root+"//root"+rand+"\\Corners.csv" ;
    
    Database db=new Database();
    Connection con=db.connect();
    stmt=con.createStatement();
        fileWriter=new FileWriter(writePath);
        write("zero");
        write("one");
        write("two");
        write("three");
        write("four");
        write("five");
        write("six");
        write("seven");
        write("eight");
        write("nine");
        write("A");
        write("B");
        write("C");write("D");
        write("E");
        write("F");
        write("G");
        write("H");
        write("I");
        write("J");
        write("K");
        write("L");write("M");write("N");write("O");write("P");write("Q");write("R");
        write("S");write("T");write("U");write("V");write("W");write("X");write("Y");
        write("Z");
           /*small letters*/
        write("aa");write("bb");write("cc");write("dd");write("ee");write("ff");write("gg");
        write("hh");write("ii");write("jj");write("kk");write("ll");write("mm");write("nn");
        write("oo");write("pp");write("qq");write("rr");write("ss");write("tt");write("uu");write("vv");
        write("ww");write("xx");write("yy");write("zz");
        
        fileWriter.flush();
        fileWriter.close();
    }
    public void write(String character) throws SQLException, IOException
    {
    int deadends,concave,convex,boundaries;
    String charac,comp;
    String sql="SELECT CHARCLASS,COMPONENTNO,DEADENDS,CONCAVE_CORNER,CONVEX_CORNER,BOUNDARIES FROM IMAGEANALYSIS where CHARCLASS like '"+character+"%'";
    csvStmt=con.createStatement();
    ResultSet rs=csvStmt.executeQuery(sql);
    while(rs.next())
    {
    charac=rs.getString(1);
    comp=rs.getString(2);
    deadends=rs.getInt(3);
    concave=rs.getInt(4);
    convex=rs.getInt(5);
    boundaries=rs.getInt(6);
    fileWriter.append(charac);
    fileWriter.append(",");
    fileWriter.append(comp);
    fileWriter.append(",");
    fileWriter.append(String.valueOf(deadends));
    fileWriter.append(",");
    fileWriter.append(String.valueOf(concave));
    fileWriter.append(",");
    fileWriter.append(String.valueOf(convex));
    fileWriter.append(",");
    fileWriter.append(String.valueOf(boundaries));
    fileWriter.append(System.lineSeparator());
    }
    rs.close();
    fileWriter.append(System.lineSeparator());
    }
}