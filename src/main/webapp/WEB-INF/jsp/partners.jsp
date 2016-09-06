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

    <title>CDMK Partners</title>

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
            <h4 class="blue-title text-center">CDMK Partners</h4>
            <p>The Caribbean Disaster Management Knowledge Broker (CDMK) is supported by funding through the Open &amp; Collaborative Science in Development Network (OCSDNet) research project, supported by Canada's International Development Research Centre (IDRC) and the UK Government's Department for International Development (DFID).</p>
            <p><strong>Partners</strong></p>
            <ol>
                <li>The Open and Collaborative Science in Development Network (<a href="http://www.ocsdnet.org">OCSDNet</a>)</li>
            </ol>
            <p>OCSDNet is composed of twelve researcher-practitioner teams from the Global South interested in understanding the role of openness and collaboration in science as a transformative tool for development thinking and practice. The primary goal&nbsp;of the network is to nurture an interactive community of Open Science practitioners and leaders to learn together&nbsp;and contribute towards&nbsp;a pool of open knowledge on how networked collaboration could address local and global development challenges.&nbsp;</p>
            <ol start="2">
                <li>International Development Research Centre (<a href="http://www.idrc.ca">IDRC</a>)</li>
            </ol>
            <p>The International Development Research Centre (IDRC, the Centre) is a Canadian Crown corporation established by an act of Parliament in 1970. IDRC was created to help developing countries ﬁnd solutions to their problems. It encourages, supports, and conducts research in the world&rsquo;s developing regions, and seeks to apply new knowledge to the economic and social improvement of those regions.</p>
            <ol start="3">
                <li>Caribbean Disaster Emergency Management Agency (<a href="http://www.cdema.org/">CDEMA</a>)</li>
            </ol>
            <p>CDEMA is a regional inter-governmental agency for disaster management in the Caribbean Community (CARICOM). The Agency was established in 1991 as CDERA (Caribbean Disaster Emergency Response Agency) with primary responsibility for the coordination of emergency response and relief efforts to Participating States that require such assistance. It transitioned to CDEMA in 2009 to fully embrace the principles and practice of Comprehensive Disaster Management (CDM). CDM is an integrated and proactive approach to disaster management and seeks to reduce the risk and loss associated with natural and technological hazards and the effects of climate change to enhance regional sustainable development.</p>
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
