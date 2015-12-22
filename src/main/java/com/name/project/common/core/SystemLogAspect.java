package com.name.project.common.core;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.name.project.common.web.core.UserSession;

/**
 * @author ft
 * @version 1.0
 * @date 2015年12月3日 下午9:59:53
 *
 */
@Aspect
@Component
public class SystemLogAspect {
    private long startTimeMillis = 0; // 开始时间  
    private long endTimeMillis = 0; // 结束时间 
    
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SystemLogAspect.class);

	// Service层切点
	@Pointcut("@annotation(com.name.project.common.core.SystemServiceLog)")
	public void serviceAspect() {

	}

	// Controller层切点
	@Pointcut("@annotation(com.name.project.common.core.SystemControllerLog)")
	public void controllerAspect() {

	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 * @throws Throwable 
	 */
	@Around("controllerAspect()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		//开始时间
		long startTimeMillis = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		UserSession userSession = (UserSession) session
				.getAttribute(UserSession.USER_SESSION_KEY);
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			LOGGER.info("-------------------前置通知开始-------------------");
			LOGGER.info("会话标识：{}", session.getId());
			LOGGER.info("请求开始：{}", startTimeMillis);
			LOGGER.info("请求方法:"
					+ (pjp.getTarget().getClass().getName() + "."
							+ pjp.getSignature().getName() + "()"));
			LOGGER.info("请求类别:" + getControllerMethodCategory(pjp));
			LOGGER.info("请求描述:"
					+ getControllerMethodDescription(pjp));
			LOGGER.info("请求者:" + ((userSession != null && userSession.getUser() != null) ? userSession.getUser().getUserName() : "匿名用户"));
			LOGGER.info("请求IP:" + ip);

			LOGGER.info("-------------------前置通知结束-------------------");
		} catch (Exception e) {
			LOGGER.error("===================前置通知异常===================");
			
			LOGGER.error("异常信息:{}", e.getMessage());
			
			e.printStackTrace();
		}
		Object result = pjp.proceed();
		
		long endTimeMillis = System.currentTimeMillis();

		LOGGER.info("-------------------后置通知开始-------------------");
		LOGGER.info("会话标识：{}", session.getId());
		LOGGER.info("请求开始：{}，请求结束：{}，耗时：{}", startTimeMillis, endTimeMillis, endTimeMillis - startTimeMillis);
		LOGGER.info("返回：", result);
		LOGGER.info("-------------------后置通知结束-------------------");
		
		return result;
	}
	/*@AfterReturning(pointcut="controllerAspect()", returning="result")
	public void doAfter(Object result) {
		endTimeMillis = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		LOGGER.info("-------------------后置通知开始-------------------");
		LOGGER.info("会话标识：{}", session.getId());
		LOGGER.info("请求开始：{}，请求结束：{}，耗时：{}", startTimeMillis, endTimeMillis, endTimeMillis - startTimeMillis);
		LOGGER.info("返回：", result);
		LOGGER.info("-------------------后置通知结束-------------------");
	}*/
	
	@AfterThrowing(pointcut = "controllerAspect()",throwing="e")  
    public void doException(JoinPoint jp,Exception e){  
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		LOGGER.error("-------------------系统异常-------------------");
		LOGGER.error("会话标识：{}", session.getId());
		LOGGER.error("异常信息：", e.getMessage());
		LOGGER.error("-------------------系统异常-------------------");
		e.printStackTrace();
    }
	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodCategory(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String category = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					category = method.getAnnotation(SystemControllerLog.class)
							.category();
					break;
				}
			}
		}
		return category;
	}
}
