package cn.lanbao.com.service;

import java.util.List;

import cn.lanbao.com.dto.TreeNode;
import cn.lanbao.com.jopo.Role;

public interface RoleService {
	 
	public List<TreeNode> getListByPid(int pid);
	
	public void save(Role role);

	public void delete(Role role);

	public void update(Role role);
	
}
