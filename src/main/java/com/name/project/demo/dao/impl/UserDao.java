package com.name.project.demo.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.name.project.common.dao.hibernate4.BaseDao4Hibernate;
import com.name.project.common.utils.StringUtils;
import com.name.project.demo.dao.IUserDao;
import com.name.project.demo.model.UserModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:31:30 
 *
 */
@Repository("userDao")
public class UserDao extends BaseDao4Hibernate<UserModel> implements IUserDao  {

	@Override
	public UserModel getUser(String userName, String password) {
		String hql = "select u from UserModel u where userName = ? and password = ?";
		return (UserModel)this.getByHql(hql, userName, password);
		
	}

}
