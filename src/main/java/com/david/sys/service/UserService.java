package com.david.sys.service;

import com.david.common.Page;
import com.david.common.service.CrudService;
import com.david.common.utils.CacheUtils;
import com.david.common.utils.JStringUtils;
import com.david.common.utils.UserUtils;
import com.david.sys.dao.IUserDao;
import com.david.sys.entity.Role;
import com.david.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User service
 *
 * @author David
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<IUserDao, User> {

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private RoleService roleService;

    /**
     * change Password
     *
     * @param userId
     * @param newPassword
     */
    @Transactional(readOnly = false)
    public void changePassword(String userId, String password, String newPassword) throws Exception {
        User user = dao.get(userId);
        String oldPassword = user.getPassword();
        user.setPassword(password);
        String myPassword = passwordHelper.getPassword(user);
        if (oldPassword.equals(myPassword)) {
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            save(user);
        } else {
            throw new RuntimeException("The original password is wrong");
        }
    }

    /**
     * Modify the login password
     *
     * @param user
     * @param newPassword
     */
    @Transactional(readOnly = false)
    public void changePassword(User user, String newPassword) throws Exception {
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        save(user);
    }

    /**
     * Find its role based on user name
     *
     * @return
     */
    public Set<String> findRoles() {
        User user = UserUtils.getLoginUser();
        if (user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(user.getRoleIds().toArray(new String[0]));
    }

    /**
     * Gets the collection of role objects for the specified user
     *
     * @param userId 用户名
     * @return
     */
    public List<Role> findRolesOfUser(String userId) {
        User user = get(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        List<Role> roles = new ArrayList<Role>();
        for (String roleId : user.getRoleIds()) {
            roles.add(roleService.get(roleId));
        }
        return roles;
    }

    /**
     * Find its permissions based on user name
     *
     * @return
     */
    public Set<String> findPermissions() {
        User user = UserUtils.getLoginUser();
        if (user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new String[0]));
    }

    /**
     * Get the user based on the login name
     *
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName) {
        if (JStringUtils.isBlank(userName)) {
            return null;
        }
        User user = null;
        try {
            user = (User) CacheUtils.get(userName);
            if (user == null) {
                user = dao.getUserByUserName(userName);
                CacheUtils.put(userName, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get the user based on the login email
     *
     * @param email
     * @return
     */
    public User getUserByUserEmail(String email) {
        if (JStringUtils.isBlank(email)) {
            return null;
        }
        User user = null;
        try {
            user = (User) CacheUtils.get(email);
            if (user == null) {
                user = dao.getUserByUserEmail(email);
                CacheUtils.put(email, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get the user list
     *
     * @param users
     * @return
     */
    public List<Map> getUsers(String[] users) {
        return dao.getUsers(users);
    }

    public List<User> findTeamUsersPage(Page<User> page) {
        User user = UserUtils.getLoginUser();
        if (user == null){
            return Collections.emptyList();
        }
        page.setTotal(dao.findTeamUsersCount(user.getId()));
        return dao.findTeamUsersPage(page,user.getId());
    }

    public List<User> findNoTeamUsers(String id) {
        return dao.findNoTeamUsers(id);
    }
}
