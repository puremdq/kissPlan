package com.aojiaoo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    public static String getUrl() {
        return getUrl(false);
    }


    public static String getUrl(boolean isNeedParameter) {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getServletPath();

        if (isNeedParameter && request.getQueryString() != null) {
            url = url + "?" + request.getQueryString();
        }

        return url;
    }


    public static String spliceUrl(String url1, String url2) {
        return FileUtils.spliceFilePath(url1, url2);
    }

    public static String spliceUrl(String... url) {
        return FileUtils.spliceFilePath(url);
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
