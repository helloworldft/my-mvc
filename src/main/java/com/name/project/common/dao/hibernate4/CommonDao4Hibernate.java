package com.name.project.common.dao.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.name.project.common.dao.ICommonDao;
import com.name.project.common.model.BaseModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月1日 上午9:54:56 
 *
 */
@Repository("commonDao4H")
@SuppressWarnings("all")
public class CommonDao4Hibernate implements ICommonDao{

	@Resource
	public SessionFactory sessionFactory;
	
	public Session getSession() {
        return sessionFactory.getCurrentSession();
    } 
	@Override
	public <T extends BaseModel> T save(T model) {
		 getSession().save(model);
	     return model;
	}

	@Override
	public <T extends BaseModel> void saveOrUpdate(T model) {
		getSession().saveOrUpdate(model);
	}

	@Override
	public <T extends BaseModel> void update(T model) {
		getSession().update(model);
		
	}

	@Override
	public <T extends BaseModel> void merge(T model) {
		getSession().merge(model);
		
	}

	@Override
	public <T extends BaseModel, PK extends Serializable> void delete(
			Class<T> entityClass, PK id) {
		getSession().delete(get(entityClass, id));
	}

	@Override
	public <T extends BaseModel> void deleteObject(T model) {
		getSession().delete(model);
	}

	@Override
	public <T extends BaseModel, PK extends Serializable> T get(
			Class<T> entityClass, PK id) {
		return (T)getSession().get(entityClass, id);
	}

	@Override
	public <T extends BaseModel> int countAll(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	public <T extends BaseModel> List<T> listAll(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return criteria.list();
	}

	@Override
	public <T extends BaseModel> List<T> listByPage(Class<T> entityClass, int pageNO, int pageSize) {
		Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(pageSize * (pageNO - 1));
        criteria.setMaxResults(pageSize);
        return criteria.list();
	}

}
