package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.HomeworkComment;

/**
 * Created by David on 2017/8/27.
 */
@MyBatisDao
public interface IHomeworkCommentDao extends ICrudDao<HomeworkComment> {
}
