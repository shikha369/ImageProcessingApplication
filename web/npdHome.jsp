<%-- 
    Document   : npdHome
    Created on : Jan 1, 2016, 3:09:23 PM
    Author     : Shikha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number Plate Detection</title>
        <style>
body {background-color:#E7EDF8}
h1   {color:black}
p    {color:green}
#header {
    font-family:Verdana, Arial, Helvetica, sans-serif;
      font-size:20px;
      font-weight:bold;

    background-color:#213E74;
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
 
}
#section {
    width:350px;
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
<h1>Welcome To Vehicle Number Plate Detection System</h1>
</div>


<div id="section">
    

    <table style="width:1300px;">
        <tr><th></th><th></th><th></th></tr>
        <tr>
            <td><form name="addclip" method="post" action="addingclip.jsp" enctype="multipart/form-data" onsubmit="return validateForm(this)">     
         <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p> BROWSE VIDEO to upload &nbsp; : <p>&nbsp;</p><input type="file" name="browseFile" style="border-style:  outset;background-color: yellowgreen" /> </p>
    <p>&nbsp;</p>
     <input type="submit" value="ADD VIDEO CLIP and START VEHICLE NUMBER EXTRACTION" name="btnAddClip" style="border-style:  outset;background-color: darksalmon" />
                </form> </td><td> </td>
<td><form name="addimg" method="post" action="addingimage.jsp" enctype="multipart/form-data" onsubmit="return validateForm(this)">     
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  
    <p> BROWSE IMAGE to upload &nbsp; : <p>&nbsp;</p> <input type="file" name="browseFile" style="border-style:  outset;background-color: yellowgreen"/> </p>
    <p>&nbsp;</p>
     <input type="submit" value="ADD IMAGE and START VEHICLE NUMBER EXTRACTION" name="btnAddImage" style="border-style: outset;background-color:  darksalmon"/>
</form></td>
        </tr>
        
        <tr>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <td><a href="ShowClip.jsp">watch clip</a></td></td><td><a href="generate_results.jsp">download results in pdf</a></td><td></td></tr>
    <tr>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <td></td><td><a href="index.jsp">Home</a></td><td></td></tr>
    </table>
 
    <p>&nbsp;</p>
    
    
</div>
     </body>
</html>

    