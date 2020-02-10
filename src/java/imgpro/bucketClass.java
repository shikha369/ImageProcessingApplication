/*
 * find buckets using regular expression and replace encoding with #
 *  regex : (0|3)(L)(1|2)(L)(1|2)(L)(0|3)
 *  0-deadend 1-sharpInnerCorner 2-InnerCorner 3-outwardCorner
 */
package imgpro;

/**
 *
 * @author Shikha
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bucketClass {

    public String findBucketStructure(String encoding) {
        ArrayList<Integer> bucketList = new ArrayList<Integer>();
        int loc;
        // String encoding ="0L0L2L0L2L0C0L2L3L";
        // String bucket = "(0|3)(L)(1|2)(L)(0|3)";
        String bucket = "(0|3)(L)(1|2)(L)(1|2)(L)(0|3)";
        // Create a Pattern object
        Pattern r = Pattern.compile(bucket);

        // Now create matcher object.
        Matcher m = r.matcher(encoding);
        int count = 0;
        int start = 0;
        while (m.find(start)) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
            start = m.end() - 1;
            loc = m.start();
            bucketList.add(loc);

        }
        String encoding1 = encoding.concat(encoding);
        String modEnc = "";
        m = r.matcher(encoding1);
        if (m.find(start)) {
            count++;
            // System.out.println("Match number " + count);
            //System.out.println("start(): " + m.start());
            //System.out.println("end(): " + m.end());
            loc = m.start();
            if (loc < encoding.length()) {
                bucketList.add(loc);
            }
        }

        /*modify encoding*/
        int j = 0;/*keep track of index in enc*/

        if (bucketList.isEmpty()) {
            modEnc = encoding;
        } else {
            String[] arEnce = encoding.split("");
            String[] arEnc = new String[arEnce.length - 1];
            for (int i = 0; i < arEnce.length - 1; i++) {
                arEnc[i] = arEnce[i + 1];
            }
            for (int i = 0; i < bucketList.size(); i++) {
                if (i == 0 & bucketList.get(0) != 0) {
                    for (j = 0; j < bucketList.get(0); j++) {
                        modEnc = modEnc + arEnc[j];
                    }
                    j--;
                }

                modEnc = modEnc + "#";
                j = j + 6; /*increment by structure.length-1*/

                if (i < bucketList.size() - 1) {
                    if (bucketList.get(i + 1) - j > 1)/*copy intermediate*/ {
                        j++;
                        for (; j < bucketList.get(i + 1); j++) {
                            modEnc = modEnc + arEnc[j];
                        }
                    }
                    if (bucketList.get(i + 1) - j == 1)/*copy intermediate*/ {
                        j++;

                    }
                } //if (j<arEnc.length-1)
                else {
                    j++;
                    for (; j < arEnc.length; j++) {
                        modEnc = modEnc + arEnc[j];
                    }
                }
            }

        }
      //  System.out.println(encoding);
        //  System.out.println(modEnc);
        return modEnc;
    }
}
