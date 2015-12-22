package com.name.project.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.name.project.common.service.impl.BaseService;
import com.name.project.common.utils.StringUtils;
import com.name.project.common.utils.page.Page;
import com.name.project.common.utils.page.PageResultSet;
import com.name.project.demo.dao.IUserDao;
import com.name.project.demo.model.UserModel;
import com.name.project.demo.service.IUserService;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:09:40 
 *
 */
@Service("userService")
public class UserService extends BaseService<UserModel> implements IUserService {

	@Resource
	IUserDao userDao;
	
	@Override
	public UserModel getUserByCondition(UserModel userModel) {
		String userName = userModel.getUserName();
		String password = userModel.getPassword();
		
		return userDao.getUser(userName, password);
	}

	@Override
	public PageResultSet<UserModel> getUserList(int pageNum, int pageSize, UserModel userModel) {
		StringBuffer hql = new StringBuffer("from UserModel u where 1 = 1");
		List<Object> paramList = new ArrayList<>();
		
		if (!StringUtils.isNullOrEmpty(userModel.getUserName())) {
			hql.append(" and userName like '%:userName%'");
			paramList.add(userModel.getUserName());
		}
		
		Long totalRow = userDao.getCount(hql.toString(), paramList.toArray());
		
		Page pages = new Page(totalRow.intValue(), pageSize, pageNum);
		
		List<UserModel> userList = userDao.getPage(pageNum, pageSize, hql.toString(), paramList.toArray());
		
		PageResultSet<UserModel> pageResultSet = new PageResultSet<UserModel>();

		pageResultSet.setList(userList);

		pageResultSet.setPage(pages);

		return pageResultSet;
	}

}
