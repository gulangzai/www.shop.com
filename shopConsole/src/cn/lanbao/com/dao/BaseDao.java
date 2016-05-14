package cn.lanbao.com.dao;

import java.util.List;

public interface BaseDao<T> {
	
	public  void save(T t);
	 
	public  void update(T t);
	
	public  void delete(T t);
	
	public void deleteById(Long id);
	
	public List<T> getByIds(Long[] ids);
	
	public T getById(Long id);
	
	public List<T> getList();
	
}
