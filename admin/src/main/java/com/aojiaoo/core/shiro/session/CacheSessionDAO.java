//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.aojiaoo.core.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * 重写CachingSessionDAO 管理session
 */
public class CacheSessionDAO extends org.apache.shiro.session.mgt.eis.CachingSessionDAO {


    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }


    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }
}
