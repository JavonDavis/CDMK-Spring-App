<%--
  Created by javon on 05/09/2016.
--%>
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

    <title>Background</title>

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

    <div class="container">

        <div class="row">
            <h4 class="blue-title text-center">Background on CDMK</h4>
            <p>CDMK, The Caribbean Disaster Management Knowledge Broker is an online, interactive system for the integration of silos of knowledge related to disaster management and recovery which is currently dispersed throughout the region. This knowledge broker approach is inspired by the well-known Climate Tagger that is widely used in the international Climate Knowledge community. A key component of the CDMK is the DRP vocabulary which defines the concepts used for referencing the available resources.&nbsp; It ensures that this is done consistently and provides the means for efficient searching of resources.</p>
            <p>CDMK provides a common semantic reference for distributed knowledge resources and in particularly, facilitates a shared, collaborative approach to addressing disaster recovery planning. This system provides an answer to the question: "How do we know what we know?"</p>
            <p>The CDMK is intended to:</p>
            <ul>
                <li>Enable the discovery and indexing of key knowledge resources in the Caribbean Disaster Management community.</li>
                <li>Support the development and validation of comprehensive consistent Disaster Recovery Plans</li>
                <li>Provide a platform for the development of value-added knowledge-based applications and services for the Caribbean Disaster Management community</li>
                <li>Facilitate research and analysis of the Caribbean Disaster Management knowledge ecosystem.</li>
            </ul>
            <p>The Caribbean Disaster Management Knowledge Broker is supported by funding through the Open &amp; Collaborative Science in Development Network (OCSDNet) research project, supported by Canada's International Development Research Centre and the UK Government's Department for International Development.</p>
            <p>The CDMK is an Open Access Public Good that benefits from the voluntary contributions of the Comprehensive Disaster Management community across the Caribbean, coordinated in partnership with the Caribbean Disaster and Energy Management Association &ndash; CDEMA</p>
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
