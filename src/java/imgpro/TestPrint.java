/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgpro;
import org.rosuda.REngine.Rserve.RConnection;
/**
 *
 * @author Shikha
 */
public class TestPrint {
    public static void main(String args[]){
        String rootPath="C:/Apache Software Foundation/Tomcat 8.0/webapps/ImageProcessingApplication/resources";
       RConnection connection = null;
            try{
            
             /* Create a connection to Rserve instance running
              * on default port 6311
              */
             connection = new RConnection();
             System.out.println("source(\""+rootPath+"//Classifier.R\")");
             connection.eval("source(\""+rootPath+"//Classifier.R\")");
             connection.eval("runC()");
             
           
             connection.close();
         
            System.out.println("Check results");
    }
            catch (Exception e){
            System.out.println(e.toString());
        }
}
}