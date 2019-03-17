<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.courier.dao.TrackUpdateDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exchange Points</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	border-color: color:black;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}

#back {
	background-color: #ffcc99;
}
</style>
</head>
<body>
<%@include file="head.jsp"%>
	<div class="container">
		<jsp:useBean id="trackupdate" scope="page"
			class="com.courier.dao.TrackUpdateDAO">
		</jsp:useBean>
		<c:set var="trackingList"
			value="${ trackupdate.fetchAllTrackUpdate() }" />
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Consignment Id</th>
							<th>City Name</th>
							<th>Date</th>
							<th>Status</th>
							<th>.........</th>
						</tr>
					</thead>

					<c:forEach items="${trackingList}" var="list">
						<tbody>
							<tr>
							<form action="./UpdateTrackServlet" method="post">
								<td>${ list.consignment.consignmentId }</td>
								<td>${ list.city.cityName }</td>
								<td><div class="form-group">
										<input type="date" class="form-control" id="email"
											value="${ list.currentDate }" name="dateUpdate">
									</div></td>
								<td>
									<div class="checkbox">

										<label><input type="checkbox" name="status"
											value="${ 1 }"
											<c:if test="${ list.status eq 1  }">checked="checked"</c:if>></label>
									</div></td>
									<input type="hidden" name="consignmentId" value="${ list.consignment.consignmentId }">
									<input type="hidden" name="cityId" value="${ list.city.cityId}">
									<td><button type="submit" class="btn btn-primary">update</button></td>
									</form>
							</tr>
						</tbody>

					</c:forEach>

				</table>
			</div>
		</div>

	</div>
</body>
</html>