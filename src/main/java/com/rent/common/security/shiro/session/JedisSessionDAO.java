/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.security.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.google.common.collect.Sets;
import com.rent.common.config.Global;
import com.rent.common.utils.DateUtils;
import com.rent.common.utils.JedisUtils;
import com.rent.common.utils.StringUtils;
import com.rent.common.web.Servlets;

/**
 * 自定义授权会话管理类
 * @author ThinkGem
 * @version 2014-7-20
 */
public class JedisSessionDAO extends AbstractSessionDAO implements SessionDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String sessionKeyPrefix = "shiro_session_";

	@Override
	public void update(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {  
            return;
        }
		
		HttpServletRequest request = Servlets.getRequest();
		if (request != null){
			String uri = request.getServletPath();
			// 如果是静态文件，则不更新SESSION
			if (Servlets.isStaticFile(uri)){
				return;
			}
			// 如果是视图文件，则不更新SESSION
			if (StringUtils.startsWith(uri, Global.getConfig("web.view.prefix"))
					&& StringUtils.endsWith(uri, Global.getConfig("web.view.suffix"))){
				return;
			}
			// 手动控制不更新SESSION
			if (Global.NO.equals(request.getParameter("updateSession"))){
				return;
			}
		}
		
		Jedis jedis = null;
		try {
			
			jedis = JedisUtils.getResource();
			
			// 获取登录者编�?
			PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			String principalId = pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY;
			
			jedis.hset(sessionKeyPrefix, session.getId().toString(), principalId + "|" + session.getTimeout() + "|" + session.getLastAccessTime().getTime());
			jedis.set(JedisUtils.getBytesKey(sessionKeyPrefix + session.getId()), JedisUtils.toBytes(session));
			
			// 设置超期时间
			int timeoutSeconds = (int)(session.getTimeout() / 1000);
			jedis.expire((sessionKeyPrefix + session.getId()), timeoutSeconds);

			logger.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
		} catch (Exception e) {
			logger.error("update {} {}", session.getId(), request != null ? request.getRequestURI() : "", e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			
			jedis.hdel(JedisUtils.getBytesKey(sessionKeyPrefix), JedisUtils.getBytesKey(session.getId().toString()));
			jedis.del(JedisUtils.getBytesKey(sessionKeyPrefix + session.getId()));

			logger.debug("delete {} ", session.getId());
		} catch (Exception e) {
			logger.error("delete {} ", session.getId(), e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
	}
	
	@Override
	public Collection<Session> getActiveSessions() {
		return getActiveSessions(true);
	}
	
	/**
	 * 获取活动会话
	 * @param includeLeave 是否包括离线（最后访问时间大�?3分钟为离线会话）
	 * @return
	 */
	@Override
	public Collection<Session> getActiveSessions(boolean includeLeave) {
		return getActiveSessions(includeLeave, null, null);
	}
	
	/**
	 * 获取活动会话
	 * @param includeLeave 是否包括离线（最后访问时间大�?3分钟为离线会话）
	 * @param principal 根据登录者对象获取活动会�?
	 * @param filterSession 不为空，则过滤掉（不包含）这个会话�??
	 * @return
	 */
	@Override
	public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession){
		Set<Session> sessions = Sets.newHashSet();
		
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
			Map<String, String> map = jedis.hgetAll(sessionKeyPrefix);
			for (Map.Entry<String, String> e : map.entrySet()){
				if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue())){
					
					String[] ss = StringUtils.split(e.getValue(), "|");
					if (ss != null && ss.length == 3){// jedis.exists(sessionKeyPrefix + e.getKey())){
						// Session session = (Session)JedisUtils.toObject(jedis.get(JedisUtils.getBytesKey(sessionKeyPrefix + e.getKey())));
						SimpleSession session = new SimpleSession();
						session.setId(e.getKey());
						session.setAttribute("principalId", ss[0]);
						session.setTimeout(Long.valueOf(ss[1]));
						session.setLastAccessTime(new Date(Long.valueOf(ss[2])));
						try{
							// 验证SESSION
							session.validate();
							
							boolean isActiveSession = false;
							// 不包括离线并符合�?后访问时间小于等�?3分钟条件�?
							if (includeLeave || DateUtils.pastMinutes(session.getLastAccessTime()) <= 3){
								isActiveSession = true;
							}
							// 符合登陆者条件�??
							if (principal != null){
								PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
								if (principal.toString().equals(pc != null ? pc.getPrimaryPrincipal().toString() : StringUtils.EMPTY)){
									isActiveSession = true;
								}
							}
							// 过滤掉的SESSION
							if (filterSession != null && filterSession.getId().equals(session.getId())){
								isActiveSession = false;
							}
							if (isActiveSession){
								sessions.add(session);
							}
							
						}
						// SESSION验证失败
						catch (Exception e2) {
							jedis.hdel(sessionKeyPrefix, e.getKey());
						}
					}
					// 存储的SESSION不符合规�?
					else{
						jedis.hdel(sessionKeyPrefix, e.getKey());
					}
				}
				// 存储的SESSION无Value
				else if (StringUtils.isNotBlank(e.getKey())){
					jedis.hdel(sessionKeyPrefix, e.getKey());
				}
			}
			logger.info("getActiveSessions size: {} ", sessions.size());
		} catch (Exception e) {
			logger.error("getActiveSessions", e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		HttpServletRequest request = Servlets.getRequest();
		if (request != null){
			String uri = request.getServletPath();
			// 如果是静态文件，则不创建SESSION
			if (Servlets.isStaticFile(uri)){
		        return null;
			}
		}
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.update(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {

		Session s = null;
		HttpServletRequest request = Servlets.getRequest();
		if (request != null){
			String uri = request.getServletPath();
			// 如果是静态文件，则不获取SESSION
			if (Servlets.isStaticFile(uri)){
				return null;
			}
			s = (Session)request.getAttribute("session_"+sessionId);
		}
		if (s != null){
			return s;
		}

		Session session = null;
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getResource();
//			if (jedis.exists(sessionKeyPrefix + sessionId)){
				session = (Session)JedisUtils.toObject(jedis.get(
						JedisUtils.getBytesKey(sessionKeyPrefix + sessionId)));
//			}
			logger.debug("doReadSession {} {}", sessionId, request != null ? request.getRequestURI() : "");
		} catch (Exception e) {
			logger.error("doReadSession {} {}", sessionId, request != null ? request.getRequestURI() : "", e);
		} finally {
			JedisUtils.returnResource(jedis);
		}
		
		if (request != null && session != null){
			request.setAttribute("session_"+sessionId, session);
		}
		
		return session;
	}
	
	@Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
    	try{
        	return super.readSession(sessionId);
    	}catch (UnknownSessionException e) {
			return null;
		}
    }

	public String getSessionKeyPrefix() {
		return sessionKeyPrefix;
	}

	public void setSessionKeyPrefix(String sessionKeyPrefix) {
		this.sessionKeyPrefix = sessionKeyPrefix;
	}

}
