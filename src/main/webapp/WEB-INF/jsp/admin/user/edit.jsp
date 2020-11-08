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
      <li class="active">Edit</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-warning">
      <div class="box-header with-border">
        <h3 class="box-title">Edit User</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
      	<div class="row">
      		<div class="col-sm-6">
      			<form id="form" onsubmit="return;">
          			<input type="hidden" value="${user.userId}" name="userId" id="userId" class="form-control">
          			<div class="form-group">
            			<label>Username</label>
            			<input type="text" name="userName" id ="userName" value="${user.userName}" class="form-control" readonly="readonly">
          			</div>
          			<div class="form-group">
            			<label>Password</label>
            			<input type="password" name="password" id ="password" value="" class="form-control" placeholder="Password cannot be empty">
        			</div>
          			<div class="form-group">
            			<label>Email</label>
            			<input type="email" name="email" id ="email" value="${user.email}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Points</label>
            			<input type="number" name="score" id ="score" value="${user.score}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Gender</label>
                		<select name="userSex" id="userSex" class="form-control">                 
             			<c:choose>
                			<c:when test="${user.userSex == 'Male'}">
                				<option value="Male" selected>Male</option>
                				<option value="Female">Female</option>
                			</c:when>
                			<c:when test="${user.userSex == 'Female'}">
                				<option value="Male">Male</option>
                				<option value="Female" selected>Female</option>
                			</c:when>
                			<c:otherwise>
                				<option value="Male">Male</option>
                				<option value="Female">Female</option>
                			</c:otherwise>
                		</c:choose>
          				</select>
          			</div>
          			<div class="form-group">
            			<label>Address</label>
            			<input type="text" name="userAddr" id ="userAddr" value="${user.userAddr}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Profile</label>
            			<input type="text" name="url" id ="url" value="${user.url}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Signature</label>
            			<input type="text" name="signature" id ="signature" value="${user.signature}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Github</label>
            			<input type="text" name="thirdId" id ="thirdId" value="${user.thirdId}" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>Token</label>
            			<div class="input-group">
            				<input type="text" name="thirdAccessToken" id ="thirdAccessToken" value="${user.thirdAccessToken}" class="form-control">
            				<span class="input-group-btn">
                  				<button type="button" onclick="refreshToken(this)" class="btn btn-info" autocomplete="off"
                          				data-loading-text="Reloading...">Reload Token</button>
                  				<script>
                    				function refreshToken(self) {
                    					// 当加载时，按钮是禁用的，且文本变为 button 元素的 data-loading-text 属性的值
                      					$(self).button("loading");
                      					$.get("/admin/user/refreshToken", function(data) {
                        				if (data.success === true) {
                          					toast("Reload Success", "success");
                         	 				$("#thirdAccessToken").val(data.error);
                        				} else {
                          					toast(data.error);
                        					}
                        				// 重置按钮状态，文本内容恢复为最初的内容
                        				$(self).button("reset");
                      					});
                    				}
                  				</script>
                			</span>
               			</div>
          			</div>
          			<div class="form-group">
            			<label>Create Time</label>
            			<input type="date" name="createDate" id ="createDate" value="<fmt:formatDate value='${user.createDate}' pattern='yyyy-MM-dd'/>" class="form-control">
          			</div>
          			<div class="form-group">
            			<label>New Message and receive Message</label>
                		<select name="receiveMsg" id="receiveMsg" class="form-control">                 
             			<c:choose>
                			<c:when test="${user.receiveMsg == true}">
                				<option value="true" selected>Yes</option>
                				<option value="false">No</option>
                			</c:when>
                			<c:when test="${user.receiveMsg == false}">
                				<option value="true">Yes</option>
                				<option value="false" selected>No</option>
                			</c:when>
                			<c:otherwise>
                				<option value="true">Yes</option>
                				<option value="false">No</option>
                			</c:otherwise>
                		</c:choose>
          				</select>
          			</div>
          			<div class="form-group">
                		<label>Avatar</label>
                		<p>
          					<button type="button" class="btn btn-warning" id="choiceAvatarBtn">Choose Avatar</button>
         					<button type="button" class="btn btn-success" id="confirmAvatarBtn">Confirm Avatar</button>
          					<input type="file" class="hidden" id="newAvatarFile" name="newAvatarFile">
          					<input type="hidden" value="${user.avatar}" name="avatar" id="adminUserAvatar">
        				</p>
        				<div class="row">
          					<div class="col-md-9" id="adjustment">
            					<img src="${user.avatar}" id="newAvatarImg" class="origin-avatar" alt="">
          					</div>
          					<div class="col-md-3">
            				<div class="preview"></div>
          					</div>
        				</div>
              		</div>
          			<button type="submit" id="btn" class="btn btn-warning">Submit</button>
        		</form>
      		</div>
      	</div>
      </div>
    </div>
  </section>
  <script type="text/javascript">
  	$(function(){
  		$(".sidebar-menu li:eq(5)").addClass("active");

  		$("#btn").click(function(){
  			if(confirm("Are you sure to edit？")){
  				console.log($("#form").serialize());
  				$.post("/admin/user/edit",$("#form").serialize(),function(data){
  					if(data.success === true){
  						toast("Edit Success", "success");
  						setTimeout(function(){
  							window.location.href = "/admin/user/list";
  						},700);
  					}else{
  						toast(data.error);
  					}
  				});
  			}
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>