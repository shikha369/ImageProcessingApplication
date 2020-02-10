<%-- 
    Document   : classification
    Created on : Feb 17, 2016, 1:32:43 PM
    Author     : Shikha
--%>

<%@page import="imgpro.testDocumentContent"%>
<%@page import="imgpro.ComponentRecognisedAs"%>
<%@page import="imgpro.ComponentInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="imgpro.Classifier"%>
<%@page import="imgpro.analysedata"%>
<%@page import="imgpro.ProcessImages"%>
<%
    ArrayList<ComponentRecognisedAs> RecognisedCharacetrList = new ArrayList<ComponentRecognisedAs>();
    Classifier clsfr = new Classifier();
    ArrayList<ComponentInfo> CharacterList = (ArrayList<ComponentInfo>) session.getAttribute("listOfChar");
    String rand = (String) session.getAttribute("rand");
    String rootPath = (String) session.getAttribute("rootpath");
    RecognisedCharacetrList = clsfr.runClassifier(rootPath, rand);
    testDocumentContent dc = new testDocumentContent();
    String contentString = dc.generateContentInString(RecognisedCharacetrList, CharacterList);
    dc.writeDocument(rootPath, rand, contentString);
    dc.writeIndexedDocument(rootPath, rand, contentString);
    for (int r = 0; r < CharacterList.size(); r++) {
        CharacterList.get(r).recognisedAs = RecognisedCharacetrList.get(r).recAs;
         CharacterList.get(r).LabelFromFileName = RecognisedCharacetrList.get(r).recAs;
          CharacterList.get(r).CharClass = RecognisedCharacetrList.get(r).recAs;
         
        
    }
    session.setAttribute("listOfChar", CharacterList);
     response.sendRedirect("DocumentAnalysisHome.jsp");
   


%>