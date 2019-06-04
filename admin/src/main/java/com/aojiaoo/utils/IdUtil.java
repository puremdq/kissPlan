
package com.aojiaoo.utils;

import java.util.UUID;


public class IdUtil {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 查看当前传入的id是否是有效的id
     *
     * @param id id
     * @return boolean
     */
    public static boolean isValidId(Integer id) {
        return (id != null && id > 0);
    }


    /**
     * 查看当前传入的id是否是有效的id
     *
     * @param id id
     * @return boolean
     */
    public static boolean isInValidId(Integer id) {
        return !isValidId(id);
    }
}
