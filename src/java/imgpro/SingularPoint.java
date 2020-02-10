
package imgpro;

/**
 *
 * @author Shikha
 */
public class SingularPoint {
    int index;  /*index at which its been found on perimeter... to track length*/  /*in case of deadend i = (start+endpoint)/2 */
    int nextIndex;/*to be used while calculating curveType*/
    int type;   /* 0-deadend , 1-concave corner , 2- convex corner */
    Pix start;  /*start coordinate */
    Pix end;    /*end coordinate */         
    /*start equals end in case of corners .... but in case of deadends u need to calculate mid point of start and end*/    
}
