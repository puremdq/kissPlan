package com.aojiaoo.core.shiro.realm;


import com.aojiaoo.core.security.KissPlanPrincipal;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DbRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /*授权时使用*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        User user = (User) principalCollection.getPrimaryPrincipal();
        System.out.println(user);
        // 根据身份信息获取权限信息
        // 连接数据库...
        // 模拟从数据库获取到数据
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create");// 用户的创建
        permissions.add("items:add");// 商品添加权限
        // ....

        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRole("sd");

        return simpleAuthorizationInfo;
    }

    /*登录时调用*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.getByUserName(userToken.getUsername());
        //若用户不行存在，可以抛出UnknownAccountException
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }


        KissPlanPrincipal kissPlanPrincipal = new KissPlanPrincipal(user.getId(), user.getUsername());

        String afterMd5Password = DigestUtils.md5Hex(String.valueOf(userToken.getPassword()) + user.getSalt());
        userToken.setPassword(afterMd5Password.toCharArray());
        return new SimpleAuthenticationInfo(kissPlanPrincipal, user.getPassword(), getName());
    }

}



