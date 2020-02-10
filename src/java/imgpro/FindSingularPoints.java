/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgpro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * first get perimeter and all angleList... then only u can get different
 * thresholds
 *
 */
public class FindSingularPoints {

    public ArrayList<Double> angles = new ArrayList<Double>();
    public ArrayList<Integer> index = new ArrayList<Integer>();
    public ArrayList<traceSingular> Listts = new ArrayList<traceSingular>();
    public ArrayList<SingularPointAsString> ListspAs = new ArrayList<SingularPointAsString>();
    public ArrayList<Pix> CoordinateList = new ArrayList<Pix>();
    public int NoiseAngleListLengthThreshold;
    public int CornerAngleListLengthThreshold;
    public int DeadendAngleListLengthThreshold;
    public int DeadEndoffsetThreshold;
    public int perimeter;

    BufferedReader fileReader;

    public EncapsulatePointStrokeResult find(String content, String root, String rand, int Nimage, int k) throws FileNotFoundException, IOException {
        EncapsulatePointStrokeResult enps = new EncapsulatePointStrokeResult();
        String enc = "";
        String psenc = "";
        String velocity = "";
        int maxLen;
        int count = 0;
        int p = 0;

        //String content = "260.0 243.0 224.0 206.0 191.0 189.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 201.0 216.0 205.0 213.0 227.0 226.0 211.0 221.0 236.0 237.0 238.0 254.0 259.0 247.0 232.0 233.0 205.0 190.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 191.0 191.0 202.0 206.0 224.0 243.0 258.0 270.0 270.0 261.0 259.0 259.0 247.0 232.0 233.0 205.0 190.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 191.0 180.0 180.0 180.0 169.0 169.0 191.0 180.0 180.0 180.0 180.0 169.0 191.0 180.0 158.0 128.0 75.0 34.0 23.0 34.0 64.0 117.0 147.0 180.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 191.0 191.0 191.0 169.0 169.0 169.0 169.0 169.0 180.0 180.0 180.0 180.0 180.0 180.0 191.0 191.0 201.0 216.0 233.0 232.0 247.0 248.0 259.0 261.0 270.0 270.0 ";
        String[] parts = content.split(" # ");
        //for (int j = 0; j < parts.length; j++) {
        String[] enc0 = parts[0].split(" ");
        for (int i = 0; i < enc0.length - 1; i++) {

            if (Double.parseDouble(enc0[i]) > 150 && Double.parseDouble(enc0[i]) < 220) {
          //  if (Double.parseDouble(enc0[i]) > 135 && Double.parseDouble(enc0[i]) < 245) {
                velocity = velocity + "0 ";
            } else {
                velocity = velocity + "1 ";
                index.add(count);
            }
            if (Double.isNaN(Double.parseDouble(enc0[i]))) {
                angles.add(360.0);
            } else {
                angles.add(Double.parseDouble(enc0[i]));
            }
            count++;
        }
            //velocity = velocity + "# ";

        //}

        /* for (int i = 0; i < index.size(); i++) {
         System.out.println(angles.get(index.get(i)));
         }*/
        traceSingularPoint(angles, index);
        setPerimeter();
        maxLen = getMaxAngleListLength();
        setThreshold(perimeter, maxLen);
        /*in case of a zero or o */
        if(Listts.size()==1)
        {
        enps.pointEnc = "";
        enps.pointStrokeEnc = "";
        return enps;
        }
        else
        {
        /*for each singular point do the following*/
        
        for (int i = 0; i < Listts.size(); i++) {
            Listts.get(i).meanAngle = Listts.get(i).getAngleMean();
            if (Listts.get(i).lengthOfAngleList <= NoiseAngleListLengthThreshold) {
                Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.Noise;
            } else if (Listts.get(i).lengthOfAngleList < DeadendAngleListLengthThreshold) {
                if (Listts.get(i).meanAngle <= 60) {
                    Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.InwardSharpCorner;
                } else if (Listts.get(i).meanAngle > 60 && Listts.get(i).meanAngle < 180) {
                    Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.InwardCorner;
                } else if (Listts.get(i).meanAngle >= 180 && Listts.get(i).meanAngle < 290) {
                    Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.OutwardCorner;
                } else if (Listts.get(i).meanAngle >= 290) {
                    Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.Deadend;
                }

            } else if (Listts.get(i).lengthOfAngleList >= DeadendAngleListLengthThreshold && Listts.get(i).meanAngle >= 180) {
                Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.Deadend;
            } else if (Listts.get(i).lengthOfAngleList >= DeadendAngleListLengthThreshold && Listts.get(i).meanAngle < 180) {
                Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.InwardCorner;
            } else {
                Listts.get(i).typeOfSingularPoint = traceSingular.SingularPoint.Noise;
            }
            System.out.println(Listts.get(i).meanAngle);
        }

        printSingularPointString();
        /*refine*/
        enc = refineSingularPointString();
        /*to get strok type*/
        prepareCoordinateList(root, rand, Nimage, k);
        psenc = getpointStrokeEncoding();

        enps.pointEnc = enc;
        enps.pointStrokeEnc = psenc;
        return enps;//also return psenc ..pack into one object
    }}

    public void traceSingularPoint(ArrayList<Double> angels, ArrayList<Integer> index) {
        //access index and angles list
        boolean newtrace = true;
        traceSingular cobjts = new traceSingular();
        ArrayList<Double> alist = new ArrayList<Double>();
        int c = 0;
        for (int i = 0; i < index.size(); i++) {
            if (i == index.size() - 1) {
                if (!newtrace) /*& (angles.get(index.get(i)) < 150 || angles.get(index.get(i)) > 220))*/ /*&& (angles.get(index.get(i)) > 150 || angles.get(index.get(i)) < 220))*/ //end point f current objts
                /*current trace has to b ended*/ {
                    alist.add(angles.get(index.get(i)));
                    cobjts.endIndex = index.get(i);
                    if (Listts.isEmpty()) {
                        cobjts.offset = 0;//only singular string in case of zero or o
                    } else {
                        cobjts.offset = angels.size() - index.get(i) + Listts.get(0).startIndex;//setting wrong offset :/
                    }
                    //System.out.println(cobjts.offset);
                    //System.out.println(index.get(i));
                    //System.out.println(angels.size());
                    cobjts.angleList = alist;
                    Listts.add(c++, cobjts);
                }
                if(newtrace)
                {
                newtrace = false;
                cobjts = new traceSingular();
                alist = new ArrayList<Double>();
                cobjts.startIndex = index.get(i);
                cobjts.endIndex = index.get(i);
                alist.add(angles.get(index.get(i)));
                // cobjts = objts;
                cobjts.offset = 1;
                cobjts.angleList = alist;
                Listts.add(c++, cobjts);
                newtrace = true;
                }
                // break;
            } else if (newtrace && index.get(i + 1) == index.get(i) + 1) {
                newtrace = false;
                cobjts = new traceSingular();
                alist = new ArrayList<Double>();
                cobjts.startIndex = index.get(i);
                alist.add(angles.get(index.get(i)));
                // cobjts = objts;
            } else if (!newtrace & index.get(i + 1) != index.get(i) + 1) {
                alist.add(angles.get(index.get(i)));
                cobjts.endIndex = index.get(i);
                cobjts.offset = (index.get(i + 1) - index.get(i));
                cobjts.angleList = alist;
                Listts.add(c++, cobjts);
                newtrace = true;
            } else if (!newtrace & index.get(i + 1) == index.get(i) + 1) {
                alist.add(angles.get(index.get(i)));
            } else if (newtrace && index.get(i + 1) != index.get(i) + 1) {
                newtrace = false;
                cobjts = new traceSingular();
                alist = new ArrayList<Double>();
                cobjts.startIndex = index.get(i);
                cobjts.endIndex = index.get(i);
                alist.add(angles.get(index.get(i)));
                // cobjts = objts;
                cobjts.offset = 1;
                cobjts.angleList = alist;
                Listts.add(c++, cobjts);
                newtrace = true;
            } 
            
            else {
                continue;
            }

        }
        for (int i = 0; i < Listts.size(); i++) {
            System.out.println(Listts.get(i).startIndex + " " + Listts.get(i).endIndex
                    + " " + Listts.get(i).angleList + " " + Listts.get(i).offset);
        }

    }

    public int getMaxAngleListLength() {
        int maxLen = 0;
        for (int i = 0; i < Listts.size(); i++) {
            Listts.get(i).lengthOfAngleList = Listts.get(i).angleList.size();
            if (maxLen < Listts.get(i).lengthOfAngleList) {
                maxLen = Listts.get(i).lengthOfAngleList;
            }
        }
        return maxLen;
    }

    public void setPerimeter() {
        perimeter = angles.size();
    }

    public void setThreshold(int perimeter, int maxAngleListLength) throws IOException {
        //while doing on encalpha change noise to zero nd set threshold for deadend offset 
        NoiseAngleListLengthThreshold = (int) Math.floor(.3 * maxAngleListLength);/*less than*/
       // NoiseAngleListLengthThreshold=0;
        CornerAngleListLengthThreshold = (int) Math.ceil(.8 * maxAngleListLength);/* b/w noise n this */

        DeadendAngleListLengthThreshold = (int) Math.ceil(.8 * maxAngleListLength);/* above this*/
        
        //pfile.setPropValues(setgap
        /*  Properties prop = new Properties();
	  String propFileName = "config.properties";
          InputStream  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
          prop.getProperty("setgap");
         DeadEndoffsetThreshold=Integer.parseInt(prop.getProperty("setgap"))+1;*/
        //DeadEndoffsetThreshold = (int) (0.03 * perimeter);
        DeadEndoffsetThreshold = (int) (.1 * perimeter);
        

    }

    public void printSingularPointString() {
        for (int i = 0; i < Listts.size(); i++) {
            System.out.println(Listts.get(i).typeOfSingularPoint);
        }
    }

    public String refineSingularPointString() {
        String enc = "";
        int n = Listts.size();
        if (n == 0) {
            return enc;
        }
        SingularPointAsString sp = new SingularPointAsString();
        for (int i = 0; i < n; i++) {
            if (i == 0 && Listts.get(i).startIndex < 5) {
                if (((Listts.get(i).typeOfSingularPoint == traceSingular.SingularPoint.Deadend
                        || Listts.get(i).typeOfSingularPoint == traceSingular.SingularPoint.OutwardCorner)
                        && (Listts.get(n - 1).typeOfSingularPoint == traceSingular.SingularPoint.Deadend
                        || Listts.get(n - 1).typeOfSingularPoint == traceSingular.SingularPoint.OutwardCorner)
                        && Listts.get(n - 1).offset < DeadEndoffsetThreshold)) {
                    sp = new SingularPointAsString();
                    sp.index = Listts.get(i).startIndex;
                    sp.singular = 0;
                    n--;
                    Listts.remove(n - 1);
                }
            } else {
                if (i != Listts.size() - 1 & (Listts.get(i).typeOfSingularPoint == traceSingular.SingularPoint.Deadend
                        || Listts.get(i).typeOfSingularPoint == traceSingular.SingularPoint.OutwardCorner)
                        && (Listts.get(i + 1).typeOfSingularPoint == traceSingular.SingularPoint.Deadend
                        || Listts.get(i + 1).typeOfSingularPoint == traceSingular.SingularPoint.OutwardCorner)
                        && Listts.get(i).offset < DeadEndoffsetThreshold) {
                    sp = new SingularPointAsString();
                    sp.index = (Listts.get(i + 1).endIndex + Listts.get(i).startIndex) / 2;
                    sp.singular = 0;
                    i++;
                } else if (Listts.get(i).typeOfSingularPoint == traceSingular.SingularPoint.Noise) {
                    //Do Nothing
                    continue;
                } else {
                    sp = new SingularPointAsString();
                    if (i == Listts.size() - 1) {
                        sp.index = (Listts.get(i).endIndex + Listts.get(i).startIndex) / 2;
                    } else {
                        sp.index = (Listts.get(i).endIndex + Listts.get(i).startIndex) / 2;
                    }
                    switch (Listts.get(i).typeOfSingularPoint) {
                        case Deadend:
                            sp.singular = 0;
                            break;
                        case InwardCorner:
                            sp.singular = 2;
                            break;
                        case InwardSharpCorner:
                            sp.singular = 1;
                            break;
                        case OutwardCorner:
                            sp.singular = 3;
                            break;
                    }
                }
            }
            ListspAs.add(sp);
        }
        for (int i = 0; i < ListspAs.size(); i++) {
            enc = enc + ListspAs.get(i).singular + " ";
        }
        return enc;
    }

    public void prepareCoordinateList(String root, String rand, int Nimage, int k) throws FileNotFoundException, IOException {
        fileReader = new BufferedReader(new FileReader(root + "//root" + rand + "//ModPerimeterCsv//Image" + Nimage + "_component" + k + ".csv"));
        String line;
        Pix p;
        while ((line = fileReader.readLine()) != null) {

            p = new Pix();
            String[] tokens = line.split(",");

            if (tokens.length > 0) {

                if ("".equals(tokens[0])) {
                    break;
                }
                p.x = Integer.parseInt(tokens[0]);
                p.y = -1 * Integer.parseInt(tokens[1]);

                CoordinateList.add(p);

            }
        }
        fileReader.close();
    }

    public String getpointStrokeEncoding() {

        String pointStrokeEncoding = "";
        int start, end;
        ArrayList<Pix> tempList;
        int ct;
        testRegression tr;
        int len = CoordinateList.size();
        /* access coordinate list as well as Singular point list*/
        for (int i = 0; i < ListspAs.size(); i++) {
            tempList = new ArrayList<Pix>();
            start = ListspAs.get(i).index;
            if (i == ListspAs.size() - 1) {
                end = ListspAs.get(0).index;
            } else {

                end = ListspAs.get(i + 1).index;
            }
            if (i == ListspAs.size() - 1) {
                for (int j = start; j < len; j++) {
                    tempList.add(CoordinateList.get(j));
                }
                for (int j = 0; j <= end; j++) {
                    tempList.add(CoordinateList.get(j));
                }
            } else {
                for (int j = start; j <= end; j++) {
                    tempList.add(CoordinateList.get(j));
                }
            }
            System.out.println(tempList.toString());
            /*get curve type and encode;*/
            if (tempList.isEmpty()) {
                pointStrokeEncoding = pointStrokeEncoding + ListspAs.get(i).singular + "E";
            } else {
                tr = new testRegression();
                ct = tr.getCurveType(tempList);
                if (ct == 0) {
                    pointStrokeEncoding = pointStrokeEncoding + ListspAs.get(i).singular + "L";
                } else {
                    pointStrokeEncoding = pointStrokeEncoding + ListspAs.get(i).singular + "C";
                }
            }

        }

        return pointStrokeEncoding;
    }
}
