<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
   <%@page import="com.courier.dao.ConsignmentDAO"%>
      <%@page import="com.courier.model.Consignment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer HomePage</title>

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
<body id="bodyclr">
 <%@include file="head.jsp"%>
	<div  id="divclr">
		<jsp:useBean id="conslist" scope="page"
			class="com.courier.dao.ConsignmentDAO">
		</jsp:useBean>
		<c:set var="userid" value="${custid}"/>
	
		<c:set var="consignmentList" scope="session" value="${conslist.listConsignment(userid)}"/>
		<div class="row">
		<a href="./customerlogin.jsp">Logout</a>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<table  id="customers">
				<tr>
							<th>Consignment Id</th>
							<th>Accepted Date</th>
							<th>Pack Weight</th>
							<th>Cost</th>
						
							
							<th>select</th>
							</tr>
						
						
					<c:forEach items="${consignmentList}" var="list">
						<tbody>
							<tr>
							
								<td>${ list.consignmentId }</td>
								<td>${ list.accptedDate}</td>
								<td>${ list.packageWeight}</td>
								<td>${ list.cost}</td>
							
							<td>
<a href="./ShowDataServlet?consignmentId=${ list.consignmentId }"><button type="button" class="btn btn-success">Track</button></a>
</td>		
							</tr>
						</tbody>

				
					</c:forEach> 
						
				</table>
			</div>
		</div>

	</div>


</body>
</html>









<%-- 
					<c:forEach items="${trackingList}" var="list">
						<tbody>
							<tr>
							<form action="./UpdateTrackServlet" method="post">
								<td>${ list.consignment.consignmentId }</td>
								<td>${ list.city.cityId }</td>
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

					</c:forEach> --%>