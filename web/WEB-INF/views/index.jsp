<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-laptop"></i> Dashboard</h3>
            <ol class="breadcrumb">
                <li><i class="fa fa-home"></i><a href="/index.jsp">Home</a></li>
                <li><i class="fa fa-laptop"></i>Dashboard</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="info-box blue-bg">
                <i class="fa fa-cloud-download"></i>
                <div class="count">78.108</div>
                <div class="title">Aviation Events</div>
            </div><!--/.info-box-->
        </div><!--/.col-->

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="info-box brown-bg">
                <i class="fa fa-shopping-cart"></i>
                <div class="count">79.258</div>
                <div class="title">Event Cases</div>
            </div><!--/.info-box-->
        </div><!--/.col-->

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="info-box dark-bg">
                <i class="fa fa-thumbs-o-up"></i>
                <div class="count">${aircraftModelCount}</div>
                <div class="title">Aircraft Models</div>
            </div><!--/.info-box-->
        </div><!--/.col-->

    </div>
    <!--/.row-->


    <div class="row">
        <div class="col-lg-12 col-md-12">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2><i class="fa fa-map-marker red"></i><strong>Countries</strong></h2>
                    <div class="panel-actions">
                        <a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                        <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                        <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="panel-body-map">
                    <div id="map" style="height:380px;"></div>
                </div>

            </div>
        </div>
    </div>

    <!-- Today status end -->

    <div class="row">

        <div class="col-lg-12 col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2><i class="fa fa-flag-o red"></i><strong>Events by country</strong></h2>
                    <div class="panel-actions">
                        <a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                        <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                        <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table bootstrap-datatable countries">
                        <thead>
                        <tr>
                            <th>Country</th>
                            <th>Events</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>United States</td>
                            <td><a href="/country_events.jsp?code=US">73.629</a></td>
                        </tr>
                        <tr>
                            <td>Canada</td>
                            <td><a href="/country_events.jsp?code=CA">248</a></td>
                        </tr>
                        <tr>
                            <td>Brazil</td>
                            <td><a href="/country_events.jsp?code=BR">216</a></td>
                        </tr>
                        <tr>
                            <td>United Kingdom</td>
                            <td><a href="/country_events.jsp?code=GB">208</a></td>
                        </tr>
                        <tr>
                            <td>Mexico</td>
                            <td><a href="/country_events.jsp?code=MX">208</a></td>
                        </tr>
                        <tr>
                            <td>Australia</td>
                            <td><a href="/country_events.jsp?code=AU">195</a></td>
                        </tr>
                        <tr>
                            <td>Bahamas</td>
                            <td><a href="/country_events.jsp?code=BS">190</a></td>
                        </tr>
                        <tr>
                            <td>France</td>
                            <td><a href="/country_events.jsp?code=FR">162</a></td>
                        </tr>
                        <tr>
                            <td>Germany</td>
                            <td><a href="/country_events.jsp?code=DE">151</a></td>
                        </tr>
                        <tr>
                            <td>Colombia</td>
                            <td><a href="/country_events.jsp?code=CO">132</a></td>
                        </tr>
                        <tr>
                            <td>Spain</td>
                            <td><a href="/country_events.jsp?code=ES">98</a></td>
                        </tr>
                        <tr>
                            <td>Venezuela</td>
                            <td><a href="/country_events.jsp?code=VE">88</a></td>
                        </tr>
                        <tr>
                            <td>Italy</td>
                            <td><a href="/country_events.jsp?code=IT">87</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!--/col-->
    </div>
</t:layout>