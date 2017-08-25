package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Permissions Dao interface
 * 
 * @author David
 */
@MyBatisDao
public interface IRoleDao extends ICrudDao<Role> {

    /**
     * Get Permission Resource ID
     * @param roleIds
     * @return
     */
    public List<Role> getRoles(@Param("roleIds") String[] roleIds);

}
