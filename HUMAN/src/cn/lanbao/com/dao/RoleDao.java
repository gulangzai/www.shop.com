package cn.lanbao.com.dao;

import java.util.List;

import cn.lanbao.com.dto.TreeNode;
import cn.lanbao.com.jopo.Role;

public interface RoleDao  extends BaseDao<Role>{

	List<TreeNode> getListByPid(int pid);
	
}
