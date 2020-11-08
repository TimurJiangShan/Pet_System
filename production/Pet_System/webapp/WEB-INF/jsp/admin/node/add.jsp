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
      <li class="active">Add</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Add Node</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form" action="/admin/tag/edit" method="post" enctype="multipart/form-data">
          <input type="hidden" value="${node.nodeId}" name="nodeId" class="node-id">
          <div class="form-group">
            <label>Name</label>
            <input type="text" name="name" value="${node.nodeTitle}" class="form-control node-name">
          </div>
          <div class="form-group">
            <label>Icon</label>
            <input type="hidden" value="${node.avatarNormal}" name="avatarNormal" class="avatarNormal">
            <input type="file" name="file" id="icon-upload"><br>
            <a href="${node.avatarNormal}" target="_blank" id="icon-href"><img src="${node.avatarNormal}" width="50" alt="" id="icon-img"></a>
          </div>
          <div class="form-group">
            <label>Background Image</label>
            <input type="hidden" value="${node.avatarLarge}" name="avatarLarge" class="avatarLarge">
            <input type="file" name="file" id="background-upload"><br>
            <a href="${node.avatarLarge}" target="_blank" id="background-href"><img src="${node.avatarLarge}" width="50" alt="" id="background-img"></a>
          </div>
          <div class="form-group">
            <label>Order by</label>
            <input type="text" name="sort" value="${node.sort}" class="form-control node-sort">
          </div>
          <div class="form-group">
            <label for="">Description</label>
            <textarea name="description" rows="7" class="form-control node-desc">${node.nodeDesc}</textarea>
          </div>
          <button type="submit" id="btn" class="btn btn-warning">Submit</button>
        </form>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(4)").addClass("active");
  		layui.use('upload', function () {
  	      var upload = layui.upload;
  	      // Upload icon
  	      upload.render({
	            elem: '#icon-upload', //Binding element
	            url: '/common/upload?customPath=node', //Upload interface
	            done: function (res) {
	            	//Request success callback
	                console.log(res)
	                console.log(res.data)
	                console.log(res.data[0])
	                $(".avatarNormal").val(res.data[0]);
	                $("#icon-href").attr("href",res.data[0]);
	                $("#icon-img").attr("src",res.data[0]);
	            }, 
	            error: function () {
	                //Request exception callback
	            }
	        });
  	      	// Upload background image
		    upload.render({
	          elem: '#background-upload', //Binding element
	          url: '/common/upload?customPath=node', //Upload interface
	          done: function (res) {
	          	  //Request success callback
	              console.log(res)
	              console.log(res.data)
	              console.log(res.data[0])
	              $(".avatarLarge").val(res.data[0]);
	              $("#background-href").attr("href",res.data[0]);
	              $("#background-img").attr("src",res.data[0]);
	          }, 
	          error: function () {
	              //Request exception callback
	          }
	      });
  	    });
  		$("#form").submit(function(){
  			// ID
  			// var nodeId = $(".node-id").val();
  			// name
  			var nodeTitle = $(".node-name").val();
  			// icon
  			var avatarNormal = $(".avatarNormal").val();
  			// Background image
  			var avatarLarge = $(".avatarLarge").val();
  			// description
  			var nodeDesc = $(".node-desc").val();
  			// Sort
  			var sort = $(".node-sort").val();
  			
  			var data = {nodeTitle, avatarNormal, avatarLarge, nodeDesc, sort};
  			if(confirm("Are your sure to add this node？")){
  				if(!nodeTitle){
  					toast('Node name cannot be empty');
  					return false;
  				}
  				console.log(data);
  				$.ajax({
  					url: "/admin/node/add",
  					type: "post",
  					dataType: "json",
  					data: data,
  					success: function(data){
  						if(data.success == true){
  							toast('Add Success','success');
  	  						setTimeout(function(){
  	  							window.location.href = "/admin/node/list";
  	  						},1000);
  						}else{
  							toast(data.error,'error');
  						}
  					},
  					error: function(data){
  						
  					}
  				});
  			}
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>