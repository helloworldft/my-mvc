package com.name.project.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.name.project.common.dao.ICommonDao;
import com.name.project.common.model.BaseModel;
import com.name.project.common.service.ICommonService;
import com.name.project.common.utils.page.Page;
import com.name.project.common.utils.page.PageResultSet;


/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:17:50 
 *
 */
public class CommonService implements ICommonService {
	@Autowired
    private ICommonDao commonDao4H;


    public <M extends BaseModel> M save(M model) {
        return commonDao4H.save(model);
    }

    public <M extends BaseModel> void saveOrUpdate(M model) {
    	commonDao4H.saveOrUpdate(model);
        
    }
    
    public <M extends BaseModel> void update(M model) {
    	commonDao4H.update(model);
    }

	@Override
	public <M extends BaseModel> void merge(M model) {
		commonDao4H.merge(model);
		
	}

	@Override
	public <M extends BaseModel, PK extends Serializable> void delete(
			Class<M> entityClass, PK id) {
		commonDao4H.delete(entityClass, id);
		
	}

	@Override
	public <M extends BaseModel> void deleteObject(M model) {
		commonDao4H.deleteObject(model);
		
	}

	@Override
	public <M extends BaseModel, PK extends Serializable> M get(
			Class<M> entityClass, PK id) {
		return commonDao4H.get(entityClass, id);
	}

	@Override
	public <M extends BaseModel> int countAll(Class<M> entityClass) {
		return commonDao4H.countAll(entityClass);
	}

	@Override
	public <M extends BaseModel> List<M> listAll(Class<M> entityClass) {
		return commonDao4H.listAll(entityClass);
	}

	@Override
	public <M extends BaseModel> PageResultSet<M> listByPage(Class<M> entityClass,
			int pageNO, int pageSize) {
		
		int totalRow = commonDao4H.countAll(entityClass);
		
		Page pages = new Page(totalRow, pageSize, pageNO);
		
		List<M> resultList = commonDao4H.listByPage(entityClass, pageNO, pageSize);
		
		PageResultSet<M> pageResultSet = new PageResultSet<M>();

		pageResultSet.setList(resultList);

		pageResultSet.setPage(pages);
		
		return pageResultSet;
		
	}
}
