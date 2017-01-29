<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="useGoogleMapsScript" value="true" scope="request"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    ${myId}
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-plane"></i> Aviation Event</h3>
            <ol class="breadcrumb">
                <li><i class="fa fa-home"></i><a href="/index.jsp">Home</a></li>
                <li><i class="fa fa-database"></i><a href="/search.jsp">Events</a></li>
                <li><i class="fa fa-folder"></i>${eventTypeName} No. ${id}</li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-<c:if test="${empty finLocationName}">12</c:if><c:if test="${not empty finLocationName}">7</c:if>">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2><strong>General Information</strong></h2>
                </div>
                <div class="panel-body bio-graph-info">
                    <h1><b>${eventTypeName}</b> No. ${id}</h1>
                    <div class="row">
                        <div class="bio-row">
                            <p><span><strong>Date</strong> </span> ${date} </p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Injury severity</strong> </span> ${injurySeverityName}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>No injuries</strong></span> ${totalNoInjuries}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Minor injuries</strong> </span> ${totalMinorInjuries}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Serious injuries</strong> </span> ${totalSeriousInjuries}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Fatal injuries</strong> </span> ${totalFatalInjuries}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Weather conditions</strong> </span> ${weatherConditionName}</p>
                        </div>
                        <div class="bio-row">
                            <p><span><strong>Report status</strong> </span> ${reportStatusName}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${not empty finLocationName}">
            <div class="col-lg-5">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2><i class="fa fa-flag-o red"></i><strong>Location</strong></h2>
                    </div>
                    <div class="panel-body">
                        <c:if test="${(not empty finLocationLatitude and not empty finLocationLongitude) or (not empty finLocationName)}">
                            <div id="gmap"></div>
                        </c:if>
                        <div class="row">
                            <div class="col-md-12">${finLocationName}</div>
                        </div>
                        <c:if test="${not empty finLocationLatitude and not empty finLocationLongitude}">
                            <div class="row">
                                <div class="col-md-12">
                                    <span><strong>Latitude: </strong></span> ${finLocationLatitude},
                                    <span><strong>Longitude: </strong></span>${finLocationLongitude}
                                </div>
                            </div>
                            <script>
                                function initMap() {
                                    var uluru = {lat: ${finLocationLatitude}, lng: ${finLocationLongitude}};
                                    var map = new google.maps.Map(document.getElementById('gmap'), {
                                        zoom: 7,
                                        center: uluru
                                    });
                                    var marker = new google.maps.Marker({
                                        position: uluru,
                                        map: map
                                    });
                                }
                            </script>
                        </c:if>
                        <c:if test="${(empty finLocationLatitude or empty finLocationLongitude) and not empty finLocationName}">
                            <script>
                                function initMap() {
                                    var geocoder = new google.maps.Geocoder();
                                    var location = "${finLocationName}";

                                    geocoder.geocode({'address': location}, function (results, status) {
                                        if (status == google.maps.GeocoderStatus.OK) {
                                            var mapLocation = results[0].geometry.location;
                                            var map = new google.maps.Map(document.getElementById('gmap'), {
                                                zoom: 7,
                                                center: mapLocation
                                            });
                                            var marker = new google.maps.Marker({
                                                position: uluru,
                                                map: mapLocation
                                            });
                                        }
                                    });
                                }
                            </script>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <c:if test="${eventCasesCount > 0}">
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading">Cases</header>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Case</th>
                            <th>Aircraft</th>
                            <th>Category</th>
                            <th>Amateur built</th>
                            <th>Registration No.</th>
                            <th>Operator</th>
                            <th>FAR</th>
                            <th>Flight purpose</th>
                            <th>Scheduled</th>
                            <th>Phase</th>
                            <th>Damage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="caseItem" items="${eventCases}">
                            <tr>
                                <td>${caseItem.key}</td>
                                <td><c:if
                                        test="${not empty caseItem.value.Aircraft_manufacturerName}">${caseItem.value.Aircraft_manufacturerName}</c:if><c:if
                                        test="${not empty caseItem.value.Aircraft_modelName}"> ${caseItem.value.Aircraft_modelName}</c:if>
                                    <c:if test="${not empty caseItem.value.Aircraft_numberEngines}"> [Engines: ${caseItem.value.Aircraft_numberEngines}]</c:if>
                                </td>
                                <td>${caseItem.value.Aircraft_categoryName}</td>
                                <td><c:if test="${caseItem.value.Aircraft_builtByAmateur == \"false\"}">No</c:if><c:if
                                        test="${caseItem.value.Aircraft_builtByAmateur == \"true\"}">Yes</c:if></td>
                                <td>${caseItem.value.Aircraft_registrationNumber}</td>
                                <td>${caseItem.value.carrierName}</td>
                                <td>${caseItem.value.federalRegulationDesc}</td>
                                <td>${caseItem.value.flightPurposeDesc}</td>
                                <td><c:if test="${caseItem.value.scheduledFlight == \"false\"}">No</c:if><c:if
                                        test="${caseItem.value.scheduledFlight == \"true\"}">Yes</c:if></td>
                                <td>${caseItem.value.flightPhaseDesc}</td>
                                <td>${caseItem.value.damageDesc}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
    </c:if>
</t:layout>