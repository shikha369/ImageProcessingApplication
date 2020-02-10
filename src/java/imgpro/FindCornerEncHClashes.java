
package imgpro;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FindCornerEncHClashes {
    ArrayList<String> charClass=new ArrayList<String>();
    ArrayList<String> Encoding=new ArrayList<String>();
    ArrayList<String> distinctEncoding=new ArrayList<String>();   
 
  public void GetDistinct(){   // perfectly fine
        for(int i=0;i<Encoding.size();i++){
            boolean isDistinct = false;
            for(int j=0;j<i;j++){
                if(Encoding.get(i).equals(Encoding.get(j))){
                    isDistinct = true;
                    break;
                }
            }
            if(!isDistinct){
                distinctEncoding.add(Encoding.get(i));
            }
        }
    }
 //end of GetDistinct
   public void createCornerEncHClashesCsv() throws ClassNotFoundException, SQLException, IOException
   {
   Database db=new Database();
   Connection con=db.connect();
   Statement stmt=con.createStatement();
   
   ArrayList<String> characters=new ArrayList<String>();
   characters.add("zero");characters.add("one");characters.add("two");characters.add("three");
   characters.add("four");characters.add("five");characters.add("six");characters.add("seven");
   characters.add("eight");characters.add("nine");characters.add("A");characters.add("B");
   characters.add("C");characters.add("D");characters.add("E");characters.add("F");
   characters.add("G");characters.add("H");characters.add("I");characters.add("J");
   characters.add("K");characters.add("L");characters.add("M");characters.add("N");
   characters.add("O");characters.add("P");characters.add("Q");characters.add("R");
   characters.add("S");characters.add("T");characters.add("U");characters.add("V");
   characters.add("W");characters.add("X");characters.add("Y");characters.add("Z");
   
       ResultSet rs;
       String distinct,orig;
       int index=characters.size();
       ArrayList<CharSign> cs=new ArrayList<CharSign>();
       int d,cc,cv;
       
       for(int i=0;i<index;i++)
       {

       String sql="SELECT CHARCLASS,CORNERENC,BOUNDARIES "
               + "FROM IMAGEANALYSIS WHERE CHARCLASS LIKE '"+characters.get(i)+"%'";
       rs=stmt.executeQuery(sql);
       while(rs.next())
       {    
       charClass.add(rs.getString(1));
       Encoding.add(rs.getString(2)+Integer.toString(rs.getInt(3)));
       }
       rs.close();
       }
       
       GetDistinct();
       String getClass;
       CharSign CharSignObj;
       charclass zero,one,two,three,four,five,six,seven,eight,nine,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
       int classindex;
       for(int i=0;i<distinctEncoding.size();i++)
       {  CharSignObj =new CharSign();
          zero=new charclass();
          one=new charclass();
          two=new charclass();
          three=new charclass();
          four=new charclass();
          five=new charclass();
          six=new charclass();
          seven=new charclass();
          eight=new charclass();
          nine=new charclass();
          A=new charclass();B=new charclass();C=new charclass();D=new charclass();E=new charclass();
          F=new charclass();G=new charclass();H=new charclass();I=new charclass();J=new charclass();
          K=new charclass();L=new charclass();M=new charclass();N=new charclass();O=new charclass();
          P=new charclass();Q=new charclass();R=new charclass();S=new charclass();T=new charclass();
          U=new charclass();V=new charclass();W=new charclass();X=new charclass();Y=new charclass();
          Z=new charclass();
          
          
          
          CharSignObj.zero=zero;CharSignObj.one=one;CharSignObj.two=two;
          CharSignObj.three=three;CharSignObj.four=four;CharSignObj.five=five;
          CharSignObj.six=six;CharSignObj.seven=seven;CharSignObj.eight=eight;
          CharSignObj.nine=nine;CharSignObj.A=A;CharSignObj.B=B;
          CharSignObj.C=C;CharSignObj.D=D;CharSignObj.E=E;
          CharSignObj.F=F;CharSignObj.G=G;CharSignObj.H=H;
          CharSignObj.I=I;CharSignObj.J=J;CharSignObj.K=K;
          CharSignObj.L=L;CharSignObj.M=M;CharSignObj.N=N;
          CharSignObj.O=O;CharSignObj.P=P;CharSignObj.Q=Q;
          CharSignObj.R=R;CharSignObj.S=S;CharSignObj.T=T;
          CharSignObj.U=U;CharSignObj.V=V;CharSignObj.W=W;
          CharSignObj.X=X;CharSignObj.Y=Y;CharSignObj.Z=Z;
          
           distinct=distinctEncoding.get(i);
           CharSignObj.enc=distinct;
           
             for(int j=0;j<Encoding.size();j++){
               orig=Encoding.get(j);
               if(distinct.equals(orig)){
               getClass=charClass.get(j);
              
              if(getClass.contains("zero"))
               CharSignObj.zero.frequency++;
              else if(getClass.contains("one"))
               CharSignObj.one.frequency++;
              else if(getClass.contains("two"))
               CharSignObj.two.frequency++;
              else if(getClass.contains("three"))
               CharSignObj.three.frequency++;
              else if(getClass.contains("four"))
               CharSignObj.four.frequency++;
              else if(getClass.contains("five"))
               CharSignObj.five.frequency++;
              else if(getClass.contains("six"))
               CharSignObj.six.frequency++;
              else if(getClass.contains("seven"))
               CharSignObj.seven.frequency++;
              else if(getClass.contains("eight"))
               CharSignObj.eight.frequency++;
              else if(getClass.contains("nine"))
               CharSignObj.nine.frequency++;
              else if(getClass.contains("nine"))
               CharSignObj.nine.frequency++;
              else if(getClass.contains("A"))
               CharSignObj.A.frequency++;
              else if(getClass.contains("B"))
               CharSignObj.B.frequency++;
              else if(getClass.contains("C"))
               CharSignObj.C.frequency++;
              else if(getClass.contains("D"))
               CharSignObj.D.frequency++;
              else if(getClass.contains("E"))
               CharSignObj.E.frequency++;
              else if(getClass.contains("F"))
               CharSignObj.F.frequency++;
              else if(getClass.contains("G"))
               CharSignObj.G.frequency++;
              else if(getClass.contains("H"))
               CharSignObj.H.frequency++;
              else if(getClass.contains("I"))
               CharSignObj.I.frequency++;
              else if(getClass.contains("J"))
               CharSignObj.J.frequency++;
              else if(getClass.contains("K"))
               CharSignObj.K.frequency++;
              else if(getClass.contains("L"))
               CharSignObj.L.frequency++;
              else if(getClass.contains("M"))
               CharSignObj.M.frequency++;
              else if(getClass.contains("N"))
               CharSignObj.N.frequency++;
              else if(getClass.contains("O"))
               CharSignObj.O.frequency++;
               else if(getClass.contains("P"))
               CharSignObj.P.frequency++;
               else if(getClass.contains("Q"))
               CharSignObj.Q.frequency++;
               else if(getClass.contains("R"))
               CharSignObj.R.frequency++;
              else if(getClass.contains("S"))
               CharSignObj.S.frequency++;
              else if(getClass.contains("T"))
               CharSignObj.T.frequency++;
              else if(getClass.contains("U"))
               CharSignObj.U.frequency++;
              else if(getClass.contains("V"))
               CharSignObj.V.frequency++;
              else if(getClass.contains("W"))
               CharSignObj.W.frequency++;
              else if(getClass.contains("X"))
               CharSignObj.X.frequency++;
              else if(getClass.contains("Y"))
               CharSignObj.Y.frequency++;
              else 
               CharSignObj.Z.frequency++;
               
               
               }
        
           }
             cs.add(CharSignObj);
       }
       
   String COMMA_DELIMITER = ",";
       String NEW_LINE_SEPARATOR = "\n";
       String writePath="C:\\Users\\Shikha\\Documents\\NetBeansProjects\\ImageProcessingApplication\\web\\CORNERencHClashes.csv" ;
       FileWriter fileWriter=new FileWriter(writePath);
       fileWriter.append("Enc");fileWriter.append(COMMA_DELIMITER);fileWriter.append("0");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("1");fileWriter.append(COMMA_DELIMITER);fileWriter.append("2");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("3");fileWriter.append(COMMA_DELIMITER);fileWriter.append("4");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("5");fileWriter.append(COMMA_DELIMITER);fileWriter.append("6");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("7");fileWriter.append(COMMA_DELIMITER);fileWriter.append("8");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("9");fileWriter.append(COMMA_DELIMITER);fileWriter.append("A");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("B");fileWriter.append(COMMA_DELIMITER);fileWriter.append("C");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("D");fileWriter.append(COMMA_DELIMITER);fileWriter.append("E");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("F");fileWriter.append(COMMA_DELIMITER);fileWriter.append("G");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("H");fileWriter.append(COMMA_DELIMITER);fileWriter.append("I");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("J");fileWriter.append(COMMA_DELIMITER);fileWriter.append("K");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("L");fileWriter.append(COMMA_DELIMITER);fileWriter.append("M");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("N");fileWriter.append(COMMA_DELIMITER);fileWriter.append("O");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("P");fileWriter.append(COMMA_DELIMITER);fileWriter.append("Q");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("R");fileWriter.append(COMMA_DELIMITER);fileWriter.append("S");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("T");fileWriter.append(COMMA_DELIMITER);fileWriter.append("U");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("V");fileWriter.append(COMMA_DELIMITER);fileWriter.append("W");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("X");fileWriter.append(COMMA_DELIMITER);fileWriter.append("Y");fileWriter.append(COMMA_DELIMITER);
       fileWriter.append("Z");fileWriter.append(COMMA_DELIMITER);fileWriter.append(NEW_LINE_SEPARATOR);
       
       
       
       
       
       
       
       
       for(int k=0;k<cs.size();k++)
      {
          
          CharSignObj=cs.get(k);
          fileWriter.append(CharSignObj.enc);fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.zero.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.one.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.two.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.three.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.four.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.five.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.six.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.seven.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.eight.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.nine.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.A.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.B.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.C.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.D.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.E.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.F.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.G.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.H.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.I.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.J.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.K.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.L.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.M.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.N.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.O.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.P.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.Q.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.R.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.S.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.T.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.U.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.V.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.W.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.X.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.Y.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(Integer.toString(CharSignObj.Z.frequency));fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(NEW_LINE_SEPARATOR);
      }
       fileWriter.flush();
       fileWriter.close();
       
       
   }
}


    

