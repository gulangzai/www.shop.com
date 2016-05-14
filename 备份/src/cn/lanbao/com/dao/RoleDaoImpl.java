package cn.lanbao.com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import cn.lanbao.com.dto.TreeNode;
import cn.lanbao.com.jopo.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public List<TreeNode> getListByPid(int pid) {
		// TODO Auto-generated method stub
		List<TreeNode> treeNodes = null;
		try {
			Session session = this.getSession();
			 List<Role> roles = session.createQuery("from Role where pid=?")
					.setParameter(0, pid)
					.list(); 
			 
			System.out.println("pid:"+pid); 
			 treeNodes = new ArrayList<TreeNode>();
			 
			 for(Role role:roles){
					TreeNode treeNode = new TreeNode();
					treeNode.setId(String.valueOf(role.getRoleId()));
					treeNode.setText(role.getRoleName());
					treeNode.setChecked(role.getChecked());
					treeNode.setParent_id(role.getPid());
					//System.out.println("role---"+role.getRoleName());
					if(getChildrents(Integer.parseInt(treeNode.getId())).size()==0){
						treeNode.setState("open");
					}else{
						treeNode.setState("closed");
					}
					Map attribute = new HashMap();
					attribute.put("url", role.getUrl());
					treeNode.setAttributes(attribute);
					treeNodes.add(treeNode);
			 }
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return treeNodes;
	}
	
	public List<Role> getChildrents(int pid)throws Exception{
		Session session = this.getSession();
		 List<Role> roles = session.createQuery("from Role where pid=?")
				.setParameter(0, pid)
				.list(); 
		 List<TreeNode> treeNodes = new ArrayList<TreeNode>(); 
		 return roles;
	}
	
	
}
