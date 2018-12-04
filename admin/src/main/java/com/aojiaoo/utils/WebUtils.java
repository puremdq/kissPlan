package com.aojiaoo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;

/**
 * web操作工具类
 *
 * @author puremdq
 */
public class WebUtils {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }


    /**
     * 给浏览器写数据
     *
     * @param writer response
     * @param body   body
     */
    public static void writeBody(Writer writer, String body) {
        try {
            writer.write(StringUtils.trimToEmpty(body));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
