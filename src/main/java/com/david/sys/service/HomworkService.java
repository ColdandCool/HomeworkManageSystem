package com.david.sys.service;

import com.david.common.Page;
import com.david.common.service.CrudService;
import com.david.common.utils.UserUtils;
import com.david.sys.dao.IHomeworkDao;
import com.david.sys.entity.Homework;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author David
 */
@Service
@Transactional(readOnly = true)
public class HomworkService extends CrudService<IHomeworkDao, Homework> {

    /**
     * Query paging data
     *
     * @param page
     * @return
     */
    @Override
    public List<Homework> findPage(Page<Homework> page) {
        page.setTotal(dao.count(page));
        List<Homework> result = dao.findPage(page);
        String userid = UserUtils.getLoginUser().getId();

        for (Homework homework : result) {
            //LLL 查看是否已经提交过作业
            int count = dao.hasUpload(homework.getId(), userid);
            if (count != 0) {
                homework.setHasUpload("Yes");
            } else {
                homework.setHasUpload("No");
            }
        }
        return result;
    }


}
