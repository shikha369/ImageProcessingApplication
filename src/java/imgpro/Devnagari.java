/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 after segmentation , redo cc analysis to retain larger component
 */
package imgpro;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.imageio.ImageIO;

public class Devnagari {

    int currentDensity;
    private final String COMMA_DELIMITER = ",";
    public int Ncomponents;
    ArrayList<Integer> indexHeader = new ArrayList<Integer>();

    public ArrayList<ComponentInfo> Extract(ArrayList<ComponentInfo> cInfo, String root, String rand, int n) throws IOException {

        //Ncomponents=cInfo.size();
        ArrayList<ComponentInfo> temp = new ArrayList<ComponentInfo>();
        ComponentInfo character = cInfo.get(n);
        String filePath = root + "//root" + rand + "\\ComponentLabelCsv\\" + character.name + ".csv";
        BufferedImage image = ImageIO.read(new File(root + "//root" + rand + "\\output\\" + character.name + ".jpg"));
        /* get the word as 0/1  matrix*/
        int[][] cpix = constructPixArray(filePath);
        /*    for (int i = 0; i < cpix.length; i++) {
         for (int j = 0; j < cpix[0].length; j++) {
         System.out.print(cpix[i][j]);
         System.out.print(" ");

         }
         System.out.println();
         }
         */
        /* make horizontal projection to trace headerline*/

        /*Projection thresholds should be relative*/
        Integer[] HProz = new Integer[cpix.length];
        int maxHp = 0;
        for (int i = 0; i < cpix.length; i++) {
            HProz[i] = 0;
            for (int j = 0; j < cpix[0].length; j++) {
                if (cpix[i][j] > 0) {
                    HProz[i]++;
                }
            }
            if (HProz[i] > maxHp) {
                maxHp = HProz[i];
            }
        }
        /*   for (int i = 0; i < cpix.length; i++) {
         System.out.println(HProz[i]);
         }
         */
        /*trace index of headerline and remove headerline*/
        ArrayList<Integer> indexHeader = new ArrayList<Integer>();
        for (int i = 0; i < cpix.length; i++) {
            if (HProz[i] > maxHp - 5) {
                for (int j = 0; j < cpix[0].length; j++) {
                    cpix[i][j] = 0;
                    indexHeader.add(j);
                }
            }

        }
        /*    for (int i = 0; i < cpix.length; i++) {
         for (int j = 0; j < cpix[0].length; j++) {
         System.out.print(cpix[i][j]);
         System.out.print(" ");

         }
         System.out.println();
         }
         */
        /*get vertical projection */
        Integer[] VProz = new Integer[cpix[0].length];
        ArrayList<Integer> breakPoints = new ArrayList<Integer>();
        int minVp = Integer.MAX_VALUE;
        for (int i = 0; i < cpix[0].length; i++) {
            VProz[i] = 0;
            for (int j = 0; j < cpix.length; j++) {
                if (cpix[j][i] > 0) {
                    VProz[i]++;
                }
            }
            if (VProz[i] < minVp) {
                minVp = VProz[i];
            }
        }
        breakPoints.add(0);
        boolean added = true;
        for (int i = 1; i < cpix[0].length; i++) {
            //System.out.println(VProz[i]);
            if (VProz[i] < minVp + 3 /* && VProz[i - 1] >= minVp + 10*/ && !added) { //set some heuristic to 8
                if (i + 3 < cpix[0].length) {
                    breakPoints.add(i + 3); /*leave space */

                } else {
                    breakPoints.add(i); /*leave space */

                }

                added = true; //step by stroke width of character
                i = i + 3;
            } else if (VProz[i] >= minVp + 3 & added) {
                added = false;
            }
        }
        //   breakPoints.add(cpix[0].length-1);

        // System.out.println(breakPoints);
        FileWriter fileWriter = null;
        int height = cpix.length;
        int lbr, lbc;
        ComponentInfo newcharacter;
        int namecounter=1;
        for (int i = 0; i < breakPoints.size() - 1; i++) {
            newcharacter = new ComponentInfo();
            newcharacter.botx = character.topx + breakPoints.get(i + 1);
            newcharacter.boty = character.boty;
            newcharacter.topx = character.topx + breakPoints.get(i);
            newcharacter.topy = character.topy;
            // newcharacter.startX=character.startX+breakPoints.get(i);
            // newcharacter.startY=character.startY;
            newcharacter.width = breakPoints.get(i + 1) - breakPoints.get(i);
            newcharacter.height = height;
            newcharacter.centroidX = character.centroidX; //kept default .... as it has no use further ;)
            newcharacter.centroidY = character.centroidY;
            newcharacter.boundingRectangleArea = newcharacter.width * newcharacter.height;
            newcharacter.name = "Image1_component" + (Ncomponents + namecounter);
            newcharacter.path = character.path;
            newcharacter.LabelFromFileName = character.LabelFromFileName;
            newcharacter.wordType = 1;

            try {
                
                lbr = 0;
                lbc = breakPoints.get(i);
                int[][] cLabel = new int[newcharacter.height][newcharacter.width];
                for (int rr = 0; rr < newcharacter.height; rr++) {
                    for (int cc = 0; cc < newcharacter.width; cc++) {
                        cLabel[rr][cc] = cpix[lbr][lbc];
                        lbc++;
                    }
                    lbr++;
                    lbc = breakPoints.get(i);
                }
                /* code to retain only larger component*/
                cLabel = refineLabelMatrix(cLabel);
                //check if noise
                if(currentDensity<10)
                    continue;
                namecounter++;
                
                /*write image*/
                BufferedImage component = image.getSubimage(breakPoints.get(i), 0, (breakPoints.get(i + 1) - breakPoints.get(i)),
                        height);
                ImageIO.write(component, "jpg", new File(root + "//root" + rand + "\\output\\" + newcharacter.name + ".jpg"));

                /*newcharacter=updateModifierInfo(component,newcharacter) again get projection profiles */
                //clear indexHeader list(which is class variable nd will b live till next invocation)
                newcharacter = updateModifierInfo(component, newcharacter);
                indexHeader.clear();
                
                fileWriter = new FileWriter(root + "//root" + rand + "\\ComponentLabelCsv\\" + newcharacter.name + ".csv");
                //csv is created with row col interchange ... to read as buffered image do appropriate conversion
                for (int k = 0; k < cLabel.length; k++) {
                    for (int j = 0; j < cLabel[0].length; j++) {
                        fileWriter.append(String.valueOf(cLabel[k][j]));
                        fileWriter.append(COMMA_DELIMITER);
                    }
                    fileWriter.append(System.lineSeparator());
                }
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            /*add newCharacter*/
            temp.add(newcharacter);

        }
        /*update with lower modifier*/
        int maxHeight = 0;
        for (int p = 0; p < temp.size(); p++) {
            if (maxHeight < temp.get(p).longestVertical) {
                maxHeight = temp.get(p).longestVertical;
            }
        }
        for (int p = 0; p < temp.size(); p++) {
            if (temp.get(p).longestVertical > .85 * maxHeight && !temp.get(p).upperModifier) {
                temp.get(p).lowerModifier = true;

            } else {
                temp.get(p).lowerModifier = false;
            }

            if (temp.get(p).longestVertical > .85 * maxHeight && temp.get(p).upperModifier) {
                temp.get(p).upperModifier = true;
            } else {
                temp.get(p).upperModifier = false;
            }
        }

        for (int p = 0; p < temp.size(); p++) {
            if (temp.get(p).upperModifier) {
                System.out.println(p + "has upperModifier");
            }
            if (temp.get(p).lowerModifier) {
                System.out.println(p + "has LowerModifier");
            }
            cInfo.add(temp.get(p));
        }

        Ncomponents = Ncomponents + breakPoints.size() - 1;
        return cInfo;

    }

    public int[][] constructPixArray(String filePath) throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));

        String line;
        ArrayList<ArrayList> outerArr = new ArrayList<ArrayList>();
        ArrayList a;
        ArrayList<Integer> aa = new ArrayList<Integer>();
        int i = 0, j = 0, nrow = 0, ncol = 0;

        while ((line = fileReader.readLine()) != null) {
            a = new ArrayList();
            String[] tokens = line.split(COMMA_DELIMITER);
            if (tokens.length > 0) {

                int l = tokens.length;
                ncol = tokens.length;
                while (l > 0) {
                    a.add(j, Integer.parseInt(tokens[j++]));
                    l--;
                }
            }
            outerArr.add(a);
            j = 0;
        }
        nrow = outerArr.size();
        int cPix[][] = new int[nrow][ncol];

        for (i = 0; i < cPix.length; i++) {
            aa = outerArr.get(i);
            for (j = 0; j < cPix[0].length; j++) {
                cPix[i][j] = (int) aa.get(j);
            }
        }
        outerArr.clear();
        fileReader.close();
        return cPix;
    }

    public ComponentInfo updateModifierInfo(BufferedImage component, ComponentInfo newcharacter) {
        int[][] pix = new int[component.getHeight()][component.getWidth()];
        int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
        for (int i = 0; i < component.getHeight(); i++) {
            for (int j = 0; j < component.getWidth(); j++) {
                int rgb = component.getRGB(j, i);
                if (rgb == 0xff000000) {
                    pix[i][j] = 1; // set 1 to foreground pixels
                    if (i < minH) {
                        minH = i;
                    }
                    if (i > maxH) {
                        maxH = i;
                    }
                } else {
                    pix[i][j] = 0;
                }
            }
        }
        /*get headerline by hz proj profile*/
        Integer[] HProz = new Integer[pix.length];
        int maxHp = 0;
        for (int i = 0; i < pix.length; i++) {
            HProz[i] = 0;
            for (int j = 0; j < pix[0].length; j++) {
                if (pix[i][j] > 0) {
                    HProz[i]++;
                }
            }
            if (HProz[i] > maxHp) {
                maxHp = HProz[i];
            }
        }
        ArrayList<Integer> indexHeader = new ArrayList<Integer>();
        for (int i = 0; i < pix.length; i++) {
            if (HProz[i] > maxHp - 5) {
                indexHeader.add(i);
            }
        }
        /*set modifier info*/
        newcharacter.wordType = 1;
        newcharacter.longestVertical = maxH - minH;
        if ((indexHeader.get(0) - minH) <= newcharacter.longestVertical / 4) {
            newcharacter.upperModifier = false;
        } else {
            newcharacter.upperModifier = true;
        }

        return newcharacter;
    }

    public int[][] refineLabelMatrix(int[][] cLabel) {
        ArrayList<AfterSegmentationCCanalysis> Listascc = new ArrayList<AfterSegmentationCCanalysis>();
        int nrow = cLabel.length;
        int ncol = cLabel[0].length;
        Stack stack = new Stack();
        int[][] label = new int[nrow][ncol];
        int[] pos;
        for (int r = 0; r < nrow; r++) {
            for (int c = 0; c < ncol; c++) {
                label[r][c] = 0;
            }
        }
        int lab = 1;
        for (int r = 1; r < nrow - 1; r++) {
            for (int c = 1; c < ncol - 1; c++) {

                if (cLabel[r][c] == 0) {
                    continue;
                }
                if (label[r][c] > 0) {
                    continue;
                }
                /* encountered unlabeled foreground pixel at position r, c */
                /* push the position on the stack and assign label */

                stack.push(new int[]{r, c});
                label[r][c] = lab;
                ArrayList<Pix> indexList = new ArrayList<Pix>();
                AfterSegmentationCCanalysis ascc = new AfterSegmentationCCanalysis();
                
                /*Pix.x=c and Pix.y=r*/
                Pix p = new Pix();
                p.x = c;
                p.y = r;
                indexList.add(p);
                int count=1;
                /* start the float fill */
                while (!stack.isEmpty()) {
                    pos = (int[]) stack.pop();
                    int i = pos[0];
                    int j = pos[1];

                    if (i != 0 & i != nrow - 1 & j != 0 & j != ncol - 1) {
                        if (cLabel[i - 1][j - 1] > 0 && label[i - 1][j - 1] == 0) {
                            stack.push(new int[]{i - 1, j - 1});
                            label[i - 1][j - 1] = lab;
                            p = new Pix();
                            p.x = j - 1;
                            p.y = i - 1;
                            indexList.add(p);
                            count++;
                        }
                        if (cLabel[i - 1][j] > 0 && label[i - 1][j] == 0) {
                            stack.push(new int[]{i - 1, j});
                            label[i - 1][j] = lab;
                            p = new Pix();
                            p.x = j;
                            p.y = i - 1;
                            indexList.add(p);
                             count++;
                        }
                        if (cLabel[i - 1][j + 1] > 0 && label[i - 1][j + 1] == 0) {
                            stack.push(new int[]{i - 1, j + 1});
                            label[i - 1][j + 1] = lab;
                            p = new Pix();
                            p.x = j + 1;
                            p.y = i - 1;
                            indexList.add(p);
                             count++;
                        }
                        if (cLabel[i][j - 1] > 0 && label[i][j - 1] == 0) {
                            stack.push(new int[]{i, j - 1});
                            label[i][j - 1] = lab;
                            p = new Pix();
                            p.x = j - 1;
                            p.y = i;
                            indexList.add(p);
                             count++;
                        }
                        if (cLabel[i][j + 1] > 0 && label[i][j + 1] == 0) {
                            stack.push(new int[]{i, j + 1});
                            label[i][j + 1] = lab;
                            p = new Pix();
                            p.x = j + 1;
                            p.y = i;
                            indexList.add(p);
                             count++; 
                        }
                        if (cLabel[i + 1][j - 1] > 0 && label[i + 1][j - 1] == 0) {
                            stack.push(new int[]{i + 1, j - 1});
                            label[i + 1][j - 1] = lab;
                            p = new Pix();
                            p.x = j - 1;
                            p.y = i + 1;
                            indexList.add(p);
                             count++;
                        }
                        if (cLabel[i + 1][j] > 0 && label[i + 1][j] == 0) {
                            stack.push(new int[]{i + 1, j});
                            label[i + 1][j] = lab;
                            p = new Pix();
                            p.x = j;
                            p.y = i + 1;
                            indexList.add(p);
                             count++;
                        }
                        if (cLabel[i + 1][j + 1] > 0 && label[i + 1][j + 1] == 0) {
                            stack.push(new int[]{i + 1, j + 1});
                            label[i + 1][j + 1] = lab;
                            p = new Pix();
                            p.x = j + 1;
                            p.y = i + 1;
                            indexList.add(p);
                             count++;
                        }

                    }
                } /* end while */

                ascc.label = lab;
                ascc.number = count;
                ascc.index=indexList;
                Listascc.add(ascc);
                    lab++;
                    stack.clear();
                }
            }
        /*get ascc with max count*/
        int max=0,maxIndex = 0;
        for(int i=0;i<Listascc.size();i++)
        {
            if(Listascc.get(i).number>max)
            {
            max=Listascc.get(i).number;
            maxIndex=i;
            }
        
        }
        
        cLabel=new int[nrow][ncol];
        int r,c;
        for(int i=0;i<Listascc.get(maxIndex).index.size();i++)
        {
        r=Listascc.get(maxIndex).index.get(i).y;
        c=Listascc.get(maxIndex).index.get(i).x;
        cLabel[r][c]=1;
        }
        
        currentDensity=max;
        return cLabel;
    }

}