/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aojiaoo.core.shiro.session;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.utils.CacheUtils;
import com.aojiaoo.utils.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author puremdq
 * 重写 DefaultWebSessionManager
 */
public class WebSessionManager extends org.apache.shiro.web.session.mgt.DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        System.out.println("getProtocol:" + request.getProtocol());
        System.out.println("getParameterMap:" + request.getParameterMap().toString());
        Serializable sessionId = null;

        if (request instanceof HttpServletRequest) {
            String token = ((HttpServletRequest) request).getHeader(GlobalProperties.TOKEN_NAME);
            if (StringUtils.isNotBlank(token)) {
                Object temp = CacheUtils.get(GlobalProperties.TOKEN_SESSION_CACHE_NAME, token);
                sessionId = temp == null ? null : temp.toString();
            }
        }

        if (sessionId == null) {
            sessionId = super.getSessionId(request, response);
        }
        return sessionId;
    }

}
