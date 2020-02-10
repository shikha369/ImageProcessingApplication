<%-- 
    Document   : DocumentAnalysisHome
    Created on : Jan 1, 2016, 3:09:23 PM
    Author     : Shikha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Document Analysis</title>
        <style>
body {background-color:#DAE9BC;
 width: 100%;
 height: auto;
}
h1   {color:black}
p    {color:green}
#header {
    font-family:Verdana, Arial, Helvetica, sans-serif;
      font-size:20px;
      font-weight:bold;

    background-color:#BCCE98;
    color:white;
    text-align:center;
    padding:5px;
    
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:500px;
    width:200px;
    float:left;
    padding:5px;
    height:800px;
 
}
#section {
    background-color:#DAE9BC;
    float:left;
    padding:10px;
    text-align:center;
  }
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px; 
}
#right {
			width: 130px;
			padding: 0 10px;
			margin-right: -100%;
		}
                
		
</style>
<%!    
    private void process() {
     
    }
%>

    </head>
    <body onload="document.addclip.txtdate.focus( )">
        
        <script>

//Validation for empty Date field
            function isNotEmpty(elem){
            var str = elem.value;
            if(str == null || str.length == 0) {
            alert("Please fill in the required field.");
            return false;
            } else {
            return true;
            
            }
            }
 //Validation for empty location selection            
            function isChosen(select) {
            if (select.selectedIndex == 0) {
             alert("Please make a choice from the list.");
             return false;
             } else {
                 return true;
             }
             }
  
 //Validation of form
            function validateForm(form) {
                
                  
                         if(validateUpload(form.browseFile))
                             {
                              return true;
                             } 
                                 
                               
                            return false;
              }
    
  //Validation for file upload  
              function validateUpload(file)
              {
                 var str = file.value;
            if(str == null || str.length == 0) {
                     alert("Please upload the file");
             return false;
             } else {
                 return true;
             }
                  
              }
            
            
        </script>
        
<div id="header">
<h1>Welcome To Document Analysis System</h1>
</div>


<div id="section">
    
    <form name="addimg" method="post" action="addingDoc.jsp" enctype="multipart/form-data" onsubmit="return validateForm(this)">     
    <a href="readme.jsp">Read Me</a> 
        <table style="width:1300px;height:auto">
        <tr height="10">     
<td>
    <p> BROWSE DOCUMENT to upload &nbsp; :  <input type="file" name="browseFile" style="border-style:  outset;background-color: yellowgreen"/> </p>
    </td>
<td> <p> Upload CLASSIFIER MODEL &nbsp; :  <input type="file" name="browseClassifier" style="border-style:  outset;background-color: yellowgreen"/> </p>
    </td>
<td><p> Upload TrainingFeaturesCsv File &nbsp; :  <input type="file" name="browseTraining" style="border-style:  outset;background-color: yellowgreen"/> </p>
    </td>
        </tr>
   <tr>
       <td></td><td><p> Upload parameter File &nbsp; : <input type="file" name="browsePfile" style="border-style:  outset;background-color: yellowgreen"/> </p>
    </td><td></td></tr>          
        
   <tr>
       <td></td><td><input type="submit" value="ADD SCANNED DOC IMAGE and START PROCESSING " name="btnAddImage" style="border-style: outset;background-color:  darksalmon"/></td><td></td></tr>     
        
 <!--   <tr>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <td></td><td><a href="download_features.jsp">download image features in CSV</a></td><td></td></tr>-->
   <tr>
            <p>&nbsp;</p>
            <td></td><td><a href="classification.jsp">Proceed To Classification </a></td><td></td></tr> 
    <tr>
            <p>&nbsp;</p>
            <td></td><td><a href="results_Analysis.jsp">Analyze Results </a></td><td></td></tr>
    <tr>
            <p>&nbsp;</p>
            <td></td><td><a href="downloadIndex.jsp">Download Index</a></td><td></td></tr>
<tr>
            <p>&nbsp;</p>
            <td></td><td><a href="downloadDoc.jsp">Download Recognized Document</a></td><td></td></tr>
 <tr>
          <p>&nbsp;</p>
            
            <td></td><td><a href="index.jsp">Home</a></td><td></td></tr>
    </table>
 </form>

    <p>&nbsp;</p>
    
    
</div>
        
        <div id="right" class="column">
			<h3><a href="utilities.jsp">Utilities</a></h3>
			
		</div>
        
     </body>
</html>

    