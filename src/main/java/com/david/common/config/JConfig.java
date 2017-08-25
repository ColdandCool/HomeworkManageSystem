package com.david.common.config;

import com.david.common.spring.SpringUtils;
import com.david.common.utils.PropertiesLoader;
import com.david.sys.entity.Config;
import com.david.sys.service.ConfigService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Jcc global configuration
 *
 * @author David
 */
public class JConfig {

    /**
     * Save global attribute values
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * The properties file loads the object
     */
    private static PropertiesLoader loader = new PropertiesLoader("config.properties");

    /**
     * Configure the service
     */
    private static ConfigService configService = SpringUtils.getBean(ConfigService.class);

    /**
     * Get configuration
     */
    public static String getConfig(String key) {
        String value = null;
        //Get configuration service information
        if ("david.name".equals(key)) {
            Config config = configService.getConfigInfo("sys", "common", "project.name");
            if (config != null) {
                value = config.getConfigValue();
            }
        } else if ("david.copyright".equals(key)) {
            Config config = configService.getConfigInfo("sys", "common", "project.copyright");
            if (config != null) {
                value = config.getConfigValue();
            }
        } else {
            value = map.get(key);
            if (value == null) {
                value = loader.getProperty(key);
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
        }
        return value;
    }

    /**
     * Get the configuration file
     *
     * @param sysName
     * @param moduleName
     * @param configName
     * @return
     */
    public static Config getConfig(String sysName, String moduleName, String configName) {
        Config config = configService.getConfigInfo(sysName, moduleName, configName);
        return config;
    }

    public static final String CURRENT_USER = "user";
    public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
    public static final String MESSAGE = "message";
    public static final String PARAM_DIGEST = "digest";
    public static final String PARAM_USERNAME = "username";
    public static String FILEUPLOAD = "file.upload";
}
