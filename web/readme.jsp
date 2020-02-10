<%-- 
    Document   : readme
    Created on : Apr 17, 2016, 9:30:19 PM
    Author     : Shikha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>readme</title>
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
	
	<script type="text/javascript">
		/* =============================
		This script generates sample text for the body content. 
		You can remove this script and any reference to it. 
		 ============================= */
		var bodyText=["MindSet Is Everything.", "Don't Be Afraid To Fail,Afraid To Remain The Same.", "The past has no power over the present moment.", "This, too, will pass.", "</p><p>The Miracle Is that I HAD COURAGE to START.", "You can't have a Million Dollar Dream with a Minimum Wage Work Ethic.", "Don't Think You Can..Know You Can..Your Mind makes It Real."];
		function generateText(sentenceCount){
			for (var i=0; i<sentenceCount; i++)
			document.write(bodyText[Math.floor(Math.random()*7)]+" ")
		}
	</script>	
</head>

<body>

	<header id="header"><p>Follow the Instructions given below to use this Tool</p></header>

	<div id="container">

		<main id="center" class="column">
			<article>
			
				<h1></h1>
				<p>This tool can be used for extracting generalized boundary features of scanned documents in JPEG
                                   format.Steps to are follows :
                                <ul>
                                    <li>Upload document in JPEG format</li>
                                    <li>Upload the parameter file that has specifications []. Tool will use the default 
                                        parameters, if not uploaded</li>
                                    <li>Click on Submit Scanned Image and Start Processing</li>
                                    <li>You will be prompted to download a zipped Folder that contains the following:
                                        <ul>
                                            <li>Image extracted Characters in JPEG format </li>
                                            <li>Features of extracted Characters in CSV file format </li>
                                            <li>Binary Matrix of each Character in CSV file format  </li>
                                            
                                        </ul>
                                    </li>
                                    <li>Click on link Analyze Results to give class label to Characters and 
                                    Click on MODIFY button to download the LabelAssigned Features CSV file.
                                    If it is not modified then Class Labels are by Default named by UploadedImage name.
                                    </li>
                                    <li>These Labeled Feature files can be used for training the Classifier while Document Indexing </li>
                                </ul>
                        <p>This tool is mainly used for Document Indexing (with your Customized Classifier and Training Data ) of scanned documents in JPEG
                                   format.Steps to are follows :
                                <ul>
                                    <li>Upload document in JPEG format</li>
                                    <li>Upload R Classifier.Tool will use the default 
                                        Decision Tree Classifier, if not uploaded
                                        <ul>
                                            <li>
                                                The Classifier should have three input parameters as shown : runC(pathToTrainingFeaturesCSV,
                                                pathToTestFeaturesCSV,pathToWriteRecognisedCharactersCSV)
                                                <ul>
                                                    <li>
                                                        pathToTrainingFeaturesCSV : Tool Stores its training file here.
                                                        Classifier builds Classification model using this file.
                                                    </li>
                                                    <li>
                                                        pathToTestFeaturesCSV : Tool Stores Features of Extracted Component from uploaded Document here.
                                                        Classifier this file for Prediction of Character Classes.
                                                    </li>
                                                    <li>
                                                        pathToWriteRecognisedCharactersCSV : Tool looks for prediction result here.
                                                        Classifier should write recognized characters here in CSV file.
                                                        [Format : Serial No , Recognized Character as factor]
                                                    </li>
                                                </ul>
                                            </li>
                                            
                                        </ul>
                                    </li>
                                    <li>Upload the Training Features file. Tool will use the default 
                                        Training Features file, if not uploaded</li>
                                    <li>Upload the parameter file that has specifications []. Tool will use the default 
                                        parameters, if not uploaded</li>
                                    <li>Click on Submit Scanned Image and Start Processing</li>
                                    <li>You will be prompted to download a zipped Folder that contains the following:
                                        <ul>
                                            <li>Image extracted Characters in JPEG format </li>
                                            <li>Features of extracted Characters in CSV file format </li>
                                            <li>Binary Matrix of each Character in CSV file format  </li>
                                            
                                        </ul>
                                    </li>
                                    <li>Click on Download Indexed File to get CSV of indexed words in LINE NO-WORD NO format for each word.
                                    </li>
                                    <li>Click on link Analyze Results to correct incorrectly recognized Characters and 
                                    Click on MODIFY button to download the correctly Labeled Features CSV file.
                                    
                                    </li>
                                    <li>These corrected Feature files can be appended with Training Features and used for re-training the Classifier </li>
                                </ul>
                                
                                
			
			</article>								
		</main>

		<nav id="left" class="column">
			<h3>Go To</h3>
                        <ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="DocumentAnalysisHome.jsp">Document Analysis</a></li>
				
			</ul>

		</nav>

		<div id="right" class="column">
			<h3>Wisdom bites :)</h3>
			<p><script>generateText(1)</script></p>
		</div>

	</div>

	<div id="footer-wrapper">
		<footer id="footer"><p>Thank You !! </p></footer>
	</div>

</body>

</html>
