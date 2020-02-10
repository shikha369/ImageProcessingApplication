/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgpro;

import java.util.ArrayList;

/**
 *
 * @author Shikha
 */
public class centeredAverage {

    public String getCenteredAverage(String line, int width) {
        String smoothenedBoundatryEncoding = "";
        ArrayList<Double> angleList = new ArrayList<Double>();
        ArrayList<Double> CenteredAvgList = new ArrayList<Double>();
       // width = width / 4;
        width=1;
        if (width == 0) {
            return line;
        }
        String[] tokens = line.split(" ");
        int size = tokens.length;
        for (int i = 0; i < size; i++) {
            angleList.add(Double.parseDouble(tokens[i]));
        }

        double cent = 0;
        for (int i = 0; i < size; i++) {
            cent = 0;
            for (int j = i - width; j <= i + width; j++) {
                cent = cent + angleList.get(getIndex(j, size));
            }
            CenteredAvgList.add(cent / (2 * width + 1));
        }

        for (int i = 0; i < size; i++) {
            //System.out.println(CenteredAvgList.get(i));
            smoothenedBoundatryEncoding = smoothenedBoundatryEncoding + Double.toString(CenteredAvgList.get(i))+" ";
        }

        return smoothenedBoundatryEncoding;
    }

    public static int getIndex(int index, int size) {
        if (index < 0) {
            return (size + index);
        } else {
            return (index % size);
        }
    }

}
