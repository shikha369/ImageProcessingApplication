package imgpro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingModule {

    double[][] distMatrix;
   

    public ArrayList<ComponentInfo> SortCharacetrs(ArrayList<ComponentInfo> CharacterList) {
        //int topLeftX,topLeftY,botRightX,botRightY;
        System.out.println("before sorting");
        for (int i = 0; i < CharacterList.size(); i++) {
            System.out.println(CharacterList.get(i).name);
        }
        
        /*generate distance matrix
        populateDistMatrix(CharacterList);
         */
        
        /*considering slightly slanted docs*/
        Collections.sort(CharacterList,new myPointComp());
        System.out.println("after sorting");
        for (int i = 0; i < CharacterList.size(); i++) {
            System.out.println(CharacterList.get(i).name);
        }
        return CharacterList;
    }

  /*  
    private void populateDistMatrix(ArrayList<ComponentInfo> CharacterList) {
        // populate euclidean distance matrix 
        int size = CharacterList.size();
        distMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distMatrix[i][j] = getEuclideonDistance(CharacterList.get(i).centroidX,CharacterList.get(i).centroidY
                ,CharacterList.get(j).centroidX,CharacterList.get(i).centroidY);
            }
        }
    }
*/
  /*  private double getEuclideonDistance(int x1, int y1, int x2, int y2) {
        double dist;
        int y = Math.abs (y1 - y2);
        int x = Math.abs (x1 - x2);    
        dist = Math.sqrt(y*y +x*x);
   
        return dist;
    }
    */
}

class myPointComp implements Comparator<ComponentInfo> {

    @Override
    public int compare(ComponentInfo C1, ComponentInfo C2) {      /*check coordinate system first*/

        if (C1.topy < C2.topy-C2.height/2) {
            return -1;
        } else if (C1.topy > C2.topy-C2.height/2 && C1.topy < C2.topy+C2.height/2) {
            if (C1.topx < C2.topx) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }

    }

}
