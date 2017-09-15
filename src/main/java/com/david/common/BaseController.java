package com.david.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Basic controller class
 *
 * @author David
 */
public abstract class BaseController {

    /**
     * Log object
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Manage the underlying path
     */
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * Front-end base path
     */
    @Value("${frontPath}")
    protected String frontPath;

    /**
     * rest interface path
     */
    @Value("${restPath}")
    protected String restPath;

    /**
     * Front URL suffix
     */
    @Value("${urlSuffix}")
    protected String urlSuffix;

    /**
     * The client returns a JSON string
     *
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JsonMapper.toJsonString(object),
                "application/json");
    }

    /**
     * Returns the JSONP data
     *
     * @param response
     * @param object
     * @param callback
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object,
                                  String callback) {
        return renderString(response,
                callback + "(" + JsonMapper.toJsonString(object) + ")",
                "application/text");
    }

    /**
     * The client returns a string
     *
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string,
                                  String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Get the user's remote address
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * Add a Model message
     *
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("msg", sb.toString());
    }

    /**
     * Add a Flash message
     *
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes,
                              String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("msg", sb.toString());
    }

}
