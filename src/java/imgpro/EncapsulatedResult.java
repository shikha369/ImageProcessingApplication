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
public class EncapsulatedResult {
    public ArrayList<ComponentRecognisedAs> RecognisedCharacetrList = new ArrayList<ComponentRecognisedAs>();
    public ArrayList<ComponentInfo> CharacterList = new ArrayList<ComponentInfo>();
    public ArrayList<Double> DistanceBetweenCharacetrs = new ArrayList<Double>();
    public double meanDistancebetweenCharacters;
    
}
