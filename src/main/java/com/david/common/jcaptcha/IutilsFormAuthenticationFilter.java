package com.david.common.jcaptcha;

import com.david.common.JsonMapper;
import com.david.common.ResultVo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码失效是不进行身份验证
 *
 * @author david.cn
 * @version 1.0
 */
public class IutilsFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 不是ajax请求
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
            issueSuccessRedirect(request, response);
        } else {
            ResultVo resultVo = new ResultVo(ResultVo.SUCCESS, "1", "login successful", null);
            httpServletResponse.reset();
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().write(JsonMapper.toJsonString(resultVo));
        }
        return false;
    }
}
