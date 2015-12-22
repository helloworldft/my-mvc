package com.name.project.common.web.core;

import com.name.project.demo.model.UserModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月3日 上午10:24:33 
 *
 */
public class UserSession {
	public static final String USER_SESSION_KEY = "USER_SESSION";
	
	private UserModel user ;

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	
	
}
