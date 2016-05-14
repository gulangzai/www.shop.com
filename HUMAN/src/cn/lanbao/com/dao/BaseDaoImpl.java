package cn.lanbao.com.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDaoImpl<T> implements BaseDao<T>{

	HibernateTemplate hibernateTemplate;
	Session session = null;
	Class<T> clazz;
	/*static{
		session = hibernateTemplate.getSessionFactory().openSession();
	}*/
	public BaseDaoImpl(){
		ParameterizedType p = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) p.getActualTypeArguments()[0];
		System.out.println("clazz---"+clazz);
	}
	
	protected Session getSession(){
		return hibernateTemplate.getSessionFactory().openSession();
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(entity);
	}
	@Override
	public void deleteById(Long id){
		Object obj = getById(id);
		if(obj!=null){
			hibernateTemplate.delete(obj);
		}
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub 
		hibernateTemplate.delete(entity);
		System.out.println("......删除操作");
	}
	
	@Override
	public T getById(Long id){
		return (T) hibernateTemplate.get(clazz, id);
	}
	
	@Override
	public List<T> getByIds(Long[] ids){
		return hibernateTemplate
				.getSessionFactory()
				.openSession()
				.createQuery("From "+clazz.getSimpleName()+" where id in(:ids)")
				.setParameter("ids", ids)
				.list();
	}

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		Session  s  = hibernateTemplate.getSessionFactory().openSession();
		List<T> obj  = s
				.createQuery("From "+clazz.getSimpleName())
				.list();
		return obj;
	}
 
 
}
