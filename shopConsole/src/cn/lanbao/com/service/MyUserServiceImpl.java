package cn.lanbao.com.service;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.lanbao.com.dao.UserDao;
import cn.lanbao.com.jopo.User;

@Service("myUserService")
public class MyUserServiceImpl implements MyUserService {

	private UserDao userDao;
 
		
	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}


	@Override
	public List<User> getUserLists() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		User user = new User("����",20); 
		users.add(user);
		user = new User("С��",20);
		users.add(user); 
		user = new User("zll",25);
		users.add(user);
		return users;
	}


	@Override
	public List<User> getPageUserLists(int page, int rows) {
		// TODO Auto-generated method stub
		 return userDao.getPageUserLists(page,rows);
		//return userDao.getListForPage("from User",page,rows);
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}


	@Override
	public int getPageCount() {
		// TODO Auto-generated method stub
		return userDao.getPageCount();
	}


	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(Long.valueOf(id));
	}

   //��������
	@Override
	public List<User> searchUser(User user) {
		// TODO Auto-generated method stub 
		return userDao.serachUser(user);
	}

}
