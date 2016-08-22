<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Caribbean Disaster Management Knowledge Broker - Main</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link href="css/business-frontpage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<%@include file="includes/nav-nosearch.jsp" %>

    <div class="wrapper">

    <!-- Page Content -->

    <div class="text-center tgline bottom-buffer">
        <div class="image-filter">
            <div class="inner-text text-center">
                <h3><strong>DISCOVERING WHAT WE KNOW ABOUT</strong></h3>
                <h2>COMPREHENSIVE DISASTER MANAGEMENT IN THE CARIBBEAN</h2>
                <form:form role="search" id="getConceptsForm" action="home" method="POST">
                    <div class="form-group">
                        <input id="search_field" name="text" type="text" class="input-lg" placeholder="Search the CDM Index...">
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row top-buffer text-center">
            <h4 class="blue-title bottom-buffer">Help Map the CDM Knowledge Ecosystem</h4>
            <div class="col-xs-4">
                <h4>Submit Your Document</h4>
                <p>Do you have an important document about any aspect of Caribbean Disaster Recovery Planning
                that you want to be evaluated? Submit your document to be tagged and validated by the CDMK.</p>
            </div>
            <div class="col-xs-4">
                <h4>Share Your Knowledge</h4>
                <p>Do you want to share documents or websites about any aspects of Comprehensive Disaster management in the Caribbean? Share it with the CDMK Knowledge Broker</p>
            </div>
            <div class="col-xs-4">
                <h4>Contribute an App</h4>
                <p>Do you want to create a Learning App about Caribbean Disaster Management? Use the CDMK API!</p>
            </div>
        </div>

        <div class="row text-center bottom-buffer">
                  <div class="col-xs-4">
                    <a href="submit"><button class="btn btn-default">Submit</button></a>
                  </div>
                  <div class="col-xs-4">
                    <a href="share"><button class="btn btn-default">Share</button></a>
                  </div>
                  <div class="col-xs-4">
                    <a href="api"><button class="btn btn-default">Get the API</button></a>
                  </div>
              </div>

        <div class="row text-center top-buffer bottom-buffer">
            <div class="col-xs-12 text-center">
                <div>
                    Share:
                    <span class="social-icon">
                        <a class="twitter-share-button" href="#"><i class="fa fa-twitter"></i></a>
                    </span>
                    <span class="social-icon">
                        <a href="#" class="unimplemented"><i class="fa fa-facebook"></i></a>
                    </span>
                    <span class="social-icon">
                        <a href="#" class="unimplemented"><i class="fa fa-linkedin"></i></a>
                    </span>
                    <span class="social-icon">
                        <a href="#" class="unimplemented"><i class="fa fa-envelope"></i></a>
                    </span>
                </div>
            </div>
        </div>

        <div class="row">
            <h4 class="blue-title text-center">About CDMK</h4>
            <p>CDMK, The Caribbean Disaster Management Knowledge Broker is an online, interactive vocabulary for the integration of silos of knowledge relating to disaster management/recovery wich is currently dispersed throughout the region. This knowledge broker approach is inspired by the well-known Climate Tagger, that is widely used in the international Climate Knowledge community. CDMK provides a common semantic reference for distributed knowledge resources and in particularly, facilitates a shared, collaborative approach to addressing disaster recovery planning. This can be best summed up as "How do we know what we know?"</p>
            <p>The CDMK Thesaurus is intended to:</p>
            <ul>
                <li>Enable the Discovery and indexing of key knowledge resources in the Caribbean Disaster Management community.</li>
                <li>Support the Development and validation of comprehensive consistent Disaster Recovery Plans</li>
                <li>Provide a platform for the development of value-added knwoeldge-based applications and services for the Caribbean Disaster Management community</li>
                <li>Facilitate research and analysis of the Caribbean Disaster Management knowledge ecosystem.</li>
            </ul>
            <p>The Caribbean Disaster Management Knowledge Broker is supported by funding through the Open &amp Collaborative Science in Development Network (OCSDNet) research project, supported by Canada's International Development Research Centre and the UK Government's Department for International Development. Find out more at <a href="www.ocsdnet.org">OCSD.</a></p>
            <h4 class="blue-title text-center">CDMK PARTNERS</h4>
            <p>The CDMK Thesaurus is an Open Access Public Good that benefits from the voluntry contributions of the Comprehensive Disaster Management community across the Caribbean, coordinated in partnership with the Caribbean Disaster and Energy Management Association - CDEMA</p>
        </div>
    </div>
    <hr>

        <h4 class="blue-title text-center">CDMK Implementation and Funding PARTNERS</h4>

    <div class="push"></div>

    </div>


    <!-- Footer -->
    <div class="footer footer-logos">
        <div class="container">
            <div class="row">
                <div class="col-xs-5">
                    <img src="images/IDRC_Canada.png" alt="IDRC Canada">
                </div>
                <div class="col-xs-1">
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


    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <script>
        var unimplemented = document.getElementsByClassName('unimplemented');
        console.log("unimplemented");

        for (var i = 0; i < unimplemented.length; i++) {
            unimplemented[i].addEventListener('click', function() {
                alert('This feature is coming soon!');
            }, false)
        }

    </script>

<script>
    function focus_on_search() {
        document.getElementById('search_field').focus();
    }
</script>

</body>

</html>
