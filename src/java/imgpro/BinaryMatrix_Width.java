package imgpro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryMatrix_Width {

    private final String COMMA_DELIMITER = ",";

    public int[][] constructPixArray(String imageName,String root,String rand) throws FileNotFoundException, IOException {
        //  BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\Shikha\\Documents\\NetBeansProjects\\ImgProc\\web\\ComponentLabelCsv\\Image"+driver.Nimage+"_component"+k + ".csv"));
        BufferedReader fileReader = new BufferedReader(new FileReader(root +"//root"+rand+ "\\ComponentLabelCsv\\" + imageName + ".csv"));
        String line;
        ArrayList<ArrayList> outerArr = new ArrayList<ArrayList>();
        ArrayList a;
        ArrayList<Integer> aa = new ArrayList<Integer>();
        int i = 0, j = 0, nrow = 0, ncol = 0;

        while ((line = fileReader.readLine()) != null) {
            a = new ArrayList();
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {

                int l = tokens.length;
                ncol = tokens.length;
                while (l > 0) {
                    a.add(j, Integer.parseInt(tokens[j++]));
                    l--;
                }
            }
            outerArr.add(a);
            j = 0;
        }
        nrow = outerArr.size();
        int cPix[][] = new int[nrow][ncol];

        for (i = 0; i < cPix.length; i++) {
            aa = outerArr.get(i);
            for (j = 0; j < cPix[0].length; j++) {
                cPix[i][j] = (int) aa.get(j);
            }
        }
        outerArr.clear();
        fileReader.close();
        return cPix;
    }
}
