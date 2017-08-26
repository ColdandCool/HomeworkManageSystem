package com.david.common.utils;

import com.david.common.spring.SpringUtils;
import com.david.sys.entity.User;
import com.david.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * User service util
 *
 * @author David
 */
public class UserUtils {

    /**
     * user service
     */
    private static UserService userService = SpringUtils.getBean(UserService.class);

    /**
     * Get the current login user name
     *
     * @return
     */
    public static String getLoginUserName() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userName;
    }

    /**
     * Get the current login user
     *
     * @return
     */
    public static User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userService.getUserByUserName(userName);
    }

}
