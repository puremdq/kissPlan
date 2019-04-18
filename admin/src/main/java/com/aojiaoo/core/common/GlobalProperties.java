package com.aojiaoo.core.common;

import com.aojiaoo.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

public interface GlobalProperties {


    int SUCCESS = 1;//成功
    int FAIL = 0;//失败

    int TRUE = 1;
    int FALSE = 0;

    String TOKEN_NAME = "kiss_plan_token";
    String IS_NEED_TOKEN_NAME = "is_need_token";
    String TOKEN_SESSION_CACHE_NAME = "token_session_cache";
    String TOKEN_USERID_CACHE_NAME = "token_userid_cache_name";
    String CONNECTED_SOCKET_USER_CACHE_NAME = "connected_socket_user_cache_name";
    String USER_CACHE_NAME = "user_cache_name";

    String IDENTIFY_TYPE_USERNAME = "username";
    String IDENTIFY_TYPE_EMAIL = "email";

    static String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        PropertiesUtil.load("classpath:application.properties");
        return PropertiesUtil.get(key);
    }

}
