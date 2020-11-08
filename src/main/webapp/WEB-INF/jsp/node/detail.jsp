<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>PetGo-${node.nodeTitle}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="/resources/images/favicon.ico">
  <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
 <div class="wrapper">
  <jsp:include page="../components/head.jsp"></jsp:include>
  <div class="row">
    <div class="col-md-9">
      <div class="panel panel-default">
       <div class="node_header">
         <div class="node_avatar">
           <div style="float: left; display: inline-block; margin-right: 10px; margin-bottom: initial!important;">
             <img src="${node.avatarNormal}" border="0" align="default" width="72"></div>
           </div>
           <div class="node_info">
             <div class="fr f12"><span>Themes</span> <strong>${countTopicByNode}</strong></div>
             <a href="/">Homepage</a> <span class="chevron">&nbsp;›&nbsp;</span> ${node.nodeTitle}
             <div class="sep10"></div>
             <div class="sep5"></div>
             <span class="f12">${node.nodeDesc}</span>
             <div class="sep10"></div>
             <div class="node_header_tabs">
               <c:if test="${fn:length(nodeTabList) > 0}">
               <c:forEach var="item" items="${nodeTabList}" varStatus="status">
               <a href="${node.url}?s=${item.nodeTabCode}" id="${item.nodeTabCode}" class="node_header_tab">${item.nodeTabTitle}</a>
             </c:forEach>
           </c:if>
         </div>
       </div>
     </div>
     <div class="panel-body paginate-bot">
      <c:forEach var="item" items="${page.list}">
      <div class="media">
        <c:if test="${fn:length(item.avatar) > 0}">
            <div class="media-left">
            <%-- <a href="/user/${item.author}"> --%><img src="${item.avatar}" class="avatar img-circle" alt=""><!-- </a> -->
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
          <!-- summary -->
          <%-- <div class="excerpt"><span>${item.excerpt}</span></div> --%>
          <div class="tip">
          <p class="gray">
            <c:if test="${item.top}">
            <span class="label label-warning">Put on top</span> <span>•</span>
          </c:if>
          <c:if test="${item.good}">
          <span class="label label-warning">Selection</span> <span>•</span>
        </c:if>
        <a href="/user/${item.author}">${item.author}</a>
        <span class="hidden-sm hidden-xs">•</span>
        <span class="hidden-sm hidden-xs">${item.viewCount}clicks</span>
        <!-- comment -->
        <!-- <span class="hidden-sm hidden-xs">•</span> -->
        <%-- <span class="hidden-sm hidden-xs"><a href="/topic/${item.topicId}">${item.replyCount}comments</a></span> --%>
        <span>•</span>
        <span><fmt:formatDate type="date" 
          value="${item.createDate}" /></span>
          <!-- last replier -->
          <%-- <c:if test="${item.lastReplyAuthor != null}">
          <span>•</span>
          <span>Last reply from <a href="/user/${item.lastReplyAuthor}">${item.lastReplyAuthor}</a></span>
        </c:if> --%>
        <!-- label -->
        <!-- <span>•</span> -->
        <%-- <a href="/topic/tag/${item.tag}"><span class="label label-success">${item.tag}</span></a> --%>
      </p>
      </div>
    </div>
    	<div class="media-right"><span class="badge badge-default"><a href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
    	<div class="divide mar-top-5"></div>
  </div>
</c:forEach>
</div>
<div class="panel-footer" id="paginate"></div>
</div>
</div>
<div class="col-md-3 hidden-sm hidden-xs">
  <jsp:include page="../components/seesion.jsp"></jsp:include>
  <!-- adjacent nodes -->
  <div class="panel panel-default">
    <!-- <div class="panel-heading"><span style="color: #ccc;">relevant nodes</span></div> -->
    <div class="panel-body">
      <div class="row">
      <c:if	test="${parentNode != null}">
      	<div class="cell" style="padding: 0 10px;border-bottom: 0px solid #eee;">
        	<strong class="gray">Parent section</strong>
        	<div class="sep10"></div>
        	<img src="${parentNode.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${parentNode.url}">${parentNode.nodeTitle}</a>
    	</div>
      </c:if>
      <c:if test="${fn:length(adjacencyNode) > 0}">
        <div class="cell" style="padding: 0 10px;border-bottom: 0px solid #eee;">
          <strong class="gray">relevant section</strong>
          <c:forEach var="item" items="${adjacencyNode}" varStatus="status">
          <div class="sep10"></div>
          <img src="${item.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${item.url}">${item.nodeTitle}</a>
          <div class="sep10"></div>
        </c:forEach> 
      </div>
    </c:if>
    <c:if test="${fn:length(childrenNode) > 0}">
    	<div class="cell" style="padding: 0 10px;border-bottom: 0px solid #e2e2e2;border-top: 1px solid #e2e2e2;">
          <strong class="gray">Child section</strong>
          <c:forEach var="item" items="${childrenNode}" varStatus="status">
          <div class="sep10"></div>
          <img src="${item.avatarMini}" border="0" align="absmiddle" width="24">&nbsp; <a href="${item.url}">${item.nodeTitle}</a>
          <div class="sep10"></div>
        </c:forEach> 
      </div>
    </c:if>
  </div>
</div>
</div>
<!-- adjacent nodes -->
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
<!-- <script src="/resources/js/login_info.js"></script> -->
<script src="/resources/js/formatDate.js"></script>
<script src="/resources/js/node/changeSectionClass.js"></script>
<script type="text/javascript">
 var nodeTitle = "${node.nodeTitle}";//node name
 var nodeCode = "${node.nodeCode}";//node code
 var nodeURL = "${node.url}";//node URL
 var avatarLarge = "${node.avatarLarge}";//node background
 $(".wrapper").css({"background-image":"url("+avatarLarge+")"});
 // console.log(avatarLarge)
 var nodeTabCode = "${nodeTab}";//node section
 var count = ${page.totalRow};//data amount
 var limit = ${page.pageSize};//entries per page
 var url = nodeURL+"?s="+nodeTabCode+"&p=";//url
 function page(){
   var page = location.search.match(/p=(\d+)/);  
   return page ? page[1] : 1;  
 }
 var p = page();//current page
 paginate(count,limit,p,url);
</script>
</body>
</html>