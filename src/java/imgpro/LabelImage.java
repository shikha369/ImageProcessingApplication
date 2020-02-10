package imgpro;

//import static imgpro.driver.charClass;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.imageio.ImageIO;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class LabelImage {

    int[][] label;
    Stack stack;
    Integer centroidX = 0, centroidY = 0;

    public ArrayList<ComponentInfo> labelImage(int[][] img, BufferedImage image,String root,String rand,int Nimage,int Ncomponents) throws Exception 
    {

       // String root=ProcessImages.rootPath;
        
        ComponentInfo character;
        
        ArrayList<ComponentInfo> cInfo = new ArrayList<ComponentInfo>(); // have to use this one
        ArrayList<Pix> ca = new ArrayList<Pix>();
        Pix coo = new Pix();
        BufferedImage component;
        FileWriter fileWriter = null;
        int[][] cLabel;
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        int nrow = img.length;
        int ncol = img[0].length;
        int nr = image.getHeight();
        int nc = image.getWidth();
        int lab = 1;
        int[] pos;
        ArrayList<Integer> ax = new ArrayList<Integer>();
        ArrayList<Integer> ay = new ArrayList<Integer>();
        DescriptiveStatistics TogetHeightConstraint = new DescriptiveStatistics();
        DescriptiveStatistics TogetWidthConstraint = new DescriptiveStatistics();
        float meanHeight = 0,meanWidth=0;
        float stdHeight= 0,stdWidth=0;

        stack = new Stack();
        label = new int[nrow][ncol];

        for (int r = 0; r < nrow; r++) {
            for (int c = 0; c < ncol; c++) {
                label[r][c] = 0;
            }
        }

        for (int r = 1; r < nrow - 1; r++) {
            for (int c = 1; c < ncol - 1; c++) {

                if (img[r][c] == 0) {
                    continue;
                }
                if (label[r][c] > 0) {
                    continue;
                }
                /* encountered unlabeled foreground pixel at position r, c */
                /* push the position on the stack and assign label */

                stack.push(new int[]{r, c});
                label[r][c] = lab;
                coo.x = c;
                coo.y = r;
                ca.add(coo);
                ax.add(c);
                ay.add(r);

                centroidX += coo.x;
                centroidY += coo.y;

                /* start the float fill */
                while (!stack.isEmpty()) {
                    pos = (int[]) stack.pop();
                    int i = pos[0];
                    int j = pos[1];

                    if (i != 0 & i != nrow - 1 & j != 0 & j != ncol - 1) {
                        if (img[i - 1][j - 1] == 1 && label[i - 1][j - 1] == 0) {
                            stack.push(new int[]{i - 1, j - 1});
                            label[i - 1][j - 1] = lab;
                            coo.x = j - 1;
                            coo.y = i - 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i - 1][j] == 1 && label[i - 1][j] == 0) {
                            stack.push(new int[]{i - 1, j});
                            label[i - 1][j] = lab;
                            coo.x = j - 1;
                            coo.y = i;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i - 1][j + 1] == 1 && label[i - 1][j + 1] == 0) {
                            stack.push(new int[]{i - 1, j + 1});
                            label[i - 1][j + 1] = lab;
                            coo.x = j - 1;
                            coo.y = i + 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i][j - 1] == 1 && label[i][j - 1] == 0) {
                            stack.push(new int[]{i, j - 1});
                            label[i][j - 1] = lab;
                            coo.x = j;
                            coo.y = i - 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i][j + 1] == 1 && label[i][j + 1] == 0) {
                            stack.push(new int[]{i, j + 1});
                            label[i][j + 1] = lab;
                            coo.x = j;
                            coo.y = i + 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i + 1][j - 1] == 1 && label[i + 1][j - 1] == 0) {
                            stack.push(new int[]{i + 1, j - 1});
                            label[i + 1][j - 1] = lab;
                            coo.x = j + 1;
                            coo.y = i - 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i + 1][j] == 1 && label[i + 1][j] == 0) {
                            stack.push(new int[]{i + 1, j});
                            label[i + 1][j] = lab;
                            coo.x = j + 1;
                            coo.y = i;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }
                        if (img[i + 1][j + 1] == 1 && label[i + 1][j + 1] == 0) {
                            stack.push(new int[]{i + 1, j + 1});
                            label[i + 1][j + 1] = lab;
                            coo.x = j + 1;
                            coo.y = i + 1;
                            ca.add(coo);
                            ax.add(coo.x);
                            ay.add(coo.y);
                            centroidX += coo.x;
                            centroidY += coo.y;
                        }

                    }
                } /* end while */

// calculate centroid oof each component
                centroidX = centroidX / ca.size();
                centroidY = centroidY / ca.size();

                character = new ComponentInfo();
                character.name = "Image" + Nimage + "_component" + lab;
                character.pixelArray = ca;
                character.botx = (int) Collections.max(ax);
                if (character.botx < ncol - 1) {
                    character.botx++;
                }
                character.boty = (int) Collections.max(ay);
                if (character.boty < nrow - 1) {
                    character.boty++;
                }
                character.topx = (int) Collections.min(ax);
                if (character.topx > 0) {
                    character.topx--;
                }
                character.topy = (int) Collections.min(ay);
                if (character.topy > 0) {
                    character.topy--;
                }
                character.boundingRectangleArea = (character.botx - character.topx) * (character.boty - character.topy);
                character.centroidX = centroidX;
                character.centroidY = centroidY;
                //character.CharClass = charClass;
                character.height = character.boty - character.topy + 1;
                character.width=character.botx - character.topx + 1;
                character.label = lab;
                character.wordType=0;

// write label csv
                int lbr = character.topy;
                int lbc = character.topx;
                cLabel = new int[character.boty - character.topy + 1][character.botx - character.topx + 1];
                for (int rr = 0; rr < character.boty - character.topy + 1; rr++) {
                    for (int cc = 0; cc < character.botx - character.topx + 1; cc++) {
                        cLabel[rr][cc] = label[lbr][lbc];
                        lbc++;
                    }
                    lbr++;
                    lbc = character.topx;
                }
                //int correctLabel;
                try {
                    fileWriter = new FileWriter(root+"//root"+rand+"\\ComponentLabelCsv\\Image" + Nimage + "_component" + lab + ".csv");
                    //csv is created with row col interchange ... to read as buffered image do appropriate conversion
                    for (int i = 0; i < cLabel.length; i++) {
                        for (int j = 0; j < cLabel[0].length; j++) {
                            if(cLabel[i][j]==lab)
                             fileWriter.append(String.valueOf(cLabel[i][j]));
                            else
                             fileWriter.append("0");
                            
                            fileWriter.append(COMMA_DELIMITER);
                        }
                        fileWriter.append(NEW_LINE_SEPARATOR);
                    }
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cLabel = null;
                }

// get subimage
                try {
                    component = image.getSubimage(character.topx, character.topy, (character.botx - character.topx + 1),
                            (character.boty - character.topy + 1));
                    ImageIO.write(component, "jpg", new File(root+"//root"+rand+"\\output\\Image" + Nimage + "_component" + lab + ".jpg"));
                    //meanHeight = meanHeight + (character.boty - character.topy + 1);
                    TogetHeightConstraint.addValue(character.boty - character.topy + 1);
                    TogetWidthConstraint.addValue(character.botx - character.topx + 1);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    cInfo.add(character);
                    lab++;
                    centroidX = 0;
                    centroidY = 0;
                    ca.clear();
                    ax.clear();
                    ay.clear();
                    stack.clear();
                }
            }
        }

        Ncomponents = lab;
        ax.clear();
        ay.clear();
        label = null;
        // meanHeight = meanHeight / Ncomponents;
        /*the constraint has to b replaced by sumthing like mean+-std condition*/
        meanHeight=(float) TogetHeightConstraint.getMean();
        stdHeight=(float) TogetHeightConstraint.getVariance();
        meanWidth=(float) TogetWidthConstraint.getMean();
        /*on the basis of height variations delete the noisy components*/
        int niterations = cInfo.size();
        for (int i = 0; i < niterations; i++) {
            character = cInfo.get(i);
            if (character.height < 0.6*meanHeight) {
                File deleteOutput = new File(root+"//root"+rand+"\\output\\" + character.name + ".jpg");
                File deleteCompLabCsv = new File(root+"//root"+rand+"\\ComponentLabelCsv\\" + character.name + ".csv");
                //System.out.println(deleteOutput.getName()+ " "+deleteCompLabCsv.getName()+" deleted");
                deleteOutput.delete();
                deleteCompLabCsv.delete();
                cInfo.remove(i);
                i--;
                niterations--; // shrinking size of arraylist
            }
        }
        /*update names of components*/
        Ncomponents = cInfo.size();
        for (int i = 0; i < Ncomponents; i++) {
            character = cInfo.get(i);
            File oldName = new File(root+"//root"+rand+"\\output\\" + character.name + ".jpg");
            File newName = new File(root+"//root"+rand+"\\output\\Image" +Nimage + "_component" + (i + 1) + ".jpg");

            oldName.renameTo(newName);
 //System.out.println(oldName.getName()+ " renamed to "+newName.getName());

            oldName = new File(root+"//root"+rand+"\\ComponentLabelCsv\\" + character.name + ".csv");
            newName = new File(root+"//root"+rand+"\\ComponentLabelCsv\\Image" + Nimage + "_component" + (i + 1) + ".csv");
            oldName.renameTo(newName);
            //System.out.println(oldName.getName()+ " renamed to "+newName.getName());
            character.name = "Image" + Nimage + "_component" + (i + 1);
            cInfo.set(i, character);
        }
        
        /* Now Seperate the devnagri characters basd on their width
        *if doc is multi lingual then width(Dev) > 2*meanwidth(component) -- extraction condition
        *if doc is devnagri only then width(component) > 2* height(component) -- extraction condition
        *else
        *leave as it is
        */
        Devnagari dev=new Devnagari();
        dev.Ncomponents=Ncomponents;
        for (int i = 0; i < Ncomponents; i++) {
            character = cInfo.get(i);
            if (character.width>=3*meanWidth || character.width >= 2*character.height) {
                cInfo=dev.Extract(cInfo, root, rand, i);
                File deleteOutput = new File(root+"//root"+rand+"\\output\\" + character.name + ".jpg");
                File deleteCompLabCsv = new File(root+"//root"+rand+"\\ComponentLabelCsv\\" + character.name + ".csv");
                //System.out.println(deleteOutput.getName()+ " "+deleteCompLabCsv.getName()+" deleted");
                deleteOutput.delete();
                deleteCompLabCsv.delete();
                cInfo.remove(i);
                i--;
                niterations--; // shrinking size of arraylist
            }
        }
        /*update names of components*/
        Ncomponents = cInfo.size();
        for (int i = 0; i < Ncomponents; i++) {
            character = cInfo.get(i);
            File oldName = new File(root+"//root"+rand+"\\output\\" + character.name + ".jpg");
            File newName = new File(root+"//root"+rand+"\\output\\Image" +Nimage + "_component" + (i + 1) + ".jpg");

            oldName.renameTo(newName);
 //System.out.println(oldName.getName()+ " renamed to "+newName.getName());

            oldName = new File(root+"//root"+rand+"\\ComponentLabelCsv\\" + character.name + ".csv");
            newName = new File(root+"//root"+rand+"\\ComponentLabelCsv\\Image" + Nimage + "_component" + (i + 1) + ".csv");
            oldName.renameTo(newName);
            //System.out.println(oldName.getName()+ " renamed to "+newName.getName());
            character.name = "Image" + Nimage + "_component" + (i + 1);
            cInfo.set(i, character);
        }

        return cInfo;
        //con.close();
    }
}
