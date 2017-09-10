package com.david.sys.controller.syssetting;

import com.david.common.BaseController;
import com.david.common.JsonMapper;
import com.david.common.Page;
import com.david.common.ResultVo;
import com.david.common.utils.CacheUtils;
import com.david.common.utils.JStringUtils;
import com.david.common.utils.UserUtils;
import com.david.sys.entity.Role;
import com.david.sys.entity.User;
import com.david.sys.service.PasswordHelper;
import com.david.sys.service.RoleService;
import com.david.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${adminPath}/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordHelper passwordHelper;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        User entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = userService.get(id);
        }
        if (entity == null) {
            entity = new User();
        }
        return entity;
    }

    /**
     * 用户列表
     *
     * @param model
     * @param page
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "/list")
    public String list(User user, Model model, Page<User> page) {
        page.setEntity(user);
        model.addAttribute("page", page.setList(userService.findPage(page)));
        return "sys/user/list";
    }

    /**
     * 用户列表
     *
     * @param model
     * @param page
     * @return
     */
    @RequiresPermissions("homework:homework:teamadd")
    @RequestMapping(value = "/teamuser")
    public String teamuser(Model model, Page<User> page) {
        //LLL 小组管理人员
        page.setEntity(UserUtils.getLoginUser());
        model.addAttribute("page", page.setList(userService.findTeamUsersPage(page)));
        return "sys/user/list";
    }

    /**
     * 小组成员新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/teamuserAddPage", method = RequestMethod.GET)
    public String teamuserAddPage(User user, Model model) {
        model.addAttribute("teamUser", user);
        model.addAttribute("userLsit", userService.findNoTeamUsers(user.getId()));
        return "sys/user/teamuserAdd";
    }

    /**
     * 小组成员新增
     *
     * @return
     */
    @RequestMapping(value = "/teamuserAdd", method = RequestMethod.GET)
    public String teamuserAdd(User user) {
        user.setTeamleaderId(UserUtils.getLoginUser().getId());
        logger.info("user info : {}", JsonMapper.toJsonString(user));
        userService.save(user);
        return "redirect:" + adminPath + "/user/teamuser";
    }

    /**
     * 小组成员新增
     *
     * @return
     */
    @RequestMapping(value = "/teamUserRemove", method = RequestMethod.GET)
    public String teamUserRemove(User user) {
        user.setTeamleaderId("-1");
        userService.save(user);
        return "redirect:" + adminPath + "/user/teamuser";
    }

    /**
     * 用户新增
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(User user, Model model) {
        Role role = new Role();
        role.setUser(UserUtils.getLoginUser());
        model.addAttribute("roleList", roleService.findList(role));
        model.addAttribute("user", user);
        return "sys/user/edit";
    }

    /**
     * 用户新增保存
     *
     * @param user
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user, RedirectAttributes redirectAttributes) {
        passwordHelper.encryptPassword(user);
        userService.save(user);
        addMessage(redirectAttributes, "Success");
        return "redirect:" + adminPath + "/user/update?id=" + user.getId();
    }

    /**
     * 用户修改
     *
     * @param user
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(User user, Model model) {
        Role role = new Role();
        role.setUser(UserUtils.getLoginUser());
        model.addAttribute("roleList", roleService.findList(role));
        model.addAttribute("user", user);
        return "sys/user/edit";
    }

    /**
     * 用户修改保存
     *
     * @param user
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user, RedirectAttributes redirectAttributes) {
        //不是部门经理，不能修改，超级管理员可以修改，自己的信息可以修改
        User loginUser = UserUtils.getLoginUser();
        if (loginUser.isAdmin() || user.getId().equals(loginUser.getId()) || loginUser.getIsDept()) {
            userService.save(user);
            CacheUtils.remove(user.getUsername());
            addMessage(redirectAttributes, "Success");
        } else {
            addMessage(redirectAttributes, "Cannot Modify");
        }
        return "redirect:" + adminPath + "/user/update?id=" + user.getId();
    }

    /**
     * 用户删除
     *
     * @param user
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(User user, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        if (user.isAdmin() && user.getId().equals(UserUtils.getLoginUser().getId())) {
            //不能删除超级用户，和当前登录用户
            addMessage(redirectAttributes, "Cannot Delete");
        } else {
            userService.delete(user);
            CacheUtils.remove(user.getUsername());
            addMessage(redirectAttributes, "Success");
        }
        return "redirect:" + adminPath + "/user/list" + "?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

    /**
     * 修改密码
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "sys/user/changePassword";
    }


    /**
     * 修改密码保存
     *
     * @param id
     * @param password
     * @param newPassword
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") String id, String password, String newPassword, RedirectAttributes redirectAttributes) {
        try {
            userService.changePassword(id, password, newPassword);
            addMessage(redirectAttributes, "Success");
            return "redirect:" + adminPath + "/user/" + id + "/changePassword";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addMessage(redirectAttributes, "Fail");
            return "redirect:" + adminPath + "/user/" + id + "/changePassword";
        }
    }

    /**
     * 修改密码保存
     *
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultVo changePassword(String newPassword) {
        ResultVo resultVo = null;
        try {
            userService.changePassword(UserUtils.getLoginUser(), newPassword);
            resultVo = new ResultVo(ResultVo.SUCCESS, "1", "Success", null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultVo = new ResultVo(ResultVo.FAILURE, "-1", "Fail", null);
        }
        return resultVo;
    }

    /**
     * 用户资料
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model) {
        User user = UserUtils.getLoginUser();
        model.addAttribute("user", user);
        return "sys/user/config-userInfo";
    }

    /**
     * 保存用户资料
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    public String saveUserInfo(User user, Model model) {
        userService.save(user);
        CacheUtils.remove(user.getUsername());
        model.addAttribute("user", user);
        addMessage(model, "Success");
        return "sys/user/config-userInfo";
    }

    /**
     * 用户选择弹出界面
     *
     * @param users 选择用户列表
     * @param user
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/selectUser")
    public String selectUser(String[] users, User user, Model model, Page<User> page) {
        page.setEntity(user);
        model.addAttribute("page", page.setList(userService.findPage(page)));
        model.addAttribute("users", users);
        return "sys/user/selectUser";
    }

    /**
     * 获取用户列表
     *
     * @param users
     * @return
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVo getUsers(String[] users) {
        ResultVo resultVo = null;
        try {
            List<Map> list = userService.getUsers(users);
            resultVo = new ResultVo(ResultVo.SUCCESS, "1", "Success", list);
        } catch (Exception e) {
            logger.error("获取用户列表调用失败", e.getMessage());
            resultVo = new ResultVo(ResultVo.FAILURE, "-1", "Fail", null);
        }
        return resultVo;
    }

}
