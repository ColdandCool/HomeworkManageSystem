package com.david.common.utils;

import com.david.common.config.JConfig;
import com.david.common.spring.SpringUtils;
import org.apache.shiro.cache.CacheManager;

/**
 * Cache utils
 *
 * @author david.cn
 */
public class CacheUtils {

    private static CacheManager cacheManager = SpringUtils.getBean("cacheManager");

    private static final String SYS_CACHE = JConfig.getConfig("homework.default.cache");

    /**
     * Get the SYS_CACHE cache
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * Write the SYS_CACHE cache
     *
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * Remove from the SYS_CACHE cache
     *
     * @param key
     * @return
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * Get cache
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        return getCache(cacheName).get(key);
    }

    /**
     * Write cache
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * Remove from cache
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * Get a cache
     *
     * @param cacheName
     * @return
     */
    private static org.apache.shiro.cache.Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

}
