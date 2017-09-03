<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>Homework Informatica</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <style type="text/css">
        .tpl-content-wrapper {
            margin-left: 0
        }
    </style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-body am-fr">
                            <div class="row" style="margin-bottom: 30px">
                                <article class="am-article">
                                    <div class="am-article-hd">
                                        <h1 class="am-article-title">${homework.title}</h1>
                                        <p class="am-article-meta">createTime：<fmt:formatDate
                                                value="${homework.createDate}" pattern="yyyy-MM-dd"/></p>
                                    </div>
                                    <div class="am-article-bd">
                                        <h3> Content:</h3>
                                        <p> ${homework.content} </p>
                                        <h3> Remark:</h3>
                                        <p> ${homework.remarks}</p>
                                        <h3> Deadline:</h3>
                                        <p><fmt:formatDate value="${homework.deadline}" pattern="yyyy-MM-dd"/></p>
                                    </div>
                                </article>
                            </div>
                            <div class="row" style="padding-left: 64px;padding-right: 63px;margin-bottom: 10px;">
                                <div class="am-form-inline" role="form">
                                    <div class="am-form-group am-u-sm-11">
                                        <input id="comment" type="text" class="am-form-field" placeholder="add new comment">
                                    </div>
                                    <button onclick="submitComment()"  type="submit" class="am-btn am-btn-default">submit</button>
                                </div>
                            </div>

                            <div class="row">
                                <ul class="am-comments-list am-comments-list-flip">
                                    <li class="am-comment am-comment-primary">
                                        <div class="am-comment-main">
                                            <header class="am-comment-hd">
                                                <div class="am-comment-meta">
                                                    <a href="#" class="am-comment-author">某人</a>
                                                    评论于
                                                    <time datetime="2013-07-27T04:54:29-07:00"
                                                          title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-12 15:30
                                                    </time>
                                                </div>
                                            </header>

                                            <div class="am-comment-bd">
                                                评论内容
                                            </div>
                                        </div>
                                    </li>

                                    <li class="am-comment am-comment-flip am-comment-secondary">
                                        <div class="am-comment-main">
                                            <header class="am-comment-hd">
                                                <div class="am-comment-meta">
                                                    <a href="#link-to-user" class="am-comment-author">某人</a>
                                                    评论于
                                                    <time datetime="2013-07-27T04:54:29-07:00"
                                                          title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-12 15:30
                                                    </time>
                                                </div>
                                            </header>

                                            <div class="am-comment-bd">
                                                评论内容
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 10000;">
    <ul id="tree" class="ztree" style="margin-top:0;"></ul>
</div>
<%@ include file="../../include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctxStatic}/custom/js/ztree.select.js"></script>
<script>
    $(document).ready(function () {
        //消息提醒
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
            if (msg == "Success") {
                closeModel(true);//关闭窗口
            }
        }
        initSelectValue(true);//初始化下拉框的值
    });

    function submitComment() {
        var url = "${ctx}/homework/addComment/${homework.id}/"
        var content = $("#comment").val()
        showMsg(url)
        $("#comment").val("")
        post(url,{"comment":content},function () {
            alert(1)
        })
    }
</script>

</body>
</html>