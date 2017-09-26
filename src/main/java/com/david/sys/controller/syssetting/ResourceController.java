package com.david.sys.controller.syssetting;

import com.david.common.BaseController;
import com.david.common.Page;
import com.david.common.utils.JStringUtils;
import com.david.sys.entity.Resource;
import com.david.sys.entity.enums.ResourceEnum;
import com.david.sys.service.ResourceService;
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

import java.util.List;

/**
 * Resource controller
 *
 * @author David
 */
@Controller
@RequestMapping("${adminPath}/resource")
public class ResourceController extends BaseController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    UserService userService;

    @ModelAttribute("types")
    public ResourceEnum[] resourceTypes() {
        return ResourceEnum.values();
    }

    @ModelAttribute
    public Resource get(@RequestParam(required = false) String id) {
        Resource entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = resourceService.get(id);
        }
        if (entity == null) {
            entity = new Resource();
        }
        return entity;
    }

    /**
     * List
     *
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"sys:resource:view"})
    @RequestMapping()
    public String list(Resource resource, Model model, Page<Resource> page) {
        Resource resource1 = new Resource();
        resource1.setType(ResourceEnum.menu);
        List<Resource> resourceList = resourceService.findList(resource1);
        model.addAttribute("resourceList", resourceList);
        if (JStringUtils.isBlank(resource.getId()) && resourceList.size() > 0) {
            resource.setId("1");
        }
        page.setEntity(resource);
        model.addAttribute("page", page.setList(resourceService.findPage(page)));
        return "sys/resource/list";
    }

    /**
     * Jump to new page
     *
     * @param resource
     * @param model
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Resource resource, Model model) {
        Resource resource1 = new Resource();
        resource1.setResource(resource);
        model.addAttribute("resource", resource1);
        return "sys/resource/edit";
    }

    /**
     * Go to the Edit page
     *
     * @param resource
     * @param model
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Resource resource, Model model) {
        model.addAttribute("resource", resource);
        return "sys/resource/edit";
    }

    /**
     * modify
     *
     * @param resource
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Resource resource, RedirectAttributes redirectAttributes) {
        resourceService.save(resource);
        addMessage(redirectAttributes, "Success");
        return "redirect:" + adminPath + "/resource/update?id=" + resource.getId();
    }

    /**
     * remove
     *
     * @param resource
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Resource resource, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        if (!"1".equals(resource.getId())) {
            //Before deciding whether there is a lower node
            int count = resourceService.findNext(resource);
            if (count > 0) {
                addMessage(redirectAttributes, "Fail.If there are subnodes, delete the child nodes first");
            } else {
                resourceService.delete(resource);
                addMessage(redirectAttributes, "Success");
            }
        } else {
            addMessage(redirectAttributes, "Super administrator can delete");
        }
        return "redirect:" + adminPath + "/resource?id=" + resource.getParentId() + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

}
