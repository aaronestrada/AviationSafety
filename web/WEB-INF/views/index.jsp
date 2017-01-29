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
                <i class="fa fa-database"></i>
                <div class="count">78.108</div>
                <div class="title">Aviation Events</div>
            </div><!--/.info-box-->
        </div><!--/.col-->

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="info-box brown-bg">
                <i class="fa fa-folder"></i>
                <div class="count">79.258</div>
                <div class="title">Event Cases</div>
            </div><!--/.info-box-->
        </div><!--/.col-->

        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
            <div class="info-box dark-bg">
                <i class="fa fa-plane"></i>
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
                            <td><a href="/search.jsp?country=US">73.629</a></td>
                        </tr>
                        <tr>
                            <td>Canada</td>
                            <td><a href="/search.jsp?country=CA">248</a></td>
                        </tr>
                        <tr>
                            <td>Brazil</td>
                            <td><a href="/search.jsp?country=BR">216</a></td>
                        </tr>
                        <tr>
                            <td>United Kingdom</td>
                            <td><a href="/search.jsp?country=GB">208</a></td>
                        </tr>
                        <tr>
                            <td>Mexico</td>
                            <td><a href="/search.jsp?country=MX">208</a></td>
                        </tr>
                        <tr>
                            <td>Australia</td>
                            <td><a href="/search.jsp?country=AU">195</a></td>
                        </tr>
                        <tr>
                            <td>Bahamas</td>
                            <td><a href="/search.jsp?country=BS">190</a></td>
                        </tr>
                        <tr>
                            <td>France</td>
                            <td><a href="/search.jsp?country=FR">162</a></td>
                        </tr>
                        <tr>
                            <td>Germany</td>
                            <td><a href="/search.jsp?country=DE">151</a></td>
                        </tr>
                        <tr>
                            <td>Colombia</td>
                            <td><a href="/search.jsp?country=CO">132</a></td>
                        </tr>
                        <tr>
                            <td>Spain</td>
                            <td><a href="/search.jsp?country=ES">98</a></td>
                        </tr>
                        <tr>
                            <td>Venezuela</td>
                            <td><a href="/search.jsp?country=VE">88</a></td>
                        </tr>
                        <tr>
                            <td>Italy</td>
                            <td><a href="/search.jsp?country=IT">87</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!--/col-->
    </div>
</t:layout>