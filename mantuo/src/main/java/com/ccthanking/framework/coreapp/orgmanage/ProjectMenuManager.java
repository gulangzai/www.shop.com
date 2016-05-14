package com.ccthanking.framework.coreapp.orgmanage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccthanking.framework.common.AuthorityVo;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.cache.Cache;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.MenuComparator;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.exception.DaoException;

public class ProjectMenuManager implements Cache {

	private static Logger logger = LoggerFactory.getLogger(ProjectMenuManager.class.getName());

	private Hashtable menutable;// 全部节点树 map<parent,List<MenuVo>>
	private Hashtable menuarr;// 全部菜单 map<name,MenuVo>

	private static ProjectMenuManager instance;
	
	private static final String ROOT_MENU = "ROOT_MENU_85183815";
	
	public ProjectMenuManager()throws Exception{}
	private ProjectMenuManager(String projectUid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			StringBuffer sql = new StringBuffer();
/*			sql.append(" SELECT t.AUTHORITY_NAME,t.AUTHORITY_CODE,t.P_AUTHORITY_UID,t.SERIAL_NO, ");
			sql.append(" t.TARGET,t.URL,t.IMAGE,t.AUTHORITY_TYPE,t.AUTHORITY_UID  FROM ");
			sql.append(" bu_project_role bpr ");
			sql.append(" LEFT JOIN sys_role_auth sra ON ");
			sql.append(" bpr.ROLE_UID = sra.ROLE_UID LEFT JOIN ");
			sql.append(" (SELECT * FROM sys_authority  WHERE ENABLED = 1) T ON  ");
			sql.append(" sra.AUTHORITY_UID = t.AUTHORITY_UID LEFT JOIN ");
			sql.append(" bu_project bp ON  bp.PROJECT_UID = bpr.PROJECT_UID ");
			sql.append(" WHERE bp.PROJECT_UID =  '"+projectUid+"'");
			sql.append(" order by SERIAL_NO");*/
/*			sql.append(" SELECT t.* ");
			sql.append(" FROM bu_project_role bpr LEFT JOIN ( ");
			sql.append(" SELECT 	a.AUTHORITY_NAME, ");
			sql.append(" 	a.AUTHORITY_CODE, ");
			sql.append(" 	a.P_AUTHORITY_UID, ");
			sql.append(" 	a.SERIAL_NO, ");
			sql.append(" 	a.TARGET, ");
			sql.append(" 	a.URL, ");
			sql.append(" 	a.IMAGE, ");
			sql.append(" 	a.AUTHORITY_TYPE, ");
			sql.append(" 	a.AUTHORITY_UID,sra.ROLE_UID from sys_role sr ");
			sql.append(" LEFT JOIN sys_role_auth sra ");
			sql.append(" ON sr.ROLE_UID = sra.ROLE_UID  ");
			sql.append(" LEFT JOIN sys_authority a ");
			sql.append(" ON a.AUTHORITY_UID = sra.AUTHORITY_UID ");
			sql.append(" WHERE a.ENABLED = 1 ");
			sql.append(" ) t ");
			sql.append(" ON t.ROLE_UID = bpr.ROLE_UID ");
			sql.append(" WHERE bpr.project_uid = '"+projectUid+"' ORDER BY t.SERIAL_NO ");*/
			
			
/*			sql.append(" SELECT 	DISTINCT(t.AUTHORITY_CODE) as AUTHORITY_CODE, ");
			sql.append("  t.AUTHORITY_NAME, 	 ");
			sql.append("  	t.P_AUTHORITY_UID,  ");
			sql.append("  	t.SERIAL_NO,  ");
			sql.append("  	t.TARGET,  ");
			sql.append("  	t.URL,  ");
			sql.append("  	t.IMAGE,  ");
			sql.append("  	t.AUTHORITY_TYPE,  ");
			sql.append("  	t.AUTHORITY_UID,t.ROLE_UID FROM bu_project_role r ");
			sql.append("  LEFT JOIN bu_project_role_user u ");
			sql.append(" ON r.PROJECT_ROLE_UID = u.PROJECT_ROLE_UID ");
			sql.append(" LEFT JOIN bu_project_role_auth a ");
			sql.append(" ON r.PROJECT_ROLE_UID = a.PROJECT_ROLE_UID ");
			sql.append(" LEFT JOIN (  ");
			sql.append("  SELECT 	a.AUTHORITY_NAME,  ");
			sql.append("  	a.AUTHORITY_CODE,  ");
			sql.append("  	a.P_AUTHORITY_UID,  ");
			sql.append("  	a.SERIAL_NO,  ");
			sql.append("  	a.TARGET,  ");
			sql.append("  	a.URL,  ");
			sql.append("  	a.IMAGE,  ");
			sql.append("  	a.AUTHORITY_TYPE,  ");
			sql.append("  	a.AUTHORITY_UID,sra.ROLE_UID from sys_role sr  ");
			sql.append("  LEFT JOIN sys_role_auth sra  ");
			sql.append("  ON sr.ROLE_UID = sra.ROLE_UID   ");
			sql.append("  LEFT JOIN sys_authority a  ");
			sql.append("  ON a.AUTHORITY_UID = sra.AUTHORITY_UID  ");
			sql.append("  WHERE a.ENABLED = 1  ");
			sql.append("  ) t  ");
			sql.append(" ON t.AUTHORITY_UID = a.AUTHORITY_UID ");
			sql.append(" WHERE r.PROJECT_UID = '"+projectUid+"' ");
			sql.append(" AND t.AUTHORITY_CODE IS NOT NULL ");
			sql.append(" ORDER BY serial_no ");*/
			
			sql.append(" SELECT DISTINCT(s.AUTHORITY_CODE) as AUTHORITY_CODE,  ");
			sql.append(" s.AUTHORITY_NAME,s.P_AUTHORITY_UID,   ");
			sql.append(" s.SERIAL_NO,s.TARGET,s.URL,   ");
			sql.append(" s.IMAGE,  s.AUTHORITY_TYPE,s.AUTHORITY_UID ");
			sql.append("  FROM ( ");
			sql.append(" SELECT AUTHORITY_UID FROM bu_project_role_auth a WHERE a.PROJECT_ROLE_UID in( ");
			sql.append(" SELECT r.PROJECT_ROLE_UID FROM bu_project_user u ");
			sql.append(" LEFT JOIN bu_project_role_user r ");
			sql.append(" ON u.PROJECT_USER_UID = r.PROJECT_USER_UID ");
			sql.append(" WHERE u.PROJECT_UID = '"+projectUid+"' AND u.PROJECT_USER_UID = '"+user.getParent()+"') UNION SELECT AUTHORITY_UID FROM sys_authority WHERE AUTHORITY_UID = '6') t ");
			sql.append(" LEFT JOIN sys_authority s ");
			sql.append(" ON t.AUTHORITY_UID = s.AUTHORITY_UID ");
			sql.append(" WHERE s.ENABLED = '1' ");
			sql.append(" ORDER BY SERIAL_NO ");


			String[][] list = DBUtil.query(conn, sql.toString());
			if (list != null) {
				menutable = new Hashtable();
				menuarr = new Hashtable(list.length);
				for (int i = 0; i < list.length; i++) {
				
					AuthorityVo m  =  new AuthorityVo();
					m.setAuthorityCode(list[i][0]);
					m.setAuthorityName(list[i][1]);
					m.setPauthorityUid(list[i][2]);
					m.setSerialNo(list[i][3]);
					m.setTarget(list[i][4]);
					m.setUrl(list[i][5]);
					m.setImage(list[i][6]);
					m.setAuthorityType(list[i][7]);
					m.setAuthorityUid(list[i][8]);

					menuarr.put(m.getAuthorityCode(), m);

					// 组织树map<key, List>
					if (menutable.get(m.getPauthorityUid()) != null) {
						ArrayList mlist = (ArrayList) menutable.get(m.getPauthorityUid());
						mlist.add(m);
					} else {
						if (!Pub.empty(m.getPauthorityUid())) {
							ArrayList mlist = (ArrayList) menutable.get(m.getPauthorityUid());
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(m);
							menutable.put(m.getPauthorityUid(), mlist);
						} else {
							// 根节点菜单设置默认的key
							ArrayList mlist = (ArrayList) menutable.get(this.ROOT_MENU);
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(m);
							menutable.put(this.ROOT_MENU, mlist);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	/**
	 * 获取本系统的第一层所有菜单.<br/>
	 * 
	 * @return
	 * @since v1.00
	 */
	public AuthorityVo[] getMenusTop(User user) {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			StringBuffer sql = new StringBuffer();
/*			sql.append(" select * from( ");
			sql.append(" select T.NAME,T.CODE,T.TITLE,T.PARENT,T.ORDERNO,T.TARGET, ");
			sql.append(" T.LOCATION,T.CHIEF,T.LEVELNO,T.IMAGE     ");
			sql.append(" from (SELECT * FROM FS_EAP_MENU WHERE SFYX = '1'  and APP_NAME = 'wndsjamis') T ");
			sql.append(" left join FS_USER_ROLE UR    on T.CODE = UR.ROLE_CODE    where UR.USERS_UID = "+user.getUserSN());
			sql.append("  and T.LEVELNO='1' ");
			sql.append(" union ");
			sql.append(" select distinct m.menu_name as name,t.code,T.TITLE,T.PARENT,T.ORDERNO, ");
			sql.append(" T.TARGET,T.LOCATION,T.CHIEF,T.LEVELNO,T.IMAGE   ");
			sql.append("   from fs_org_role_psn_map p left join  fs_org_role r  ");
			sql.append("   on p.role_id = r.role_id  left join fs_org_role_menu_map m  ");
			sql.append("   on m.role_id = r.role_id   left join (SELECT * FROM FS_EAP_MENU WHERE SFYX = '1'  ");
			sql.append("    and APP_NAME = 'wndsjamis') T  on m.menu_name = t.name where p.person_account = '"+user.getAccount()+"' and T.LEVELNO='1') tab ");
			sql.append(" order by tab.ORDERNO ");*/
			sql.append(" SELECT t.AUTHORITY_NAME,t.AUTHORITY_CODE,t.P_AUTHORITY_UID,t.SERIAL_NO, ");
			sql.append(" t.TARGET,t.URL,t.IMAGE,t.AUTHORITY_TYPE,t.AUTHORITY_UID  FROM ");
			sql.append(" bu_project_role bpr ");
			sql.append(" LEFT JOIN sys_role_auth sra ON ");
			sql.append(" bpr.ROLE_UID = sra.ROLE_UID LEFT JOIN ");
			sql.append(" (SELECT * FROM sys_authority  WHERE ENABLED = 1) T ON  ");
			sql.append(" sra.AUTHORITY_UID = t.AUTHORITY_UID LEFT JOIN ");
			sql.append(" bu_project bp ON  bp.PROJECT_UID = bpr.PROJECT_UID ");
			sql.append(" where 1=1 ");
			//sql.append("  and  bp.PROJECT_UID =  "+user.getIdCard());
			sql.append("         and T.AUTHORITY_TYPE='M'  ");

			String[][] list = DBUtil.query(conn, sql.toString());

			if (null != list) {
				//MenuVo[] re_menu = new MenuVo[list.length];
				AuthorityVo[] re_menu = new AuthorityVo[list.length];
				for (int i = 0; i < list.length; i++) {
					String code = list[i][1];
					AuthorityVo menu = (AuthorityVo) menuarr.get(code);
					re_menu[i] = menu;
				}
				return re_menu;
			}

			

		} catch (Exception e) {
			DaoException.handleException("***查询顶层菜单出错!***");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public AuthorityVo[] getMenus(String parent) throws Exception {
		try {
			if (Pub.empty(parent))
				return (AuthorityVo[]) ((ArrayList) menutable.get(this.ROOT_MENU)).toArray(new AuthorityVo[((ArrayList) menutable
						.get(this.ROOT_MENU)).size()]);
			else
				return (AuthorityVo[]) ((ArrayList) menutable.get(parent)).toArray(new AuthorityVo[((ArrayList) menutable
						.get(parent)).size()]);
		} catch (Exception e) {
			return null;
		}

	}
	
	public AuthorityVo[] getMenus(String projectUid,String projectUserId,String root_uid,String parent) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			StringBuffer sql = new StringBuffer();
	
			if("".equals(parent)){
				sql.append(" SELECT DISTINCT(t2.AUTHORITY_CODE) as AUTHORITY_CODE,   ");
				sql.append(" t2.AUTHORITY_NAME,t2.P_AUTHORITY_UID,    ");
				sql.append(" t2.SERIAL_NO,t2.TARGET,t2.URL,    ");
				sql.append(" t2.IMAGE,  t2.AUTHORITY_TYPE,t2.AUTHORITY_UID  ");
				sql.append("  FROM (  ");
				sql.append(" SELECT AUTHORITY_UID FROM bu_project_role_auth a WHERE a.PROJECT_ROLE_UID in(  ");
				sql.append(" SELECT r.PROJECT_ROLE_UID FROM bu_project_user u  ");
				sql.append(" LEFT JOIN bu_project_role_user r  ");
				sql.append(" ON u.PROJECT_USER_UID = r.PROJECT_USER_UID  ");
				sql.append(" WHERE u.PROJECT_UID = '"+projectUid+"' AND u.PROJECT_USER_UID = '"+projectUserId+"') UNION SELECT AUTHORITY_UID FROM sys_authority WHERE AUTHORITY_UID = '"+root_uid+"') t  ");
				sql.append(" LEFT JOIN (SELECT * FROM sys_authority s WHERE s.ENABLED = '1' and s.AUTHORITY_TYPE <> 'O' AND s.AUTHORITY_UID = '"+root_uid+"' OR s.P_AUTHORITY_UID = '"+root_uid+"' ) t2 ");
				sql.append(" ON t.AUTHORITY_UID = t2.AUTHORITY_UID  WHERE t2.AUTHORITY_CODE IS NOT NULL");
				sql.append(" ORDER BY SERIAL_NO  ");
			}else{
				sql.append(" SELECT DISTINCT(s.AUTHORITY_CODE) as AUTHORITY_CODE,  ");
				sql.append(" s.AUTHORITY_NAME,s.P_AUTHORITY_UID,   ");
				sql.append(" s.SERIAL_NO,s.TARGET,s.URL,   ");
				sql.append(" s.IMAGE,  s.AUTHORITY_TYPE,s.AUTHORITY_UID ");
				sql.append("  FROM ( ");
				sql.append(" SELECT AUTHORITY_UID FROM bu_project_role_auth a WHERE a.PROJECT_ROLE_UID in( ");
				sql.append(" SELECT r.PROJECT_ROLE_UID FROM bu_project_user u ");
				sql.append(" LEFT JOIN bu_project_role_user r ");
				sql.append(" ON u.PROJECT_USER_UID = r.PROJECT_USER_UID ");
				sql.append(" WHERE u.PROJECT_UID = '"+projectUid+"' AND u.PROJECT_USER_UID = '"+projectUserId+"') UNION SELECT AUTHORITY_UID FROM sys_authority WHERE AUTHORITY_UID = '6') t ");
				sql.append(" LEFT JOIN sys_authority s ");
				sql.append(" ON t.AUTHORITY_UID = s.AUTHORITY_UID ");
				sql.append(" WHERE s.ENABLED = '1' and s.AUTHORITY_TYPE <> 'O' ");
				sql.append(" ORDER BY SERIAL_NO ");
			}



			String[][] list = DBUtil.query(conn, sql.toString());
			if (list != null) {
				menutable = new Hashtable();
				menuarr = new Hashtable(list.length);
				for (int i = 0; i < list.length; i++) {
				
					AuthorityVo m  =  new AuthorityVo();
					m.setAuthorityCode(list[i][0]);
					m.setAuthorityName(list[i][1]);
					m.setPauthorityUid(list[i][2]);
					m.setSerialNo(list[i][3]);
					m.setTarget(list[i][4]);
					m.setUrl(list[i][5]);
					m.setImage(list[i][6]);
					m.setAuthorityType(list[i][7]);
					m.setAuthorityUid(list[i][8]);

					menuarr.put(m.getAuthorityCode(), m);

					// 组织树map<key, List>
					if (menutable.get(m.getPauthorityUid()) != null) {
						ArrayList mlist = (ArrayList) menutable.get(m.getPauthorityUid());
						mlist.add(m);
					} else {
						if (!Pub.empty(m.getPauthorityUid())) {
							ArrayList mlist = (ArrayList) menutable.get(m.getPauthorityUid());
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(m);
							menutable.put(m.getPauthorityUid(), mlist);
						} else {
							// 根节点菜单设置默认的key
							ArrayList mlist = (ArrayList) menutable.get(this.ROOT_MENU);
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(m);
							menutable.put(this.ROOT_MENU, mlist);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}

		if (Pub.empty(parent)){
			return (AuthorityVo[]) ((ArrayList) menutable.get(this.ROOT_MENU)).toArray(new AuthorityVo[((ArrayList) menutable
					.get(this.ROOT_MENU)).size()]);
		}else{
			if(null!=menutable.get(parent)){
				return (AuthorityVo[]) ((ArrayList) menutable.get(parent)).toArray(new AuthorityVo[((ArrayList) menutable
						.get(parent)).size()]);
			}else{
				return null;
			}
			
		}
	}
	
	
	public AuthorityVo[] getAllMenus(User user) throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			StringBuffer sql = new StringBuffer();
/*			sql.append(" select * from( ");
			sql.append(" select T.NAME,T.CODE,T.TITLE,T.PARENT,T.ORDERNO,T.TARGET, ");
			sql.append(" T.LOCATION,T.CHIEF,T.LEVELNO,T.IMAGE     ");
			sql.append(" from (SELECT * FROM FS_EAP_MENU WHERE SFYX = '1'  and APP_NAME = 'wndsjamis') T ");
			sql.append(" left join FS_USER_ROLE UR    on T.CODE = UR.ROLE_CODE    where UR.USERS_UID = "+user.getUserSN());
			//sql.append("  and T.LEVELNO='1' ");
			sql.append(" union ");
			sql.append(" select distinct m.menu_name as name,t.code,T.TITLE,T.PARENT,T.ORDERNO, ");
			sql.append(" T.TARGET,T.LOCATION,T.CHIEF,T.LEVELNO,T.IMAGE   ");
			sql.append("   from fs_org_role_psn_map p left join  fs_org_role r  ");
			sql.append("   on p.role_id = r.role_id  left join fs_org_role_menu_map m  ");
			sql.append("   on m.role_id = r.role_id   left join (SELECT * FROM FS_EAP_MENU WHERE SFYX = '1'  ");
			sql.append("    and APP_NAME = 'wndsjamis') T  on m.menu_name = t.name where p.person_account = '"+user.getAccount()+"') tab ");
			sql.append(" order by tab.ORDERNO ");*/
			sql.append(" SELECT t.AUTHORITY_NAME,t.AUTHORITY_CODE,t.P_AUTHORITY_UID,t.SERIAL_NO,t.TARGET,t.URL,t.IMAGE,t.AUTHORITY_TYPE   ");
			sql.append("        from (SELECT * FROM sys_authority  WHERE ENABLED = 1) T  ");
			sql.append("        left join sys_user_auth ua    on T.AUTHORITY_UID = ua.AUTHORITY_UID ");
			sql.append(" where Ua.USER_UID = "+user.getIdCard());
			sql.append("         and T.AUTHORITY_TYPE != 'M'  ");

			String[][] list = DBUtil.query(conn, sql.toString());

			if (null != list) {
				AuthorityVo[] re_menu = new AuthorityVo[list.length];
				for (int i = 0; i < list.length; i++) {
					String menuname = list[i][0];
					AuthorityVo menu = (AuthorityVo) menuarr.get(menuname);
					re_menu[i] = menu;
				}
				return re_menu;
			}

			

		} catch (Exception e) {
			DaoException.handleException("***查询顶层菜单出错!***");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
		
	}

	public MenuVo getMenu(String name) throws Exception {
		if (Pub.empty(name))
			return null;
		return (MenuVo) menuarr.get(name);
	}

	public MenuVo[] getAllMenus() throws Exception {
		MenuVo[] menus = new MenuVo[menuarr.values().size()];
		return (MenuVo[]) menuarr.values().toArray(menus);
	}

	public MenuVo[] getAllowedMenus(User user) throws Exception {
		Role[] roles = user.getRoles();
		ArrayList ml = null;
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				MenuVo[] menus = roles[i].getMenus();
				if (menus != null) {
					if (ml == null)
						ml = new ArrayList();
					for (int j = 0; j < menus.length; j++) {
						if (ml.contains(menus[j]))
							continue;
						ml.add(menus[j]);
					}
				}
			}
			// yum 增加菜单按OrderNo排序
			if (ml != null) {
				Comparator comp = new MenuComparator();
				Collections.sort(ml, comp);
			}
			// Arrays.sort(ml.toArray());
		}
		if (ml == null) {
			return null;
		} else
			return (MenuVo[]) ml.toArray(new MenuVo[ml.size()]);
	}

	public MenuVo[] getAllowedMenus(User user, String parent) throws Exception {
		Role[] roles = user.getRoles();
		ArrayList ml = null;
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				MenuVo[] menus = roles[i].getMenus();
				if (menus != null) {
					if (ml == null)
						ml = new ArrayList();
					for (int j = 0; j < menus.length; j++) {
						if (ml.contains(menus[j]))
							continue;
						if (Pub.empty(menus[j].getParent()) && Pub.empty(parent))
							ml.add(menus[j]);
						else if (!Pub.empty(parent) && parent.equals(menus[j].getParent()))
							ml.add(menus[j]);
					}
				}
			}
		}
		return (MenuVo[]) ml.toArray(new MenuVo[ml.size()]);
	}

	/**
	 * 查询某应用的所有菜单.<br/>
	 * 
	 * @param appName
	 * @return MenuVo[]
	 * @since v1.00
	 */
	public MenuVo[] getAllowedMenusApp() {

		ArrayList ml = null;
		
		
		return null;
	}

	public synchronized static ProjectMenuManager getInstance(String projectUid) throws Exception {
		if (instance == null) {
			instance = new ProjectMenuManager(projectUid);
			logger.debug("--------------- 初始化 menu ------------------");
			CacheManager.register(CacheManager.CACHE_MENU, instance);
		}
		return instance;
	}

	public void reBuildMemory() throws Exception {
		if (menutable != null) {
			menutable.clear();
			menutable = null;
		}
		if (menuarr != null) {
			menuarr.clear();
			menuarr = null;
		}
		if (instance != null)
			instance = null;
		if (instance == null) {
			instance = new ProjectMenuManager();
		}
	}

	public void synchronize(String data, int action) throws Exception {
		if (menutable != null) {
			menutable.clear();
			menutable = null;
		}
		if (menuarr != null) {
			menuarr.clear();
			menuarr = null;
		}
		if (instance != null)
			instance = null;
		if (instance == null) {
			instance = new ProjectMenuManager();
		}
	}
	
	public  MenuVo[] union(MenuVo[] menu1, MenuVo[] menu2) {
		Set<MenuVo> set = new HashSet<MenuVo>();
		for (MenuVo menu : menu1) {
			set.add(menu);
		}
		for (MenuVo menu : menu2) {
			set.add(menu);
		}
		MenuVo[] result = {};
		return set.toArray(result);
	}
	public String[][] getAllowedMenus(String projectUid) {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = null;
		String[][] list = null;
		try {
			conn = DBUtil.getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append(" SELECT DISTINCT(s.AUTHORITY_CODE) as AUTHORITY_CODE,  ");
			sql.append(" s.AUTHORITY_NAME,s.P_AUTHORITY_UID,   ");
			sql.append(" s.SERIAL_NO,s.TARGET,s.URL,   ");
			sql.append(" s.IMAGE,  s.AUTHORITY_TYPE,s.AUTHORITY_UID ");
			sql.append("  FROM ( ");
			sql.append(" SELECT * FROM bu_project_role_auth a WHERE a.PROJECT_ROLE_UID in( ");
			sql.append(" SELECT r.PROJECT_ROLE_UID FROM bu_project_user u ");
			sql.append(" LEFT JOIN bu_project_role_user r ");
			sql.append(" ON u.PROJECT_USER_UID = r.PROJECT_USER_UID ");
			sql.append(" WHERE u.PROJECT_UID = '"+projectUid+"' AND u.PROJECT_USER_UID = '"+user.getParent()+"')) t ");
			sql.append(" LEFT JOIN sys_authority s ");
			sql.append(" ON t.AUTHORITY_UID = s.AUTHORITY_UID ");
			sql.append(" WHERE s.ENABLED = '1' ");
			sql.append(" ORDER BY SERIAL_NO ");


			 list = DBUtil.query(conn, sql.toString());
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					AuthorityVo m  =  new AuthorityVo();
					m.setAuthorityCode(list[i][0]);
					m.setAuthorityName(list[i][1]);
					m.setPauthorityUid(list[i][2]);
					m.setSerialNo(list[i][3]);
					m.setTarget(list[i][4]);
					m.setUrl(list[i][5]);
					m.setImage(list[i][6]);
					m.setAuthorityType(list[i][7]);
					m.setAuthorityUid(list[i][8]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}

}