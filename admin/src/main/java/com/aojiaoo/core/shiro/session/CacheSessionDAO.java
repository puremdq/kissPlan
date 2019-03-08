//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.aojiaoo.core.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;

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

    /*调readSession  如果返回为空才会调用doReadSession*/
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        return super.readSession(sessionId);
    }


    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }
}
