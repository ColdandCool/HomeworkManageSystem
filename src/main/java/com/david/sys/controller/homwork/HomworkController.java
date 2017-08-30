package com.david.sys.controller.homwork;

import com.david.common.BaseController;
import com.david.common.JsonMapper;
import com.david.common.Page;
import com.david.common.utils.CacheUtils;
import com.david.common.utils.DateUtils;
import com.david.common.utils.JStringUtils;
import com.david.sys.entity.Homework;
import com.david.sys.service.HomworkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * @author David
 */
@Controller
@RequestMapping(value = "${adminPath}/homework")
public class HomworkController extends BaseController {

    @Autowired
    private HomworkService homworkService;

    @ModelAttribute
    public Homework get(@RequestParam(required = false) String id) {
        Homework entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = homworkService.get(id);
        }
        if (entity == null) {
            entity = new Homework();
        }
        return entity;
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Homework homework, Model model, Page<Homework> page) {
        page.setEntity(homework);
        model.addAttribute("page", page.setList(homworkService.findPage(page)));
        return "sys/homework/list";
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Homework homework, Model model) {
        model.addAttribute("homework", homework);
        return "sys/homework/edit";
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String modify(Homework homework, Model model) {
        model.addAttribute("homework", homework);
        return "sys/homework/edit";
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Homework homework, RedirectAttributes redirectAttributes) {
        //LLL 后期需要更改
        homework.setDeadline(DateUtils.parseDate(homework.getDeadlineStr()));
        logger.info("insert into func data is :" + JsonMapper.toJsonString(homework));
        homworkService.save(homework);
        addMessage(redirectAttributes, "Success");
        return "redirect:" + adminPath + "/homework/update?id=" + homework.getId();
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, int pageNo, int pageSize, RedirectAttributes redirectAttributes) {
        Homework homework = homworkService.get(id);
        // LLL delete need to yanzheng data is have
        homworkService.delete(homework);
        addMessage(redirectAttributes,"Success Delete");
        return "redirect:"+ adminPath +"/homework/list?pageNo="+pageNo+"&pageSize="+pageSize;
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Homework homework, Model model) {
        // LLL 拿出所有的评论，按照评论时间 排序，
        model.addAttribute("homework", homework);
        return "sys/homework/detail";
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(Homework homework, Model model) {
        // LLL 进入到上传页面
        model.addAttribute("homework", homework);
        return "sys/homework/submit";
    }

    //LLL todo
    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/submitgrade", method = RequestMethod.GET)
    public String submitgrade(Homework homework, Model model) {
        // LLL 进入到处理分数的页面
        model.addAttribute("homework", homework);
        return "sys/homework/submit";
    }
}
