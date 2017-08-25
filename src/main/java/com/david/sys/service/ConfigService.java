package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.common.utils.CacheUtils;
import com.david.sys.dao.ConfigDao;
import com.david.sys.entity.Config;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Common configuration table Service layer
* @author david.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class ConfigService extends CrudService<ConfigDao, Config> {

    /**
     * Get configuration information
     * @param sysName
     * @param moduleName
     * @param configName
     * @return
     */
    public Config getConfigInfo(String sysName, String moduleName, String configName){
        Config config = null;
        try{
            config = (Config) CacheUtils.get(sysName + moduleName + configName);
            if(config==null){
                Config param = new Config();
                param.setSysName(sysName);
                param.setModuleName(moduleName);
                param.setConfigName(configName);
                config = dao.getConfigInfo(param);
                CacheUtils.put(sysName + moduleName + configName, config);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return config;
    }

}
