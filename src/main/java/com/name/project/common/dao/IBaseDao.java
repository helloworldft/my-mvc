package com.name.project.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.name.project.common.model.BaseModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:27:35 
 *
 */
public interface IBaseDao<M extends BaseModel> {
	
	public M get(Serializable id);
	
	public Serializable save(M entity) ;
	
	public void update(M entity) ;
	
	public M merge(M entity);
	
	public void saveOrUpdate(M entity) ;
	
	public void deleteObject(M entity) ;
	
	public void delete(Serializable id) ;
	
	public int countAll();
	
	public List<M> listAll();
	
	public List<M> listByPage(int pageNO, int pageSize);
	
	public M getByHql(String hql, Object... values) ;
	
	public M getBySql(String sql, Object... values) ;
	
	public List<M> getList(String hql, Object... values) ;

	public List<M> getListBySql(String sql, Object... values) ;

	public Long getCount(String hql, Object... values) ;
	
	public Long getCountBySql(String sql, Object... values) ;
	
	public List<M> getPage(int pageNum, int pageSize, String hql, Object... values) ;
	
	public List<M> getPageBySql(int pageNum, int pageSize, String sql, Object... values) ;
	
	public List<Object[]> getArray(String hql, Object... values) ;

	public List<Object[]> getArrayBySql(String sql, Object... values) ;

	public List<Object[]> getArrayPage(int pageNO, int pageSize, String hql, Object... values);

	public List<Object[]> getArrayPageBySql(int pageNO, int pageSize, String sql, Object... values) ;

	public Object getUniqueResult(String hql, Object... values) ;

	public Object getUniqueResultBySql(String sql, Object... values) ;
	
	/**
	 * �Զ�����ѯ���Ĳ���ֵ
	 * 
	 * @param query
	 * @param values
	 */
	public void setQueryParameters(Query query, Object... values) ;
	
	/**
	 * �Զ��Բ�ѯ�����з�ҳ
	 * 
	 * @param query
	 * @param pageNO
	 * @param pageSize
	 */
	public void setPageParameter(Query query, int pageNO, int pageSize) ;
	
	/**
	 * ����HQL Query
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public Query createQuery(String hql, Object... values) ;
	/**
	 * ����SQL Query
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public SQLQuery createSQLQuery(String sql, Object... values);
}
