<%--
    Document   : addingDoc
    Created on : Jan 2, 2016, 4:39:20 PM
    Author     : Shikha
--%>

<%@page import="imgpro.ComponentInfo"%>
<%@page import="imgpro.makeDirectory"%>
<%@page import="imgpro.TimeStamp"%>
<%@page import="imgpro.ZipBeforeClassification"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="imgpro.analysedata"%>
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
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.*"%>

<%@page import="java.util.List"%>
<%@page import="imgpro.Clean" %>
<%@page import="imgpro.Database" %>
<%@page import="imgpro.ProcessImages" %>
<%
     HttpSession currSess=request.getSession();
     
    /*Code to delete existing classifier and trainingFeatures begin here ... */
    /*Code to delete existing files from uploadedDocs and testimageanalysis begins here*/
    // imgpro.ProcessImages.rootPath=getServletContext().getRealPath("/resources");
    String rootPath = getServletContext().getRealPath("");
    currSess.setAttribute("rootpath", rootPath);
   // imgpro.ProcessImages.ParameterPath=getServletContext().getRealPath("/WEB-INF");
    TimeStamp currTime=new TimeStamp();
    String rand= currTime.setTime();
    currSess.setAttribute("rand", rand);
  //  Clean cleanDir = new Clean();
  //  cleanDir.delete();

    
    
    String root = rootPath;
    makeDirectory mkDir=new makeDirectory();
    mkDir.make(root,rand);
    
    String DirectoryPath = root + "//root"+rand+"//UploadedDocs";
    String ClassifierDirectoryPath = root +"//root"+rand+ "//UploadedClassifier";
    String TrainingDirectoryPath = root +"//root"+rand+ "//UploadedTrainingFeature";
    String ParameterPath = root +"//root"+rand+ "//UploadedParameter";

    /*Code to delete existing files from directory ends here*/

    /*Code to upload image into UploadedDocs directory begins here*/
    String saveFile = "";
    int itemNo = 0;
    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
    for (FileItem item : items) {
        itemNo++;
        
        String fieldName = item.getFieldName();
        String fileName = FilenameUtils.getName(item.getName());
        InputStream fileContent = item.getInputStream();

        System.out.println(fieldName);
        System.out.println(fileName);
        /*if filename==null copy file from default to web else upload file to uploaded folder..rename it and copy to web*/
        File uploads = new File("");
        if (fileName.equals("")) {
            if (itemNo == 2) {
                //copy from Defaults
                File source = new File(root +"\\Default\\Classifier.R");
                File dest = new File(root + "//root"+rand+"\\Classifier.R");
                FileUtils.copyFile(source, dest);
            }
            if (itemNo == 3) {
                //copy from Defaults
                File source = new File(root + "\\Default\\trainedFeatures.csv");
                File dest = new File(root +"//root"+rand+ "\\trainedFeatures.csv");
                FileUtils.copyFile(source, dest);
            }
            if (itemNo == 4) {
                //copy from Defaults
                //use default property file ... do nothing
                System.out.println("using default property file");
            }
        } else {

            if (itemNo == 1) {
                uploads = new File(DirectoryPath);
                File file = new File(uploads, fileName);
                if (!file.exists()) {
                    Files.copy(fileContent, file.toPath());
                }
                System.out.println(file.toPath());
            } else if (itemNo == 2) {
                uploads = new File(ClassifierDirectoryPath);
                File file = new File(uploads, fileName);
                if (!file.exists()) {
                    Files.copy(fileContent, file.toPath());
                }
                saveFile = saveFile + file.toPath();

                /*code to copy and rename files*/
                File source = new File(saveFile);
                File dest = new File(root + "//root"+rand+"\\Classifier.R");
                FileUtils.copyFile(source, dest);
            } else if (itemNo == 3) {
                uploads = new File(TrainingDirectoryPath);
                File file = new File(uploads, fileName);
                if (!file.exists()) {
                    Files.copy(fileContent, file.toPath());
                }
                saveFile = "";
                saveFile = saveFile + file.toPath();

                /*code to copy and rename files*/
                File source = new File(saveFile);
                File dest = new File(root +"//root"+rand+ "\\trainedFeatures.csv");
                FileUtils.copyFile(source, dest);
            } else if (itemNo == 4) {
                uploads = new File(ParameterPath);
                File file = new File(uploads, fileName);
                if (!file.exists()) {
                    Files.copy(fileContent, file.toPath());
                }
                saveFile = "";
                saveFile = saveFile + file.toPath();

                /*code to copy and rename files*/
                File source = new File(saveFile);
                File dest = new File(root +"//root"+rand+ "\\Parameter.txt");
                FileUtils.copyFile(source, dest);
                //update config.properties file
                imgpro.CrunchifyGetPropertyValues prop = new imgpro.CrunchifyGetPropertyValues();
                prop.clearP();
                //retrieve key and value from parameter file
                ArrayList<imgpro.parameters> listParam = new ArrayList<imgpro.parameters>();
                imgpro.param Lparam = new imgpro.param();
                listParam = Lparam.readParam(rootPath,rand);
                String key, value;
                for (int i = 0; i < listParam.size(); i++) {
                    key = listParam.get(i).key;
                    value = listParam.get(i).value;
                    prop.setPropValues(key, value);
                }
                //prop.setPropValues(Key,value);
            }

        }

        System.out.println(saveFile);
        if (itemNo > 3) {
            break;
        }
    }
    /*Code to upload image into UploadedDocs directory ends here*/

     /*Code to process all the images in UploadedDocs begins here*/
    ProcessImages objProcessImages = new ProcessImages();
    objProcessImages.rootPath=root;
    objProcessImages.rand=rand;
    ArrayList<ComponentInfo> CharacterList=objProcessImages.ProcessImageList(DirectoryPath, 1);
    currSess.setAttribute("listOfChar", CharacterList);
    /*Code to process all the images in UploadedDocs ends here*/

    //pass csv to dtree in r..invoke classifier and retrieve result in array
   // imgpro.Classifier cls = new imgpro.Classifier();
  //  ArrayList<String> recognisedCharacetrs = new ArrayList<String>();
    /*call to classifier*/

    /*  cls.runClassifier();

     //based on distance metric on sorted characters ... write a text file and give a pdf link
     //pass control to generate_results.jsp
     response.sendRedirect("generate_results.jsp");
     */
    /*
     provide a link to download test features in zipped folder only
     and redirect back to index.jsp
     */
    imgpro.ZipBeforeClassification ZipFileObj = new imgpro.ZipBeforeClassification();
    ZipFileObj.ProcedureZipAndDownload(root,rand);
    /*code to download starts here*/
    String filePath = ZipFileObj.randomFileName;
    File downloadFile = new File(filePath);
    FileInputStream inStream = new FileInputStream(downloadFile);

    // if you want to use a relative path to context root:
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

    response.sendRedirect("DocumentAnalysisHome.jsp");
    /*code to download ends here*/

%>