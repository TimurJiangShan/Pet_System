<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>PetGo - Somethings Went Wrong</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="container">
  <br>
  <br>
  <div class="panel panel-default">
    <div class="panel-body">
      <h1>: (</h1>
      <h3>${errorCode}</h3>
      <p>${exception}</p>
    </div>
  </div>
</div>
</body>
</html>