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

    <title>CDMK Use-Cases</title>

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
            <h4 class="blue-title text-center">Use-Cases for CDMK</h4>
            <p><strong>Case 1 &ndash; Centralized Access to Repository</strong></p>
            <p>Currently, a number of agencies, organisations and individuals across the Caribbean region are developing DRP resources. The problem is that these resources are being developed in isolation and other experts and entities are unaware of what is available. Additionally, because they are being developed independently of each other they may be using different concepts and terms to describe the resources.</p>
            <p>CDMK provides <strong>a single point of access</strong> for finding the resources that have been created throughout the region. The development of a DRP vocabulary also provides a consistent set of concepts for describing these resources.</p>
            <p><strong>Case 2 &ndash; Searchable Resource Repository</strong></p>
            <p>The Caribbean region has amassed a vast, valuable repository of disaster management resources (e.g. electronic documents, human experts). Unfortunately, these resources are not particularly well-ordered or categorized. They also lack tagging for online searchability, so those looking for resources have no effective way of finding and accessing the wealth of information that is available.</p>
            <p>CDMK provides a faster, easier and more reliable way of making these disaster management resources searchable &ndash; and findable! CDMK can tag all the reports, articles, scientific documents and other resources instantly. Since the system relies on a defined DRP vocabulary with clearly defined terms and interrelationships, it will automatically use a consistent set of keywords to describe content. This means that resources can be placed in useful clusters and make them all accessible to others.</p>
            <p>There are a number of DRP resources other than electronic documents and in the same way and the CDMK can act as an expert finder for these resources or institutions. In other words it can be viewed as a searchable directory to the DRP resources available in the region.</p>
            <p><strong>Case 3 &ndash; To offer definitions, synonyms and links to resources</strong></p>
            <p>CDMK provides a simple system that allows end users to traverse the structure of the domain and provides a glossary of the concepts defined within the domain. This includes not just the meaning of the concepts but also their synonyms and the hierarchical structure of concepts.</p>
            <p>This understanding of the domain and its structure is supported though a visualization of the vocabulary which the end users can traverse.</p>
            <p><strong>Case 4 &ndash; Check the Completeness of DRPs</strong></p>
            <p>A number of institutions are required to develop Disaster Recovery Plans within their specific domain, however, these plans are expected to conform to a specific structure. The CDMK will allow these institutions to upload their plans which will then be validated by the system. Any missing sections will be identified and sample plans provided. This ensures the completeness of these plans.</p>
            <p><strong>Case 5 &ndash; Classifying New Documents </strong></p>
            <p>As new resources are made available for sharing they can be tagged and once tagged will be a part of the searchable CDMK repository. Again the tagging will be based on the defined DRP vocabulary is will be consistent with the other resources of the system.</p>
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
