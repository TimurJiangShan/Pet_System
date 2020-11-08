<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Change Password</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- into Bootstrap -->
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
        <a href="/user/settings/profile" class="list-group-item ">Personal Settings</a>
        <a href="/user/settings/changeAvatar" class="list-group-item">Change Avatar</a>
        <a href="/user/settings/changePassword" class="list-group-item active">Change Password</a>
      </div>
    </div>
  </div>

  <div class="col-md-9">
    <div class="panel panel-default">
      <div class="panel-heading">
        <a href="/">Home Page</a> / Change Password
      </div>
      <div class="panel-body">
        <form id="form">
          <div class="form-group">
            <label for="oldPassword">Previous Password</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword">
          </div>
          <div class="form-group">
            <label for="newPassword">New Password</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword">
          </div>
          <div class="form-group">
            <label for="newPassword2">Plz type new password again</label>
            <input type="password" class="form-control" id="newPassword2" name="newPassword2">
          </div>
          <button type="submit" class="btn btn-warning">Change Password</button>
          <span class="text-danger"></span>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $("#form").submit(function() {
      var oldPassword = $("#oldPassword").val();
      var newPassword = $("#newPassword").val();
      var newPassword2 = $("#newPassword2").val();
      if(!oldPassword) {
        alert('Plz enter previous password');
        return false;
      }
      if(!newPassword) {
        alert('Plz enter new password');
        return false;
      }
      if (newPassword.length < 6) {
        alert('The length of the password cannot be less than 6 digits');
        return false;
      }
      if(newPassword != newPassword2){
        alert('2 passwords are inconsistent');
        return false;
      }
      $.ajax({
        url: '/user/setting/changePassword',
        type: 'post',
        async: true,
        cache: false,
        dataType: 'json',
        data: {
          oldPassword: oldPassword,
          newPassword: newPassword
        },
        success: function(data) {
          if(data.success != null && data.success == true) {
            alert('Change success! Plz log in again.');
            window.location.href = '/logout';
            /* setTimeout(function() {
              window.location.href = '/logout';
            }, 2000); */
          } else {
            alert(data.error);
          }
        }
      });
      return false;
    })
  })
</script>
</div>
</div>
<jsp:include page="../components/foot.jsp"></jsp:include>
<script type="text/javascript">
  $(function(){
    $("#shezhili").addClass("active");
  });
</script>
</body></html>
