<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>modify profile </title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- introduce Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
<div class="wrapper">
<jsp:include page="../components/head.jsp"></jsp:include>
  <div class="col-md-3 hidden-sm hidden-xs">
<div class="panel panel-default">
  <div class="list-group">
    <a href="/user/settings/profile" class="list-group-item active">Personal setting</a>
    <a href="/user/settings/changeAvatar" class="list-group-item ">Change avatar</a>
    <a href="/user/settings/changePassword" class="list-group-item ">Change password</a>
  </div>
</div>
  </div>

  <div class="col-md-9">
    <div class="panel panel-default">
      <div class="panel-heading">
        <a href="/">home page</a> / Personal settings
      </div>
      <div class="panel-body">
        <form action="/user/setting/profile" method="post" id="userProfileForm" enctype="multipart/form-data">
          <div class="form-group">
            <label for="username">nickname</label>
            <input type="text" disabled="" class="form-control" id="username" value="${user.userName}">
          </div>
          <div class="form-group">
            <label for="email">mailbox</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}">
          </div>
          <div class="form-group">
            <label for="url">Personal website</label>
            <input type="text" class="form-control" id="url" name="url" value="${user.url}">
          </div>
          <div class="form-group">
            <label for="thirdId">github</label>
            <input type="text" class="form-control" id="thirdId" name="thirdId" value="${user.thirdId}">
          </div>
          <div class="form-group">
            <label for="userAddr">address</label>
            <input type="text" class="form-control" id="userAddr" name="userAddr" value="${user.userAddr}">
          </div>
          <div class="form-group">
            <label for="signature">Signature</label>
            <textarea class="form-control" name="signature" id="signature">${user.signature}</textarea>
          </div>
            <button type="button" id="userProfileUpdateBtn" onclick="updateUserProfile()" class="btn btn-warning">Save Setting
            </button>
          <span id="error_message"></span>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  function updateUserProfile() {
    var errors = 0;
    var em = $("#error_message");
    var signature = $("#signature").val();
    var email = $("#email").val();
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (signature.length > 20) {
      errors++;
      em.html("The individual signature cannot exceed 20 characters");
    }
    if(!myreg.test(email)){
    	errors++;
    	em.html("E-mail format is incorrect");
    }
    if (errors === 0) {
      var form = $("#userProfileForm");
      form.submit();
    }
  }
</script>
  </div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
</body>
</html>