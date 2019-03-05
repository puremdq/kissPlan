package com.aojiaoo.utils;

import com.aojiaoo.core.security.KissPlanPrincipal;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;

import java.security.Principal;

public class UserUtil {

    public static Integer getCurrentUserId() {
        try {
            KissPlanPrincipal kissPlanPrincipal = (KissPlanPrincipal) SecurityUtils.getSubject().getPrincipal();
            return kissPlanPrincipal.getId();
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
        UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(id);
        return user == null ? new User() : user;
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
