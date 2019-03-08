package com.aojiaoo.modules.sys.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import com.aojiaoo.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserMapper> {

    public User getByUserName(String username) {
        return this.mapper.selectByUserName(username);
    }

    public User get(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }


    public User getByEmail(String email) {
        return this.mapper.getByEmail(email);
    }

    public Integer register(User user) {

        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword(), user.getEmail())) {
            logger.warn("注册用户：[username={},email={}]失败,参数有误");
            return null;
        }

        Integer i = this.mapper.checkUserNameAndEmail(user);
        if (i != null && i > 0) {
            logger.warn("注册用户：[username={},email={}]失败,用户名或邮箱已存在", user.getUsername(), user.getEmail());
            return null;
        }

        user.setSalt(StringUtils.getRandomString(8));
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + user.getSalt()));
        this.insert(user);
        return user.getId();
    }


}
