package cn.lanbao.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.lanbao.com.dto.TreeNode;
import cn.lanbao.com.jopo.Role;
import cn.lanbao.com.service.RoleService;
import cn.lanbao.com.util.CommonUtil;

public class RoleController extends MultiActionController{
	
	private RoleService roleService;
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void getRoleList(HttpServletRequest req,HttpServletResponse res){  
		try {
			int pid = 0;
			String pidd = req.getParameter("id"); 
			if(pidd!=null){
				pid = Integer.parseInt(pidd);
			} 
			List<TreeNode>  treeNodes = roleService.getListByPid(pid); 
			String str = JSONArray.fromObject(treeNodes).toString();
			System.out.println("str:-----"+str);
			res.getWriter().write(str);
			res.getWriter().close();
		} catch (Exception e) {
	
			// TODO: handle exception
			e.printStackTrace();
		} 	 	
	}
	
	public void saveRole(HttpServletRequest req,HttpServletResponse res){
		String str = "{\"status\":\"ok\",\"message\":\"添加成功\"}";
		try {
			Role role = (Role) CommonUtil.objectFromReq(Role.class, req);
			roleService.save(role);
			res.getWriter().write(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				res.getWriter().write("{\"message\":\"保存失败\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}
	
	public void deleteRole(HttpServletRequest req,HttpServletResponse res){
		String str = "{\"status\":\"ok\",\"message\":\"删除成功\"}";
		try {
			Role role = (Role) CommonUtil.objectFromReq(Role.class, req);
			System.out.println(role.getRoleId());
			roleService.delete(role);
			res.getWriter().write(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				res.getWriter().write("{\"message\":\"删除失败\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}
	
	public void updateRole(HttpServletRequest req,HttpServletResponse res){
		String str = "{\"status\":\"ok\",\"message\":\"更新成功\"}";
		try {
			Role role = (Role) CommonUtil.objectFromReq(Role.class, req);
			System.out.println(role.getRoleId());
			roleService.update(role);
			res.getWriter().write(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				res.getWriter().write("{\"message\":\"更新失败\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}
}
