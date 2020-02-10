<%-- 
    Document   : addingImg
    Created on : Jan 1, 2016, 4:30:45 PM
    Author     : Shikha
--%>

<%@page import="java.io.File" %>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload.*"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory.*"%>
<%@page import="org.apache.commons.fileupload.FileItem.*"%>
<%@page import="java.util.List"%>

<%     
    String saveFile="" ;
    
    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        
                // Process form file field (input type="file").
                String fieldName = items.get(0).getFieldName();
                String fileName = FilenameUtils.getName(items.get(0).getName());
                InputStream fileContent = items.get(0).getInputStream();
                //.. 
            System.out.println(fieldName);
            System.out.println(fileName);
        
       File uploads = new File("C:/Users/Shikha/Documents/NetBeansProjects/ImageProcessingApplication/web/UploadedDocs");
 
       File file = new File(uploads,fileName); 
      
        if(!file.exists())
       {
          Files.copy(fileContent,file.toPath()) ;       
       }
       saveFile = saveFile+file.toPath();
        

        
        
        
        
    
    
       response.sendRedirect("DocumentAnalysisHome.jsp");
        
    
    
    
    
%>