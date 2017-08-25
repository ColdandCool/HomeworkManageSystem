package com.david.sys.controller;

import com.david.common.BaseController;
import com.david.common.ResultVo;
import com.david.common.utils.UserUtils;
import com.david.sys.entity.User;
import com.david.sys.service.PasswordHelper;
import com.david.sys.service.UserService;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 *
 * @author David
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 获取异步登录界面
     *
     * @param response
     * @return
     */
    @RequestMapping("/ajaxLogin")
    public String ajaxLogin(HttpServletResponse response) {
        ResultVo resultVo = null;
        //获取用户登录信息 验证已登录，返回登录信息
        String userName = UserUtils.getLoginUserName();
        if (userName != null) {
            resultVo = new ResultVo(ResultVo.SUCCESS, "1", "login successful", null);
            return renderString(response, resultVo);
        } else {
            return "ajaxLogin";
        }
    }

    /**
     * 登录处理
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        //ajax登录失败处理
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            ResultVo resultVo = new ResultVo(ResultVo.FAILURE, "0", "Login failed", null);
            return renderString(response, resultVo);
        }
        //获取用户登录信息 验证已登录，跳转到管理页
        String userName = UserUtils.getLoginUserName();
        if (userName != null) {
            return "redirect:" + adminPath;
        }
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        model.addAttribute("username", request.getParameter("username"));
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "username or password is incorrect";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "username or password is incorrect";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "The number of error operations exceeds the limit";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "Account is locked";
        } else if ("jCaptcha.error".equals(exceptionClassName)) {
            error = "Verification code error";
        } else if (exceptionClassName != null) {
            error = "Unknown error";
        }
        model.addAttribute("msg", error);
        if (request.getParameter("forceLogout") != null) {
            error = "You have been forced to log out by the administrator. Please log in again";
            model.addAttribute("msg", error);
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param password 确认密码
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password, String rpassword, Model model) {
        String url = "register";
        //判断是否密码重复
        if (!password.equals(rpassword)) {
            addMessage(model, "Two passwords are inconsistent");
        } else {
            User user = userService.getUserByUserName(username);
            if (user == null) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setOrganizationId("1");
                user.setRoleIdsStr("3,");
                passwordHelper.encryptPassword(user);
                userService.save(user);
                addMessage(model, "registration success");
                url = "login";
            } else {
                addMessage(model, "Account already exists");
            }
        }
        return url;
    }

}
