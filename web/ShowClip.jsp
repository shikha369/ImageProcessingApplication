<%-- 
    Document   : ShowClip
    Created on : Jun 28, 2015, 1:56:45 PM
    Author     : cool
--%>


<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Clip</title>
    </head>
    <body>
        <% String path=session.getValue("spath").toString();
     System.out.println(path); 
   int LastIndexSlash=path.lastIndexOf('\\');
   int LastIndexPeriod=path.lastIndexOf('.');
   String videoName=path.substring(LastIndexSlash+1, LastIndexPeriod);
  
    
   String srcwebm="./Videos/"+videoName+".webm"+"#<"+"%=startPoint%"+">,<"+"%=endPoint%"+">";
   String srcogg="./Videos/"+videoName+".ogg"+"#<"+"%=startPoint%"+">,<"+"%=endPoint%"+">";
   String srcmp4="./Videos/"+videoName+".mp4"+"#<"+"%=startPoint%"+">,<"+"%=endPoint%"+">";
   //System.out.println(srcwebm);
  
   
   String start=request.getParameter("value");
   String end=request.getParameter("val");
   
   
%>

  <%
    float videorate=0;

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.8:1522:orcl11g","MCS25","MCS25");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from VIDEORATE where VPATH='" + path + "'");
    if (rs.next()) {
        //videorate=rs.getInt(2);
        videorate=rs.getFloat(2);
        System.out.println(videorate);
    } 
  
    //calculate starttime and endtime
   float startPoint=Integer.parseInt(start)*videorate;
   float endPoint=Integer.parseInt(end)*videorate;
   System.out.println(startPoint);
   System.out.println(endPoint);
     
             
  
  %>  
        
  
     
<div align="center">
        <video id="video1" align="centre" width="640" height="480" loop controls>
            <source src= <%=srcwebm%> type=video/webm>
          <source src= <%=srcogg%> type=video/ogg>
          <source src= <%=srcmp4%> type=video/mp4>
</video>
        </div>

  <div align="center">
      
     
       </div>
 <script>
     
  var myVideo = document.getElementById("video1");
   
  myVideo.addEventListener('loadedmetadata', function() {
 this.currentTime = <%=startPoint%>;
}, false);
   myVideo.addEventListener('timeupdate', function() {
if (this.currentTime >= <%=endPoint%>) {
this.pause();
}}, false);
myVideo.play();

  </script>

    </body>
</html>
