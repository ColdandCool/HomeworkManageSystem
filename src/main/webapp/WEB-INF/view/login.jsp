<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("david.name")}' />${iutilsName} - Login In</title>
    <%@ include file="include/head.jsp"%>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <div class="tpl-login">
        <div class="tpl-login-content">
            <%--<div class="tpl-login-logo">--%>
            <%--</div>--%>
            <form class="am-form tpl-form-line-form" action="${ctx}/login" method="post" data-am-validator>
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="text" name="username"  required>
                        <label class="color-form-control-label">Account</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="color-form-group color-form-line color-floating-label">
                        <input class="color-form-control tpl-form-input" type="password" name="password" required>
                        <label class="color-form-control-label">Password</label>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="am-g">
                        <div class="am-u-sm-8">
                            <div class="color-form-group color-form-line color-floating-label">
                                <input class="color-form-control tpl-form-input" type="text" name="jcaptchaCode" required>
                                <label class="color-form-control-label">Code</label>
                            </div>
                        </div>
                        <div class="am-u-sm-4">
                            <div class="jcaptcha-box" style="margin-top: 20px;">
                                <img class="jcaptcha-btn jcaptcha-img" src="${pageContext.request.contextPath}/jcaptcha.code" title="Click to replace the verification code">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-form-group tpl-login-remember-me">
                    <div class="am-g">
                        <div class="am-u-sm-4">
                            <input id="remember-me" name="rememberMe" type="checkbox">
                            <label for="remember-me">
                                Remember me
                            </label>
                        </div>
                        <div class="am-u-sm-4"></div>
                        <div class="am-u-sm-4 right">
                                <a href="${ctx}/forgetpassword"><strong>Forget password</strong></a>
                        </div>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="am-u-sm-6 am-center">
                        <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">log in</button>
                    </div>
                    <div class="am-u-sm-6 am-center">
                        <a href="${ctx}/register" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">register</a>
                    </div>
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
        $(".jcaptcha-btn").click(function() {
            $(".jcaptcha-img").attr("src", '${pageContext.request.contextPath}/jcaptcha.code?'+new Date().getTime());
        });
    });
    var rest = '${rest}';
    // If in the frame or in the dialog box, the pop-up prompts and jumps to the home page
    if (top.location !== self.location) {
        alert("Not logged in or logged in timeout. Please re-login, thank you!");
        top.location="${ctx}";
    }
</script>
</body>
</html>