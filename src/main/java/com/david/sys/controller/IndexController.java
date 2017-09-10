package com.david.sys.controller;

import com.david.common.BaseController;
import com.david.common.utils.SystemUtils;
import com.david.sys.entity.Resource;
import com.david.sys.service.ResourceService;
import com.david.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

/**
 * 首页控制器
 *
 * @author David
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class IndexController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        Set<String> permissions = userService.findPermissions();
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        //LLL 后期删除
        model.addAttribute("systemInfo", SystemUtils.getSystemInfo());
        return "welcome";
    }

}
