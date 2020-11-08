<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Pet-go Login</title>
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
            <a href="/">Main</a> / Login
          </div>
          <c:if test="${message != null}">
          <div class="message">${message}</div>
          </c:if>
          <div class="panel-body">
            <form role="form" id="form">
              <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="username">
              </div>
              <div class="form-group">
                <label for="password">password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="password">
              </div>
              <button type="button" id="btn" class="btn btn-warning">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
 </div>
</div>
  <jsp:include page="components/foot.jsp"></jsp:include>
  <!-- jQuery -->
  <script src="/resources/js/jquery.js"></script>
  <!-- Bootstrap -->
  <script src="/resources/js/bootstrap.min.js"></script>
  <script src="/resources/js/login.js"></script>
</body>
</html>