package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Resource Dao interface
 * 
 * @author David
 */
@MyBatisDao
public interface IResourceDao extends ICrudDao<Resource> {

	/**
	 * Get the resource sort value
	 * 
	 * @param id
	 * @return
	 */
	public int getResourceSort(@Param("id") String id);

	/**
	 * Get the permission ID
	 * @param resourceIds
	 * @return
	 */
	public List<Resource> getResources(@Param("resourceIds") Set<String> resourceIds);

	/**
	 * Query whether there are child nodes
	 * @param resource
	 * @return
	 */
	public int findNext(Resource resource);

}
