package com.name.project.common.service;

import java.io.Serializable;
import java.util.List;

import com.name.project.common.model.BaseModel;
import com.name.project.common.utils.page.PageResultSet;


/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:20:01 
 *
 */
public interface IBaseService<M extends BaseModel> {
	
	public Serializable save(M model);

    public void saveOrUpdate(M model);
    
    public void update(M model);
    
    public M merge(M model);

    public void delete(Serializable id);

    public void deleteObject(M model);

    public M get(Serializable id);
    
    public int countAll();
    
    public List<M> listAll();
        
    public PageResultSet<M> listByPage(int pageNO, int pageSize);
}
