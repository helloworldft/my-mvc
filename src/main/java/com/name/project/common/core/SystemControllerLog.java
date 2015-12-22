package com.name.project.common.core;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**   
 * controller拦截器器
 * @author ft
 * @version 1.0
 * @date 2015年12月4日 上午9:20:43 
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String category() default "";
	String description()  default "";
}
