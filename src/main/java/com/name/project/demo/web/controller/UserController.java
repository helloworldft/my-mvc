package com.name.project.demo.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.name.project.common.Constants;
import com.name.project.common.core.SystemControllerLog;
import com.name.project.common.utils.Base64Utils;
import com.name.project.common.utils.DesUtils;
import com.name.project.common.utils.MD5;
import com.name.project.common.utils.page.PageResultSet;
import com.name.project.common.web.controller.BaseController;
import com.name.project.common.web.core.UserPurviewValidate;
import com.name.project.common.web.core.UserSession;
import com.name.project.common.web.core.UserSessionValidate;
import com.name.project.demo.model.UserModel;
import com.name.project.demo.service.IUserService;

/**
 * @author ft
 * @version 1.0
 * @date 2015年11月30日 下午8:52:28 
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;
	

	@RequestMapping("/addUser")
	@SystemControllerLog(category = "用户管理", description = "新增用户")
	public String addUser(UserModel userModel, Model model) {
		userModel.setPassword(MD5.encryptMD5(userModel.getPassword()));
		userModel.setCreateUser("admin");
		userModel.setCreateTime(new Date());
		userModel.setPassword(MD5.encryptMD5(userModel.getPassword()));
		userModel.setCreateTime(new Date());
		userService.save(userModel);

		return "redirect:/user/index";
	}
	
	@SystemControllerLog(category = "用户管理", description = "用户登录")
	@UserSessionValidate(validate = false)
	@UserPurviewValidate(validate = false)
	@RequestMapping("/userLogin")
	public String userLogin(UserModel userModel, Model model, HttpSession session, HttpServletResponse req, HttpServletResponse resp) {

		userModel.setPassword(MD5.encryptMD5(userModel.getPassword()));
		
		UserModel user = userService.getUserByCondition(userModel);
		if (user == null) {
			model.addAttribute("errorInfo", "you password is error!");
			return "/user/login";
		}
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
		session.setAttribute(UserSession.USER_SESSION_KEY, userSession);
		
		return "redirect:/user/index";
	}

	@RequestMapping("/index")
	public ModelAndView index(UserModel userModel, HttpServletRequest request) {
		return new ModelAndView("/user/userList");
	}

	// 返回json
	@RequestMapping(value="/getJsonUserList",produces={"application/json"})
	@ResponseBody
	public ModelMap getJsonUserList(@RequestParam(required = false, defaultValue = "0") Integer start, 
			@RequestParam(required = false, defaultValue = "10") Integer length, 
			UserModel userModel, HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();

		PageResultSet<UserModel> userPageResult = userService.getUserList((start / length) + 1, length, userModel);
		
		initPageResult(modelMap, userPageResult);

		return modelMap;
	}
}
