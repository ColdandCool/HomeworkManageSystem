package com.david.sys.controller;

import com.david.common.BaseController;
import com.david.common.ResultVo;
import com.david.common.spring.SpringMailUtil;
import com.david.common.utils.RandomUtils;
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
 * Log in to the controller
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
     * Get the asynchronous login screen
     *
     * @param response
     * @return
     */
    @RequestMapping("/ajaxLogin")
    public String ajaxLogin(HttpServletResponse response) {
        ResultVo resultVo = null;
        //Get user login information Verify login, return login information
        String userName = UserUtils.getLoginUserName();
        if (userName != null) {
            resultVo = new ResultVo(ResultVo.SUCCESS, "1", "login successful", null);
            return renderString(response, resultVo);
        } else {
            return "ajaxLogin";
        }
    }

    /**
     * Login processing
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        //ajax login failed handling
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            ResultVo resultVo = new ResultVo(ResultVo.FAILURE, "0", "Login failed", null);
            return renderString(response, resultVo);
        }
        //Get User Login Information Verify that you are logged in and go to the Administration page
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
     * User registration
     *
     * @param username  username
     * @param password  password
     * @param rpassword password confirm
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String email, String password, String rpassword, Model model) {
        String url = "register";
        if (username.contains("& lt;") || username.contains("& gt;")) {
            addMessage(model, "No <> is allowed in the username");
            return url;
        } else if (!password.equals(rpassword)) {
            //To determine whether the password is repeated
            addMessage(model, "Two passwords are inconsistent");
            return url;
        } else {
            User user = userService.getUserByUserName(username);
            if (user == null) {
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setRoleIdsStr("2,");
                passwordHelper.encryptPassword(user);
                userService.save(user);
                addMessage(model, "registration success");
                return "login";
            } else {
                addMessage(model, "Account already exists");
                return url;
            }
        }
    }

    /**
     * forget password
     *
     * @return
     */
    @RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
    public String forgetpassword() {
        return "forgetpassword";
    }

    @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
    public String sendmail(String email, Model model) {
        User user = userService.getUserByUserEmail(email);
        if (user == null) {
            addMessage(model, "There is no email Account");
            return "forgetpassword";
        }
        String newPassword = RandomUtils.randomString(6);
        try {
            userService.changePassword(user, newPassword);
            String context = "the new password is :" + newPassword + ",please log in and change the password!";
            SpringMailUtil.sendTextMail(email, "Retrieve the password", context);
        } catch (Exception e) {
            logger.error("send mail error,ther msg is :{}", e.toString());
            addMessage(model, "The server is out Please try again later");
            return "forgetpassword";
        }
        addMessage(model, "A new password has been sent to the " + email + ". Please sign in again");
        return "forgetpassword";
    }

}
