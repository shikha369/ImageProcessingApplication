/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Shikha
 */
public class traceSingular {
    
    public enum SingularPoint{InwardSharpCorner,OutwardSharpCorner,InwardCorner,OutwardCorner,Deadend,Noise}
    int startIndex;
    int endIndex;
    ArrayList<Double> angleList;
    int offset;
    Double meanAngle;
    int lengthOfAngleList;
    SingularPoint typeOfSingularPoint;
    
    
    public Double getAngleMean()
    {
    Double mean = null;
        DescriptiveStatistics stats=new DescriptiveStatistics();
        for(int i=0;i<this.angleList.size();i++)
        {
        stats.addValue(angleList.get(i));
        }
        mean=stats.getMean();
        return mean;
    }
}
