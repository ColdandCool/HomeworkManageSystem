package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.sys.dao.ISessionsDao;
import com.david.sys.entity.Sessions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* session管理 Service层
* @author david.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class SessionService extends CrudService<ISessionsDao, Sessions> {

    /**
     * 新增会话
     * @param sessions
     * @return
     */
    @Transactional(readOnly = false)
    public int add(Sessions sessions){
        return dao.insert(sessions);
    }

    /**
     * 更新会话
     * @param sessions
     * @return
     */
    @Transactional(readOnly = false)
    public int update(Sessions sessions){
        return dao.update(sessions);
    }

}
