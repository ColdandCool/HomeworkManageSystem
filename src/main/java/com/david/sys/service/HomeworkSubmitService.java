package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.sys.dao.IHomeworkCommentDao;
import com.david.sys.dao.IHomeworkSubmitDao;
import com.david.sys.entity.HomeworkSubmit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * Created by david on 2017/9/6.
 */
@Service
@Transactional(readOnly = true)
public class HomeworkSubmitService extends CrudService<IHomeworkSubmitDao, HomeworkSubmit> {


    public HomeworkSubmit findByHomeworkIDandUserID(String homeworkID, String userID) {
        return dao.findByHomeworkIDandUserID(homeworkID, userID);
    }

    @Override
    @Transactional(readOnly = false)
    public int save(HomeworkSubmit entity) {
        if (entity.getIsNewId()) {
            HomeworkSubmit old = findByHomeworkIDandUserID(entity.getHomeworkId(), entity.getUserid());
            if (old == null) {
                entity.preInsert();
                return dao.insert(entity);
            } else {
                entity.setId(old.getId());
                new File(old.getFileUrl()).delete();
                return dao.update(entity);
            }
        } else {
            return dao.update(entity);
        }
    }
}
