package com.david.sys.controller.syssetting;

import com.david.common.BaseController;
import com.david.common.Page;
import com.david.common.utils.JStringUtils;
import com.david.common.utils.UserUtils;
import com.david.sys.entity.Role;
import com.david.sys.entity.User;
import com.david.sys.entity.enums.DataScopeEnum;
import com.david.sys.service.ResourceService;
import com.david.sys.service.RoleService;
import com.david.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
     * Authority controller
 *
 * @author David
 */
@Controller
@RequestMapping("${adminPath}/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @ModelAttribute("dataScope")
    public DataScopeEnum[] dataScopeTypes() {
        return DataScopeEnum.values();
    }

    @ModelAttribute
    public Role get(@RequestParam(required = false) String id) {
        Role entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = roleService.get(id);
        }
        if (entity == null) {
            entity = new Role();
        }
        return entity;
    }

    /**
     * Load the list page
     *
     * @param role
     * @param model
     * @param page
     * @return
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping()
    public String list(Role role, Model model, Page<Role> page) {
        role.setUser(UserUtils.getLoginUser());
        page.setEntity(role);
        model.addAttribute("page", page.setList(roleService.findPage(page)));
        return "sys/role/list";
    }

    /**
     * Open the new or edit page
     *
     * @param role
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Role role, Model model) {
        User user = UserUtils.getLoginUser();
        //Resource list
        if (user.isAdmin()) {
            model.addAttribute("resourceList", resourceService.findList(null));
        } else {
            model.addAttribute("resourceList", resourceService.findRoleMenus(userService.findPermissions()));
        }
        model.addAttribute("role", role);
        return "sys/role/edit";
    }

    /**
     * Save data
     *
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.save(role);
        addMessage(redirectAttributes, "Success");
        return "redirect:" + adminPath + "/role/update?id=" + role.getId();
    }

    /**
     * Delete
     *
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Role role, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        // lll
        if (!"1".equals(role.getId())) {
            roleService.delete(role);
            addMessage(redirectAttributes, "Success");
        } else {
            addMessage(redirectAttributes, "Cannot Delete");
        }
        return "redirect:" + adminPath + "/role?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }
}
