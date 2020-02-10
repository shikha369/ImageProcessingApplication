package imgpro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class reconstruct {

    private final String COMMA_DELIMITER = ",";

    public int[][] constructPixArray(int k,String root,String rand,String Nimage) throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(root+"//root"+rand+"\\BoundingRectCsv\\Image"+Nimage+"_component"+k + ".csv"));
        String line;

        ArrayList<ArrayList> outerArr = new ArrayList<ArrayList>();
        ArrayList a;
        ArrayList<Integer> aa=new ArrayList<Integer>();

        int i = 0, j = 0, nrow = 0, ncol = 0;
       
        while ((line = fileReader.readLine()) != null) {
            a = new ArrayList();
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {
               
                int l = tokens.length;
                ncol = tokens.length;
                while (l > 0) {
                    a.add(j,Integer.parseInt(tokens[j++]));
                    l--;
                }
            }
            outerArr.add(a);
            j = 0;
        }
        nrow = outerArr.size();
        int cPix[][] = new int[nrow][ncol];

         for (i = 0; i < cPix.length; i++)
         {
            aa=outerArr.get(i);
           for (j = 0; j < cPix[0].length; j++)
           {
            cPix[i][j]=(int)aa.get(j);
           }
         }
      return cPix;
    }  
}

