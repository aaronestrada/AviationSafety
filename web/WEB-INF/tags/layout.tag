<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Aviation Safety - Semantic Technologies - UNIBZ">
    <meta name="author" content="Aaron Estrada, Happy Das">
    <meta name="keyword" content="Aviation, Semantic Technologies, UNIBZ">
    <link rel="shortcut icon" href="/img/favicon.png">

    <title>Aviation Safety - Semantic Technologies - UNIBZ</title>

    <!-- Bootstrap CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google maps -->
    <link href="/css/googlemaps.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="/css/elegant-icons-style.css" rel="stylesheet"/>
    <link href="/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- full calendar css-->
    <link href="/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet"/>
    <link href="/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet"/>
    <!-- easy pie chart-->
    <link href="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"
          media="screen"/>
    <!-- owl carousel -->
    <link rel="/stylesheet" href="css/owl.carousel.css" type="text/css">
    <link href="/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <!-- Custom styles -->
    <link rel="stylesheet" href="/css/fullcalendar.css">
    <link href="/css/widgets.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet"/>
    <link href="/css/xcharts.min.css" rel=" stylesheet">
    <link href="/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
</head>

<body>
<!-- container section start -->
<section id="container" class="">

    <header class="header dark-bg">
        <div class="toggle-nav">
            <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i
                    class="icon_menu"></i></div>
        </div>
        <!--logo start-->
        <a href="/index.jsp" class="logo">Aviation <span class="lite">Safety</span></a>
        <!--logo end-->
        <div class="nav search-row" id="top_menu"></div>
    </header>
    <!--header end-->

    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li class="active">
                    <a class="" href="index.jsp">
                        <i class="icon_house_alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="active">
                    <a class="" href="search.jsp">
                        <i class="icon_search"></i>
                        <span>Events</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <!--overview start-->
            <jsp:doBody/>
        </section>
        <div class="text-right">
            <div class="credits">
                <!--
                    All the links in the footer should remain intact.
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
                <a href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">Business Bootstrap
                    Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
    </section>
    <!--main content end-->
</section>
<!-- container section start -->

<!-- javascripts -->
<script src="/js/jquery.js"></script>
<script src="/js/jquery-ui-1.10.4.min.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
<!-- bootstrap -->
<script src="/js/bootstrap.min.js"></script>
<!-- nice scroll -->
<script src="/js/jquery.scrollTo.min.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<!-- custom select -->
<script src="/js/jquery.customSelect.min.js"></script>
<script src="/assets/chart-master/Chart.js"></script>

<!--custome script for all page-->
<script src="/js/scripts.js"></script>
<!-- custom script for this page-->
<script src="/js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/js/jquery-jvectormap-world-mill-en.js"></script>
<script src="/js/jquery.autosize.min.js"></script>
<script src="/js/jquery.placeholder.min.js"></script>
<script src="/js/gdp-data.js"></script>
<script src="/js/morris.min.js"></script>
<script src="/js/jquery.slimscroll.min.js"></script>
<script>
    //custom select box
    $(function () {
        $('select.styled').customSelect();
    });

    /* ---------- Map ---------- */
    $(function () {
        $('#map').vectorMap({
            map: 'world_mill_en',
            series: {
                regions: [{
                    values: gdpData,
                    normalizeFunction: 'linear'
                }]
            },
            scaleColors: ['#b6d6ff', '#005ace'],
            selectedColor: '#c9dfaf',
            backgroundColor: '#eef3f7',
            onLabelShow: function (e, el, code) {
                el.text(el.text() + ' (Events - ' + gdpData[code] + ')');
            },
            onRegionClick: function (element, code, region) {
                window.location.href = "/search.jsp?country=" + code.toUpperCase();
            }

        });
    });

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBmEv8F0SFC-yM-6PJboyGo3292h_VkhEU&callback=initMap"></script>
</body>
</html>