<%-- 
    Document   : utilities
    Created on : May 5, 2016, 12:07:50 AM
    Author     : Shikha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Utilities</title>
	<style type="text/css">

		/* Layout */
		body {
			min-width: 630px;
		}

		#container {
			padding-left: 200px;
			padding-right: 190px;
		}
		
		#container .column {
			position: relative;
			float: left;
		}
		
		#center {
			padding: 10px 20px;
			width: 100%;
		}
		
		#left {
			width: 180px;
			padding: 0 10px;
			right: 240px;
			margin-left: -100%;
		}
		
		#right {
			width: 130px;
			padding: 0 10px;
			margin-right: -100%;
		}
		
		#footer {
			clear: both;
		}
		
		/* IE hack */
		* html #left {
			left: 150px;
		}

		/* Make the columns the same height as each other */
		#container {
			overflow: hidden;
		}

		#container .column {
			padding-bottom: 1001em;
			margin-bottom: -1000em;
		}

		/* Fix for the footer */
		* html body {
			overflow: hidden;
		}
		
		* html #footer-wrapper {
			float: left;
			position: relative;
			width: 100%;
			padding-bottom: 10010px;
			margin-bottom: -10000px;
			background: #fff;
		}

		/* Aesthetics */
		body {
			margin: 0;
			padding: 0;
			font-family:Sans-serif;
			line-height: 1.5em;
		}
		
		p {
			color: #555;
		}

		nav ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
		}
		
		nav ul a {
			color: darkgreen;
			text-decoration: none;
		}

		#header, #footer {
			font-size: large;
			padding: 0.3em;
			background: #BCCE98;
		}

		#left {
			background: #DAE9BC;
		}
		
		#right {
			background: #F7FDEB;
		}

		#center {
			background: #fff;
		}

		#container .column {
			padding-top: 1em;
		}
		
	</style>
	
		
</head>

<body>

	<header id="header"><p>Give the path to source folder to combine training features : </p></header>

	<div id="container">

		<main id="center" class="column">
		<form name="combineCSV" method="post" enctype="multipart/form-data" action="combiningCsv.jsp"  >     
    
     <!--<p> Enter Path &nbsp; : <input type="text" name="txtPath" /> </p>-->
     BROWSE DOCUMENT to upload &nbsp; : <p>&nbsp;</p> <input name="browseFile" type="file" multiple  style="border-style:  outset;background-color: yellowgreen"/> 
    <p>&nbsp;</p>
     <input type="submit" value="GENERATE TRAINING SET" name="btnCombine" />
</form>
		</main>

		<nav id="left" class="column">
			<h3>Go To</h3>
                        <ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="DocumentAnalysisHome.jsp">Document Analysis</a></li>
				
			</ul>

		</nav>

		<div id="right" class="column">
			<h3>Menu </h3>
			
		</div>

	</div>

	<div id="footer-wrapper">
		<footer id="footer"><p>Thank You !! </p></footer>
	</div>

</body>

</html>

