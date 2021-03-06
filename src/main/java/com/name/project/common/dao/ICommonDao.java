package com.name.project.common.dao;

import java.io.Serializable;
import java.util.List;

import com.name.project.common.model.BaseModel;



/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月1日 上午9:54:36 
 *
 */
public interface ICommonDao {
	public <M extends BaseModel> M save(M model);

    public <M extends BaseModel> void saveOrUpdate(M model);
    
    public <M extends BaseModel> void update(M model);
    
    public <M extends BaseModel> void merge(M model);

    public <M extends BaseModel, PK extends Serializable> void delete(Class<M> entityClass, PK id);

    public <M extends BaseModel> void deleteObject(M model);

    public <M extends BaseModel, PK extends Serializable> M get(Class<M> entityClass, PK id);
    
    public <M extends BaseModel> int countAll(Class<M> entityClass);
    
    public <M extends BaseModel> List<M> listAll(Class<M> entityClass);
    
    public <M extends BaseModel> List<M> listByPage(Class<M> entityClass, int pageNO, int pageSize);
}
