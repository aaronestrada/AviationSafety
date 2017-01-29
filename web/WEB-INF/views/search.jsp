<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"><i class="fa fa-database"></i>Aviation Events</h3>
            <ol class="breadcrumb">
                <li><i class="fa fa-home"></i><a href="/index.jsp">Home</a></li>
                <li><i class="fa fa-search"></i>Events</li>
            </ol>
        </div>
    </div>
    <!-- Inline form-->
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">Filters</header>
                <div class="panel-body">
                    <form class="form-inline" role="form" action="search.jsp" method="GET">
                        <div class="form-group">
                            <label class="sr-only" for="country">Country</label>
                            <select class="form-control" name="country" id="country">
                                <option value="">Country</option>
                                <c:forEach var="countryItem" items="${countryList}">
                                    <option value="${countryItem.key}"<c:if test="${selectedCountryCode == countryItem.key}"> selected</c:if>>${countryItem.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="country">Injury severity</label>
                            <select class="form-control" name="injury_severity" id="injury_severity">
                                <option value="">Injury severity</option>
                                <c:forEach var="injuryItem" items="${injuryList}">
                                    <option value="${injuryItem.key}"<c:if test="${selectedInjurySeverity == injuryItem.key}"> selected</c:if>>${injuryItem.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
            </section>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <section class="panel">
                <header class="panel-heading">Events</header>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Type</th>
                        <th>Injury Severity</th>
                        <th>Aircrafts involved</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="eventItem" items="${eventList}">
                        <tr>
                            <td><a href="/aviationevent.jsp?id=${eventItem.key}">${eventItem.key}</a></td>
                            <td>${eventItem.value.date}</td>
                            <td>${eventItem.value.finLocationName}</td>
                            <td>${eventItem.value.eventType}</td>
                            <td>${eventItem.value.injurySeverityName}</td>
                            <td>${eventItem.value.caseCount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </section>
        </div>
    </div>
</t:layout>