
package imgpro;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class TraceBoundary1 {
    //String root=ProcessImages.rootPath;
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
    ArrayList<Boundary> boundary = new ArrayList<Boundary>();
    int i,j;
    Boundary b,start;
    ArrayList<Boundary> perim;
   // int cPix[][],marked[][],int encode[][];
    int iter;
    int nextSx,nextSy,nextTonextSx,nextTonextSy;
    int ax,ay;
    private FileWriter fileWriter;
    int nBoundaries=1;    //no of boundaries
    private int ii;
    private int jj;
    private int modifiedDir;
    ComponentInfo tempCharacter;
    object traverseNeighbour(int i,int j,int [][]cPix,int k,int startAt){
     int dir,foundAt;   
     Boundary b;
     object ob=new object();
     dir=startAt;
     boolean found=false;
     int neighborsVisited=0; // to keep track of no of visits .. if exceeds 7 then return the same pixel
     
     while(!found & neighborsVisited<9)
     {          // seperate condition for direction with no else section
         //traverse in dir 0
         if(dir==0){
         if(i>=0 & i<cPix.length &(j+1)>=0&(j+1)<cPix[0].length){//check boundary conditions
              if(cPix[i][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
               
     }     
     else
         {dir=(dir+1)%8; neighborsVisited++;}         
         }
//traverse in dir 1
         if(dir==1){
         if(i-1>=0&i-1<cPix.length&(j+1)>=0&(j+1)<cPix[0].length ){//check boundary conditions
              if(cPix[i-1][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
      neighborsVisited++;        
     }     
     else
         { dir=(dir+1)%8; neighborsVisited++;}
         }
         //traverse in dir 2
         if(dir==2){
         if(i-1>=0&i-1<cPix.length&(j)>=0&(j)<cPix[0].length){//check boundary conditions
              if(cPix[i-1][j]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         { dir=(dir+1)%8; neighborsVisited++;}
         }
         //traverse in dir 3
         if(dir==3){
         if(i-1>=0&i-1<cPix.length&(j-1)>=0&(j-1)<cPix[0].length){//check boundary conditions
              if(cPix[i-1][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {  dir=(dir+1)%8; neighborsVisited++;}
         }
     //traverse in dir 4
         if(dir==4) {
         if(i>=0&i<cPix.length&(j-1)>=0&(j-1)<cPix[0].length){//check boundary conditions
              if(cPix[i][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {  dir=(dir+1)%8;neighborsVisited++;}
         }
         if(dir==5)
         //traverse in dir 5
         {if(i+1>=0&i+1<cPix.length&(j-1)>=0&(j-1)<cPix[0].length){//check boundary conditions
              if(cPix[i+1][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
          neighborsVisited++;    
     }     
     else
         {   dir=(dir+1)%8;neighborsVisited++;}
         }
         //traverse in dir 6
         if(dir==6)
         {if(i+1>=0&i+1<cPix.length&(j)>=0&(j)<cPix[0].length){//check boundary conditions
              if(cPix[i+1][j]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {dir=(dir+1)%8;neighborsVisited++;}
         }
         if(dir==7){
         //traverse in dir 7
         if(i+1>=0&i+1<cPix.length&(j+1)>=0&(j+1)<cPix[0].length){//check boundary conditions
              if(cPix[i+1][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
               neighborsVisited++;              
     }     
     else
         {dir=(dir+1)%8;neighborsVisited++;}
    } }
     
     if(neighborsVisited>9)
     {
     ob.i=i;
     ob.j=j;
     ob.dir=startAt;
     
     }
     return ob;
    }
    
     object traverseInnerNeighbour(int i,int j,int [][]cPix,int k,int startAt,int[][] marked){
     int dir,foundAt;   
     Boundary b;
     object ob=new object();
     dir=startAt;
     boolean found=false;
     int neighborsVisited=0; // to keep track of no of visits .. if exceeds 7 then return the same pixel
     
     while(!found & neighborsVisited<9)
     {          // seperate condition for direction with no else section
         //traverse in dir 4
         if(dir==4){
         if(i>=0 && i<cPix.length && (j+1)>=0 &&(j+1)<cPix[0].length && marked[i][j+1]==0){//check boundary conditions
              if(cPix[i][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
               
     }     
     else
         {dir=(dir+1)%8; neighborsVisited++;}         
         }
//traverse in dir 5
         if(dir==5){
         if(i-1>=0 && i-1<cPix.length &&(j+1)>=0 && (j+1)<cPix[0].length && marked[i-1][j+1]==0){//check boundary conditions
              if(cPix[i-1][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
      neighborsVisited++;        
     }     
     else
         { dir=(dir+1)%8; neighborsVisited++;}
         }
         //traverse in dir 6
         if(dir==6){
         if(i-1>=0 && i-1<cPix.length && j>=0 && j<cPix[0].length && marked[i-1][j]==0){//check boundary conditions
              if(cPix[i-1][j]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         { dir=(dir+1)%8; neighborsVisited++;}
         }
         //traverse in dir 7
         if(dir==7){                  //aaply operator such that if wrong condition is found it stops checking rest conditions
         if(i-1>=0 && i-1<cPix.length && (j-1)>=0 && (j-1)<cPix[0].length && marked[i-1][j-1]==0){//check boundary conditions
              if(cPix[i-1][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i-1;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {  dir=(dir+1)%8; neighborsVisited++;}
         }
     //traverse in dir 0
         if(dir==0) {
         if(i>=0 &&i <cPix.length && (j-1)>=0 && (j-1)<cPix[0].length && marked[i][j-1]==0){//check boundary conditions
              if(cPix[i][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {  dir=(dir+1)%8;neighborsVisited++;}
         }
         if(dir==1)
         //traverse in dir 1
         {if(i+1>=0 && i+1<cPix.length && (j-1)>=0 && (j-1)<cPix[0].length && marked[i+1][j-1]==0){//check boundary conditions
              if(cPix[i+1][j-1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j-1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
          neighborsVisited++;    
     }     
     else
         {   dir=(dir+1)%8;neighborsVisited++;}
         }
         //traverse in dir 2
         if(dir==2)
         {if(i+1>=0 && i+1<cPix.length && (j)>=0 && (j)<cPix[0].length && marked[i+1][j]==0){//check boundary conditions
              if(cPix[i+1][j]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
              neighborsVisited++;
     }     
     else
         {dir=(dir+1)%8;neighborsVisited++;}
         }
         if(dir==3){
         //traverse in dir 3
         if(i+1>=0 && i+1<cPix.length &&(j+1)>=0 && (j+1)<cPix[0].length && marked[i+1][j+1]==0){//check boundary conditions
              if(cPix[i+1][j+1]!=0&!found)
                  {
                  foundAt=dir; 
                  ob.i=i+1;
                  ob.j=j+1;
                  ob.dir=foundAt;
                  found=true;
                  }
              else
                  dir=(dir+1)%8;
               neighborsVisited++;              
     }     
     else
         {dir=(dir+1)%8;neighborsVisited++;}
    } }
     
     if(neighborsVisited>9)
     {
     ob.i=i;
     ob.j=j;
     ob.dir=startAt;
     
     }
     return ob;
    }
    //***********************************************************************************************************************
    
  /* main algo to trace inner as well as outer boundary  
    
      outer boundary extraction as soon as u get first foreground pixel
      set marked[][] for each such boundary pixel ... this marked[][] will be checked while tracing inner boundaries 
      an edgepixel with marked[][] not set will be pixel in inner boundary ... trace the boundary.. dont check marked[][]
      while adding... just set that to 1.. then go once again in while loop ... this while loop goes until u r getting 
      an non marked[][] edge pixel while sanning one row after other
    
    */
   public ComponentInfo traceComponentBoundary(int k,ComponentInfo character,String root,String rand,int Nimage) throws IOException, ClassNotFoundException, SQLException 
    {    int prevx=0,prevy=0;
        tempCharacter=new ComponentInfo();
         tempCharacter=character;
        //Database db=new Database();
        //Connection con=db.connect();
        int charLabel=tempCharacter.label;
        //Statement stmt = ProcessImages.con.createStatement();
        fileWriter = new FileWriter(root+"//root"+rand+"\\RPerimeterCsv\\Image"+Nimage+"_component"+k + ".csv");
        object ob;
        int[] nr=new int[100];
        nr[0]=0;nr[1]=0;nr[2]=0;
        int nb=0;
        int pos=0,startAt,foundAt;
        reconstructLabel rcl=new reconstructLabel();
        int cPix[][]= rcl.constructPixArray(k,root,rand,Nimage);      //labelArray
         int encode[][]=new int[cPix.length][cPix[0].length]; 
         int marked[][] =new int[cPix.length][cPix[0].length];//marked visited or not
        for(i=0;i<encode.length;i++)
        {
        for(j=0;j<encode[0].length;j++)
        {
        encode[i][j]=-1;       //set to one.. if pixel is visited
        marked[i][j]=0;       // to mark edge pixels which are visited  
        }
        }
       boolean doBreak=false;
       boolean doTrace=false; 
      // find first foreground pixel
       for(i=0;i<cPix.length;i++) //top to bottom tracing
       {
           if(doBreak==true)
               break;
       for(j=0;j<cPix[0].length;j++)
       {
           if(cPix[i][j]!=0){
              start=new Boundary();   //need to initialse everytime u get a Boundary pixel and then add to perim :)
              start.x=j;ax=j;
              start.y=i;ay=i;
             // String sql = "UPDATE TESTIMAGEANALYSIS SET STARTX="+j+",STARTY="+i+" WHERE COMPONENTNO='Image"+driver.Nimage+"_component"+k+"'";
             // stmt.executeUpdate(sql);
              // dump coordinates into csv
               fileWriter.append(String.valueOf(j));
               fileWriter.append(COMMA_DELIMITER);
               fileWriter.append(String.valueOf(i));
               fileWriter.append(NEW_LINE_SEPARATOR);
        encode[i][j]=7;
        marked[i][j]=1;
        doBreak=true;
        break;
           }}}
   // now wen u got the first foreground pixel ... traverse neighbours anti-clockwise to get the first foreground neighbour
       foundAt=7;
       nr[0]=1;
       nb=1;
       boolean done=false; //until all boundary pixels are traversed
        iter=1;
       while(true&!done) {
           iter++;
          // 1. calculate value for startat .. using foundat
          if(foundAt%2==0)
            startAt=(foundAt+7)%8;
          else
              startAt=(foundAt+6)%8;
          // 2. invoke traverseNeighbor function
          
          ob=traverseNeighbour(i, j, cPix, charLabel, startAt);
          i=ob.i;
          j=ob.j;  
          if(iter==2)
          {
          nextSx=j;   //sec cordinate
          nextSy=i;
          }
          if(iter==3)
          {
          nextTonextSx=j;   //third cordinate
          nextTonextSy=i;
          }
          
          //dump coordinates into csv
          fileWriter.append(String.valueOf(j));
          fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(String.valueOf(i));
          fileWriter.append(NEW_LINE_SEPARATOR);
          nr[0]++;
          foundAt=ob.dir;
          encode[i][j]=foundAt;
          marked[i][j]=1;

//check terminating condition here 
          //also check if after 5 iterations it reaches adjacent to start.
        if((ax==j&&ay==i)||(iter>3&&nextTonextSx ==j&&nextTonextSy==i&&nextSx==prevx&&nextSy==prevy))
           /* ||
                (iter>5&&(ax==j-1&ay==i-1)
                ||(ax==j-1&ay==i)||(ax==j-1&ay==i+1)||(ax==j&ay==i-1)||(ax==j&ay==i)||(ax==j&ay==i+1)
                ||(ax==j+1&ay==i-1)||(ax==j+1&ay==i)||(ax==j+1&ay==i+1))) */
            done=true;
        prevx=j;
        prevy=i;
       }
// end of outer boundary .. insert a hint in csv for break..
       start=null;
  /*below code is to find inner boundaries in the components ....
       Logic : scan from the first row till the last row
               find an edge pixel .. the one having marked=0
               invoke traverseInnerNeighbour with the coordinates positions
               (in the function reverse the direction values) and trace antiClockWise only
       u need to keep track of number of boundaries also :(
      */
       int b=0;
       for(i=1;i<cPix.length;i++) //top to bottom tracing
       {
         doTrace=false;  
           
       for(j=1;j<cPix[0].length-1;j++)
       {
           if(cPix[i][j]!=0 & cPix[i][j+1]==0 & marked[i][j]==0){  //condition to find an edge pixel that is not visited yet
        fileWriter.append(System.getProperty("line.separator"));
        nb++;
        b++; // index to boundary
        start=new Boundary();   //need to initialse everytime u get a Boundary pixel and then add to perim :)
        start.x=j;ax=j;
        start.y=i;ay=i;
        
         fileWriter.append(String.valueOf(j));
         fileWriter.append(COMMA_DELIMITER);
         fileWriter.append(String.valueOf(i));
         fileWriter.append(NEW_LINE_SEPARATOR);
         nr[b]++;
        encode[i][j]=7;
        doTrace=true;
        break;
     }
           
     }
       if(doTrace==true)
       {  foundAt=7;
          done=false;
       while(true&!done) {
     
          if(foundAt%2==0)
            startAt=(foundAt+7)%8;
          else
              startAt=(foundAt+6)%8;
          // 2. invoke traverseNeighbor function
          
          ii=i;
          jj=j;
          ob=traverseInnerNeighbour(i, j, cPix, charLabel, startAt,marked);
          i=ob.i;
          j=ob.j;
         fileWriter.append(String.valueOf(j));
         fileWriter.append(COMMA_DELIMITER);
         fileWriter.append(String.valueOf(i));
         fileWriter.append(NEW_LINE_SEPARATOR);
        nr[b]++;
          foundAt=ob.dir;
          /*
          HERE WE ARE GETTING DIRECTION AS ..              7    6   5
                                                           0    .   4
                                                           1    2   3
          this was done to suit the reqiurements of interior boundary tracing ...
          our original convention of direction is ...       3    2   1
                                                            4    .   0
                                                            5    6   7
          there before encoding direction .. make the reqrd changes to adhere by the Dir. conventions
          
          
          */ 
          ob=null;
          switch(foundAt)
          {
              case 0:modifiedDir=4;
                  break;
              case 1:modifiedDir=5;
                  break;
              case 3:modifiedDir=7;
                  break;
              case 4:modifiedDir=0;
                  break;
              case 5:modifiedDir=1;
                  break;
              case 6:modifiedDir=2;
                  break;
              case 7:modifiedDir=3;
                  break;
              case 2:modifiedDir=6;
                  break;
              
          }
          encode[i][j]=modifiedDir;
          marked[i][j]=1;
//check terminating condition here
        if(ax==j&&ay==i || jj==j&ii==i)
            done=true;
 
       }
       marked[start.y][start.x]=1;
       }
       start=null;
       } 
       /*print char 
       for(int ii=0;ii<encode.length;ii++){
           
        for (int jj = 0; jj < encode[0].length; jj++) {
               if(encode[ii][jj]!=-1)
              System.out.print(encode[ii][jj]+" ");
               else
               System.out.print("  ");    
           }
          System.out.println();

       }
             */
       fileWriter.flush();
       fileWriter.close();
       
     //  String sql = "UPDATE TESTIMAGEANALYSIS SET PERIMETER="+nr[0]+",BOUNDARIES="+nb+",INTERNAL_ONE="+nr[1]+",INTERNAL_TWO="+nr[2]+" WHERE COMPONENTNO='Image"+driver.Nimage+"_component"+k+"'";
      // stmt.executeUpdate(sql);
       tempCharacter.perimeter=nr[0];
       tempCharacter.B0=nr[0];
       tempCharacter.B1=nr[1];
       tempCharacter.B2=nr[2];
       tempCharacter.nBoundaries=nb;
       
       try {
           
            fileWriter = new FileWriter(root+"//root"+rand+"\\Encoding\\Image"+Nimage+"_component"+k + ".csv");
            //csv is created with row col interchange ... to read as buffered image do appropriate conversion
            for(int i=0;i<encode.length;i++)
            {
            for(int j=0;j<encode[0].length;j++)
            {
             fileWriter.append(String.valueOf(encode[i][j]));
             fileWriter.append(COMMA_DELIMITER);
            }
             fileWriter.append(NEW_LINE_SEPARATOR);
            }
    }
         catch(Exception e)
         {e.printStackTrace();}
         finally{
         fileWriter.flush();
         fileWriter.close();
      //   stmt.close();
         }
    //con.close();
        return tempCharacter;
    }
}