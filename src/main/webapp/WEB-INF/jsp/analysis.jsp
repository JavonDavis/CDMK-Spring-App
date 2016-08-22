<%--
  Created by javon on 22/08/2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Document Analysis</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <link href="css/business-frontpage.css" rel="stylesheet">

    <style>
        .noBorder {
            border:none ! important;
        }
    </style>

</head>

<body>

    <%@include file="includes/nav.jsp" %>
    <div class="container">

        <div class="row">
            <%--<div class="col-xs-6">--%>
                <h2>Analysis Results</h2>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th class="text-center">Section</th>
                        <th class="text-center">Missing?</th>
                        <th class="text-center">Misplaced?</th>
                        <th class="text-center">Recommendation?</th>
                        <th class="text-center">Concepts</th>
                    </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${items!=null}">
                            <c:forEach items="${items}" var="item">
                                <tr>
                                    <td>
                                        ${item.titleMap.get(item.tag)}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.presentInDocument}">
                                                No
                                            </c:when>
                                            <c:otherwise>
                                                Yes
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.inCorrectPosition}">
                                                No
                                            </c:when>
                                            <c:otherwise>
                                                Yes
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${!item.presentInDocument}">
                                                <strong>Did not detect this section in the document</strong>
                                                ${item.descriptionMap.get(item.tag)}
                                                ${item.correctionMap.get(item.tag)}
                                            </c:when>
                                            <c:when test="${!item.inCorrectPosition}">
                                                <strong>This section is present <em>but not</em> in it's recommended position.</strong>
                                                ${item.correctionMap.get(item.tag)}
                                            </c:when>
                                            <c:otherwise>
                                                <strong>This section is present and in it's recommended position.</strong>
                                                 ${item.descriptionMap.get(item.tag)}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.concepts!=null}">
                                                <table class="table table-condensed">
                                                    <thead>
                                                    <tr>
                                                        <th>Concept</th>
                                                        <th>Strength</th>
                                                    </tr>
                                                    </thead>
                                                    <c:forEach items="${item.concepts}" var="concept">
                                                        <tr>
                                                            <td><a href="/home?q=${concept.prefLabel}">${concept.prefLabel}</a></td><td><meter value="${concept.score/100}">${concept.score}%</meter></td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </c:when>
                                            <c:otherwise>
                                                Could not tag this section.
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            Error Analysing Structure. Please check back again later.
                        </c:otherwise>
                    </c:choose>
                </table>

            </div>
        </div>
    <%--</div>--%>

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


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
