package com.ccthanking.framework.taglib;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.coreapp.orgmanage.MenuManager;

/**
 * 项层菜单显示.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-5-27
 * 
 */
public class MenuSpywTag extends TagSupport {

	private static String contextPath = null;

	// 回调js函数
	private String pid;

	private String doYwMenu(){
		String menuTreeHtml = "";

		MenuVo[] menus = getYwxxMenu("");
		if(menus!=null&&menus.length>0){
			for (int i = 0; i < menus.length; i++) {
				String icss = menus[i].getImage();
				if (icss == null || "".equalsIgnoreCase(icss)) {
					icss = "icon-briefcase";
				}
				// menuTreeHtml
				// +="<h3><i class=\"icon-briefcase icon-white\"></i>"+menus[i].getTitle()+"</h3>\r\n";
				menuTreeHtml += " <li class=\"open\" lType=\"out\" appName=\"" + menus[i].getName() + "\"> ";
				if (!"#".equals(menus[i].getLocation())) {
					menuTreeHtml += "<a href=\"#\">";
				} else {
					menuTreeHtml += "<a href=\"#\">";
				}
				menuTreeHtml += " <i class=\"" + icss + " icon-white\"></i>  <span class=\"title\">"
						+ menus[i].getTitle() + "</span> </a>\r\n";

				MenuVo[] menus_two = getYwxxMenu(menus[i].getName());
				// ----------------
				if (menus_two != null && menus_two.length > 0) {
					menuTreeHtml += "<ul style=\"display: block;\" class=\"sub-menu\" >\r\n";// style=\"height:400px;display:;overflow:auto;\"
					for (int j = 0; j < menus_two.length; j++) {
						// menuTreeHtml
						// +="<h4><a href=\"#\" onclick=\"menutree_click('"+menus_two[j].getName()+"','"+menus_two[j].getLocation()+"','"+menus_two[j].getTitle()+"','"+menus_two[j].getTarget()+"')\">"+menus_two[j].getTitle()+"</a></h4>\r\n";
						menuTreeHtml += "<li appName=\"" + menus[i].getName()
								+ "\"> <a href=\"#\"onclick=\"menutree_click('" + menus_two[j].getName() + "','"
								+ menus_two[j].getLocation() + "','" + menus_two[j].getTitle() + "','"
								+ menus_two[j].getTarget() + "')\">" + menus_two[j].getTitle() + "</a>\r\n";

						
							menuTreeHtml += "<div></div>\r\n";

					}
					menuTreeHtml += "</ul>\r\n";
				}
				menuTreeHtml += "</li>\r\n";
			}
			
		}
		System.out.println(menuTreeHtml);
		return menuTreeHtml;
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
			pageContext.getOut().write(doYwMenu());
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

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public MenuVo[] getYwxxMenu(String p_yw_uid){
		MenuVo[] menus = null;
		List<MenuVo> mus = new ArrayList<MenuVo>();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select spyw_uid, p_spyw_uid, spywmc, spywlx, "
			+"yzmc, sfzlc, menuid, clurl, multi_y, event_uid, enabled, "
			+"describe, created_uid, created_name, created_date, update_uid, "
			+"update_name, update_date, serial_no, sxlb from bu_sp_ywxx";
			if(StringUtils.isNotBlank(p_yw_uid)){
				sql+= " where p_spyw_uid = "+p_yw_uid;
			}else{
				sql+= " where p_spyw_uid is null";
			}

			sql+=" order by  SERIAL_NO";
			String[][] list = DBUtil.query(conn, sql); 
			if (list!=null) {
				menus = new MenuVo[list.length];
				for (int i = 0; i < list.length; i++) {
					if("24".equals(list[i][0])){
						System.out.println(1);
					}
					MenuVo menu = new MenuVo();
					menu.setName(list[i][0]);
					menu.setTitle(list[i][2]);
					menu.setParent(StringUtils.isNotBlank(p_yw_uid)?list[i][1]:"xmgl");
					// add by wuxp
					menu.setOrderNo(list[i][0]);
					// add by wuxp
					menu.setTarget("pagearea");
					
//						menu.setLocation("ywsqIndex/"+list[i][0]);
						menu.setLocation("ywsqView/"+list[i][0]+":"+list[i][8]);
					menu.setLayersno(list[i][6]);
					menu.setChief(list[i][9]);
					menus[i] = menu;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return menus;
	}
}
