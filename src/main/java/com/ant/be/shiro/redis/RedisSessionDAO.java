package com.ant.be.shiro.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDAO extends AbstractSessionDAO{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// session 在redis过期时间是30分钟30*60
    private static int expireTime = 1800;

    private static String redisPrefix = "shiro-redis-session:";
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private String getKey(String originalKey) {
        return redisPrefix + originalKey;
    }

    // 更新session的最后一次访问时间
	@Override
	public void update(Session session) throws UnknownSessionException {
		logger.debug("updateSession:{}", session.getId().toString());
        String key = getKey(session.getId().toString());
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	// 删除session
	@Override
	public void delete(Session session) {
		logger.debug("delSession:{}", session.getId());
        redisTemplate.delete(getKey(session.getId().toString()));
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		logger.debug("activeSession");
        return Collections.emptySet();
	}

	// 创建session，保存到数据库
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        logger.debug("createSession:{}", session.getId().toString());
        try {
            redisTemplate.opsForValue().set(getKey(session.getId().toString()), session);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return sessionId;
	}

	// 获取session
	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("readSession:{}", sessionId.toString());
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = null ;
        try {
            session = (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return session;
	}

}
