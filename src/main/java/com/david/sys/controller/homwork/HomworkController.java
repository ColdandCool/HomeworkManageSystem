package com.david.sys.controller.homwork;

import com.david.common.BaseController;
import com.david.common.JsonMapper;
import com.david.common.Page;
import com.david.common.config.JConfig;
import com.david.common.utils.DateUtils;
import com.david.common.utils.JStringUtils;
import com.david.common.utils.UserUtils;
import com.david.sys.entity.Homework;
import com.david.sys.entity.HomeworkComment;
import com.david.sys.entity.HomeworkSubmit;
import com.david.sys.service.HomeworkCommentService;
import com.david.sys.service.HomeworkSubmitService;
import com.david.sys.service.HomworkService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
    @Autowired
    HomeworkCommentService homeworkCommentService;
    @Autowired
    HomeworkSubmitService homeworkSubmitService;

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
        addMessage(redirectAttributes, "Success Delete");
        return "redirect:" + adminPath + "/homework/list?pageNo=" + pageNo + "&pageSize=" + pageSize;
    }

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Homework homework, Model model) {
        // LLL 拿出所有的评论，按照评论时间 排序，

        HomeworkComment homeworkComment = new HomeworkComment();
        homeworkComment.setHomeworkId(homework.getId());
        List<HomeworkComment> comments = homeworkCommentService.findList(homeworkComment);

        model.addAttribute("comments", comments);
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

    @RequiresPermissions("homework:homework")
    @RequestMapping(value = "/addComment/{id}", method = RequestMethod.POST)
    public String addComment(HttpServletResponse response, @PathVariable("id") String id, String comment) {
        HomeworkComment commentObj = new HomeworkComment();
        commentObj.setContent(comment);
        commentObj.setHomeworkId(id);
        commentObj.setUserName(UserUtils.getLoginUserName());
        commentObj.setTime(new Date());

        int count = homeworkCommentService.save(commentObj);
        if (count > 0) {
            return renderString(response, "Success");
        } else {
            return renderString(response, "fail");
        }
    }


    /**
     * 进入修改分数的页面
     *
     * @param homeworkid
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/{id}/submitgrade", method = RequestMethod.GET)
    public String toGradePage(@PathVariable("id") String homeworkid, Model model) {
        HomeworkSubmit submit = homeworkSubmitService.get(homeworkid);
        model.addAttribute("data", homeworkSubmitService.findList(submit));
        return "sys/homework/usergrade";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/setGrade/{id}/{grade}", method = RequestMethod.GET)
    public String submitGrade(@PathVariable("id") String homeworkid, @PathVariable("grade") Integer grade) {
        HomeworkSubmit submit = homeworkSubmitService.get(homeworkid);
        submit.setGrade(grade);
        homeworkSubmitService.save(submit);
        return "redirect:" + adminPath + "/homework/" + submit.getHomeworkId() + "/submitgrade";
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void submitGrade(@PathVariable("id") String id, HttpServletResponse response) {
        HomeworkSubmit submit = homeworkSubmitService.get(id);
        File file = new File(submit.getFileUrl());
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(submit.getFileName().getBytes("UTF-8"), "iso-8859-1"));
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/submithomework/{id}", method = RequestMethod.POST)
    public String submithomwork(@RequestParam("file") MultipartFile file, @PathVariable("id") String homeworkid, HttpServletRequest request) {
        // 获取本地存储路径
        String tempPath = request.getSession().getServletContext().getRealPath(JConfig.getConfig(JConfig.FILEUPLOAD));
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        String path = tempPath + "\\" + homeworkid + "\\";
        String filename = path + file.getOriginalFilename();
        File temp = new File(path);
        if (!temp.exists()) {
            temp.mkdir();
        }
        try {
            file.transferTo(new File(filename));
            HomeworkSubmit submit = new HomeworkSubmit();
            submit.setFileName(file.getOriginalFilename());
            submit.setFileUrl(filename);
            submit.setHomeworkId(homeworkid);
            submit.setUserid(UserUtils.getLoginUser().getId());
            submit.setUsername(UserUtils.getLoginUserName());
            homeworkSubmitService.save(submit);
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
