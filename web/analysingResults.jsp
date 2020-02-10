<%-- 
    Document   : analysingResults
    Created on : Jan 17, 2016, 8:55:02 PM
    Author     : Shikha
--%>

<%@page import="imgpro.ComponentInfo"%>
<%@page import="imgpro.updationsBeforeClassification"%>
<%@page import="imgpro.ProcessImages"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="imgpro.MLChar"%>
<%@page import="imgpro.UpdationsToTrainingData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="imgpro.Updates"%>
<%
    /*
     things to do here : 
     1. Invoke updateTrainingSet(name of component,CorrectTo) one by one (depending -- if checked get txtval)
     or pass arraylist of comp-rec
     2. After all updates done ... Update ImageAnalysis Table  
    
     */
    String rand=(String)session.getAttribute("rand");
    String root=(String)session.getAttribute("rootpath");
    ArrayList<ComponentInfo> CharacterList = (ArrayList<ComponentInfo>) session.getAttribute("listOfChar");
    String cno;
    String correctTo;
    String select[] = request.getParameterValues("char");
    System.out.print(select);
    ArrayList<Updates> upData = new ArrayList<Updates>();
    Updates currUpdate;
    //MLChar currMLChar; 
    //ArrayList<MLChar> ListML=new ArrayList<MLChar>();
    int index = select.length;
    if (select.length != 0) {
        for (int i = 0; i < index; i++) {
            cno = select[i];
            imgpro.ComponentRecognisedAs o = new imgpro.ComponentRecognisedAs();
            imgpro.ComponentRecognisedAs oo = new imgpro.ComponentRecognisedAs();
            //o.cname=cno;
            correctTo = request.getParameter(cno + "_txt");
            currUpdate = new Updates();
            // currMLChar=new MLChar();
            currUpdate.ComponentName = cno;//currMLChar.ComponentName=cno;
            currUpdate.CorrectTo = correctTo;//currMLChar.CorrectTo=correctTo;
            //oo=imgpro.ProcessImages.RecognisedCharacetrList.get(imgpro.ProcessImages.RecognisedCharacetrList.indexOf(o.cname==cno));
            //currMLChar.RecognisedAs=oo.recAs;
            upData.add(currUpdate);//ListML.add(currMLChar);
        }
    }
    /*
    UpdationsToTrainingData uttd = new UpdationsToTrainingData();
    uttd.updateTrainingSet(upData);
    /*copy updated trainingCsv to Default*/
  /*  File dest = new File(ProcessImages.rootPath+"//root"+rand+"\\Default\\trainedFeatures.csv");
    File source = new File(ProcessImages.rootPath+"//root"+rand+"\\trainedFeatures.csv");
    FileUtils.copyFile(source, dest);
    
    imgpro.MLCharacterFeatures MLCF = new imgpro.MLCharacterFeatures();
    MLCF.modifyTestDB(upData);
    /*
     create a zipped folder and give user link to download the zipped folder while redirecting to updateSuccessFul.jsp
     Code for Download begins here
     */
    
 /*   imgpro.DemoZip ZipFileObj = new imgpro.DemoZip();
    ZipFileObj.ProcedureZipAndDownload();
*/
    
    /*code to update charactrList and write updates to new csv*/
    updationsBeforeClassification ubcl=new updationsBeforeClassification();
    ubcl.updateCharList(CharacterList, upData, root, rand);
    String filePath = root + "//root" + rand + "\\downloadTestFeaturesAfterCorrection.csv";
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

    response.sendRedirect("updateSuccessFul.jsp");
%>