package com.name.project.demo.dao;

import java.util.List;

import com.name.project.common.dao.IBaseDao;
import com.name.project.demo.model.UserModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:31:13 
 *
 */
public interface IUserDao extends IBaseDao<UserModel>{

	public UserModel getUser(String userName, String password);
	
}
