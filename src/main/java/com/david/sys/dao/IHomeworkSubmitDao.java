package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.HomeworkSubmit;
import org.apache.ibatis.annotations.Param;

/**
 * Created by David on 2017/9/9.
 */
@MyBatisDao
public interface IHomeworkSubmitDao extends ICrudDao<HomeworkSubmit> {

    HomeworkSubmit findByHomeworkIDandUserID(@Param("homeworkID") String homeworkID, @Param("userID") String userID);
}
