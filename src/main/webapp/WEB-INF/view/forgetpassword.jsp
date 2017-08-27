<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("david.name")}' />get password</title>
    <%@ include file="include/head.jsp"%>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-title">Retrieve the password</div>
                <span class="tpl-login-content-info">
                  We will send an email to your email address
              </span>
            <form class="am-form tpl-form-line-form" action="${ctx}/forgetpassword" method="post" data-am-validator>
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="text" name="email" required>
                        <label class="color-form-control-label">email</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="include/bottom.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if(msg!=''){
            showMsg(msg);
        }
    });
</script>
</body>
</html>