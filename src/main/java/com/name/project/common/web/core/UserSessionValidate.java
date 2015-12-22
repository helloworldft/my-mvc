package com.name.project.common.web.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * 会员会话校验
 * @author ft
 * @version 1.0
 * @date 2015年12月2日 下午3:47:44 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserSessionValidate {

	/**
	 * 标示符
	 */
	String name() default "";
	
	/**
	 * 是否校验
	 */
	boolean validate() default true;
	
}
