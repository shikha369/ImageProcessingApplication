<%-- 
    Document   : combiningCsv
    Created on : May 5, 2016, 12:16:31 AM
    Author     : Shikha
--%>

<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="imgpro.combineCsvs"%>
<%@page import="java.io.File"%>
<%@page import="imgpro.TimeStamp"%>
<%
//*******************adding image here ***********
     
     String path,csvPath;
    // path=request.getParameter("txtPath");
      File uploads = new File("");
     /*create user specific directory*/
     TimeStamp currTime=new TimeStamp();
     String rand= currTime.setTime();
     String rootPath = getServletContext().getRealPath("");
     new File(rootPath + "//root" + rand + "//Input").mkdirs();
     new File(rootPath + "//root" + rand + "//Output").mkdirs();
     String DirectoryPath = rootPath + "//root"+rand+"//Input";
     
     /*upload all csvs int input folder*/
     
    // File[] fileList =new File(path).listFiles();
     /*
     for(File csv:fileList)
     {
     csvPath=csv.getAbsolutePath();
     File source = new File(csvPath);
                File dest = new File(rootPath + "//root"+rand+"//Input//"+csv.getName());
                FileUtils.copyFile(source, dest);

     }
     */
     
    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
    for (FileItem item : items) {
                if (!item.isFormField())
                    {
        String fieldName = item.getFieldName();
        String fileName = FilenameUtils.getName(item.getName());
        InputStream fileContent = item.getInputStream();

       System.out.println(fieldName);
       System.out.println(fileName);
        
                uploads = new File(DirectoryPath);
                File file = new File(uploads, fileName);
                if (!file.exists()) {
                    Files.copy(fileContent, file.toPath());
                }
                System.out.println(file.toPath());
    }
                }
     combineCsvs cmbc=new combineCsvs();
     cmbc.CombineAndDownload(rootPath + "//root" + rand + "//Input",rootPath + "//root" + rand + "//Output");
      
     /*Download*/
     String outputPath=rootPath + "//root" + rand + "//Output";
     String filePath=outputPath+"\\TrainingFeatures.csv";
        File downloadFile = new File(filePath);
    FileInputStream inStream = new FileInputStream(downloadFile);

    String relativePath = getServletContext().getRealPath("");
    System.out.println("relativePath = " + relativePath);

    // obtains ServletContext
    ServletContext context = getServletContext();

    // gets MIME type of the file
    String mimeType = context.getMimeType(filePath);
    if (mimeType == null) {
        // set to binary type if MIME mapping not found
        mimeType = "application/octet-stream";
    }
    System.out.println("MIME type: " + mimeType);

    // modifies response
    response.setContentType(mimeType);
    response.setContentLength((int) downloadFile.length());

    // forces download
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
    response.setHeader(headerKey, headerValue);

    // obtains response's output stream
    OutputStream outStream = response.getOutputStream();

    byte[] buffer = new byte[4096];
    int bytesRead = -1;

    while ((bytesRead = inStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
    }

    inStream.close();
    outStream.close();


%>