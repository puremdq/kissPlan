package com.aojiaoo.utils;

import org.ehcache.Cache;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.core.EhcacheManager;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoadingException;
import org.ehcache.spi.loaderwriter.CacheWritingException;
import org.ehcache.xml.XmlConfiguration;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class CacheUntil {


    private static EhcacheManager ehcacheManager = null;
    private static XmlConfiguration xmlConfiguration = null;

    private CacheUntil() {
    }

    /*start cacheManager相关*/
    public static EhcacheManager getEhcacheManager() {
        if (ehcacheManager == null) {
            throw new IllegalStateException("未设置缓存manager,请设置后调用");
        }
        return ehcacheManager;
    }

    public static XmlConfiguration getXmlConfigurationr() {
        if (xmlConfiguration == null) {
            throw new IllegalStateException("未设置xmlConfiguration,请设置后调用");
        }
        return xmlConfiguration;
    }

    public static void setEhcacheManager(EhcacheManager ehcacheManager) {
        CacheUntil.ehcacheManager = ehcacheManager;
    }

    public static void setEhcacheManager(String resource) {

    }
    /*end cacheManager相关*/


    /*start cache 对象相关*/
    public static Cache<String, Object> getCache(String cacheName) {
        Cache<String, Object> cache = getEhcacheManager().getCache(cacheName, String.class, Object.class);
        if (cache == null) {
            cache = createCache(cacheName);
        }
        return cache;
    }

    private static Cache<String, Object> createCache(String name) {

        if (xmlConfiguration == null || ehcacheManager == null) {
            throw new IllegalStateException("未设置xmlConfiguration，请先调用load进行设置");
        }

        try {
            CacheConfigurationBuilder<String, Object> configurationBuilder = xmlConfiguration.newCacheConfigurationBuilderFromTemplate(
                    "default", String.class, Object.class);
            CacheConfiguration<String, Object> cacheConfiguration = configurationBuilder.build();
            return ehcacheManager.createCache(name, cacheConfiguration);
        } catch (Exception e) {
            throw new CacheLoadingException(e);
        }
    }

    public static void remove(String cacheName) {
        getEhcacheManager().removeCache(cacheName);
    }

    public static void clear(String cacheName) {
        getCache(cacheName).clear();
    }
    /*end cache 对象相关*/


    public static Object get(String cacheName, String key) throws CacheLoadingException {
        return getCache(cacheName).get(key);
    }

    public static void put(String cacheName, String key, Object value) throws CacheWritingException {
        getCache(cacheName).put(key, value);
    }

    public static boolean containsKey(String cacheName, String key) {
        return getCache(cacheName).containsKey(key);
    }


    public static void remove(String cacheName, String key) throws CacheWritingException {
        getCache(cacheName).remove(key);
    }

    public static Map<String, Object> getAll(String cacheName, Set<String> keys) throws BulkCacheLoadingException {
        return getCache(cacheName).getAll(keys);
    }

    public static void putAll(String cacheName, Map<String, Object> entries) throws BulkCacheWritingException {
        getCache(cacheName).putAll(entries);
    }

    public static void removeAll(String cacheName, Set<String> keys) throws BulkCacheWritingException {
        getCache(cacheName).removeAll(keys);
    }


    public static Object putIfAbsent(String key, Object value) throws CacheLoadingException, CacheWritingException {
        return null;
    }

    public static boolean remove(String cacheName, String key, Object value) throws CacheWritingException {
        return getCache(cacheName).remove(key, value);
    }

    public static Object replace(String cacheName, String key, Object value) throws CacheLoadingException, CacheWritingException {
        return getCache(cacheName).replace(key, value);

    }

    public static EhcacheManager load(String resource) {
        if (StringUtils.isBlank(resource)) {
            throw new IllegalArgumentException("非法的参数，[resource]不能为空");
        }
        try {
            xmlConfiguration = new XmlConfiguration(ResourceUtil.getURL(resource));
            ehcacheManager = new EhcacheManager(xmlConfiguration);
            ehcacheManager.init();
            return ehcacheManager;
        } catch (FileNotFoundException e) {
            throw new CacheLoadingException(e);
        }
    }
}
