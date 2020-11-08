<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>PetGo-Search Result</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Import Bootstrap -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <!-- Import layui.css -->
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
<div class="wrapper">
<jsp:include page="../components/head.jsp"></jsp:include>
<div class="row">
  <div class="col-md-12">
    <div class="box box-warning">
      <div class="box-header with-border">Search result</div>
        <div class="box-body">

  <c:forEach var="item" items="${pageLike.list}">
          <div class="media" id="topic">
            <c:if test="${fn:length(item.avatar) > 0}">
            <div class="media-left">
            <img src="${item.avatar}" class="avatar img-circle" alt="">
            </div>
        	</c:if>
            <div class="media-body">
              <div class="title">
                <c:choose>
                	<c:when test="${item.url != null}">
                		<a href="${item.url}" target="_blank">${item.title}</a>
                	</c:when>
                	<c:otherwise>
                		<a href="/topic/${item.topicId}">${item.title}</a>
                	</c:otherwise>
                </c:choose>
              </div>
              <div class="tip">
              <p class="gray">
              <c:if test="${item.top}">
			  <span class="label label-warning">Top</span> <span>•</span>
			  </c:if>
			  <c:if test="${item.good}">
			  <span class="label label-warning">Excellent</span> <span>•</span>
			  </c:if>
			  
			  <c:if test="${not empty item.nodeTitle}">
			  	<a href="/n/${item.nodeTitle}" class="node">${item.nodeTitle}</a>
			  	<span>•</span>
			  </c:if>
			  	
                <strong><a href="/user/${item.author}">${item.author}</a></strong>
                <span class="hidden-sm hidden-xs">•</span>
                <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>

                <span>•</span>
                <span><fmt:formatDate type="date" 
                  value="${item.createDate}" /></span>
                </p>
                </div>
              </div>
              <div class="media-right"><span class="badge badge-default"><a href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
              <c:if test="${!status.last}">
		            	<div class="divide mar-top-5"></div>
		            </c:if>
            </div>
          </c:forEach>
      </div>
      <div class="panel-footer" id="paginate"></div>
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
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<script src="/resources/js/goTop.js"></script>
<script src="/resources/js/formatDate.js"></script>
<script src="/resources/js/search/search.js"></script>
<script type="text/javascript">
  var search = "${search}";
  //var url = "/search?s="+search+"&";
  //$(".pagination2").pagination("${pageLike.pageNumber}","${pageLike.totalPage}",10);
  var count = ${pageLike.totalRow};//total data
  var limit = ${pageLike.pageSize};//the number of page display
  var url = "/search?s="+search+"&p=";//url
  function page(){
	   var page = location.search.match(/p=(\d+)/);  
	   return page ? page[1] : 1;  
	 }
  var p = page();//current page

  paginate(count,limit,p,url);
</script>
</body>
</html>