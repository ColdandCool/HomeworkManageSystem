package com.david.common.jcaptcha;

import com.david.common.utils.JCaptchaUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Verification code filter
 *
 * @author david.cn
 * @version 1.0
 */
public class JCaptchaValidateFilter extends AccessControlFilter {

    private boolean jcaptchaEbabled;//Whether to enable verification code support in the configuration file to configure

    private String jcaptchaParam = "jcaptchaCode";//The name of the verification code parameter submitted by the foreground

    private String failureKeyAttribute = "shiroLoginFailure"; //The attribute name stored in the verification code failed

    public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
        this.jcaptchaEbabled = jcaptchaEbabled;
    }

    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //1、Set whether the verification code to open the property, the page can be based on the property to determine whether to show the verification code
        request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //2、To determine whether the verification code is disabled or not a form submission (permission to access)
        if (jcaptchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        //3、At this point the form is submitted to verify that the verification code is correct
        return JCaptchaUtils.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
    }

    /**
     * get verification code
     *
     * @param id sessionID
     * @return
     */
    public static BufferedImage getImageChallengeForID(String id) {

        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //If the verification code fails, the storage fails the key attribute
        request.setAttribute(failureKeyAttribute, "jCaptcha.error");
        return true;
    }
}