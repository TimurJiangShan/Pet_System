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
      System
      <small>Setting</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> HomePage</a></li>
      <li><a href="/admin/system/edit">System</a></li>
      <li class="active">Setting</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">${systemConfig.description}</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="form">
          <%-- <input type="hidden" name="pid" id="pid" value="${systemConfig.systemConfigId}"> --%>
          <div class="form-group">
            <c:forEach items="${systemConfigs}" var="systemConfig" >
            
			<c:if test="${systemConfig.type == 'text'}">
			 	<label>${systemConfig.description}</label>
               	<input type="text" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
            <c:if test="${systemConfig.type == 'number'}">
			 	<label>${systemConfig.description}</label>
               	<input type="number" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
            <c:if test="${systemConfig.type == 'hidden'}">
               	<input type="hidden" name="${systemConfig.key}" id="${systemConfig.key}" value="${systemConfig.value}" class="form-control"/>
            	<br/>
            </c:if>
            
           <!--   normal radio -->
            <c:if test="${systemConfig.type == 'radio' && systemConfig.pid != 2}">
            	<label>${systemConfig.description}</label><br/>
            	<c:if test="${systemConfig.value == '1'}">
					<span style="padding-right: 5px;">Yes</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="1" checked="checked"/>
					<span style="padding-right: 5px;">No</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="0"/>
				</c:if>
               	<c:if test="${systemConfig.value == '0'}">
               		<span style="padding-right: 5px;">Yes</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="1"/>
					<span style="padding-right: 5px;">No</span><input type="radio" name="${systemConfig.key}" id="${systemConfig.key}" value="0" checked="checked"/>
				</c:if>
            </c:if>
            <!--  normal radio -->
            
            <!-- radio for upload -->
			<c:if test="${systemConfig.type == 'radio' && systemConfig.pid == 2}">
				<c:if test="${systemConfig.value == '1'}">
					<span style="font-weight: 700;padding-right: 5px;">${systemConfig.description}</span><input type="radio" name="upload-type" id="${systemConfig.key}" value="${systemConfig.systemConfigId}" checked="checked"/>
				</c:if>
               	<c:if test="${systemConfig.value == '0'}">
					<span style="font-weight: 700;padding-right: 5px;">${systemConfig.description}</span><input type="radio" name="upload-type" id="${systemConfig.key}" value="${systemConfig.systemConfigId}"/>
				</c:if>
            </c:if>
            <!--radio for upload-->
            
			</c:forEach>
          </div>
          <button type="submit" class="btn btn-warning">保存</button>
        </form>
      </div>
    </div>
  </section>
<%--    Jquery--%>
  <script type="text/javascript">
  $(function() {
	  $(".system-menu").addClass("active");
	  $(".system-treeview-menu li:eq(${index})").addClass("active");

	  // Get the clicked radio button
	  var uploadValue = $("input[name='upload-type']:checked").attr("value")
	  //console.log(uploadValue);
	  // Get upload configuration
	  if (uploadValue) {
	    $.ajax({
	      url: '/admin/system/upload/list',
	      async: true,
	      cache: false,
	      type: 'get',
	      dataType: 'json',
	      data: {
	        pid: uploadValue
	      },
	      success: function(data) {
	        if (data.success === true) {
	          //console.log(data.data);
	          $(".form-group").append('<div class="upload-input" style="margin-top: 20px;"></>');
	          for (var i = 0; i < data.data.length; i++) {
	            $(".upload-input").append('<label>' + data.data[i].description + '</label><input type="text" name="' + data.data[i].key + '" id="' + data.data[i].key + '" value="' + data.data[i].value + '" class="form-control"/><br/>');
	          }
	        } else {
	          toast(data.error, 'error');
	        }
	      }
	    })
	  }

	  // Switch upload configuration
	  $("input[name=upload-type]").each(function() {
	    $(this).click(function() {
	      var id = $(this).val();
	      if (id) {
	    	$("#upload_type").val(id);
	        $.ajax({
	          url: '/admin/system/upload/list',
	          async: true,
	          cache: false,
	          type: 'get',
	          dataType: 'json',
	          data: {
	            pid: id
	          },
	          success: function(data) {
	            if (data.success === true) {
	              $(".upload-input").html('');
	              for (var i = 0; i < data.data.length; i++) {
	                $(".upload-input").append('<label>' + data.data[i].description + '</label><input type="text" name="' + data.data[i].key + '" id="' + data.data[i].key + '" value="' + data.data[i].value + '" class="form-control"/><br/>');
	              }
	            } else {
	              toast(data.error, 'error');
	            }
	          }
	        })
	      }

	    });
	  });

	  // Update configuration
	  $("#form").submit(function() {
	    $.ajax({
	      url: '/admin/system/edit',
	      contentType:"application/json; charset=utf-8",
	      type: 'post',
	      dataType: 'json',
	      data: JSON.stringify($("#form").serializeArray()),
	      success: function(data) {
	        if (data.success === true) {
	          toast('Edit Success', 'success');
	          setTimeout(function() {
	        	  window.location.reload();
	          },1000);
	          console.log(data);
	        } else {
	          toast(data.error, 'error');
	        }
	      }
	    })
	    return false;
	  })
	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>