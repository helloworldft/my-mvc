package com.name.project.common.dao.hibernate4;



import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.name.project.common.dao.IBaseDao;
import com.name.project.common.model.BaseModel;


/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月1日 上午9:54:56 
 *
 */  
@Repository("baseDao4H")
@SuppressWarnings("all")
public class BaseDao4Hibernate <M extends BaseModel> implements IBaseDao<M> {
	/**
	 * ����һ��Class�Ķ�������ȡ���͵�class
	 */
	public Class<M> clz;
	
	@Resource
	public SessionFactory sessionFactory;
	
	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Class<M> getClz() {
		if (clz == null) {
			clz = ((Class<M>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	
	@Override
	public M get(Serializable id) {
		return (M) getSession().get(getClz(), id);
	}
	
	public Serializable save(M entity) {
		return getSession().save(entity);
	}
	
	public void saveOrUpdate(M entity) {
		getSession().saveOrUpdate(entity);
	}
	
	public M merge(M entity) {
		return (M)getSession().merge(entity);
	}
	
	public void update(M entity) {
		getSession().update(entity);
	}
	
	public void deleteObject(M entity) {
		getSession().delete(entity);
	}
	
	public void delete(Serializable id) {
		deleteObject(get(id));
	}
	
	public int countAll() {
		Criteria criteria = getSession().createCriteria(getClz());
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
	}
	
	public List<M> listAll() {
		Criteria criteria = getSession().createCriteria(getClz());
        criteria.setProjection(Projections.rowCount());
        return criteria.list();
	}
	
	public List<M> listByPage(int pageNO, int pageSize) {
		Criteria criteria = getSession().createCriteria(getClz());
        criteria.setFirstResult(pageSize * (pageNO - 1));
        criteria.setMaxResults(pageSize);
        return criteria.list();
	}
	
	public M getByHql(String hql, Object... values) {
		List<M> list = createQuery(hql, values).list();

		return list == null || list.size() == 0 ? null : list.get(0);
	}
	
	public M getBySql(String sql, Object... values) {
		SQLQuery query = createSQLQuery(sql, values);

		// ����ʵ�����
		query.addEntity(getClz());

		List<M> list = query.list();

		return list == null || list.size() == 0 ? null : list.get(0);
	}
	
	public List<M> getList(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	public List<M> getListBySql(String sql, Object... values) {
		SQLQuery query = createSQLQuery(sql, values);

		// ����ʵ�����
		query.addEntity(getClz());

		return query.list();
	}

	public Long getCount(String hql, Object... values) {
		return new Long ((long)createQuery(hql, values).list().size());
	}
	
	public Long getCountBySql(String sql, Object... values) {
		return new Long ((long)createSQLQuery(sql, values).list().size());
	}
	
	public List<M> getPage(int pageNum, int pageSize, String hql, Object... values) {
		
		Query query = createQuery(hql, values);
		
		setPageParameter(query, pageNum, pageSize);
		
		return query.list();

	}
	
	public List<M> getPageBySql(int pageNum, int pageSize, String sql, Object... values) {
		
		Query query = createSQLQuery(sql, values);
		
		setPageParameter(query, pageNum, pageSize);
		
		return query.list();

	}

	public List<Object[]> getArray(String hql, Object... values) {
		return (List<Object[]>) createQuery(hql, values).list();
	}

	public List<Object[]> getArrayBySql(String sql, Object... values) {
		return (List<Object[]>) createSQLQuery(sql, values).list();
	}

	public List<Object[]> getArrayPage(int pageNO, int pageSize, String hql, Object... values) {
		Query query = createQuery(hql, values);

		// ���÷�ҳ
		setPageParameter(query, pageNO, pageSize);

		return (List<Object[]>) query.list();
	}

	public List<Object[]> getArrayPageBySql(int pageNO, int pageSize, String sql, Object... values) {
		SQLQuery query = createSQLQuery(sql, values);

		// ���÷�ҳ
		setPageParameter(query, pageNO, pageSize);

		return (List<Object[]>) query.list();
	}

	public Object getUniqueResult(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	public Object getUniqueResultBySql(String sql, Object... values) {
		return createSQLQuery(sql, values).uniqueResult();
	}
	
	/**
	 * �Զ�����ѯ���Ĳ���ֵ
	 * 
	 * @param query
	 * @param values
	 */
	public void setQueryParameters(Query query, Object... values) {
		if (values != null) {
			int count = values.length;
			for (int i = 0; i < count; i++) {
				query.setParameter(i, values[i]);
			}
		}
	}
	
	/**
	 * �Զ��Բ�ѯ�����з�ҳ
	 * 
	 * @param query
	 * @param pageNO
	 * @param pageSize
	 */
	public void setPageParameter(Query query, int pageNO, int pageSize) {
		query.setFirstResult(pageSize * (pageNO - 1));
		query.setMaxResults(pageSize);
	}
	
	/**
	 * ����HQL Query
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public Query createQuery(String hql, Object... values) {
		Query query = getSession().createQuery(hql);

		setQueryParameters(query, values);

		return query;
	}
	
	/**
	 * ����SQL Query
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public SQLQuery createSQLQuery(String sql, Object... values) {
		SQLQuery query = getSession().createSQLQuery(sql);

		setQueryParameters(query, values);

		return query;
	}
	
}
