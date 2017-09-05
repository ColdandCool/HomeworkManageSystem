package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.sys.dao.IHomeworkCommentDao;
import com.david.sys.entity.HomeworkComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by david on 2017/9/6.
 */
@Service
@Transactional(readOnly = true)
public class HomeworkCommentService extends CrudService<IHomeworkCommentDao, HomeworkComment> {


}
