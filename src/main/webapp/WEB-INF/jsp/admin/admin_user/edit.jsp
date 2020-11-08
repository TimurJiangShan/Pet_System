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
      <small>Edit</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/admin_user/list">User</a></li>
      <li class="active">Edit</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">User Edit</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-6">
            <form id="form" action="/admin/admin_user/edit" method="post">
              <input type="hidden" name="id" value="${adminUser.adminUserId}">
              <div class="form-group">
                <label>Username</label>
                <input type="text" id="username" name="username" value="${adminUser.username}" class="form-control" placeholder="username" disabled>
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="If the password is empty, it cannot be edited">
              </div>
              <div class="form-group">
                <label>Avatar</label>
                <p>
          			<button type="button" class="btn btn-warning" id="choiceAvatarBtn">Choose Avatar</button>
         			<button type="button" class="btn btn-success" id="confirmAvatarBtn">Confirm Avatar</button>
          			<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          			<input type="hidden" value="${adminUser.avatar}" name="adminUserAvatar" id="adminUserAvatar">
        		</p>
        		<div class="row">
          			<div class="col-md-9" id="adjustment">
            			<img src="${adminUser.avatar}" id="newAvatarImg" class="origin-avatar" alt="">
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
  		
  		// 被编辑用户ID
  		var adminUserId = ${adminUser.adminUserId}
  		console.log(adminUserId);

  		
  		$("#form").submit(function() {
  	      // var username = $("#username").val();
  	      var password = $("#password").val();
  	      var avatar = $("#adminUserAvatar").val();
  	      $.ajax({
  	        url: '/admin/admin_user/edit',
  	        async: true,
  	        cache: false,
  	        type: 'post',
  	        dataType: 'json',
  	      	traditional:true, //防止深度序列化,默认false
  	        data: {
  	          id: adminUserId,
  	          password: password,
  	          avatar: avatar
  	        },
  	        success: function(data) {
  	          if(data.success === true) {
  	            // 如果修改的是当前登录用户，则强制重新登录
  	            if(data.data.logout){
  	            	alert('Edit success, please relogin');
  	            	window.location.href = '/admin/logout';
  	            }else{
  	            	toast('Edit success','success');
  	            	setTimeout(function() {
  	  	              window.location.href = '/admin/admin_user/list';
  	  	            }, 1000);
  	            }
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