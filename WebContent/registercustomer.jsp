<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.courier.dao.StateDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<title>Customer Register</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
.register{
    background: -webkit-linear-gradient(left, #3931af, #00c6ff);
    margin-top: 3%;
    padding: 3%;
}
.register-left{
    text-align: center;
    color: #fff;
    margin-top: 4%;
}
.register-left input{
    border: none;
    border-radius: 1.5rem;
    padding: 2%;
    width: 60%;
    background: #f8f9fa;
    font-weight: bold;
    color: #383d41;
    margin-top: 30%;
    margin-bottom: 3%;
    cursor: pointer;
}
.register-right{
    background: #f8f9fa;
    border-top-left-radius: 10% 50%;
    border-bottom-left-radius: 10% 50%;
}
.register-left img{
    margin-top: 15%;
    margin-bottom: 5%;
    width: 25%;
    -webkit-animation: mover 2s infinite  alternate;
    animation: mover 1s infinite  alternate;
}
@-webkit-keyframes mover {
    0% { transform: translateY(0); }
    100% { transform: translateY(-20px); }
}
@keyframes mover {
    0% { transform: translateY(0); }
    100% { transform: translateY(-20px); }
}
.register-left p{
    font-weight: lighter;
    padding: 12%;
    margin-top: -9%;
}
.register .register-form{
    padding: 10%;
    margin-top: 10%;
}
.btnRegister{
    float: right;
    margin-top: 10%;
    border: none;
    border-radius: 1.5rem;
    padding: 2%;
    background: #0062cc;
    color: #fff;
    font-weight: 600;
    width: 50%;
    cursor: pointer;
}
.register .nav-tabs{
    margin-top: 3%;
    border: none;
    background: #0062cc;
    border-radius: 1.5rem;
    width: 28%;
    float: right;
}
.register .nav-tabs .nav-link{
    padding: 2%;
    height: 34px;
    font-weight: 600;
    color: #fff;
    border-top-right-radius: 1.5rem;
    border-bottom-right-radius: 1.5rem;
}
.register .nav-tabs .nav-link:hover{
    border: none;
}
.register .nav-tabs .nav-link.active{
    width: 100px;
    color: #0062cc;
    border: 2px solid #0062cc;
    border-top-left-radius: 1.5rem;
    border-bottom-left-radius: 1.5rem;
}
.register-heading{
    text-align: center;
    margin-top: 8%;
    margin-bottom: -15%;
    color: #495057;
}
</style>

</head>
<body>

	<h1>
		<center>Funny Courier Services</center>
	</h1>
	<form action="./CustomerRegistrationServlet" method="post" >
		<div class="container register">
			<div class="row">
				<div class="col-md-3 register-left">
					<img src="https://rpelm.com/images/clipart-door-ship-17.png" alt="" />
					<h3>Welcome</h3>
					<p>You are 30 seconds away from registering yourself!</p>

				</div>
				<div class="col-md-9 register-right">

					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<h3 class="register-heading">Register as a Regular Customer</h3>
							<div class="row register-form">


								<div class="col-md-6">
									<div class="form-group">
										<input type="text" name="custName" class="form-control"
											placeholder="Enter Your Name *" value="" />
									</div>
									<div class="form-group">
										<input type="password" name="custPassword"
											class="form-control" placeholder="Enter Password *" value="" />
									</div>

									<div class="form-group">
										<input type="text" minlength="10" maxlength="10"
											name="empPhone" class="form-control"
											placeholder="Your Phone *" value="" />
									</div>

									<div class="form-group">
										<input type="mail" name="email" class="form-control"
											placeholder="Enter Your Mail *" value="" />
									</div>

									<div class="form-group">
										<input type="text" name="street" class="form-control"
											placeholder="Enter Street *" value="" />
									</div>
									<jsp:useBean id="stateList" scope="page"
										class="com.courier.dao.StateDAO">
									</jsp:useBean>
									<div class="form-group">
										<label for="sel1">Select State:</label> <select
											class="form-control" id="countrySelect" name="state">

											<c:forEach items="${stateList.getState() }" var="list">
												<option value="${ list.stateId }">${ list.stateName }</option>

											</c:forEach>
										</select>
									</div>
									<script type="text/javascript">
										$(document)
												.ready(
														function() {

															$('#countrySelect')
																	.on(
																			'change',
																			function() {
																				var stateID = $(
																						this)
																						.val();
																				if (stateID) {
																					$
																							.ajax({
																								type : 'POST',
																								url : './AjaxState',
																								data : 'stateId='
																										+ stateID,
																								success : function(
																										html) {
																									$(
																											'#city')
																											.html(
																													html);
																								}
																							});
																				} else {
																					$(
																							'#city')
																							.html(
																									'<option value="">Select state first</option>');
																				}
																			});
														});
									</script>
									<span id="city"></span>
									

									<div class="form-group">
										<input type="text" name="pincode" class="form-control"
											placeholder="Enter Pincode *" value="" />
									</div>

									<div class="form-group">
										<input type="submit" class="btnRegister" value="Register" />

									</div>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>