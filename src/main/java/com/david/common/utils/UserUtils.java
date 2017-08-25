package com.david.common.utils;

import com.david.common.spring.SpringUtils;
import com.david.sys.entity.User;
import com.david.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 用户管理工具
 * 
 * @author David
 * 
 */
public class UserUtils {

	/**
	 * 用户服务对象
	 */
	private static UserService userService = SpringUtils.getBean(UserService.class);

	/**
	 * 获取当前访问用户名
	 * 
	 * @return
	 */
	public static String getLoginUserName() {
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		return userName;
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static User getLoginUser() {
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		return userService.getUserByUserName(userName);
	}

}
