package cn.lanbao.com.dao;

import java.util.List;

import cn.lanbao.com.jopo.User;

public interface UserDao extends BaseDao<User>{
	
	/*public void save(User user) ;*/

	public List<User> getPageUserLists(int page, int rows);

	/*public void updateUser(User user);
  
	public void deleteUserById(int id);*/
	
	public int getPageCount();

	public List<User> serachUser(User user);

	public List<User> getListForPage(String string, int page, int rows);
}
