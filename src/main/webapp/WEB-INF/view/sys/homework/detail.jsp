<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>Homework Informatica</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <style type="text/css">
        .tpl-content-wrapper{margin-left:0}
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
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl am-left">Homework Info</div>
                            <div class="widget-title am-fl am-right">Homework Info</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-form tpl-form-border-form">
                                <input type="hidden" name="id" value="${homework.id}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label"><span class="error">*</span>Title：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="title" minlength="3" placeholder="Account at least 3 character" value="${homework.title}" readonly/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">Content：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="content" placeholder="Name" value="${homework.content}" readonly/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">Deadline：</label>
                                    <div class="am-u-sm-9 am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd'}">
                                        <input type="text" name="deadlineStr" class="am-form-field" placeholder="deadline" value="<fmt:formatDate value="${homework.deadline}" pattern="yyyy-MM-dd" />" readonly>
                                        <span class="am-input-group-btn am-datepicker-add-on">
                                            <button class="am-btn am-btn-default" type="button">
                                                <span class="am-icon-calendar"></span>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">Remark：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="Remarks" placeholder="remarks" value="${user.remarks}" readonly/>
                                    </div>
                                </div>
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
</script>

</body>
</html>