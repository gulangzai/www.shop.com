package cn.lanbao.com.service;

import java.util.List;

import cn.lanbao.com.jopo.User;

public interface MyUserService {
	
	public void save(User user);

	public List<User> getUserLists();

	public List<User> getPageUserLists(int page, int rows);
  
	public void updateUser(User user);

	public int getPageCount();

	public void deleteUserById(int id);

	public List<User> searchUser(User user);
}
