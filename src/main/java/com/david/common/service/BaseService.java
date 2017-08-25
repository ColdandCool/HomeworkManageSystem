package com.david.common.service;

import com.david.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service base class
 *
 * @author David
 */
@Transactional(readOnly = true)
public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Data permission filtering
     *
     * @param user
     * @return
     */
    public static String dataScopeFilter(User user) {

        return null;
    }

}

