package com.name.project.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.name.project.common.dao.IBaseDao;
import com.name.project.common.model.BaseModel;
import com.name.project.common.service.IBaseService;
import com.name.project.common.utils.page.Page;
import com.name.project.common.utils.page.PageResultSet;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:19:49 
 *
 */
public class BaseService<M extends BaseModel> implements IBaseService<M> {

	@Autowired
	public IBaseDao<M> baseDao4H;
	
	@Override
	public Serializable save(M model) {
		return baseDao4H.save(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		baseDao4H.saveOrUpdate(model);
		
	}

	@Override
	public void update(M model) {
		baseDao4H.update(model);
		
	}

	@Override
	public M merge(M model) {
		return baseDao4H.merge(model);
		
	}

	@Override
	public void delete(Serializable id) {
		baseDao4H.delete(id);
	}

	@Override
	public void deleteObject(M model) {
		baseDao4H.deleteObject(model);
	}

	@Override
	public M get(Serializable id) {
		return baseDao4H.get(id);
	}

	@Override
	public int countAll() {
		return baseDao4H.countAll();
	}

	@Override
	public List<M> listAll() {
		return baseDao4H.listAll();
	}

	@Override
	public PageResultSet<M> listByPage(int pageNO, int pageSize) {
		
		int totalRow = baseDao4H.countAll();
		
		Page pages = new Page(totalRow, pageSize, pageNO);
		List<M> resultList = baseDao4H.listByPage(pageNO, pageSize);
		
		PageResultSet<M> pageResultSet = new PageResultSet<M>();

		pageResultSet.setList(resultList);

		pageResultSet.setPage(pages);
		
		return pageResultSet;

	}

}
