<%-- 
    Document   : results_Analysis
    Created on : Jan 17, 2016, 8:54:25 PM
    Author     : Shikha
--%>

<%@page import="imgpro.ComponentInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="imgpro.TimeStamp"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="imgpro.ProcessImages"%>
<%@page import="imgpro.ProcessImages.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <style>
            body {background-color:lightgray}
            h1   {color:blue}
            p    {color:green}
            #header {
                background-color:black;
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
            }
            #footer {
                background-color:black;
                color:white;
                clear:both;
                text-align:center;
                padding:5px; 
            }


            thead {  position: relative;display: block}
            tbody {
                display: block;
                height: 500px;       /* Just for the demo          */
                /* overflow-y: auto;     Trigger vertical scroll    */
                /* overflow-x: hidden;   Hide the horizontal scroll */
                overflow: auto;
            }
            td:nth-child(1), th:nth-child(1) { width: 100px; }
            td:nth-child(2), th:nth-child(2) { width: 100px; }
            td:nth-child(3), th:nth-child(3) { width: 100px; }
            td:nth-child(4), th:nth-child(4) { width: 100px; }
            td:nth-child(5), th:nth-child(5) { width: 100px; }

        </style>
    </head>
    <body>

        <div id="header">
            <h1>Analysis Results</h1>
        </div>

        <div align="center" id="section" >
            <div>
                <p> Results </p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
            </div>
            <%
                response.setHeader("Expires", "0");

                String path="";
                String recognisedAs = "";

                String cno = "a";
            %>



            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <form name="resultsAnalysis" method="post" action="analysingResults.jsp" >       
                <table border="1">
                    <thead>
                        <tr>
                            <th>COMPONENT NUMBER</th>
                            <th>SELECT</th>
                            <th>COMPONENT IMAGE</th>
                            <th>RECOGNIZED AS</th>
                            <th>CHANGE TO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            /*imgpro.ComponentInfo cInf;
     
                             for(int i=0;i<ProcessImages.CharacterList.size();i++ )
                             {
                             cInf=ProcessImages.CharacterList.get(i);
                             cno=cInf.name;
                             recognisedAs=ProcessImages.RecognisedCharacetrList.get(i).recAs;
                             // path=rs.getString(2);
                             path="./root"+TimeStamp.rand+"/output/"+cno+".jpg";
                             //   System.out.println(path);
                             */
                            imgpro.ComponentInfo cInf;
                            ArrayList<ComponentInfo> CharacterList = (ArrayList<ComponentInfo>) session.getAttribute("listOfChar");
                            String rand=(String)session.getAttribute("rand");
                            for (int i = 0; i < CharacterList.size(); i++) {
                                cInf = CharacterList.get(i);
                                cno = cInf.name;
                                //recognisedAs = cInf.LabelFromFileName;
                                recognisedAs = cInf.recognisedAs;
                                // path=rs.getString(2);
                                path = "./root" + rand + "/output/" + cno + ".jpg";
                                //   System.out.println(path);
                            
                        %>
                        <tr>
                            <td><%=cno%></td>
                            <td><input type="checkbox" name="char" value=<%=cno%> ></td>
                            <td><img src=<%=path%> /></td>
                            <td><%=recognisedAs%></td>
                            <td><input type="text" name=<%=cno + "_txt"%> value="" /></td>
                        </tr>
                        <%  }%>
                    </tbody>
                </table>
                <input type="Submit" value="Update MOdifications " name="btnSearch" />
            </form>   

        </div>  

    </body>
</html>

