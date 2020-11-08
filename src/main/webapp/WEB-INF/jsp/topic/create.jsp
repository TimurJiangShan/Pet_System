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
                            <a href="/">Homepage</a> / Post
                        </div>
                        <div class="box-body">
                            <form id="form">
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input type="text" class="form-control" id="title" name="title"
                                           placeholder="Please enter the topic title. If the title can express the whole content, the body can be blank">
                                </div>
                                <div class="form-group">
                                    <label for="editor">Content</label>
                                    <input type="hidden" id="commentId" value="">
                                    <p class="hidden" id="replyP">Reply<span id="replyAuthor"></span>: <a
                                            href="javascript:cancelReply();">Cancel</a></p>
                                    <div id="editor" style="margin-bottom: 10px;"></div>
                                </div>


                                <div class="form-group">
                                    <label for="node">Module</label>
                                    <select id="node" class="form-control" name="node">
                                        <c:forEach var="item" items="${nodeList}" varStatus="status">
                                            <option value="${item.nodeTitle}">${item.nodeTitle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group" style="display: none;">
                                    <div class="form-group">
                                        <label for="title">Tag</label>
                                        <input type="text" class="form-control" id="tag" name="title"
                                               placeholder="Please select a label for your topic. Proper categorization will make the information you post more useful">
                                    </div>
                                </div>
                                <button type="button" id="btn" class="btn btn-warning">Publish</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 hidden-sm hidden-xs">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <b>Instruction</b>
                        </div>
                        <div class="box-body">
                            <p>• Describe the key points in the title. If a thing can be made clear within the length of the title, there is no need to write the text.</p>
                            <p>• Be friendly to strangers. Use knowledge to help others.</p>
                            <p>• If it is a reprint of the article, please be sure to fill in the original URL, the content does not need to be copied.</p>
                            <p>• Please select a board for your post. Proper categorization will make the information you post more useful.</p>
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
                // Limitation of picture is 5MB
                editor.customConfig.uploadImgMaxSize = 1024 * 1024 * 1024;
                // Uploading maximum 5 pictures one time
                editor.customConfig.uploadImgMaxLength = 10;
                // Timeout is 10 s
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
                        title: 'animate',
                        type: 'image',
                        content: animeEmotions
                    },
                    {
                        title: 'GIF',
                        type: 'image',
                        content: gifEmotions
                    },
                    {
                        title: 'other',
                        type: 'image',
                        content: otherEmotions
                    }
                ];

                // Upload video
                editor.customConfig.uploadVideoServer = '/common/upload'; // Upload interface
                editor.customConfig.uploadVideoHooks = { // Upload completion processing method
                    customInsert: function (insertVideo, result) {
                        console.log(result);
                        if (result.errno === 0) {
                            $.each(result.data,function(index,value){
                                insertVideo(value);
                            });
                        } else {
                            alert('Upload failed');
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
                    // Title
                    var title = $("#title").val();
                    // html Content of format
                    var contentHtml = editor.txt.html();
                    // Content in normal format

                    var contentText = editor.txt.text();

                    // var tab = $("#tab option:selected").val();
                    // var nodeCode = $("#node option:selected").val();
                    // alert(contentHtml);

                    var node = "${node}";
                    var statusCd = "${statusCd}";

                    // node
                    var nodeTitle = node ? node : $("#node option:selected").val();
                    // Tag
                    var tag = $("#tag").val();
                    // var avatar = $("#editor").find("img:first").attr("src");
                    if (!title || title.length > 120) {
                        alert('Please enter the title, and the maximum length is less than 120 characters');
                        return false;
                    } else if (!nodeTitle) {
                        alert('Please select a suitable plate');
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