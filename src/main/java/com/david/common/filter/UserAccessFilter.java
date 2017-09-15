package com.david.common.filter;

import com.david.common.JsonMapper;
import com.david.common.ResultVo;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * User access verification
 *
 * @author david.cn
 * @version 1.0
 */
public class UserAccessFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        return true;// true means to allow access to false means to deny access to the onAccessDenied method
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResultVo resultVo = new ResultVo(ResultVo.FAILURE, "99", "No permission to access", null);
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JsonMapper.toJsonString(resultVo));
        return false;
    }

}
