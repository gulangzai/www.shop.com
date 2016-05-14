package com.ccthanking.framework.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BusinessMenu;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.MenuService;
import com.copj.modules.utils.exception.DaoException;

@Service
public class MenuServiceImpl implements MenuService {

	private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class.getName());

	// 本系统的 所有菜单sql
	private static final String SQL_APPMENU = "select AUTHORITY_NAME, URL from sys_authority where  ENABLED=1 and URL is not null and URL !='#'";

	public String getAllMenu() {
		Connection conn = DBUtil.getConnection();
		String queryDictSql = "SELECT NAME, TITLE, PARENT, ORDERNO, TARGET, LOCATION, "
				+ "LEVELNO, IMAGE, ALTIMAGE, APP_NAME, LAYERSNO, MEMO, LRR, LRSJ, LRBM, "
				+ "LRBMMC, GXR, GXSJ, GXBM, GXBMMC, SJMJ, SFYX FROM FS_EAP_MENU " + "WHERE SFYX='1' ORDER BY ORDERNO";
		JSONArray jsonArr = new JSONArray();
		JSONObject rootJson = new JSONObject();
		rootJson.put("id", "");
		rootJson.put("parentId", "0");
		rootJson.put("name", "菜单树");
		rootJson.put("open", "true");
		rootJson.put("levelno", "0");
		jsonArr.add(rootJson);
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("NAME"));
				json.put("parentId", rsMap.get("PARENT"));
				json.put("name", rsMap.get("TITLE"));

				json.put("orderno", rsMap.get("ORDERNO"));
				json.put("target", rsMap.get("TARGET"));
				json.put("location", rsMap.get("LOCATION"));
				json.put("levelno", rsMap.get("LEVELNO"));
				json.put("MEMO", rsMap.get("MEMO"));
				jsonArr.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	public String executeMenu(String json, User user, String operatorSign) throws Exception {
		Connection conn = DBUtil.getConnection();
		String msg = null;
		String resultVo = null;
		int sign = Integer.parseInt(operatorSign);
		try {
			BusinessMenu menuVo = new BusinessMenu();
			conn.setAutoCommit(false);
			JSONArray list = menuVo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			switch (sign) {
			case 1:
				msg = "添加";
				logger.debug("{} | {}", msg, json);

				String queryOrderNoSql = "select decode(max(orderno), NULL, 1, max(orderno)) maxOrderNo from FS_EAP_MENU where parent='"
						+ jsonObj.getString("PARENT") + "'";
				String[][] orderNo = DBUtil.query(conn, queryOrderNoSql);
				int maxOrderNo = Integer.parseInt(orderNo == null ? "1" : orderNo[0][0]) + 1;

				jsonObj.put("ORDERNO", maxOrderNo);

				menuVo.setValueFromJson(jsonObj);
				BusinessUtil.setInsertCommonFields(menuVo, user);
				BaseDAO.insert(conn, menuVo);
				resultVo = menuVo.getRowJson();
				conn.commit();

				com.ccthanking.framework.coreapp.orgmanage.MenuManager.getInstance().synchronize(menuVo.getName(),
						CacheManager.ADD);
				break;
			case 2:
				msg = "修改";
				menuVo.setValueFromJson(jsonObj);
				BusinessUtil.setUpdateCommonFields(menuVo, user);
				BaseDAO.update(conn, menuVo);
				resultVo = menuVo.getRowJson();
				conn.commit();

				com.ccthanking.framework.coreapp.orgmanage.MenuManager.getInstance().synchronize("", 0);
				break;
			case 3:
				msg = "删除";
				// 状态是0表示删除
				jsonObj.put("SFYX", "0");
				menuVo.setValueFromJson(jsonObj);
				BusinessUtil.setUpdateCommonFields(menuVo, user);
				BaseDAO.update(conn, menuVo);
				resultVo = menuVo.getRowJson();
				conn.commit();

				com.ccthanking.framework.coreapp.orgmanage.MenuManager.getInstance().synchronize("", 0);
				break;
			default:
				break;
			}

			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_MENU, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + msg + "菜单信息成功",
					user, "", "");
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace(System.out);
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_MENU, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_FAILURE, user.getOrgDept().getDeptName() + " " + user.getName() + msg + "菜单信息失败",
					user, "", "");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVo;
	}

	public String queryUnique(String menuName, User user) {
		String sql = "SELECT COUNT(1) CNT FROM FS_EAP_MENU WHERE NAME = '" + menuName + "'";
		String[][] cnt = DBUtil.query(sql);
		JSONObject jsonObj = new JSONObject();
		if ("0".equals(cnt[0][0])) {
			jsonObj.put("isUnique", "可以使用");
			jsonObj.put("sign", "0");
		} else {
			jsonObj.put("isUnique", "菜单名称重复，请重新填写");
			jsonObj.put("sign", "1");
		}
		return jsonObj.toString();
	}

	public String loadAllMenu(String roleId) {
		Connection conn = DBUtil.getConnection();
		String queryDictSql = "SELECT RM.MENU_NAME MENU_NAME, T.NAME, T.TITLE, T.PARENT, "
				+ " T.ORDERNO, T.TARGET, T.LOCATION, T.CHIEF, T.LEVELNO "
				+ " FROM (SELECT * FROM FS_EAP_MENU WHERE SFYX='1') T LEFT JOIN FS_ORG_ROLE_MENU_MAP RM "
				+ " ON T.NAME = RM.MENU_NAME AND RM.ROLE_ID = '" + roleId + "' ORDER BY T.ORDERNO";
		JSONArray jsonArr = new JSONArray();
		try {
			logger.debug("query role_menu | {}", queryDictSql);
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("NAME"));
				json.put("parentId", rsMap.get("PARENT"));
				json.put("name", rsMap.get("TITLE"));
				if (rsMap.get("NAME").equals(rsMap.get("MENU_NAME"))) {
					json.put("checked", "true");
					json.put("open", "true");
				}
				jsonArr.add(json);
			}

			String resourceSql = "select " + " f.MENU_NAME,FS_PAGE_RESOURCE_ID,NAME,MEMO,URL,PARENT,SFYX,SSFL"
					+ " from FS_PAGE_RESOURCE t LEFT JOIN FS_ORG_ROLE_MENU_MAP f"
					+ " ON t.FS_PAGE_RESOURCE_ID = f.MENU_NAME AND f.ROLE_ID = '" + roleId + "'";
			List<Map<String, String>> resourceList = DBUtil.queryReturnList(conn, resourceSql);
			for (int i = 0; i < resourceList.size(); i++) {
				Map<String, String> resourceMap = resourceList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", resourceMap.get("FS_PAGE_RESOURCE_ID"));
				json.put("parentId", resourceMap.get("PARENT"));
				json.put("name", resourceMap.get("NAME"));
				if (resourceMap.get("FS_PAGE_RESOURCE_ID").equals(resourceMap.get("MENU_NAME"))) {
					json.put("checked", "true");
					json.put("open", "true");
				}
				json.put("icon", "/xmgl/img/diy/2.png");
				jsonArr.add(json);
			}
			logger.debug("menu json: {}", jsonArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	public void awardMenuToRole(String roleId, String[] menuName, User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteMenuByRoleSql = "DELETE FROM FS_ORG_ROLE_MENU_MAP WHERE ROLE_ID='" + roleId + "'";
			boolean bDel = DBUtil.exec(conn, deleteMenuByRoleSql);

			if (bDel) {
				for (int i = 0; i < menuName.length; i++) {
					String addMenuToRoleSql = "INSERT INTO FS_ORG_ROLE_MENU_MAP(ROLE_ID, MENU_NAME) " + " VALUES ('"
							+ roleId + "','" + menuName[i] + "')";
					DBUtil.exec(conn, addMenuToRoleSql);
				}
			}
			conn.commit();
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_MENU, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + "为角色分配菜单成功",
					user, "", "");
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_MENU, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + "为角色分配菜单失败",
					user, "", "");
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public MenuVo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MenuVo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MenuVo> find(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public int remove(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int remove(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(MenuVo bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(MenuVo bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Map<String, String>> getAll() {

		Connection conn = DBUtil.getConnection();
		try {
			List<Map<String, String>> lMap = DBUtil.queryReturnList(conn, SQL_APPMENU);
			return lMap;
		} catch (Exception e) {
		} finally {
			DBUtil.closeConnetion(conn);
		}

		return null;
	}

	public String[][] loadRoleByMid(String menuid) {

		logger.debug("menuid ={}", menuid);
		Connection conn = DBUtil.getConnection();
		try {
			return DBUtil.querySql(conn, "select menu_name, role_id from fs_org_role_menu_map where menu_name=?",
					new Object[] { menuid });
		} catch (Exception e) {
			DaoException.handleException("***根据菜单ID查询角色 出现错误！***");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}
	
public String getMenuTreeHtml() throws Exception {
		
		User user = ActionContext.getCurrentUserInThread();
		
		MenuVo[] menus = null;
		String menuTreeHtml = "";
		menus = getAllowedMenus(user,"");//显示第一层菜单
		if(menus!=null&&menus.length>0)
		{
	      for(int i=0;i<menus.length;i++)
	      {
	    	  String icss = menus[i].getImage();
	    	  if(icss==null||"".equalsIgnoreCase(icss))
	    	  {
	    		  icss = "icon-briefcase";
	    	  }
	    	 // menuTreeHtml +="<h3><i class=\"icon-briefcase icon-white\"></i>"+menus[i].getTitle()+"</h3>\r\n";
	    	  menuTreeHtml   +=" <li> ";
	    	  if(!"#".equals(menus[i].getLocation())){
	    		  menuTreeHtml += "<a href=\"#\"onclick=\"menutree_click('"+menus[i].getName()+"','"+menus[i].getLocation()+"','"+menus[i].getTitle()+"','"+menus[i].getTarget()+"')\">";
	    	  }else{
	    		  menuTreeHtml += "<a href=\"#\">";
	    	  }
	    	  menuTreeHtml += " <i class=\""+icss+" icon-white\"></i>  <span class=\"title\">"+menus[i].getTitle()+"</span> </a>\r\n";
	    	  MenuVo[] menus_two =getAllowedMenus(user,menus[i].getName());
	    	  if(menus_two!=null&&menus_two.length>0){
	    		  menuTreeHtml +="<ul class=\"sub-menu\" >\r\n";//style=\"height:400px;display:;overflow:auto;\"
	    		  for(int j=0;j<menus_two.length;j++)
	    		  {
	    			 // menuTreeHtml +="<h4><a href=\"#\" onclick=\"menutree_click('"+menus_two[j].getName()+"','"+menus_two[j].getLocation()+"','"+menus_two[j].getTitle()+"','"+menus_two[j].getTarget()+"')\">"+menus_two[j].getTitle()+"</a></h4>\r\n";
	    			  menuTreeHtml +="<li> <a href=\"#\"onclick=\"menutree_click('"+menus_two[j].getName()+"','"+menus_two[j].getLocation()+"','"+menus_two[j].getTitle()+"','"+menus_two[j].getTarget()+"')\">"+menus_two[j].getTitle()+"</a>\r\n";
	    			  MenuVo[] menus_three =getAllowedMenus(user,menus_two[j].getName());
	    			  if(menus_three!=null&&menus_three.length>0){
	    				  menuTreeHtml +=" <ul class=\"sub-menu\" style=\"display:;overflow:auto;\">\r\n";
	    				  for(int k=0;k<menus_three.length;k++)
	    				  {
	    					  menuTreeHtml +=" <li><a href=\"javascript:void(0);\" onclick=\"menutree_click('"+menus_three[k].getName()+"','"+menus_three[k].getLocation()+"','"+menus_three[k].getTitle()+"','"+menus_three[k].getTarget()+"')\">"+menus_three[k].getTitle()+"</a></i>\r\n";
	    				  }
	    				  
	    				  menuTreeHtml +="</ul>\r\n";
	    			  
	    			  }else if(menus_three==null||menus_three.length==0){
	    				  
	    				  menuTreeHtml +="<div></div>\r\n";
	    			  }
	    			  
	    		  }
	    		  menuTreeHtml +="</ul>\r\n";
	    	  }
	    	  menuTreeHtml +="</li>\r\n";
	      }
		}
		//System.out.println(menuTreeHtml);
		return menuTreeHtml;
	}

	protected MenuVo[] getAllowedMenus(User user,String parent) throws Exception {
	// 超级用户，看见系统中所有菜单
	if (User.SUPER_USER.equals(user.getPersonKind())) {
		//return MenuManager.getInstance().getMenus(parent);
	} else {
		//return user.getAllowedMenus(parent);
	}
	return null;
}	
	
}
