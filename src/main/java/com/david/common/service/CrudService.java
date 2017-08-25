package com.david.common.service;

import com.david.common.BaseEntity;
import com.david.common.ICrudDao;
import com.david.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service base class
 *
 * @author David
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends ICrudDao<T>, T extends BaseEntity<T>>
        extends BaseService {

    /**
     * Persistence layer object
     */
    @Autowired
    protected D dao;

    /**
     * Get a single piece of data
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * Get a single piece of data
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * Query list data
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * Total number of queries
     *
     * @return
     */
    public int count(Page<T> page) {
        return dao.count(page);
    }

    /**
     * Query paging data
     *
     * @param page
     * @return
     */
    public List<T> findPage(Page<T> page) {
        page.setTotal(dao.count(page));
        return dao.findPage(page);
    }

    /**
     * Save data (insert or update)
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int save(T entity) {
        if (entity.getIsNewId()) {
            entity.preInsert();
            return dao.insert(entity);
        } else {
            entity.preUpdate();
            return dao.update(entity);
        }
    }

    /**
     * delete data
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public int delete(T entity) {
        return dao.delete(entity);
    }

    /**
     * delete data
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public int delete(String id) {
        return dao.delete(id);
    }

}
