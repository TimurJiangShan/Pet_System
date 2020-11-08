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
      Reply
      <small>Reply List</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/reply/list">Reply</a></li>
      <li class="active">List</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Reply List</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/reply/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" readonly id="startDate" name="startDate" value="${startDate}"
                   class="form-control" placeholder="Start time">
            <input type="text" readonly id="endDate" name="endDate" value="${endDate}"
                   class="form-control" placeholder="End time">
            <input type="text" name="author" value="${author}" class="form-control" placeholder="username">
            <input type="text" name="topic" value="${topic}" class="form-control" placeholder="Topic">
            <button type="submit" class="btn btn-warning btn-sm">Search</button>
          </div>
        </form>
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>#</th>
            <th>Reply</th>
            <th>Topic</th>
            <th>User</th>
            <th>Time</th>
            <th>Operation</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="reply" items="${page.list}">
            <tr>
              <td>${reply.reply_id}</td>
              <td>${reply.reply_content}</td>
              <td><a href="/topic/${reply.topic_id}" target="_blank">${reply.title}</a></td>
              <td><a href="/user/${reply.reply_author_name}" target="_blank">${reply.reply_author_name}</a></td>
              <td><fmt:formatDate type="both" value="${reply.create_date}" /></td>
              <td>
                  	<a href="/admin/reply/edit?id=${reply.reply_id}" class="btn btn-xs btn-warning">Edit</a>
                  	<button onclick="actionBtn('${reply.reply_id}', 'delete', this)" class="btn btn-xs btn-danger">Delete</button>
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
  		$(".sidebar-menu li:eq(3)").addClass("active");
  		$("#startDate").datepicker({
  	      autoclose: true,
  	      format: 'yyyy-mm-dd',
  	      todayBtn: true,
  	      todayHighlight: true,
  	    });
  	    $("#endDate").datepicker({
  	      autoclose: true,
  	      format: 'yyyy-mm-dd',
  	      todayBtn: true,
  	      todayHighlight: true,
  	    });
  	   
  	    var startDate = '${startDate}';
  	    var endDate = '${endDate}';
  	    var author = '${author}';
  	  	var topic = '${topic}';
  	  	var count = ${page.totalRow};//Total amount of data
  	 	var limit = ${page.pageSize};//Number of items displayed per page
  	 	var url = "?startDate="+startDate+"&endDate="+endDate+"&author="+author+"&topic="+topic+"&p=";//url
  	 	var p = ${p};//current page number
  	 	paginate(count,limit,p,url);
  	});
  	
  	function actionBtn(id, action, self){
  		var msg,url;
  		var tip = $(self).text().replace(/[\r\n]/g, '').trim();
  		if(action === 'delete'){
  			url = '/admin/reply/delete?id=' + id;
  	    	msg = 'Are you sure '+tip+'this reply？';
  		}
  		if(confirm(msg)){
  			$.get(url,function(data){
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