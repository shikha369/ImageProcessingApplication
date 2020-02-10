
package imgpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class param {
  public ArrayList<imgpro.parameters> readParam(String root,String rand) throws FileNotFoundException, IOException
  {
  ArrayList<imgpro.parameters> listParam=new ArrayList<imgpro.parameters>();
  BufferedReader pr=new BufferedReader(new FileReader(root+"//root"+rand+"\\Parameter.txt"));
  String line;
  parameters par;
  while ((line = pr.readLine()) != null) 
           {
               String[] tokens = line.split(" ");
               par=new parameters();
               par.key=tokens[0];
               par.value=tokens[1];
               listParam.add(par);
           }
  return listParam;
  }
}
