package com.name.project.common.core;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.name.project.common.exception.BusinessException;
import com.name.project.common.web.core.UserPurviewValidate;
import com.name.project.common.web.core.UserSession;
import com.name.project.common.web.core.UserSessionValidate;
import com.name.project.demo.model.UserModel;

/**   
 * 权限校验和会话校验
 * @author ft
 * @version 1.0
 * @date 2015年12月7日 上午10:13:33 
 *
 */
@Aspect
@Component
public class SecurityAspect {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecurityAspect.class);
	@Pointcut("execution(* com.name.project.*.web.controller..*.*(..))")
	public void securityAspect() {
	}
	
	@Around("securityAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		
		return securityCheck(pjp);
		
	}
	
	public Object securityCheck(ProceedingJoinPoint pjp) throws Throwable  {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		UserSession userSession = (UserSession) session
				.getAttribute(UserSession.USER_SESSION_KEY);
		
		// 获取连接点的方法签名对象
		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
		// 连接点对象的方法
		Method method = joinPointObject.getMethod();
		// 获取连接点所在的目标对象
		Object target = pjp.getTarget();
				
		boolean validateUserSession = true; 
		
		if (method.isAnnotationPresent(UserSessionValidate.class)) {
			UserSessionValidate sessionValidate = method.getAnnotation(UserSessionValidate.class);
			
			validateUserSession = sessionValidate.validate();
		}
		
		if (validateUserSession && userSession == null) {
			//会话过期或者未登录
			throw new BusinessException("请重新登录");
		}
		
		// 权限名称
		String permissionName = target.getClass().getSimpleName() + ":" + method.getName();
		
		boolean validatePurview = true;
		if (method.isAnnotationPresent(UserPurviewValidate.class)) {
			UserPurviewValidate purviewValidate = method.getAnnotation(UserPurviewValidate.class);
			
			validatePurview = purviewValidate.validate();
		}
		boolean isPass = !validatePurview;
		if(validatePurview) {
			//校验权限
			LOGGER.info("权限校验：{}", permissionName);
		}
		if(isPass) {
			return pjp.proceed();
		} else {
			return pjp.proceed();//权限相关表还没配置
			//校验不通过
			//throw new BusinessException("权限不足");
		}
	}
	
}
