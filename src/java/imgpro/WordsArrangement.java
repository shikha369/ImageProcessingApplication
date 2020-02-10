package imgpro;

import java.util.ArrayList;

/*code to arrange sorted characters into words*/
public class WordsArrangement {

    public EncapsulatedDistanceResult makeWords(ArrayList<ComponentInfo> charList, ArrayList<Double> DistanceBetweenCharacetrs) {
        EncapsulatedDistanceResult edr = new EncapsulatedDistanceResult();
        double meanDistBwChar = 0;
        double DistBwChar = 0;
        System.out.println("call from word arrang");
        for (int i = 0; i < charList.size() - 1; i++) {
            System.out.println(charList.get(i).path);
            DistBwChar = (Math.pow((Math.pow((charList.get(i).topx - charList.get(i + 1).topx), 2) + Math.pow((charList.get(i).topy - charList.get(i + 1).topy), 2)), .5));
            DistanceBetweenCharacetrs.add(DistBwChar);
            meanDistBwChar = meanDistBwChar + DistBwChar;
        }
        meanDistBwChar = meanDistBwChar / DistanceBetweenCharacetrs.size();
        edr.DistanceBetweenCharacetrs = DistanceBetweenCharacetrs;
        edr.meanDistBwChar = meanDistBwChar;
        return edr;
    }
}
