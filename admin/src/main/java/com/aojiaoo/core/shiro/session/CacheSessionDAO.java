//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.aojiaoo.core.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

public class CacheSessionDAO extends CachingSessionDAO {

    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        return null;
    }

    protected void doUpdate(Session session) {
    }

    protected void doDelete(Session session) {
    }
}
