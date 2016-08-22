<%--
  Created by javon on 20/07/2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CDMK Results</title>

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
    <!-- Page Content -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
            <h3>Resources related to '${concept}' from the CDM Content Pool:</h3>

            <c:choose>
                <c:when test="${filters != null}">
                    <c:forEach items="${filters}" var="concept">
                        <c:choose>
                            <c:when test="${concept.checked}">
                                <div class="checkbox">
                                    <label><input type="checkbox" onchange="window.location.href='/home?expand=${concept.prefLabel}'" checked>${concept.prefLabel}</label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="checkbox">
                                    <label><input type="checkbox" onchange="window.location.href='/home?filter=${concept.prefLabel}'">${concept.prefLabel}</label>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
            </c:choose>
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>Resource</th>
                    <th>Concepts</th>
                </tr>
                </thead>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>
                            <table class="
                            noBorder" >
                                <c:choose>
                                    <c:when test="${item.title!=null}">
                                        <c:choose>
                                            <c:when test="${item.url!=null}">
                                                <tr><td><a href="${item.url.get(0)}">${item.title.get(0)}</a></td></tr>
                                            </c:when>
                                            <c:when test="${item.file!=null}">
                                                <tr><td><a href="<c:url value="/cdmk${item.file.get(0)}"/>">${item.title.get(0)}</a></td></tr>
                                                <%--<td><iframe src="${item.file.get(0)}"></iframe></td>--%>
                                                <%--<a href="/ViewerJS/#..${item.file.get(0)}">--%>
                                            </c:when>
                                            <c:otherwise>
                                                <tr><td>ERROR FINDING URL OR FILE</td></tr>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${item.source!=null}">
                                                <tr><td>Source or Author:${item.source.get(0)}</td></tr>
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${item.email!=null}">
                                                <tr><td><a href="mailto:${item.email.get(0)}">Contact</a></td></tr>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${item.url!=null}">
                                                <tr><td><a href="${item.url.get(0)}">ERROR LOADING TITLE FOR URL</a></td></tr>
                                            </c:when>
                                            <c:when test="${item.file!=null}">
                                                <tr><td><a href="<c:url value="/cdmk${item.file.get(0)}"/>">ERROR LOADING TITLE FOR FILE</a></td></tr>
                                                <%--<td><iframe src="${item.file.get(0)}"></iframe></td>--%>
                                                <%--<a href="/ViewerJS/#..${item.file.get(0)}">--%>
                                            </c:when>
                                            <c:otherwise>
                                                <tr><td>ERROR LOADING RESULT</td></tr>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${item.source!=null}">
                                                <tr><td>${item.source.get(0)}</td></tr>
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${item.email!=null}">
                                                <tr><td><a href="mailto:${item.email.get(0)}">Contact</a></td></tr>
                                            </c:when>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </table>
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
                                    Resource not tagged
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

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
