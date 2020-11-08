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
      <small>List</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/user/list">User</a></li>
      <li class="active">List</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">User List</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/user/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" name="username" value="${username}" class="form-control" placeholder="username">
            <input type="text" name="email" value="${email}" class="form-control" placeholder="email">
            <button type="submit" class="btn btn-warning btn-sm">Search</button>
          </div>
        </form>
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>Username</th>
            <th>Email</th>
            <th>Points</th>
            <th>Time</th>
            <th>Disable</th>
            <th>Operation</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="user" items="${page.list}">
            <tr>
              <td>${user.userId}</td>
              <td><a href="/user/${user.userName}" target="_blank">${user.userName}</a></td>
              <td>${user.email}</td>
              <td>${user.score}</td>
              <td><fmt:formatDate type="both" value="${user.createDate}" /></td>
              <c:choose>
                	<c:when test="${user.isBlock == true}">
                		<td><input name="userBlock" type="checkbox" value="${user.userId}" checked="checked"></td>
                	</c:when>
                	<c:otherwise>
                		<td><input name="userBlock" type="checkbox" value="${user.userId}"></td>
                	</c:otherwise>
              </c:choose>
              <td>
                  	<a href="/admin/user/edit?id=${user.userId}" class="btn btn-xs btn-warning">Edit</a>
                  	<button onclick="actionBtn('${user.userId}')" class="btn btn-xs btn-danger">Delete</button>
              </td>
            </tr>
			</c:forEach>
          </tbody>
        </table>
      </div>
      <div class="panel-footer" id="paginate"></div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(5)").addClass("active");
  	    var username = '${username}';
  	    var email = '${email}';
  	    var p = '${p}';
  	  	var count = ${page.totalRow};
  	 	var limit = ${page.pageSize};
  	 	var url = "?username="+username+"&email="+email+"&p=";//url 	 	
  	 	paginate(count,limit,p,url);
  	 	
  	 	$('[name="userBlock"]').bootstrapSwitch({
  	       onColor:"success",
  	       offColor:"danger",
  	       onSwitchChange:function(event,status){
  	    	 var id = $(this).val();
 	    	   $.get("/admin/user/block?id=" + id + "&status=" + status, function(data){
				if(data.success === true){
					toast(data.error, "success");
				}else{
					toast(data.error);
				}
			})
  	      }
  	    });
  	});
  	
  	// 删除用户
  	function actionBtn(id){
  		if(confirm("Are you sure to delete this user？Also delete the related topcis and other things！")){
  			$.get("/admin/user/delete?id=" + id,function(data){
  				if(data.success === true){
  					toast(data.error, "success");
  					setTimeout(function(){
  						window.location.reload();
  					},700);
  				}else{
  					toast(data.error);
  				}
  			})
  		}
 	}
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>