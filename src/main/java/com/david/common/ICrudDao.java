package com.david.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO支持类实现
 *
 * @param <T>
 * @author David
 */
public interface ICrudDao<T> extends IBaseDao {

    /**
     * get one data
     *
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * get one data
     *
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * get list data
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * get total data
     *
     * @return
     */
    public int count(@Param("page") Page<T> page);

    /**
     * get page data
     *
     * @param page
     * @return
     */
    public List<T> findPage(@Param("page") Page<T> page);

    /**
     * insert data
     *
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * update data
     *
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * delete data  by ogject
     *
     * @param entity
     * @return
     */
    public int delete(T entity);

    /**
     * delete data by id
     *
     * @param id
     * @return
     */
    public int delete(String id);

}