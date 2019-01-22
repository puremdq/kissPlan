package com.aojiaoo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties = null;
    private static String loadedPath = null;//已经加载的path


    /********* start使用全局Properties 相关方法 **********/
    /**
     * 得到全局Properties
     *
     * @return 得到全局Properties
     */
    public static Properties getProperties() {
        if (PropertiesUtil.properties != null) {
            return PropertiesUtil.properties;
        }
        PropertiesUtil.properties = new Properties();
        return PropertiesUtil.properties;
    }


    /**
     * 用全局的 properties 加载 新的文件
     *
     * @param path 要加载的文件的地址
     */
    public static void load(String path) {
        if (loadedPath != null && loadedPath.equals(path)) {
            //已经加载过直接返回
            return;
        }
        System.out.println("-------------加载配置文件 [" + path + "] -------------");
        load(PropertiesUtil.getProperties(), path);
        loadedPath = path;
    }

    /**
     * 得到全局Properties 的某个值
     *
     * @return 得到全局Properties 的某个值
     */
    public static String get(String key) {
        return PropertiesUtil.getProperties().getProperty(key);
    }


    /**
     * 清空全局Properties
     *
     * @return 清空全局Properties
     */
    public static void clearProperties() {
        PropertiesUtil.properties = null;
    }

    /*********  end 使用全局Properties 相关方法**********/


    /**
     * 创建一个新的Properties对象 并加载properties 文件
     *
     * @return 创建一个新的Properties对象  并加载新的的 文件
     */
    public static Properties getProperties(String path) {
        Properties currentProperties = new Properties();
        load(currentProperties, path);
        return currentProperties;
    }


    /**
     * 为外部传入的 Properties 加载path
     *
     * @param currentProperties 要加载进入的 Properties
     * @param path              要加载的文件 可以是classpath
     * @return 是否加载成功
     */
    public static Boolean load(Properties currentProperties, String path) {

        if (currentProperties == null) {
            return false;
        }

        if (!path.endsWith(".properties")) {
            return false;
        }
        path = FileUtils.getFilePathByClasspathOrSelf(path);
        try {
            currentProperties.load(new FileInputStream(path));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
