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
      Post
      <small>Edit</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> Index</a></li>
      <li><a href="/admin/topic/list">Post</a></li>
      <li class="active">Edit</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Post Edit</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <form id="from">
          <div class="form-group" style="margin-bottom: 10px;">
          	<div class="form-group">
            	<label for="title">Title</label>
            	<input type="text" name="title" id="title" value="${topic.title}" class="form-control" placeholder="Title"/>
          	</div>
          	<div class="form-group">
            	<label for="content">Content</label>
            	<div id="editor" style="margin-bottom: 10px;"></div>
          	</div>
            <c:if test="${fn:length(nodes) > 0}">
         	<div class="form-group">
          		<label for="node">Node</label>
          		<select id="node" class="form-control" name="node">
          			<c:forEach var="item" items="${nodes}" varStatus="status">
                 		<c:choose>
                			<c:when test="${topic.nodeTitle == item.nodeTitle}">
                				<option value="${item.nodeTitle}" selected = "selected">${item.nodeTitle}</option>
                			</c:when>
                			<c:otherwise>
                				<option value="${item.nodeTitle}">${item.nodeTitle}</option>
                			</c:otherwise>
                		</c:choose>
             		</c:forEach>
          		</select>
        	</div>
        	</c:if>
            <button type="submit" class="btn btn-warning btn-sm">Update posts</button>
          </div>
        </form>
      </div>
    </div>
  </section>
  <script src="/resources/wangEditor/wangEditor.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(2)").addClass("active");
  		var E = window.wangEditor;
  	    var editor = new E('#editor');
  	    editor.customConfig.uploadFileName = 'file';
  	    editor.customConfig.uploadImgServer = '/common/wangEditorUpload';
  	    editor.customConfig.menus = [
  	    	  'head',  // 标题
  	    	  'bold',  // 粗体
  	    	  'italic',  // 斜体
  	    	  'underline',  // 下划线
  	    	  'strikeThrough',  // 删除线
  	    	  'link',  // 插入链接
  	    	  'list',  // 列表
  	    	  'quote',  // 引用
  	    	  'emoticon',  // 表情
  	    	  'image',  // 插入图片
  	    	  'table',  // 表格
  	    	  'code',  // 插入代码
  	    	  'undo',  // 撤销
  	    	  'redo'  // 重复
  	        ];
  	    editor.create();
  	    editor.txt.html('${fn:replace(topic.content,vEnter,'')}');
  	  $("#from").submit(function() {
  		if (confirm("Edit confirm？")) {
  			var title = $("#title").val();
  			var contentHtml = editor.txt.html();
  			var nodeTitle = $("#node option:selected").val();
  			if (!title || title.length > 120) {
  				alert('Please type title, words < 120');
  				return false;
  			} else if (!nodeTitle) {
  				alert('Please choose a node');
  				return false;
  			} else {
  				$.ajax({
  					url: '/admin/topic/edit',
  					type: 'post',
  					async: false,
  					cache: false,
  					dataType: 'json',
  					data: {
  						id: ${topic.topicId},
  						title: title,
  						content: contentHtml,
  						nodeTitle: nodeTitle
  					},
  					success: function(data) {
  						if (data.success != null && data.success == true) {
  							toast(data.error, "success");
  							setTimeout(function() {
  								window.location.href = "/admin/topic/list";
  							},
  							700);
  						} else {
  							toast(data.error);
  						}
  					},
  					error: function(data) {
  						toast(data.error);
  					}
  				})
  			}
  		}
  		return false;	
  	});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>