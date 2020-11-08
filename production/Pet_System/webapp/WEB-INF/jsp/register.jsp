<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Pet-go register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--  Bootstrap -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="/">main</a> / register
					</div>
					<div class="panel-body">
						<form role="form" id="form" method="post">
							<div class="form-group">
								<label for="username">username</label> <input type="text"
									class="form-control" id="username" name="username"
									placeholder="User name can only be a combination of 2-16 digits a-z,A-Z,0-9">
							</div>
							<div class="form-group">
								<label for="password">password</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="password">
							</div>
							<div class="form-group">
								<label for="email">emial</label> <input type="email"
									class="form-control" id="email" name="email" placeholder="email">
							</div>
							<div class="form-group" style="display: none">
								<label for="email">type</label>
								<br>
								<span style="padding-right: 5px;">user</span>
								<input type="radio" name="userType" value="1" checked="checked">
								<span style="padding-right: 5px;">admin</span>
								<input type="radio" name="userType" value="2">
							</div>
							<button type="button" class="btn btn-warning" id="reg_btn">register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
  </div>
</div>
	<jsp:include page="components/foot.jsp"></jsp:include>
	<!-- jQuery  -->
	<script src="/resources/js/jquery.js"></script>
	<!-- Bootstrap -->
	<script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/js/register.js"></script>
</body>
</html>