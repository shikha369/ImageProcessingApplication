/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Shikha
 */
public class ApproximateWidth {
    int[][] Image_Width_Demo; 
    
    public int getApproximateWidth(String imageName,String  root,String rand) throws ClassNotFoundException, SQLException, IOException
    {
        //String root=ProcessImages.rootPath;
        int approxWidth=0;
        BufferedReader fileReader;
        DescriptiveStatistics min_Width = new DescriptiveStatistics();
        DescriptiveStatistics width = new DescriptiveStatistics();
        
        BinaryMatrix_Width rc=new BinaryMatrix_Width();
        // System.out.println("testing width calculation");
        // System.out.println("get component in BinaryMatrix");
        //call reconstructLabel with Image1
        Image_Width_Demo=rc.constructPixArray(imageName,root,rand);
    /*    
        for(int r=0;r<Image_Width_Demo.length;r++)
        {
        for(int c=0;c<Image_Width_Demo[0].length;c++)
        {
        System.out.print(Image_Width_Demo[r][c]+" ");
        }
        System.out.println();
        }
        
        System.out.println(Image_Width_Demo.length);
        System.out.println(Image_Width_Demo[0].length);
        System.out.println();
            */
        //read ModPerimeterCsv coordinates
          fileReader=new BufferedReader(new FileReader(root+"//root"+rand+"\\ModPerimeterCsv\\"+imageName+".csv"));
          String line = "";
          int x,y,count=0;
          //ArrayList<Integer> width=new ArrayList<Integer>();
          //ArrayList<Integer> min_Width=new ArrayList<Integer>();
          while ((line = fileReader.readLine()) != null) 
           {
               
               String[] tokens = line.split(",");
               
               if (tokens.length > 0) {
                  if("".equals(tokens[0]))
                   break;
                   else
                   {
                   if(count%10==0)
                   {
                   x=Integer.parseInt(tokens[0]);
                   y=1*Integer.parseInt(tokens[1]);
                   //System.out.println(x+" "+y);
                   //from this coordinate traverse in all 8 dir to find w1 to w8 ...Go AntiClockwise
                   width.addValue(get_Width_E(x,y));
                   width.addValue(get_Width_NE(x,y));
                   width.addValue(get_Width_N(x,y));
                   width.addValue(get_Width_NW(x,y));
                   width.addValue(get_Width_W(x,y));
                   width.addValue(get_Width_SW(x,y));
                   width.addValue(get_Width_S(x,y));
                   width.addValue(get_Width_SE(x,y));
                   }
                   count++;
                   }
               }
               //choose minimum of all and store in min width array 
                 min_Width.addValue(width.getMean());
               //  System.out.println(width.getMean());
               
           }
          //calculate mean
          //  System.out.println("***");
            approxWidth=(int) min_Width.getMean();
         //   System.out.println(min_Width.getMean());
         //   System.out.println(min_Width.getStandardDeviation());
            fileReader.close();
            return approxWidth+1; //rounding off ..  ceiling
    
    }
    
    public int get_Width_E(int x,int y)
    {
        int width=0;
        
        while(x<Image_Width_Demo[0].length&&Image_Width_Demo[y][x]!=0) //Image_Width_Demo[y][x]==label 
        {
        width++;
        x=x+1;
        }
        return width;
    }
    
    public  int get_Width_W(int x,int y)
    {
        int width=0;
        while(x>=0&&Image_Width_Demo[y][x]!=0 )
        {
        width++;
        x=x-1;
        }
        return width;
    }
     
    public  int get_Width_N(int x,int y)
    {
        int width=0;
        while(y>=0&&Image_Width_Demo[y][x]!=0)
        {
        width++;
        y=y-1;
        }
        return width;
    }
    
    public int get_Width_S(int x,int y)
    {
        int width=0;
        while(y<Image_Width_Demo.length&&Image_Width_Demo[y][x]!=0  )
        {
        width++;
        y=y+1;
        }
        return width;
    }
    
    public  int get_Width_NE(int x,int y)
    {
        int width=0;
        while(y>=0 && x<Image_Width_Demo[0].length&&Image_Width_Demo[y][x]!=0 )
        {
        width++;
        y=y-1;
        x=x+1;
        }
        return width;
    }
    
    public int get_Width_SE(int x,int y)
    {
        int width=0;
        while(y<Image_Width_Demo.length && x<Image_Width_Demo[0].length&&Image_Width_Demo[y][x]!=0 )
        {
        width++;
        y=y+1;
        x=x+1;
        }
        return width;
    }
    
    public  int get_Width_SW(int x,int y)
    {
        int width=0;
        while(y<Image_Width_Demo.length && x>=0&&Image_Width_Demo[y][x]!=0 )
        {
        width++;
        y=y+1;
        x=x-1;
        }
        return width;
    }
    
    public int get_Width_NW(int x,int y)
    {
        int width=0;
        while(y>=0 && x>=0&&Image_Width_Demo[y][x]!=0 )
        {
        width++;
        y=y-1;
        x=x-1;
        }
        return width;
    }
    
  }
