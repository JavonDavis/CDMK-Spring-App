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

    <style>
        .footer {
            position:;
        }
    </style>

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
                        <li><a href="http://cdmk-caribbean.net:8080/skosmos/cdmk/en/">CDMK Thesaurus</a></li>
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
            <div class="col-xs-12">
                <h2>Share Your Knowledge</h2>
                <h4>Demo</h4>
                <p>Input some text in the form below. The Caribbean Disaster Knowledge Broker will match the concepts in your text to concepts in the vocabulary.</p>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <form id="getConceptsForm" role="form" action="share" method="POST">
                    <div class="row">
                        <div class="col-xs-12">
                          <div class="form-group">
                                <label for="text">Text</label> <br>
                                <textarea class="form-control" name="text" id="text"></textarea>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-default" type="submit">Extract Concepts</button>
                </form>
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
