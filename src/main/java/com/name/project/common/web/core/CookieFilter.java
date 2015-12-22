package com.name.project.common.web.core;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.name.project.common.Constants;
import com.name.project.common.utils.Base64Utils;
import com.name.project.common.utils.DesUtils;
import com.name.project.demo.model.UserModel;
import com.name.project.demo.service.IUserService;
import com.name.project.demo.service.impl.UserService;



/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月3日 下午9:11:31 
 *
 */
public class CookieFilter implements Filter {
	
	protected WebApplicationContext springContext;
	private FilterConfig config; 
	private final String LAST_LOGIN_TIME = "LastLoginTime";
	private final int MAX_LOGIN_TIME = 5 * 1000;
	private final Map<String, Object> loginCache = new ConcurrentHashMap<String, Object>();
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(CookieFilter.class);

	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		springContext = (WebApplicationContext) config
				.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		LOGGER.info("CookieFilter初始化完成");
		if (springContext == null) {
			throw new ServletException("Check your web.xml setting, no Spring context configured");
		}
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		doCheckCookie((HttpServletRequest)req, (HttpServletResponse)resp);
		chain.doFilter(req, resp);
		
	}
	
	protected void doCheckCookie(HttpServletRequest req , HttpServletResponse resp) {
		
		UserSession userSession = (UserSession)req.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		if (userSession == null) {
			String key = req.getSession().getId();
			
			if(loginCache.containsKey(key)) // 已经在进行登录中
				return;
			
			try {
				loginCache.put(key, key);
				
				Long x = (Long) req.getSession().getAttribute(LAST_LOGIN_TIME);
				
				if(x != null && (System.currentTimeMillis() - x) <= MAX_LOGIN_TIME) {// 小于5秒的不检查,避免过于频繁
					return;
				}
				
				doAction(req, resp);
				
				req.getSession().setAttribute(LAST_LOGIN_TIME, System.currentTimeMillis());
				
			} catch(Exception e) {
				
				LOGGER.error(e.getMessage(), e);
				
			} finally {
				
				loginCache.remove(key);
				
			}
		}
	}
	
	public void doAction(HttpServletRequest req, HttpServletResponse resp) {
		Cookie [] cookie = req.getCookies();
		Cookie cookieUserName = new CookieManager().getCookieValue(cookie, Constants.COOKIE_KEY_USER_NAME);
		Cookie cookiePassword = new CookieManager().getCookieValue(cookie, Constants.COOKIE_KEY_USER_PWD);
		if (cookieUserName != null && cookiePassword != null) {
			String userName = Base64Utils.decode(cookieUserName.getValue());
			String password = new DesUtils().decrypt(cookiePassword.getValue());
			
			IUserService userService = (IUserService)springContext.getBean("userService");
			
			UserModel userModel = new UserModel();
			userModel.setUserName(userName);
			userModel.setPassword(password);
			
			UserModel user = userService.getUserByCondition(userModel);
			
			LOGGER.info("CookieFilter 通过Cookie进行登录校验:{} {}", userName, user != null);
			
			if (user != null) {
				//验证通过
				
				//设置Cookie,保持8小时内自动登录
				Cookie ckUserName = new Cookie(Constants.COOKIE_KEY_USER_NAME, Base64Utils.encode(user.getUserName()));
				int expiry = 60 * 60 * 8;
				ckUserName.setMaxAge(expiry);
				ckUserName.setPath("/");
				Cookie ckPassword = new Cookie(Constants.COOKIE_KEY_USER_PWD, new DesUtils().encrypt(user.getPassword())); 
				ckPassword.setMaxAge(expiry);
				ckPassword.setPath("/");
				
				resp.addCookie(ckUserName);
				resp.addCookie(ckPassword);
				
		        UserSession userSession = new UserSession();
		        userSession.setUser(user);
		        req.getSession().setAttribute(UserSession.USER_SESSION_KEY, userSession);
				
			} else {
				//清除cookie信息
				cookieUserName.setMaxAge(0);
				cookiePassword.setMaxAge(0);
				
				resp.addCookie(cookieUserName);
				resp.addCookie(cookiePassword);
				
			}
			
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
