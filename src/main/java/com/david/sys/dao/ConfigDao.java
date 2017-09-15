package com.david.sys.dao;

import com.david.common.ICrudDao;
import com.david.common.annotation.MyBatisDao;
import com.david.sys.entity.Config;

/**
* Common configuration table DAO interface
* @author david.cn
* @version 1.0
*/
@MyBatisDao
public interface ConfigDao extends ICrudDao<Config> {

    /**
     * Get configuration information
     * @param config
     * @return
     */
    Config getConfigInfo(Config config);

}
