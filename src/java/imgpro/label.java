
package imgpro;

import java.awt.image.BufferedImage;

public class label {

     public static int[][] labelImg(BufferedImage binarized) {
     int [][] pix;
     pix=new int[binarized.getHeight()][binarized.getWidth()];
     for (int y = 0;y < binarized.getHeight();y++) {
     for (int x = 0;x < binarized.getWidth();x++) {
          int rgb = binarized.getRGB(x,y);
          if(rgb==0xff000000) 
             pix[y][x]=1; // set 1 to foreground pixels
          else                
              pix[y][x]=0; // set 0 to background pixels
     }
 }   
     return pix;
}   
}