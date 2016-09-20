<%--
  Created by javon on 22/08/2016.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Submit your document</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <link href="css/business-frontpage.css" rel="stylesheet">

    <style>
        .footer {
            position:;
        }
    </style>

</head>

<body>

<%@include file="includes/nav.jsp" %>
<div class="container">

    <!-- Page Content -->

    <div class="row">
        <div class="col-xs-12">
            <h2>Submit your document</h2>
            <h4>Demo</h4>
            <p>The Caribbean Disaster Knowledge Broker can be used to analyse the content and structure of a
                DRP and make any necessary recommendations. Submit a docx document below to demonstrate this feature,
                at the moment only docx files are supported. We are working hard to support more of the commonly
            used file formats such as pdf and doc.</p>
        </div>
    </div>

    <br />

    <div class="row">
        <div class="col-xs-12">
            <form:form name="extractionForm" id="getConceptsForm" role="form" action="submit" method="POST" onsubmit="return validateForm()" enctype="multipart/form-data">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="filebutton">Upload a File</label>
                        <div class="col-md-4">
                            <input id="filebutton" name="file" class="input-file" type="file"/>
                        </div>
                    </div>
                </div>

                <br />
                <button class="btn btn-default" type="submit">Submit</button>
            </form:form>
        </div>
    </div>
</div>

<br />
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
                <h4><a href="http://ocsdnet.org/">www.ocsdnet.org</a></h4>
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