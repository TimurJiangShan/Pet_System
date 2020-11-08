<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Pet Information Service Platform-${nodeName}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="/resources/images/favicon.ico">
        <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="components/head.jsp"></jsp:include>
            <div class="row">
                <div class="col-md-9">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <ul class="nav nav-pills" id="tab">
                                <li class="${tab eq 'all' ? 'all active' : 'all'}"><a href="/?tab=all&node=${nodeName}">All</a>
                                </li>
                                <li class="${tab eq 'hot' ? 'hot active' : 'hot'}"><a href="/?tab=hot&node=${nodeName}">Hottest</a>
                                </li>
                                <li class="${tab eq 'new' ? 'new active' : 'new'}"><a href="/?tab=new&node=${nodeName}">latest</a>
                                </li>
                            </ul>
                        </div>
                        <div class="box-body">
                            <c:forEach var="item" items="${page.list}" varStatus="status">
                                <div class="media">
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
                                                    <span class="label label-warning">top</span> <span>•</span>
                                                </c:if>
                                                <c:if test="${item.good}">
                                                    <span class="label label-success">highlight</span> <span>•</span>
                                                </c:if>

                                                <c:if test="${not empty item.nodeTitle}">
                                                    <span><a href="/n/${item.nodeTitle}"
                                                             class="node">${item.nodeTitle}</a></span>
                                                    <span>•</span>
                                                </c:if>

                                                <a href="/user/${item.author}">${item.author}</a>
                                                <c:if test="${item.viewCount > 0}">
                                                    <span class="hidden-sm hidden-xs">•</span>
                                                    <span class="hidden-sm hidden-xs">${item.viewCount}次点击</span>
                                                </c:if>

                                                <!-- Number of comments -->
                                                <c:if test="${item.replyCount > 0}">
                                                    <span class="hidden-sm hidden-xs">•</span>
                                                    <span class="hidden-sm hidden-xs"><a
                                                            href="/topic/${item.topicId}">${item.replyCount}个评论</a></span>
                                                </c:if>

                                                <span>•</span>
                                                <span><fmt:formatDate type="date" value="${item.createDate}"/></span>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="media-right"><span class="badge badge-default"><a
                                            href="/topic/${item.topicId}">${item.replyCount}</a></span></div>
                                    <c:if test="${!status.last}">
                                        <div class="divide mar-top-5"></div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="box-footer panel-footer" id="paginate"></div>
                    </div>
                </div>
                <div class="col-md-3 hidden-sm hidden-xs">
                    <c:if test="${sessionScope.user != null}">
                        <div class="box box-warning" id="session">
                            <div class="box-body">
                                <div class="media">
                                    <div class="media-left">
                                        <a href="/user/${sessionScope.user.userName}">
                                            <img src="${sessionScope.user.avatar}" title=""
                                                 class="user-avatar img-circle">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                        <div class="media-heading">
                                            <strong><a
                                                    href="/user/${sessionScope.user.userName}">${sessionScope.user.userName}</a></strong>
                                            <div style="color: #7A7A7A; font-size: 12px; margin-top: 5px;">
                                                <i>${sessionScope.user.signature}</i>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 15px;">
                                        <a href="/topic/create" style="font-size: 14px;">
                                            <button class="btn btn-warning">Create Topic</button>
                                        </a>
                                    </div>
                                </div>
                                <div class="sep10" style="height: 10px;"></div>
                                <table cellpadding="0" cellspacing="0" border="0" width="100%"
                                       class="table_fade" style="font-size: 14px;">
                                    <tbody>
                                    <tr>
                                        <td width="33%" align="center"><a href="/user/topics"
                                                                          class="dark" style="display: block;"><span
                                                class="bigger">${countTopic}</span>
                                            <div class="sep3"></div>
                                            <span class="fade">my theme</span></a></td>
                                        <td width="34%"
                                            style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);"
                                            align="center"><a href="/collect/topics" class="dark"
                                                              style="display: block;"><span
                                                class="bigger">${countCollect}</span>
                                            <div class="sep3"></div>
                                            <span class="fade">my collection</span></a></td>
                                        <td width="33%" align="center"><a href="/follow/topics"
                                                                          class="dark" style="display: block;"><span
                                                class="bigger">${countFollow}</span>
                                            <div class="sep3"></div>
                                            <span class="fade">Special attention</span></a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel-footer" style="background-color: white">
                                <div class="row">
								<span class="col-md-6"><a href="/notification/list"><span
                                        id="n_count">${countNotReadNotice}</span> unread messages</a></span> <span
                                        class="col-md-6 text-right">points：<a
                                        href="/top100">${countScore}</a></span>
                                </div>
                            </div>
                        </div>
                    </c:if>


                    <!-- Popular node -->
                    <div class="box box-warning">
                        <div class="box-header with-border"><b>Popular sections</b></div>
                        <div class="box-body">
                            <div class="row">
                                <c:forEach var="item" items="${hotNodeList}">
                                    <div class="col-md-4" style="margin-bottom: 10px; padding-left: 10px;">
                                        <a href="${item.url}"><span
                                                class="label label-warning text-muted">${item.nodeTitle}</span></a>
                                        <small class="excerpt text-muted"
                                               style="display: block; margin-top: 10px;"></small>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <!-- Community operation status -->
                    <div class="box box-warning">
                        <div class="box-header with-border"><b>Community operation status</b></div>
                        <div class="box-body">
                            <p>number of registered member:${countUserAll}</p>
                            <p>number of posts:${countAllTopic}</p>
                            <p>number of replies:${countAllReply}</p>
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
        <jsp:include page="components/foot.jsp"></jsp:include>
        <script src="/resources/js/jquery.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>
        <script src="/resources/js/index.js"></script>
        <script src="/resources/layui/layui.js"></script>
        <script src="/resources/layui/layui-paginate.js"></script>
        <!-- <script src="/resources/js/login_info.js"></script> -->
        <script src="/resources/js/formatDate.js"></script>
        <script type="text/javascript">
            var tab = "${tab}";//parent node
            var nodeName = "${nodeName}";
            var count = ${page.totalRow};//Total amount of data
            var limit = ${page.pageSize};//Number of items displayed per page
            var url = "?node=" + nodeName + "&tab=" + tab + "&p=";//url
            function page() {
                var page = location.search.match(/p=(\d+)/);
                return page ? page[1] : 1;
            }

            var p = page();//current page number
            paginate(count, limit, p, url);
        </script>
    </body>
</html>