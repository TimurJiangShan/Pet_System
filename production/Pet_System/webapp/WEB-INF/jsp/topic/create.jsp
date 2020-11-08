<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>PetGo-Create Posts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/css/app.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="/resources/wangEditor/wangEditor.min.css">
        <link rel="shortcut icon" href="/resources/images/favicon.ico">
        <script src="/resources/js/logout.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="../components/head.jsp"></jsp:include>
            <div class="row">
                <div class="col-md-9">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <a href="/">Home</a> / Post a post
                        </div>
                        <div class="box-body">
                            <form id="form">
                                <div class="form-group">
                                    <label for="title">title</label>
                                    <input type="text" class="form-control" id="title" name="title"
                                           placeholder="Please enter the topic title, if the title can express the complete content, the body can be empty">
                                </div>
                                <div class="form-group">
                                    <label for="editor">content</label>
                                    <input type="hidden" id="commentId" value="">
                                    <p class="hidden" id="replyP">Reply<span id="replyAuthor"></span>: <a
                                            href="javascript:cancelReply();">cancel</a></p>
                                    <div id="editor" style="margin-bottom: 10px;"></div>
                                </div>
                                <%-- <div class="form-group">
                                  <label for="tab">板块</label>
                                  <select id="tab" class="form-control" name="tab" onchange="getNode()">
                                   <c:forEach var="item" items="${tabList}" varStatus="status">
                                   <c:if test="${item.tabName != 'all' && item.tabName != 'member'}">
                                   <option value="${item.tabName}">${item.tabDesc}</option>
                                 </c:if>
                               </c:forEach>
                             </select>
                           </div> --%>

                                <div class="form-group">
                                    <label for="node">node</label>
                                    <select id="node" class="form-control" name="node">
                                        <c:forEach var="item" items="${nodeList}" varStatus="status">
                                            <option value="${item.nodeTitle}">${item.nodeTitle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group" style="display: none;">
                                    <div class="form-group">
                                        <label for="title">tag</label>
                                        <input type="text" class="form-control" id="tag" name="title"
                                               placeholder="Please choose a label for your topic. Proper classification will make the information you post more useful">
                                    </div>
                                </div>
                                <button type="button" id="btn" class="btn btn-warning">post</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 hidden-sm hidden-xs">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <b>posting guidelines</b>
                        </div>
                        <div class="box-body">
                            <p>• Describe the main points of the content in the title. If one thing can be stated clearly within the length of the title, there is no need to write the text.</p>
                            <p>• Be kind to strangers. Use knowledge to help others。</p>
                            <p>• If it is a reprinted article, please be sure to fill in only the original URL, and the content does not need to be copied.</p>
                            <p>• Please select a section for your post. Proper categorization will make the information you post more useful.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <jsp:include page="../components/foot.jsp"></jsp:include>
        <script src="/resources/js/jquery.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>
        <script src="/resources/wangEditor/wangEditor.js"></script>
        <script src="/resources/js/emotions.js"></script>
        <!-- <script src="/resources/js/topic/node.js"></script> -->
        <script type="text/javascript">

            $(function () {
                var E = window.wangEditor;
                var editor = new E('#editor');
                editor.customConfig.uploadFileName = 'file';
                editor.customConfig.uploadImgServer = '/common/upload';
                // Limit image size to 5MB
                editor.customConfig.uploadImgMaxSize = 1024 * 1024 * 1024;
                // Limit upload of 5 pictures at a time
                editor.customConfig.uploadImgMaxLength = 10;
                // Change the timeout time to 10s
                editor.customConfig.uploadImgTimeout = 10000;
                editor.customConfig.menus = [
                    'head',
                    'bold',
                    'fontSize',
                    'fontName',
                    'italic',
                    'underline',
                    'strikeThrough',
                    'foreColor',
                    'backColor',
                    'link',
                    'list',
                    'justify',
                    'quote',
                    'emoticon',
                    'image',
                    'table',
                    'video',
                    'code',
                    'undo',
                    'redo'
                ];
                editor.customConfig.emotions = [
                    {
                        title: 'default',
                        type: 'image',
                        content: defaultEmotions
                    },
                    {
                        title: 'Anime',
                        type: 'image',
                        content: animeEmotions
                    },
                    {
                        title: 'GIF',
                        type: 'image',
                        content: gifEmotions
                    },
                    {
                        title: 'others',
                        type: 'image',
                        content: otherEmotions
                    }
                ];

                // Video upload
                editor.customConfig.uploadVideoServer = '/common/upload'; // Upload interface
                editor.customConfig.uploadVideoHooks = { // Upload complete processing method
                    customInsert: function (insertVideo, result) {
                        console.log(result);
                        if (result.errno === 0) {
                            $.each(result.data,function(index,value){
                                insertVideo(value);
                            });
                        } else {
                            alert('upload failed');
                        }
                    }
                };

                editor.create();


                function cancelReply() {
                    $("#replyAuthor").text("");
                    $("#commentId").val("");
                    $("#replyP").addClass("hidden");
                }

                $("#btn").click(function () {
                    // title
                    var title = $("#title").val();
                    // html format content
                    var contentHtml = editor.txt.html();
                    // Normal format content
                    var contentText = editor.txt.text();

                    // var tab = $("#tab option:selected").val();
                    // var nodeCode = $("#node option:selected").val();
                    // alert(contentHtml);

                    var node = "${node}";
                    var statusCd = "${statusCd}";

                    // node
                    var nodeTitle = node ? node : $("#node option:selected").val();
                    // tag
                    var tag = $("#tag").val();
                    // var avatar = $("#editor").find("img:first").attr("src");
                    if (!title || title.length > 120) {
                        alert('Please enter the title, and the maximum length is within 120 characters');
                        return false;
                    } else if (!nodeTitle) {
                        alert('Please choose a suitable node');
                        return false;
                    } else {
                        $.ajax({
                            url: '/topic/save',
                            type: 'post',
                            async: false,
                            cache: false,
                            dataType: 'json',
                            data: {
                                title: title,
                                content: contentHtml,
                                statusCd: statusCd,
                                // nodeCode:nodeCode,
                                nodeTitle: nodeTitle,
                                tag: tag
                            },
                            success: function (data) {
                                //console.log(JSON.stringify(data));
                                if (data.success != null && data.success == true) {
                                    window.location.href = "/topic/" + data.data.topic.topicId;
                                } else {
                                    alert(data.error);
                                }
                            },
                            error: function (data) {
                                console.log(data);
                                //alert(data.error);
                            }
                        })
                    }
                });
            })



        </script>
    </body>
</html>