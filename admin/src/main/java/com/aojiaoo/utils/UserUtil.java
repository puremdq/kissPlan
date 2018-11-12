package com.aojiaoo.utils;

import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;

public class UserUtil {

    public static User getCurrentUser() {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            return user == null ? new User() : user;
        } catch (Exception e) {
            return new User();
        }

    }

    public static User getUserById(Integer id) {
        UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(id);
        return user == null ? new User() : user;
    }

    public static boolean isRemembered() {
        return SecurityUtils.getSubject().isRemembered();
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
