package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.sys.dao.IHomeworkDao;
import com.david.sys.entity.Homework;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author David
 */
@Service
@Transactional(readOnly = true)
public class HomworkService extends CrudService<IHomeworkDao, Homework> {
}
