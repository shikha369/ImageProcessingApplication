package imgpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.rosuda.REngine.Rserve.RConnection;

public class Classifier {

    public ArrayList<ComponentRecognisedAs> runClassifier(String root, String rand) throws ClassNotFoundException {
        ArrayList<ComponentRecognisedAs> RecognisedCharacetrList = new ArrayList<ComponentRecognisedAs>();

        RConnection connection = null;
       String TrainReadpath=root+"/root"+rand+"/trainedFeatures.csv";
       String TestReadpath=root+"/root"+rand+"/downloadTestFeatures.csv";
       String ResWritePath=root+"/root"+rand+"/recognisedCharacters.csv";
       TrainReadpath = TrainReadpath.replace("\\", "/");
       TestReadpath = TestReadpath.replace("\\", "/");
       ResWritePath = ResWritePath.replace("\\", "/");
     /*   String TrainReadpath = "C:/Users/Shikha/Desktop/fmergedcsv/downloadTestFeatures.csv";
        String TestReadpath = "C:/Users/Shikha/Desktop/fmergedcsv/test.csv";//change tfc 
        String ResWritePath = "C:/Users/Shikha/Desktop/fmergedcsv/res.csv";
*/
        try {
            connection = new RConnection();

            String Cpath=root+"/root"+rand+"/Classifier.R";
            Cpath = Cpath.replace("\\", "/");
            //String Cpath = "C:/Users/Shikha/Desktop/fmergedcsv/Classifier.R";

            System.out.println("source(\"" + Cpath + "\")");
            connection.eval("source(\"" + Cpath + "\")");
            System.out.println("runC('" + TrainReadpath + "','" + TestReadpath + "','" + ResWritePath + "')");
            System.out.println("call R");
            connection.eval("runC('" + TrainReadpath + "','" + TestReadpath + "','" + ResWritePath + "')");
            connection.close();

            System.out.println("Check results");

            //read csv of recognised characters insrt in arraylist
            String line;
            String recChar;
            String rcname;
            ComponentRecognisedAs curRec;
            BufferedReader fileReader = new BufferedReader(new FileReader(root +"//root"+rand+ "\\recognisedCharacters.csv"));

           // BufferedReader fileReader = new BufferedReader(new FileReader(ResWritePath));
            line = fileReader.readLine();//skip header
            while ((line = fileReader.readLine()) != null) {
                curRec = new ComponentRecognisedAs();
                String[] tokens = line.split(",");

                if (tokens.length > 0) {
                    // tokens[0] gives u row number
                    // rcname = tokens[0].replace("\"", "");
                    recChar = tokens[1].replace("\"", "");
                    recChar = convertCharClassToChar(recChar);
                    //  curRec.cname = rcname;
                    curRec.recAs = recChar;
                    RecognisedCharacetrList.add(curRec);
                }
            }
            fileReader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {

            
            for (int i = 0; i < RecognisedCharacetrList.size(); i++) {
                System.out.println(RecognisedCharacetrList.get(i));
            }

            System.out.println(RecognisedCharacetrList.size());

            return RecognisedCharacetrList;
        }
    }

    public String convertCharClassToChar(String charClass) {
        String rec = null;
        if (charClass.equals("ZERO")) {
            rec = "0";
        } else if (charClass.equals("ONE")) {
            rec = "1";
        } else if (charClass.equals("TWO")) {
            rec = "2";
        } else if (charClass.equals("THREE")) {
            rec = "3";
        } else if (charClass.equals("FOUR")) {
            rec = "4";
        } else if (charClass.equals("FIVE")) {
            rec = "5";
        } else if (charClass.equals("SIX")) {
            rec = "6";
        } else if (charClass.equals("SEVEN")) {
            rec = "7";
        } else if (charClass.equals("EIGHT")) {
            rec = "8";
        } else if (charClass.equals("NINE")) {
            rec = "9";
        } else if (charClass.equals("A1")) {
            rec = "A";
        } else if (charClass.equals("B1")) {
            rec = "B";
        } else if (charClass.equals("C1")) {
            rec = "C";
        } else if (charClass.equals("D1")) {
            rec = "D";
        } else if (charClass.equals("E1")) {
            rec = "E";
        } else if (charClass.equals("F1")) {
            rec = "F";
        } else if (charClass.equals("G1")) {
            rec = "G";
        } else if (charClass.equals("H1")) {
            rec = "H";
        } else if (charClass.equals("I1")) {
            rec = "I";
        } else if (charClass.equals("J1")) {
            rec = "J";
        } else if (charClass.equals("K1")) {
            rec = "K";
        } else if (charClass.equals("L1")) {
            rec = "L";
        } else if (charClass.equals("M1")) {
            rec = "M";
        } else if (charClass.equals("N1")) {
            rec = "N";
        } else if (charClass.equals("O1")) {
            rec = "O";
        } else if (charClass.equals("P1")) {
            rec = "P";
        } else if (charClass.equals("Q1")) {
            rec = "Q";
        } else if (charClass.equals("R1")) {
            rec = "R";
        } else if (charClass.equals("S1")) {
            rec = "S";
        } else if (charClass.equals("T1")) {
            rec = "T";
        } else if (charClass.equals("U1")) {
            rec = "U";
        } else if (charClass.equals("V1")) {
            rec = "V";
        } else if (charClass.equals("W1")) {
            rec = "W";
        } else if (charClass.equals("X1")) {
            rec = "X";
        } else if (charClass.equals("Y1")) {
            rec = "Y";
        } else if (charClass.equals("Z1")) {
            rec = "Z";
        }
        else if (charClass.equals("AA")) {
            rec ="\u0905";
        }else if (charClass.equals("AAA")) {
            rec ="\u0906";
        }else if (charClass.equals("EE")) {
            rec ="\u0907";
        }else if (charClass.equals("EEE")) {
            rec ="\u0908";
        }else if (charClass.equals("UU")) {
            rec ="\u0909";
        }else if (charClass.equals("UUU")) {
            rec ="\u090A";
        }else if (charClass.equals("EI")) {
            rec ="\u090F";
        }else if (charClass.equals("EII")) {
            rec ="\u0910";
        }else if (charClass.equals("OO")) {
            rec ="\u0913";
        }else if (charClass.equals("OU")) {
            rec ="\u0914";
        }else if (charClass.equals("AAMATRA")) {
            rec ="\u093E";
        }else if (charClass.equals("KA")) {
            rec ="\u0915";
        }else if (charClass.equals("KHA")) {
            rec ="\u0916";
        }else if (charClass.equals("GA")) {
            rec ="\u0917";
        }else if (charClass.equals("GHA")) {
            rec ="\u0918";
        }else if (charClass.equals("ADA")) {
            rec ="\u0919";
        }else if (charClass.equals("CHA")) {
            rec ="\u091A";
        }else if (charClass.equals("CHHA")) {
            rec ="\u091B";
        }else if (charClass.equals("JA")) {
            rec ="\u091C";
        }else if (charClass.equals("JHA")) {
            rec ="\u091D";
        }else if (charClass.equals("EYA")) {
            rec ="\u091E";
        }else if (charClass.equals("TTA")) {
            rec ="\u091F";
        }else if (charClass.equals("TTHA")) {
            rec ="\u0920";
        }else if (charClass.equals("DDA")) {
            rec ="\u0921";
        }else if (charClass.equals("DDHA")) {
            rec ="\u0922";
        }else if (charClass.equals("NDA")) {
            rec ="\u0923";
        }else if (charClass.equals("TA")) {
            rec ="\u0924";
        }else if (charClass.equals("THA")) {
            rec ="\u0925";
        }else if (charClass.equals("DA")) {
            rec ="\u0926";
        }else if (charClass.equals("DHA")) {
            rec ="\u0927";
        }else if (charClass.equals("NA")) {
            rec ="\u0928";
        }else if (charClass.equals("PA")) {
            rec ="\u092A";
        }else if (charClass.equals("PHA")) {
            rec ="\u092B";
        }else if (charClass.equals("BA")) {
            rec ="\u092C";
        }else if (charClass.equals("BHA")) {
            rec ="\u092D";
        }else if (charClass.equals("MA")) {
            rec ="\u092E";
        }else if (charClass.equals("YA")) {
            rec ="\u092F";
        }else if (charClass.equals("RA")) {
            rec ="\u0930";
        }else if (charClass.equals("LA")) {
            rec ="\u0932";
        }else if (charClass.equals("VA")) {
            rec ="\u0935";
        }else if (charClass.equals("SHA")) {
            rec ="\u0936";
        }else if (charClass.equals("SSHA")) {
           rec ="\u0937";
            //rec =" e0 a4 b7";
        }else if (charClass.equals("SA")) {
            rec ="\u0938";
        }else if (charClass.equals("HA")) {
            rec ="\u0939";
        }else if (charClass.equals("KSHA")) {
            rec ="\u091B";
        }else if (charClass.equals("TRA")) {
            rec ="\u0931";
        }else if (charClass.equals("GYA")) {
            rec ="\u095A";
        }
        else {
            rec = charClass;
        }

        return rec;
    }
}
