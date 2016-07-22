<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Caribbean Disaster Management Knowledge Broker - API</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <link href="css/business-frontpage.css" rel="stylesheet">

</head>

<body>

    <div class="wrapper">

    <!-- Page Content -->
    <div class="container">
        <div class="row bottom-buffer">
            <div class="col-xs-3">
                <div class="dropdown">
                    <button class="btn nav-btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">ABOUT CDMK <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="">CDMK Background</a></li>
                        <li><a href="">CDMK Use-Cases</a></li>
                        <li><a href="">CDMK Brochure</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="dropdown">
                    <button class="btn nav-btn dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown">CDMK TOOLS <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="http://cdmk.poolparty.biz/CDMK.html">CDMK Thesaurus</a></li>
                        <li><a href="api">CDMK API</a></li>
                        <li><a href="http://skos.cdmk-caribbean.net/">CDMK Tree View</a></li>
                        <li><a href="share">Share Your Knowledge</a></li>
                        <li><a href="search">Search the Index</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="dropdown">
                    <button class="btn nav-btn dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown">PARTNERS &amp CONTRIBUTORS <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="">Partners &amp Stakeholders</a></li>
                        <li><a href="">Contribute to CDMK</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-3">
                <button class="btn nav-btn"><a href="">CONTACT US</a></button>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="text-center">
            <h2>API Guide</h2>
            <h4>There are two ways to access the knowledge base programmatically: via SPARQL and REST.</h4>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <h3>SPARQL</h3>
                <p>The SPARQL endpoint is provided using <a href="https://jena.apache.org/documentation/serving_data/">Apache Jena - Fuseki</a> and supports JSON, XML, CSV and TSV</p>
                <p>The CDMK SPARQL endpoint is located at <a href="http://cdmk-caribbean.net:3030/cdmk/sparql">http://cdmk-caribbean.net:3030/cdmk/sparql.</a>
                </p>
            </div>

            <div class="col-xs-6">
                <h3>REST</h3>
                <p>The REST API is provided by Skosmos and supports JSON and JSONP callbacks. For a full reference, visit the: <a href="https://github.com/NatLibFi/Skosmos/wiki/REST-API">Skosmos documentation.</a></p>
                <p>The CDMK Skosmos page is located at <a href="http://thesaurus.cdmk-caribbean.net/">http://thesaurus.cdmk-caribbean.net/</a></p>
            </div>
        </div>
    </div>

    <div class="push"></div>

    </div>

    <!-- Footer -->
    <div class="footer footer-logos">
        <div class="container">
            <div class="row">
                <div class="col-xs-5">
                    <img src="images/IDRC_Canada.png" alt="IDRC Canada">
                </div>
                <div class="col-xs-2">
                    <img src="images/ukaid.png" alt="UK aid">
                </div>
                <div class="col-xs-2">
                    <img src="images/MSBM_logo.png" alt="">
                </div>
                <div class="col-xs-3">
                    <h4><a href="www.ocsdnet.org">www.ocsdnet.org</a></h4>
                    <h4><a href="http://twitter.com/ocsdnet">http://twitter.com/ocsdnet</a></h4>
                    <h4><a href="https://facebook.com/OCSDNet">https://facebook.com/OCSDNet</a></h4>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
