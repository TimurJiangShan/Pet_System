<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- Main content area -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
     User
      <small>List</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/admin_user/list">User</a></li>
      <li class="active">List</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">User List</h3>
          <a href="/admin/admin_user/add" class="btn btn-xs btn-primary">Add</a>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>Username</th>
            <th>Registeration time</th>
            <th>Operation</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="adminUser" items="${page.list}">
            <tr>
              <td>${adminUser.adminUserId}</td>
              <td>${adminUser.username}</td>
              <td><fmt:formatDate type="both" value="${adminUser.createDate}" /></td>
              <td>
                  <a href="/admin/admin_user/edit?id=${adminUser.adminUserId}" class="btn btn-xs btn-warning">Edit</a>
                  <a href="javascript:if(confirm('Are you sure to delete？')) location.href='/admin/admin_user/delete?id=${adminUser.adminUserId}'" class="btn btn-xs btn-danger">Delete</a>
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
  		$(".sidebar-menu li:eq(6)").addClass("active");
  		$(".treeview-menu li:eq(0)").addClass("active");
  	   
  	  	var count = ${page.totalRow};//Total amount of data
  	 	var limit = ${page.pageSize};//Number of items displayed per page
  	 	var p = ${p};//current page number
  	 	var url = "?p=";//path
  	 	paginate(count,limit,p,url);
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>