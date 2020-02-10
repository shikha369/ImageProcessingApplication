<%@page import="java.io.File" %>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FilenameUtils.*"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@ page import="matlabcontrol.*" %>
<%
//*******************adding video clip here ***********test with img_0478

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","system","sys");
      
    String saveFile="" ;
    String vdate=""  ;
    String vloc=""  ;
    
    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                // ..
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                if(fieldName.equals("txtdate"))
                    vdate=fieldValue;
                if(fieldName.equals("listLocation"))
                    vloc=fieldValue;
                System.out.println(fieldName);
                System.out.println(fieldValue);
                // ... 
            //  newFile=newFile+"_"+fieldValue ;
            } else {
                // Process form file field (input type="file").
                String fieldName = item.getFieldName();
                String fileName = FilenameUtils.getName(item.getName());
                InputStream fileContent = item.getInputStream();
                //.. 
            System.out.println(fieldName);
            System.out.println(fileName);
        
       File uploads = new File("C:/Users/Shikha/Documents/NetBeansProjects/NPD/NPD1_New/Videos");//chnge path
       File webuploads=new File("C:/Users/Shikha/Documents/NetBeansProjects/NPD/web/Videos");//chnge path
        File file = new File(uploads,fileName); 
       File webfile=new File(webuploads,fileName);
        if(!file.exists())
       {
     Files.copy(fileContent, webfile.toPath()) ;       
             }
      saveFile = saveFile+file.toPath();
        
  //************ inserting date,loc and savfile in table test.dateloc 
  
 Statement stmt = con.createStatement();
 String sql = "INSERT INTO dateloc" + " VALUES ('"+vdate+"', '"+vloc+"','"+saveFile+"')";
 //  inserting vdate,vlocation,vpath into dateloc
  stmt.executeUpdate(sql);
  
  //using matlabcontrol for connecting to matlab
  MatlabProxyFactory factory = new MatlabProxyFactory();
  MatlabProxy proxy = factory.getProxy();  
  
  //invoke matlab function here 
  proxy.setVariable("a", saveFile);
  proxy.eval("InvokeDetection(a)" );
  proxy.disconnect();             
  //above code inserts vpath,car_no,frame_no into framecar
     
 
    
    //invoke generateresult() which inserts videoname,carno,fromframe,toframe in table generate
   CallableStatement cstmt = con.prepareCall ("{call generate_result(?)}");
   cstmt.setString(1, saveFile);
   cstmt.executeUpdate();
        
        
            }
        }
        
        
        
    
    
        response.sendRedirect("addclip.jsp");
        
    
    
    
    
%>