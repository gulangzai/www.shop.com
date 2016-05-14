package com.ccthanking.framework.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import com.ccthanking.framework.common.AuthorityVo;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.ProjectMenuManager;

public class MenuSubTag extends TagSupport {

	private String projectUid = "";
	
	private String projectUserId = "";
	
	private String root_uid = "";
	
	
	
	public String getProjectUserId() {
		return projectUserId;
	}



	public void setProjectUserId(String projectUserId) {
		this.projectUserId = projectUserId;
	}



	public String getProjectUid() {
		return projectUid;
	}



	public void setProjectUid(String projectUid) {
		this.projectUid = projectUid;
	}


	

	public String getRoot_uid() {
		return root_uid;
	}



	public void setRoot_uid(String root_uid) {
		this.root_uid = root_uid;
	}



	public String getMenuTreeHtml() throws Exception {

		User user = RestContext.getCurrentUser();

		AuthorityVo[] menus_t = null;
		String menuHtml = "";
		menus_t = getAllowedMenus(user, "");
		if (menus_t != null && menus_t.length > 0) {
			for (int f = 0; f < menus_t.length; f++) {
				//第一层菜单不显示
				AuthorityVo[] menus = getAllowedMenus(user, menus_t[f].getAuthorityUid());
				if (menus != null && menus.length > 0) {
					for (int i = 0; i < menus.length; i++) {
						//------------------------------------------第一级菜单组成
						menuHtml += " <li appName= \""+menus[i].getPauthorityUid()+"\" id=\"" + menus[i].getAuthorityUid() + "\" class= > \r\n";//第一级菜单开始
						
						AuthorityVo[] menus_two = getAllowedMenus(user, menus[i].getAuthorityUid());
						//有子菜单的，则添加 <b class=\"arrow fa fa-angle-down\"></b> 否则不添加
						if(menus_two != null && menus_two.length > 0){
							menuHtml += "<a href=\"#\" class=\'dropdown-toggle\'  onclick=\"menutree_click('" + menus[i].getAuthorityUid() + "','"+ menus[i].getUrl() + "','" + menus[i].getAuthorityName() + "','" + menus[i].getTarget()+ "')\" >";
							menuHtml += "<i class=\""+menus[i].getImage()+"\"></i>&nbsp; <span class=\"menu-text\">" + menus[i].getAuthorityName() + "</span> " ;
							menuHtml+= "<b class=\"arrow fa fa-angle-down\"></b></a><b class=\"arrow\"></b>\r\n";
							
						}else{	
							menuHtml += "<a href=\"pageproject/framework/project/frame_project_main#"+menus[i].getUrl()+"\" data-url=\""+menus[i].getUrl()+"\">";
							menuHtml += "<i class=\""+menus[i].getImage()+"\"></i> &nbsp;<span class=\"menu-text\">" + menus[i].getAuthorityName() + "</span> " ;
							menuHtml+= "</a><b class=\"arrow\"></b>\r\n";
						}	
						// ----------------
						if (menus_two != null && menus_two.length > 0) {
							//------------------------------------------第二级菜单组成
							menuHtml += "<ul class=\"submenu\" >\r\n";// 第二级菜单开始
							for (int j = 0; j < menus_two.length; j++) {
								AuthorityVo[] menus_three = getAllowedMenus(user, menus_two[j].getAuthorityUid());		
								//有子菜单的，则添加 <b class=\"arrow fa fa-angle-down\"></b> 否则不添加
								if(menus_three != null && menus_three.length > 0){
									menuHtml += "<li > " +
											" <a href=\"javascript:void(0);\" class=\'dropdown-toggle\' " +
											" onclick=\"menutree_click('" 
												+ menus_two[j].getAuthorityUid() + "','"
												+ menus_two[j].getUrl() + "','" 
												+ menus_two[j].getAuthorityName() + "','"
												+ menus_two[j].getTarget() + "')\">" +
											"<i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_two[j].getAuthorityName()+"<b class=\"arrow fa fa-angle-down\"></b></a>" +
											"<b class=\"arrow\"></b></li>\r\n";
								}else{
									menuHtml += "<li class> " +
											" <a href=\"pageproject/framework/project/frame_project_main#"+menus_two[j].getUrl()+"\" " +
											" data-url=\""+menus_two[j].getUrl()+"\" " +
											" onclick=\"menutree_click('" 
												+ menus_two[j].getAuthorityUid() + "','"
												+ menus_two[j].getUrl() + "','" 
												+ menus_two[j].getAuthorityName() + "','"
												+ menus_two[j].getTarget() + "')\"  >" +
											" <i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_two[j].getAuthorityName()+"</a>" +
											" <b class=\"arrow\"></b></li>\r\n";
								}		
	
							
							}
							menuHtml += "</ul>\r\n";// 第二级菜单结束
						}
						menuHtml += "</li>\r\n";//第一级菜单结束
					}	
				}
			}
		}
//		System.out.println(menuTreeHtml);
		return menuHtml;
	}



/*	protected AuthorityVo[] getAllowedMenus(User user, String parent) throws Exception {
		// 超级用户，看见系统中所有菜单
		if (User.SUPER_USER.equals(user.getPersonKind())) {
			return  ProjectMenuManager.getInstance(this.projectUid).getMenus(parent);
		} else {
			//return user.getAllowedMenus(parent);
			return  ProjectMenuManager.getInstance(this.projectUid).getMenus(parent);
		}
	}*/
	protected AuthorityVo[] getAllowedMenus(User user, String parent) throws Exception {
		// 超级用户，看见系统中所有菜单
		if (User.SUPER_USER.equals(user.getPersonKind())) {
			return new ProjectMenuManager().getMenus(projectUid,projectUserId,root_uid,parent);
		} else {
			//return user.getAllowedMenus(parent);
			return new ProjectMenuManager().getMenus(projectUid,projectUserId,root_uid,parent);
		}
	}
	

	public int doEndTag() throws JspTagException {
		try {
			// 获取页面输出流，并输出字符串
			//String appPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			//String base = "";
			//base += "";
//			pageContext.getOut().write(getMenuTreeHtml() + doYwMenu());
			pageContext.getOut().write(getMenuTreeHtml());
		}
		// 捕捉异常
		catch (IOException ex) {
			// 抛出新异常
			// throw new JspTagException("错误"};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 值回返
		return EVAL_PAGE;
	}


}
