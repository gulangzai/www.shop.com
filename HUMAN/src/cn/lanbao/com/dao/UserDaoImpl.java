package cn.lanbao.com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.lanbao.com.jopo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(user);
	}

	public List<User> getPageUserLists(int cunPage, int pageSize) {
		// TODO Auto-generated method stub
		Query q = hibernateTemplate.getSessionFactory().openSession()
				.createQuery("from User")
				.setFirstResult((cunPage-1)*pageSize)
				.setMaxResults(pageSize);
		System.out.println(cunPage + "  pageSize:" + pageSize);
		return q.list();
	}
	
	/**/
	public List<User> getListForPage(final String hql,final int offset,final int length){
		List list = hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException,SQLException{
				Query query = session.createQuery(hql);
				query.setFirstResult((offset-1)*length);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			} 
		});
		return list;
/*	Session session = hibernateTemplate.getSessionFactory().openSession();
				String sql="SELECT * FROM ( SELECT A.*, ROWNUM RN  FROM (SELECT * FROM t_user) A WHERE ROWNUM <= 4 ) WHERE RN >= 1"; 
				Query query = session.createSQLQuery(sql); 
				List list = query.list();
				return list; 
				
				 //String ahql = "FROM ( SELECT NAME,AGE,SEX ROWNUM RN  FROM (SELECT NAME,AGE,SEX FROM User) A WHERE ROWNUM <= :offset )  WHERE RN >= :length ";

	String ahql = "SELECT * FROM ( SELECT User.*, ROWNUM RN  FROM (SELECT * FROM User) User WHERE ROWNUM <= 4 ) WHERE RN >= 1";

	
				Query query = session.createQuery(ahql);	
*/
			/*	query.setParameter("offset", offset);

				query.setParameter("length",length) ;*/
				
				/*List list = query.list();*/
				 
	}

	public int getPageCount() {
		Query query = hibernateTemplate.getSessionFactory().openSession()
				.createQuery("select count(*) from User");
		Long l = (Long) query.uniqueResult();
		return l.intValue();
	}

/*	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(user);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(new User(id));
	}*/

 
	@Override
	public List<User> serachUser(User user) {
		// TODO Auto-generated method stub
		String hql = "from User where id = id  ";
	/*	if (user.getId() != 0) {
			hql = hql + " and id = " + user.getId();
		}*/
		if (user.getAge() != 0) {
			hql = hql + " and age = " + user.getAge();
		}
		if (user.getName() != null) {
			hql = hql + " and name = '" + user.getName() + "'";
		}
		
		if(user.getSex()!=null&&!user.getSex().equals("")){
			hql = hql + " and sex = " + user.getSex();
		}
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		List<User> obj = session.createQuery(hql).list();
		return obj;
	}
	
	public List<User> searchUser(User user){
		 Session s = hibernateTemplate.getSessionFactory().openSession();
		 Criteria c = s.createCriteria(User.class);
		   /* if (user.getId() != 0) {
			    c.add(Restrictions.eq("id",user.getId()));
			}*/
			if (user.getAge() != 0) {
				 c.add(Restrictions.eq("age",user.getAge()));
			}
			
			if(user.getSex()!=null){
				c.add(Restrictions.eq("sex", user.getSex()));
			}
			
			if (user.getName() != null) {
				c.add(Restrictions.eq("name",user.getName()));
			}
		 List<User> users = c.list();
		 return users;
	}

}
