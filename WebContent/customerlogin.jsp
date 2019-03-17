<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Customer Login</title>
  <link rel="stylesheet" href="styles.css">
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="login.css">
</head>
 
<body>
<h1><center>Login for Customer</center></h1>
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6" style="margin-top: 20%">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <form action="LoginServlet" method="post" class="form-signin">
              <div class="form-label-group">
                <input type="text" id="customerId" name="email" class="form-control" placeholder="customer id" required autofocus>
                <label for="inputEmail">Customer Id</label>
              </div>

              <div class="form-label-group">
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                <label for="inputPassword">Password</label>
              </div>

              <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Remember password</label>
              </div>
               <input type="hidden" name="empLogin" value="customerLogin">
              <button class="btn btn-lg btn-danger btn-block text-uppercase" type="submit">Sign in</button>
             </form>
             <a style="color:white" href="registercustomer.jsp">Register for New Customer</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>