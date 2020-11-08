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
     Node
      <small>List</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/node/list">Node</a></li>
      <li class="active">List</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Node List</h3>
          <a href="/admin/node/add" class="btn btn-xs btn-primary">Add</a>
        </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form action="/admin/node/list" method="get" class="form-inline">
          <div class="form-group" style="margin-bottom: 10px;">
            <input type="text" name="nodeTitle" value="${nodeTitle}" class="form-control" placeholder="Node Name">
            <button type="submit" class="btn btn-warning btn-sm">Search</button>
          </div>
        </form>
        <table class="table table-bordered table-hover">
          <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Icon</th>
            <th>Background Image</th>
            <th>Number of Topics</th>
            <th>Description</th>
            <th>Create Time</th>
            <th>Order</th>
            <th>Add to the homepage</th>
            <th>Add to the naviBar</th>
            <th>Operation</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="node" items="${page.list}">
            <tr>
              <td>${node.nodeId}</td>
              <td>${node.nodeTitle}</td>
              <td>
              <c:if test="${node.avatarNormal != null}">
              	<a href="${node.avatarNormal}" target="_blank"><img src="${node.avatarNormal}" width="30" alt=""></a>
              </c:if>
              </td>
              <td>
              <c:if test="${node.avatarLarge != null}">
              	<a href="${node.avatarLarge}" target="_blank"><img src="${node.avatarLarge}" width="30" alt=""></a>
              </c:if>
              </td>
              <td>${node.countTopic}</td>
              <td>${node.nodeDesc}</td>
              <td><fmt:formatDate type="both" value="${node.createDate}" /></td>
              <td>${node.sort}</td>
              <c:choose>
                	<c:when test="${node.addIndex == true}">
                		<td><input name="addIndexStatus" type="checkbox" value="${node.nodeId}" checked="checked"></td>
                	</c:when>
                	<c:otherwise>
                		<td><input name="addIndexStatus" type="checkbox" value="${node.nodeId}"></td>
                	</c:otherwise>
              </c:choose>
              <c:choose>
                	<c:when test="${node.addNav == true}">
                		<td><input name="addNavStatus" type="checkbox" value="${node.nodeId}" checked="checked"></td>
                	</c:when>
                	<c:otherwise>
                		<td><input name="addNavStatus" type="checkbox" value="${node.nodeId}"></td>
                	</c:otherwise>
              </c:choose>
              <td>
                  	<a href="/admin/node/edit?id=${node.nodeId}" class="btn btn-xs btn-warning">Edit</a>
                  	<button onclick="actionBtn('${node.nodeId}')" class="btn btn-xs btn-danger">Delete</button>
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
  		$(".sidebar-menu li:eq(4)").addClass("active");
  	  	var nodeTitle = '${nodeTitle}';
  	  	var count = ${page.totalRow};//Total data - paging
  	 	var limit = ${page.pageSize};//Number of items displayed per page
  	 	var url = "?nodeTitle="+nodeTitle+"&p=";//url
  	 	var p = ${p};//current page number
  	 	paginate(count,limit,p,url);
  	 	
  		// Initialize button
  	 	$('[name="addIndexStatus"]').bootstrapSwitch({
  	       onColor:"success",
  	       offColor:"danger",
  	       onSwitchChange:function(event,status){
  	    	 var id = $(this).val();
 	    	   $.get("/admin/node/add/index?id=" + id + "&status=" + status, function(data){
				if(data.success === true){
					toast(data.error, "success");
				}else{
					toast(data.error);
				}
			})
  	      }
  	    });
  		
  	 	$('[name="addNavStatus"]').bootstrapSwitch({
  	 	 	onColor:"success",
	        offColor:"danger",
  	 		onSwitchChange:function(event, status){
   	    	   var id = $(this).val();
   	    	   $.get("/admin/node/add/nav?id=" + id + "&status=" + status, function(data){
  				if(data.success === true){
  					toast(data.error, "success");
  				}else{
  					toast(data.error);
  				}
  			})
   	      }
   	    });
  	});
  	
  	// Delete node
  	function actionBtn(id){
  		if(confirm("Are you sure to delte this node？Related things can be deleted too")){
  			if(!id){
  				toast("Node Id cannot be empty", "error");
  			}
  			$.post("/admin/node/delete",{id: id},function(data){
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