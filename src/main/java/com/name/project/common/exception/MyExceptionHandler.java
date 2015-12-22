package com.name.project.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月7日 下午1:52:41 
 *
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			return new ModelAndView("error/error-business", model);
		} else if(ex instanceof ParameterException) {
			return new ModelAndView("error/error-parameter", model);
		} else if(ex instanceof SecurityException) {
			return new ModelAndView("error/error-security", model);
		} else {
			return new ModelAndView("error/error", model);
		}
	}
}