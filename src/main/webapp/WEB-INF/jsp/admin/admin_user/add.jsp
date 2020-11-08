<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
     User
      <small>Add</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/admin_user/list">User</a></li>
      <li class="active">Add</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Add User</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-6">
            <form id="form" action="/admin/admin_user/add" method="post">
              <div class="form-group">
                <label>Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="username">
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="password">
              </div>
              <div class="form-group">
                <label>Avatar</label>
                <p>
          			<button type="button" class="btn btn-warning" id="choiceAvatarBtn">Choose Avatar</button>
         			<button type="button" class="btn btn-success" id="confirmAvatarBtn">Confirm Avatar</button>
          			<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          			<input type="hidden" value="" name="adminUserAvatar" id="adminUserAvatar">
        		</p>
        		<div class="row">
          			<div class="col-md-9" id="adjustment">
            			<img src="" id="newAvatarImg" class="hidden origin-avatar" alt="">
          			</div>
          			<div class="col-md-3">
            			<div class="preview"></div>
          			</div>
        		</div>
              </div>
              <button type="submit" class="btn btn-warning">Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(0)").addClass("active");
  		$("#form").submit(function() {
  	      var username = $("#username").val();
  	      var password = $("#password").val();
  	      var avatar = $("#adminUserAvatar").val();
  	      if(!username) {
  	        toast('Username should not be empty');
  	        return false;
  	      }
  	      if(!password) {
  	        toast('Password should not be empty');
  	        return false;
  	      }
  	      
  	      console.log(username);
  	    console.log(password);
  	      $.ajax({
  	        url: '/admin/admin_user/add',
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	      	traditional:true, //防止深度序列化,默认false
  	        data: {
  	          username: username,
  	          password: password,
  	          avatar: avatar
  	        },
  	        success: function(data) {
  	          if(data.success === true) {
  	            toast('Add Success','success');
  	            setTimeout(function() {
  	              window.location.href = '/admin/admin_user/list';
  	            }, 1000);
  	          } else {
  	            toast(data.error,'error');
  	          }
  	        }
  	      })
  	      return false;
  	    })
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>