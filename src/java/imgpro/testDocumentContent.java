package imgpro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class testDocumentContent {

    class indexStructure {

        int lineNo;
        int wordNo;
        String word;
    }

    ArrayList<indexStructure> idsList = new ArrayList<indexStructure>();

    public String generateContentInString(ArrayList<ComponentRecognisedAs> RecognisedCharacetrList,
            ArrayList<ComponentInfo> characterList) throws SQLException {
        indexStructure ids;
        String docContent = "";
        int wordCount = 1;
        int lineCOunt = 1;
        String word = "";
        //char word;
        int first = 0;
        System.out.println("dc writ");
        try {
            for (int i = 0; i < RecognisedCharacetrList.size() - 1; i++) {
                docContent = docContent + RecognisedCharacetrList.get(i).recAs;
                word = word +RecognisedCharacetrList.get(i).recAs;
               // String encoded = new String(RecognisedCharacetrList.get(i).recAs.getBytes("utf-8"),"utf-8");
                //System.out.println(encoded);
                if (characterList.get(i+1).topx-characterList.get(i).topx>1.2*characterList.get(i).width) //measure for word seperation
               
                {
                    docContent = docContent + "  ";
                    ids = new indexStructure();
                    ids.lineNo = lineCOunt;
                    ids.wordNo = wordCount;
                    ids.word = word;
                    idsList.add(ids);
                    word = "";
                    wordCount++;

                } else if (characterList.get(i+1).topx-characterList.get(i).topx<0) //measure for line change
                {
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
            /* check for last word */
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
        try{
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(writePath));
        document.open();
        //add data dynamically here from arraylist of classified characetrs
        document.add(new Paragraph(contentString));
        document.close();}
        catch(Exception e)
        {
        e.printStackTrace();
        }
    }

    public void writeIndexedDocument(String root, String rand, String contentString) throws IOException {
        String writePath = root + "//root" + rand + "\\indexed.txt";
       // FileWriter fileWriter = new FileWriter(writePath,"UTF8");
      //   Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
	//	new FileOutputStream(writePath), System.getProperty("file.encoding")));
       FileOutputStream fileStream = new FileOutputStream(new File(writePath));
       OutputStreamWriter fileWriter = new OutputStreamWriter(fileStream, "UTF-8");

       for (int i = 0; i < idsList.size(); i++) {
            fileWriter.write(idsList.get(i).lineNo + "," + idsList.get(i).wordNo + ",");
            fileWriter.append(idsList.get(i).word);
            fileWriter.write(System.lineSeparator());
            //System.out.println("UTF: " + new String(idsList.get(i).word.getBytes("UTF-8"),"UTF-8"));
        }
        fileWriter.flush();
        fileWriter.close();

    }

}
