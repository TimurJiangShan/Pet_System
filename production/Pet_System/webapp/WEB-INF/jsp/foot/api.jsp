<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>FPI-PetGo</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="/">HomePage</a> / API
					</div>
					<div class="panel-body" style="color: #333;">
						<h2>User's topic</h2>
						<p>
							<strong>Request URL：</strong>
						</p>
<%--						<ul>--%>
<%--							<li><code style="color: rgb(221, 17, 68);">https://www.petgo.au/api/user/topic</code></li>--%>
<%--						</ul>--%>
						<p>
							<strong>Request ways：</strong>
						</p>
						<ul>
							<li>GET</li>
						</ul>
						<p>
							<strong>Params：</strong>
						</p>
						<div style="width: 100%; overflow-x: auto;">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>Params</th>
										<th>Necessary</th>
										<th>Category</th>
										<th>Description</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>name</td>
										<td>no</td>
										<td>string</td>
										<td>Username</td>
									</tr>
									<tr>
										<td>p</td>
										<td>No</td>
										<td>string</td>
										<td>Pagination</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
</body>
</html>