<%--
  Created by javon on 20/07/2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Caribbean Disaster Management Knowledge Broker - Share your knowledge</title>

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

        <div class="row">
            <div class="col-xs-6">
                <h2>Resources related to '${concept}' from the CDM Content Pool:</h2>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>Resource</th>
                        <th>Concepts</th>
                    </tr>
                    </thead>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <c:choose>
                                <c:when test="${item.url!=null}">
                                    <td><a href="${item.url.get(0)}">${item.url.get(0)}</a></td>
                                </c:when>
                                <c:when test="${item.file!=null}">
                                    <td><a href="${item.file.get(0)}" target="_blank">Download File</a></td>
                                    <%--<td><iframe src="${item.file.get(0)}"></iframe></td>--%>
                                    <%--<a href="/ViewerJS/#..${item.file.get(0)}">--%>
                                </c:when>
                                <c:otherwise>
                                    <td>${item.text.get(0)}</td>
                                    <br />
                                </c:otherwise>
                            </c:choose>
                            <td>

                            <table class="table table-condensed">
                                <thead>
                                <tr>
                                    <th>Concept</th>
                                    <th>Strength</th>
                                </tr>
                                </thead>
                                <c:forEach items="${item.concepts}" var="concept">
                                    <tr>
                                        <td><a href="/search?q=${concept.prefLabel}">${concept.prefLabel}</a></td><td><meter value="${concept.score/100}">${concept.score}%</meter></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

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
