package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.Homework;
import org.apache.ibatis.annotations.Param;

/**
 * Created by David on 2017/8/27.
 */
@MyBatisDao
public interface IHomeworkDao extends ICrudDao<Homework> {
    int hasUpload(@Param("homeworkid")String homeworkid, @Param("userid")String userid);
}
