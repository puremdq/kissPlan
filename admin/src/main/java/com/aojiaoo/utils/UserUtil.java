package com.aojiaoo.utils;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;

import java.security.Principal;

public class UserUtil {

    public static Integer getCurrentUserId() {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            return user.getId();
        } catch (Exception e) {
            return null;
        }
    }


    public static User getCurrentUser() {
        try {
            return (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public static Principal getPrincipal() {
        try {
            return (Principal) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public static User getUserById(Integer id) {

        Object obj = CacheUtils.get(GlobalProperties.USER_CACHE_NAME, String.valueOf(id));
        if (obj instanceof User) {
            return (User) obj;
        }

        UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            CacheUtils.put(GlobalProperties.USER_CACHE_NAME, String.valueOf(id), user);
        }
        return user;
    }

    public static boolean isRemembered() {
        if (!UserUtil.isAuthenticated()) {
            return false;
        }
        return SecurityUtils.getSubject().isRemembered();
    }

    public static boolean isAuthenticated() {
        return IdUtil.isValidId(getCurrentUserId());
    }
}
