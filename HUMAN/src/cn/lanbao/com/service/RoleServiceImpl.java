package cn.lanbao.com.service;

import java.util.List;

import cn.lanbao.com.dao.RoleDao;
import cn.lanbao.com.dto.TreeNode;
import cn.lanbao.com.jopo.Role;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	 
	public RoleDao getRoleDao() {
		return roleDao;
	}
 
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}



	@Override
	public List<TreeNode> getListByPid(int pid) {
		// TODO Auto-generated method stub
		return roleDao.getListByPid(pid);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

}
