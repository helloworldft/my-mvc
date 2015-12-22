package com.name.project.demo.service;

import org.springframework.stereotype.Service;

import com.name.project.common.service.IBaseService;
import com.name.project.common.utils.page.PageResultSet;
import com.name.project.demo.model.UserModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:09:27 
 *
 */
@Service("userService")
public interface IUserService extends IBaseService<UserModel> {

	public UserModel getUserByCondition(UserModel userModel) ;
	
	public PageResultSet<UserModel> getUserList(int page, int pageSize, UserModel userModel);
	
}
