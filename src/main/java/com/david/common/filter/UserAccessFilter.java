package com.david.common.filter;

import com.david.common.JsonMapper;
import com.david.common.ResultVo;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 用户访问验证
 *
 * @author david.cn
 * @version 1.0
 */
public class UserAccessFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        return true;// true表示允许访问 false表示拒绝访问，进入onAccessDenied方法处理
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
