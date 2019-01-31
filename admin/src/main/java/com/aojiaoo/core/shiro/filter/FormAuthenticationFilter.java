package com.aojiaoo.core.shiro.filter;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    /*执行登录*/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

//        System.out.println(request.getParameter("username"));
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }

        String isNeedToken = request.getParameter(GlobalProperties.IS_NEED_TOKEN_NAME);//如果需要token 直接返回实体
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);

            if (!StringUtils.equals("1", isNeedToken)) {
                return onLoginSuccess(token, subject, request, response);
            }

            String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
            String _token = IdGenerator.uuid();
            CacheUtils.put(GlobalProperties.TOKEN_SESSION_CACHE_NAME, _token, sessionId);

            Map<String, Object> resp = new HashMap<>();
            resp.put(GlobalProperties.TOKEN_NAME, _token);
            resp.put("user", UserUtil.getCurrentUser());
//            resp.put(GlobalProperties.TOKEN_NAME, _token);
            ServerResponse serverResponse = ServerResponse.createBySuccess(resp);
            WebUtils.writeBody(response.getWriter(), JsonUtil.toJson(serverResponse));
            return false;

        } catch (AuthenticationException e) {
            e.printStackTrace();
            if (!StringUtils.equals("1", isNeedToken)) {
                return onLoginFailure(token, e, request, response);
            }
            ServerResponse serverResponse = ServerResponse.createByErrorMessage("登录失败");
            WebUtils.writeBody(response.getWriter(), JsonUtil.toJson(serverResponse));
            return false;//不在继续到下一个fitter
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Authentication exception", e);
        }

        this.setFailureAttribute(request, e);
        return true;
    }


    /**
     * 未退出时执行登录  直接将之前的账户退出
     *
     * @param request     request
     * @param response    response
     * @param mappedValue mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        if (isLoginRequest(request, response)) {

            Subject subject = getSubject(request, response);
            if (subject.isAuthenticated()) {
                subject.logout();
            }

//            if (isLoginSubmission(request, response)) {
//                //本次用户登陆账号
//                String username = this.getUsername(request);
//
//                Subject subject = this.getSubject(request, response);
//
//                //之前登陆的用户
//                User user = (User) subject.getPrincipal();
//                //如果两次登陆的用户不一样，则先退出之前登陆的用户
//                if (username != null && user != null && !username.equals(user.getUsername())) {
//                    subject.logout();
//                }
//            }
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }


}