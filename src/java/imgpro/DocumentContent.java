/*
 * To generate documnet content
 *it has access to both : 
 RecognisedCharacterList    //to be created by R decision tree or Java If-Else rules with String Matching 
 DistanceBetweenCharacetrs
 */
package imgpro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentContent {

    class indexStructure {

        int lineNo;
        int wordNo;
        String word;
    }

    ArrayList<indexStructure> idsList = new ArrayList<indexStructure>();

    public String generateContentInString(ArrayList<ComponentRecognisedAs> RecognisedCharacetrList,
            ArrayList<Double> DistanceBetweenCharacetrs,
            double meanDistancebetweenCharacters) throws SQLException {
        indexStructure ids;
        String docContent = "";
        int wordCount = 1;
        int lineCOunt = 1;
        String word = "";
        try {
            double meanDistBwChar = meanDistancebetweenCharacters;
            for (int i = 0; i < RecognisedCharacetrList.size() - 1; i++) {
                docContent = docContent + RecognisedCharacetrList.get(i).recAs;
                word = word + RecognisedCharacetrList.get(i).recAs;
                if (DistanceBetweenCharacetrs.get(i) >.85* meanDistBwChar&&DistanceBetweenCharacetrs.get(i)<3* meanDistBwChar) //measure for word seperation
               
                {
                    docContent = docContent + "  ";
                    ids = new indexStructure();
                    ids.lineNo = lineCOunt;
                    ids.wordNo = wordCount;
                    ids.word = word;
                    idsList.add(ids);
                    word = "";
                    wordCount++;

                } else if (DistanceBetweenCharacetrs.get(i) >= 3* meanDistBwChar) {
                    docContent = docContent + System.lineSeparator();
                    
                    ids = new indexStructure();
                    ids.lineNo = lineCOunt;
                    ids.wordNo = wordCount;
                    ids.word = word;
                    idsList.add(ids);
                    word = "";
                    wordCount = 1;
                    lineCOunt++;
                }
            }
            docContent = docContent + RecognisedCharacetrList.get(RecognisedCharacetrList.size() - 1).recAs;
            word = word + RecognisedCharacetrList.get(RecognisedCharacetrList.size() - 1).recAs;
            ids = new indexStructure();
            ids.lineNo = lineCOunt;
            ids.wordNo = wordCount;
            ids.word = word;
            idsList.add(ids);
        } catch (Exception e) {
            e.printStackTrace();
            //ProcessImages.con.close();

        } finally {
            return docContent;
        }
    }

    public void writeDocument(String root, String rand, String contentString) throws DocumentException, FileNotFoundException {
        String writePath = root + "//root" + rand + "\\result.pdf";
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(writePath));
        document.open();
        //add data dynamically here from arraylist of classified characetrs
        document.add(new Paragraph(contentString));
        document.close();
    }

    public void writeIndexedDocument(String root, String rand, String contentString) throws IOException {
        String writePath = root + "//root" + rand + "\\indexed.txt";
        FileWriter fileWriter = new FileWriter(writePath);
        for (int i = 0; i < idsList.size(); i++) {
            fileWriter.write(idsList.get(i).lineNo + " , " + idsList.get(i).wordNo + " , " + idsList.get(i).word);
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.flush();
        fileWriter.close();

    }

}
