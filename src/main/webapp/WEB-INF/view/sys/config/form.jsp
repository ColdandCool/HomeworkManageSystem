<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>PublicConfiguration</title>
    <%@ include file="../../include/head.jsp"%>
    <style type="text/css">
        .tpl-content-wrapper{margin-left:0}
    </style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- Content area -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-head am-cf">
                            <div class="widget-title am-fl">Configuration Information</div>
                        </div>
                        <div class="widget-body am-fr">
                            <form class="am-form tpl-form-border-form" data-am-validator modelAttribute="config" action="${ctx}/sys/config/<c:choose><c:when test="${empty config.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
                                <input type="hidden" name="id" value="${config.id}"/>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label"><span class="error">*</span>SysName：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="sysName" placeholder="SysName" value="${config.sysName}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label"><span class="error">*</span>ModuleName：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="moduleName" placeholder="ModuleName" value="${config.moduleName}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label"><span class="error">*</span>Key：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="configName" placeholder="key" value="${config.configName}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label"><span class="error">*</span>Value：</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" name="configValue" placeholder="value" value="${config.configValue}" required/>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-u-sm-3 am-form-label">Remarks：</label>
                                    <div class="am-u-sm-9">
                                        <textarea name="remarks">${config.remarks}</textarea>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary">Save</button>
                                        <button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">Close</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if(msg!=''){
            showMsg(msg);
            closeModel(true);//close the window
        }
    });
</script>
</body>
</html>
