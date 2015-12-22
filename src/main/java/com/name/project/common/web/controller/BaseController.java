package com.name.project.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;




import com.name.project.common.exception.BusinessException;
import com.name.project.common.exception.ParameterException;
import com.name.project.common.utils.page.PageResultSet;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月30日 下午8:52:28 
 *
 */
public class BaseController {

	public void initPageResult(ModelMap modelMap, PageResultSet userPageResult) {
		//设置返回数据
		modelMap.addAttribute("data", userPageResult.getList());
		//设置分页信息
		modelMap.addAttribute("recordsTotal",userPageResult.getPage().getTotalRow());
		modelMap.addAttribute("recordsFiltered",userPageResult.getPage().getTotalRow());
	}
	
/*	统一通过专门的异常捕获类进行处理所有的异常信息，所以这里就不用再进行处理
 * @ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		
		request.setAttribute("ex", ex);
		
		// 根据不同错误转向不同页面
		if(ex instanceof BusinessException) {
			return "error/error-business";
		}else if(ex instanceof ParameterException) {
			return "error/error-parameter";
		} else {
			return "error/error";
		}
	}*/
}
