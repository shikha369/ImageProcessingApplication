
package imgpro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EncodeLeftRight {
   BufferedReader fileReader;
   BufferedWriter fileWriter;
   String COMMA_DELIMITER = ",";
   String NEW_LINE_SEPARATOR = "\n";
   ArrayList<String> DirectionList=new ArrayList<String>(); 
  
  public String findDir(int prev,int next)
   {
       String dir;
       
       //condition for left
       if((prev==1&next==2)||(prev==1&next==3)||(prev==1&next==4)||(prev==0&next==1)||(prev==0&next==2)||
          (prev==0&next==3)||(prev==2&next==3)||(prev==2&next==4)||(prev==2&next==5)||(prev==3&next==4)||
          (prev==3&next==5)||(prev==3&next==6)||(prev==4&next==5)||(prev==4&next==6)||(prev==4&next==7)||
          (prev==5&next==0)||(prev==5&next==6)||(prev==5&next==7)||(prev==6&next==0)||(prev==6&next==1)||
          (prev==6&next==7)||(prev==7&next==0)||(prev==7&next==1)||(prev==7&next==2))
           dir="L";
       
       //condition for straight
       else if((prev==1&next==1)||(prev==0&next==0)||(prev==0&next==4)||(prev==2&next==2)||(prev==2&next==6)||
               (prev==3&next==3)||(prev==4&next==0)||(prev==4&next==4)||(prev==5&next==5)||(prev==6&next==2)||
               (prev==6&next==6)||(prev==7&next==7))
           dir="S";
       
        //condition for angle
       else if((prev==1&next==5)||(prev==3&next==7)||(prev==5&next==1)||(prev==7&next==3))
           dir="A";
       
       //condition for right
       else
           dir="R";
       return dir;
  }
  
  
  public String encode(String line) throws IOException
   // public static void main(String [] args) throws FileNotFoundException, IOException
    {
       String dir;
       String word = null;
       int i,prev,next = 0;
       boolean first=false;
       
               dir="L";//start with L
               
               String[] numbers=line.split(" ");
              // int nWords=words.length;
               
             //  for(int c=0;c<nWords;c++)
              // {
             //  String[] numbers = words[c].split(" "); 
               int size=numbers.length;
               
               first=false;
               for(i=0;i<size;i++)
               {
                   if("#".equals(numbers[i])){
                   dir=dir+" L #";
                   first=false;
                   continue;
                   }
                   else{
                   if(!first)
                   {
                   prev=Integer.parseInt(numbers[i]);
                   next=prev;
                   first=true;
                   }
                   else
                   {
                   prev=next;
                   next=Integer.parseInt(numbers[i]);
                   dir=dir+findDir(prev,next);
                  
                   }
            //   }
               dir=dir+" ";
               }}
            dir=dir+" L"; //end with L
     return dir;
   }
}
