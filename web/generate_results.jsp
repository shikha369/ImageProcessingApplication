<%-- 
    Document   : generate_results
    Created on : Jan 2, 2016, 4:01:11 PM
    Author     : Shikha
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.itextpdf.text.Section"%>
<%@page import="com.itextpdf.text.Chapter"%>
<%@page import="com.itextpdf.text.pdf.CMYKColor.*"%>
<%@page import="com.itextpdf.text.FontFactory.*"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.Anchor.*"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String documentContent="";
   
    
    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\result.pdf"));
           document.open();
           //add data dynamically here from arraylist of classified characetrs
           imgpro.DocumentContent genDocContent=new imgpro.DocumentContent();
           documentContent=genDocContent.generateContentInString();
           //temp=request.getParameter("recognisedCharacters");
           //request.getPa
           //documentContent=genDocContent.generateContentInString(imgpro.ProcessImages.meanDistancebetweenCharacters);
           document.add(new Paragraph(documentContent));
           document.close();

        String filePath = "d:\\result.pdf";
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

    response.sendRedirect("index.jsp");
    %>
