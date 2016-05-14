package cn.lanbao.com.test;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.lanbao.com.dao.UserDao;
import cn.lanbao.com.dao.UserDaoImpl;
import cn.lanbao.com.jopo.Chara;
import cn.lanbao.com.jopo.Department;
import cn.lanbao.com.jopo.Employee;
import cn.lanbao.com.jopo.Role;
import cn.lanbao.com.jopo.User;
import cn.lanbao.com.util.TranscodUtil;

public class UserTest {

	private static SessionFactory sf = null;
	
	static{
		 Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		   sf = cfg.buildSessionFactory();
	}
	
	@Test
	public void testDao(){
		UserDao userDao = new UserDaoImpl(); 
		userDao.getById(1L);
	}
	@Test
	public void testSave() {  
		User user1 = new User("李益",22);
		User user2 = new User("赵瑶瑶",22);
		Session session = sf.openSession();
		Transaction ts = session.beginTransaction();
		session.save(user1);
		session.save(user2);
		ts.commit();
		session.close();
	}
	
	@Test
	public void getUser()throws Exception{
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
	    User user = (User) session.get(User.class, 6);
	    System.out.println(user.toString());
		tx.commit();
		session.close();
	}
	
	@Test
	public void testUser(){
		Configuration cf = new Configuration().configure("hibernate.cfg.xml");
		cf.addClass(User.class);
		Session s = cf.buildSessionFactory().openSession();
		User user1;
		try {
			user1 = new User("小明",22,"女",TranscodUtil.stringToDate("1994-12-11"),TranscodUtil.stringToDate1("2015-12-11 11:21:23"));
			User user2 = new User("小红",22,"女",TranscodUtil.stringToDate("1993-11-21"),TranscodUtil.stringToDate1("2015-12-11 11:21:23")); 
			Transaction tx = s.beginTransaction();
			s.save(user1);
		    s.save(user2);
		    tx.commit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testDepartment(){
		Configuration cf = new Configuration().configure()
				.addClass(Employee.class)
				.addClass(Department.class);
		Session session = cf.buildSessionFactory()
				.openSession();
		
		Transaction tx = session.beginTransaction();
		Department dept = new Department("�з���");
		Employee emp1 = new Employee("����");
		Employee emp2 = new Employee("������");
		dept.getEmployees().add(emp1);
		dept.getEmployees().add(emp2);
		session.save(dept);
		session.save(emp1);
		session.save(emp2); 
		tx.commit(); 
	}
	
	@Test
	public void getDepartment(){
		Configuration cf = new Configuration().configure()
				.addClass(Employee.class)
				.addClass(Department.class);
		Session session = cf.buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		Department dept= (Department) session.get(Department.class,Long.valueOf(1));
		tx.commit();
		Set<Employee> employees = dept.getEmployees();
		for (Employee e : employees) {
			System.out.println(e.getName());		
		}
	} 
	
	@Test
	public void testRole(){
		Configuration cf = new Configuration().configure("hibernate.cfg.xml")
				.addClass(Role.class)
				.addClass(Chara.class);
		Session session = cf.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.get(Role.class, Long.valueOf(1));
		tx.commit(); 
	}
}
