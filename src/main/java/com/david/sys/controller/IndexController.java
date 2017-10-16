package com.david.sys.controller;

import com.david.common.BaseController;
import com.david.common.utils.HttpClientUtil;
import com.david.sys.entity.Resource;
import com.david.sys.service.ResourceService;
import com.david.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * Home controller
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

    /**
     * according city ID to show Weather API
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/weather/{id}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String id) {
        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            id = "2147714";
        }
        String url = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=43aacfb6fa831b24200a39d2d4351449";
        return HttpClientUtil.doGet(url);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        Set<String> permissions = userService.findPermissions();
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
