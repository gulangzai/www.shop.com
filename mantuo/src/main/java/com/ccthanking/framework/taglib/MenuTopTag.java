package com.ccthanking.framework.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.ccthanking.framework.common.AuthorityVo;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.MenuManager;

/**
 * 项层菜单显示.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-5-27
 * 
 */
public class MenuTopTag extends TagSupport {

	private static String contextPath = null;

	// 回调js函数
	private String jsfunc;

	public String getMenuTreeHtml() throws Exception {

		User user = RestContext.getCurrentUser();
		AuthorityVo[] menus = null;
		StringBuilder menuTreeHtml = new StringBuilder();
		menus = getAllowedMenus(user, "");// 显示第一层菜单
		if (menus != null && menus.length > 0) {
			for (int i = 0; i < menus.length; i++) {
				
				menuTreeHtml.append(" <li> ");
				menuTreeHtml.append(" <a id=\"top-"+menus[i].getAuthorityCode()+"\" href=\"javascript:void(0);\" onclick=\"doPage('"+menus[i].getUrl()+"','top-"+menus[i].getAuthorityCode()+"','"+menus[i].getAuthorityUid()+"');\"> ");
				menuTreeHtml.append(" <i class=\""+menus[i].getImage()+"\"></i>");
				menuTreeHtml.append(menus[i].getAuthorityName());
				menuTreeHtml.append(" </a></li> ");
				
/*			   menuTreeHtml.append("<li class=\"green\">");
			   menuTreeHtml.append("<a data-toggle=\"dropdown\" class=\"dropdown-toggle\" href=\"javascript:void(0)\" onclick=\"" + jsfunc + "('" + menus[i].getName()+ "')\">");
			   menuTreeHtml.append("<i class=\"icon-tasks\"></i>");
			   menuTreeHtml.append("<span class=\"badge badge-grey\">"+menus[i].getTitle()+"</span>");
			   menuTreeHtml.append("</a> </li>");*/
			   
			 /**  
				menuTreeHtml.append("<div class=\"Nav text-center DefaultLink\">");
				menuTreeHtml.append("<a href=\"javascript:void(0)\" onclick=\"" + jsfunc + "('" + menus[i].getName()
						+ "')\"><img class=\"DefaultLinkImg\" src=\"" + contextPath + menus[i].getImage() + "\" alt=\""
						+ menus[i].getTitle() + "\"><br>" + menus[i].getTitle() + "</a>");
				menuTreeHtml.append("</div>\n");**/

			}
		}
		// System.out.println(menuTreeHtml);
		return menuTreeHtml.toString();
	}

	protected AuthorityVo[] getAllowedMenus(User user, String parent) throws Exception {
		
		return MenuManager.getInstance().getMenusTop(user);
		
	}

	// 查询第一级菜单.
	private void rollTopMenu(List<MenuVo> list, MenuVo menuVo) throws Exception {
		if ("1".equals(menuVo.getLevelno())) {
			if (!list.contains(menuVo))
				list.add(menuVo);
		} else if (menuVo.getLevelno() != null && Integer.parseInt(menuVo.getLevelno()) > 1
				&& StringUtils.isNotBlank(menuVo.getParent())) {
			menuVo = MenuManager.getInstance().getMenu(menuVo.getParent());
			if (!list.contains(menuVo)) {
				rollTopMenu(list, menuVo);
			}
		}
	}

	public int doEndTag() throws JspTagException {
		try {
			// 获取页面输出流，并输出字符串
			if (contextPath == null) {
				contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			}
			// String appPath = ((HttpServletRequest)
			// pageContext.getRequest()).getContextPath();
			// String base = "";
			// base += "";
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

	public void setJsfunc(String jsfunc) {
		this.jsfunc = jsfunc;
	}

}
