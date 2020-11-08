<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>PetGo-${user.userName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
			<div class="box box-warning">
    <div class="cell" style="padding: 10px;">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tbody><tr>
            <td width="73" valign="top" align="center">
            <img src="${user.avatar}"  border="0" align="default" style="border-radius: 4px;" width="73" height="73px"/>
            <div class="sep10"></div>
            </td>
            <td width="10"></td>
            <td width="auto" valign="top" align="left">
            <c:if test="${user2 != null && user2.userId != user.userId}">
            	<div class="fr">
                    <button class="btn btn-follow" onclick="save()" id="follow">Follow in special</button>
                    <div class="sep10"></div>
                    <button class="btn btn-warning">Block</button>
                </div>
            </c:if>
                <h1 title="${user.userId}" id="user_id" class= "user_id">${user.userName}</h1>
                <span class="gray" style="font-size: 14px;">PetGo No. ${user.userId} member，join in <fmt:formatDate type="both" value="${user.createDate}" /><div class="sep5"></div>
                </span> 
            </td>
        </tr>
    </tbody></table>
    <div class="sep5"></div>
</div>
</div>
<div class="sep20"></div>
				<div class="panel panel-default">

					<div class="cell_tabs"><div class="fl"><img src="${user.avatar}" width="24" style="border-radius: 24px; margin-top: -2px;" border="0"></div>

					<a href="javascript:void(0);" onclick="topicList()" class="cell_tab_current" >Topic</a>
					<a href="javascript:void(0);" onclick="replyList()" class="cell_tab">Reply</a>
					<a href="javascript:void(0);" onclick="collectList()" class="cell_tab">Collection</a>
					<a href="javascript:void(0);" onclick="followList()" class="cell_tab">Following</a>
					<a href="javascript:void(0);" onclick="fansList()" class="cell_tab">Follower</a>
					<a href="javascript:void(0);" onclick="topicQnaList()" class="cell_tab">Question</a></div>
					<div class="itemList"></div>	
				</div>
				<button id="toggleBigImageBtn" data-toggle="modal" class="hidden"
					data-target="#showBigImageModal"></button>
				<div class="modal fade" tabindex="-1" role="dialog"
					id="showBigImageModal">
					<div class="modal-dialog" style="width: 98%" role="document">
						<div class="modal-content">
							<div class="modal-body text-center">
								<img src="" id="bigImage" style="max-width: 100%;" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="back2Top" class="backTop___6Q-ki" style="display:none">
		<div class="line___F1WY0"></div>
		<div class="arrow___3UCwo"></div>
	</div>
	</div>
	<jsp:include page="../components/foot.jsp"></jsp:include>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/goTop.js"></script>
	<script src="/resources/layui/layui.js"></script>
	<script src="/resources/layui/layui-paginate.js"></script>
	<script src="/resources/js/user/detail.js"></script>
	<script src="/resources/js/formatDate.js"></script>
</body>
</html>