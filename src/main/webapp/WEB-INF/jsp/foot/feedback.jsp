<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Q?-PetGo</title>
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
						<a href="/">HomePage</a> / Q
					</div>
					<div class="panel-body" style="color: #333;">
					<h5>Thanks</h5>
					<form id="feedback_add" enctype="multipart/form-data" method="post"
						action="/feedback/add">
						<div class="form-group">
							<textarea name="info" id="info" class="form-control" rows="3"></textarea>
						</div>
						<button type="button" class="btn btn-warning" id="feedback_add_btn">Submit</button>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/feedback.js"></script>
</body>
</html>