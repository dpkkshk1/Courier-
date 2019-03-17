<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.courier.dao.ConsignmentDAO"%>
<%@page import="com.courier.dao.CityDAO"%>
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
</head>
<body>
 <%@include file="head.jsp"%>
<div class="container">
<div class="row">
<jsp:useBean id="trackupdate" scope="page"
			class="com.courier.dao.ConsignmentDAO">
		</jsp:useBean>
		
		<c:set var="custid" scope="session" value="${ customerId }"/>
	
				<c:set var="consignmentList" scope="session"
			value="${ trackupdate.listConsignment(custid) }" />
			
<div class="col-md-offset-3 col-md-6">
 <table class="table table-hover">
 <thead>
      <tr>
      <th>Consignment Id</th>
        <th>From Name</th>
        <th>To City</th>
        <th>Track</th>
         </tr>
    </thead>
     <tbody>
<c:forEach items="${ consignmentList }" var="list">
          <tr>
        <td>${ list.consignmentId }</td>
        <td>${ list.addressFrom.city.cityName }</td>
        <td>${ list.addressTo.city.cityName }</td>
        <td>
<a href="./ShowDataServlet?consignmentId=${ list.consignmentId }"><button type="button" class="btn btn-success">Track</button></a>
</td>
	
</c:forEach>
</tbody>
</table>
</div>

</div>

</div>
</body>
</html>