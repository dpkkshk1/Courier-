<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tracking</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
#divclr
{
background: linear-gradient(to top left, #ccffff 0%, #3399ff 90%);
}
#bodyclr
{
 background-color: #99e6ff;
}
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background: -webkit-linear-gradient(left, #3931af, #00c6ff);
    color: white;
}
</style>
</head>
<body>
 <%@include file="head.jsp"%>
<div class="container" style="margin-top:15%">
<div class="row">
<div class="col-md-offset-3 col-md-6 divclr" >
<table id="customers">
 <thead>
      <tr>
      <th>Consignment Id</th>
        <th>City Name</th>
        <th>Current Date</th>
        <th>Details</th>
         </tr>
    </thead>
     <tbody>
<c:forEach items="${ trackList }" var="list">
   <tr>
        <td>${ list.consignment.consignmentId }</td>
        
        <td>${ list.city.cityName }</td>
        <td>${ list.currentDate }</td>
        <td>Reached</td>
        

</c:forEach>
</tbody>
</table>
</div>
</div>
</div>
<a href="./customerconsignments.jsp">Return to Customer HomePage</a>
</body>
</html>