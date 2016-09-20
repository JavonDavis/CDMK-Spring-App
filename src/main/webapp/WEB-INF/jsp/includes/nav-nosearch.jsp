<%--
  Created by javon on 22/08/2016.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home">CDMK</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">About CDMK <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="background">CDMK Background</a></li>
                        <li><a href="usecases">CDMK Use-Cases</a></li>
                        <li><a href="">CDMK Brochure</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">CDMK Tools<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="http://cdmk.poolparty.biz/cdmk.html">CDMK Thesaurus</a></li>
                        <li><a href="api">CDMK API</a></li>
                        <li><a href="http://skos.cdmk-caribbean.net/">CDMK Tree View</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="share">Share Your Knowledge</a></li>
                        <li><a href="submit">Submit your document</a></li>
                        <%--<li><a href="search">Search the Index</a></li>--%>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Partners <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="partners">Partners &amp Stakeholders</a></li>
                        <li><a href="api">Contribute to CDMK</a></li>
                    </ul>
                </li>
                <li><a href="mailto:lila.rao@uwimona.edu.jm?Subject=CDMK">Contact us</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
