<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout/header.jsp" %>
<!-- Main content area -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
    <section class="content-header">
        <h1>
            HomePage <small>Send Email</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/admin/index"><i class="fa fa-dashboard"></i>
                HomePage</a></li>
            <li class="active">Send Email</li>
        </ol>
    </section>
    <section class="content">

        <div class="box box-warning">
            <div class="box-header with-border">
				Send Email
            </div>
            <div class="box-body">
                <form id="form">
                    <div class="form-group">
                        <label for="title">Email Title</label>
                        <input type="text" class="form-control" id="title" name="title"
                               placeholder="Please type the title of email">
                    </div>
                    <div class="form-group">
                        <label for="editor">Email Content</label>
                        <div id="editor" style="margin-bottom: 10px;"></div>
                    </div>

                    <button type="button" id="btn" class="btn btn-warning">Send</button>
                </form>
            </div>
        </div>
        <!-- ./col -->
    </section>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/wangEditor/wangEditor.js"></script>
	<script src="/resources/js/emotions.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".sidebar-menu li:eq(1)").addClass("active");
        });
    </script>
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
                'code',
                'undo',
                'redo'
            ];
            editor.customConfig.emotions = [
                {
                    title: 'Default',
                    type: 'image',
                    content: defaultEmotions
                },
                {
                    title: 'AnimeEmotion',
                    type: 'image',
                    content: animeEmotions
                },
                {
                    title: 'GIF',
                    type: 'image',
                    content: gifEmotions
                },
                {
                    title: 'Others',
                    type: 'image',
                    content: otherEmotions
                }
            ];


            editor.create();


            $("#btn").click(function () {
                // title
                var title = $("#title").val();
                // html Format content
                var contentHtml = editor.txt.html();
                // Normal format content
                var contentText = editor.txt.text();

                // var avatar = $("#editor").find("img:first").attr("src");
                if (!title || title.length > 150) {
                    alert('Type Title，length should < 150');
                    return false;
                }  else {
                    $.ajax({
                        url: '/admin/email/send',
                        type: 'post',
                        async: false,
                        cache: false,
                        dataType: 'json',
                        data: {
                            title: title,
                            content: contentHtml,
                        },
                        success: function (data) {
                            //console.log(JSON.stringify(data));
                            if (data.success != null && data.success == true) {
								alert('Send Success');
								$("#title").val('');
								editor.txt.html('')
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
</div>
<%@ include file="layout/footer.jsp" %>