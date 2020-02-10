/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;

/**
 *
 * @author Shikha
 */
public class Segregate {
    public int doSegregate(String str,String element,boolean space)
    {
    int count = 0;
    String[] arstr;
    if(str.equals(""))
        return count;
    if(space)
     arstr=str.split(" ");
    else
     arstr=str.split("");   
    for(int i=0;i<arstr.length;i++)
    {
    if(arstr[i].equals(element))
        count++;
    }
    return count;
    }
}
