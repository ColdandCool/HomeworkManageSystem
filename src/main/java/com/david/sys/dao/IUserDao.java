package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User Dao interface
 * 
 * @author David
 */
@MyBatisDao
public interface IUserDao extends ICrudDao<User> {

	/**
	 * Obtain user information based on user name
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUserName(@Param("username") String username);

	/**
	 * Get the user list
	 * @param users
	 * @return
	 */
	public List<Map> getUsers(@Param("users") String[] users);

}
