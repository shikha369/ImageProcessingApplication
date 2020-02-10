<%-- 
    Document   : downloadDoc
    Created on : May 26, 2016, 3:03:54 PM
    Author     : Shikha
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String root=(String) session.getAttribute("rootpath");
    String rand=(String) session.getAttribute("rand");
String filePath = root + "//root" + rand + "\\result.pdf";
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