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
import com.ccthanking.framework.util.MenuComparator;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.exception.DaoException;

public class MenuManager implements Cache {

	private static Logger logger = LoggerFactory.getLogger(MenuManager.class.getName());

	private Hashtable menutable;// 全部节点树 map<parent,List<MenuVo>>
	private Hashtable menuarr;// 全部菜单 map<name,MenuVo>

	private static MenuManager instance;
	//private static final String APP_NAME = "wndsjamis";
	private static final String ROOT_MENU = "ROOT_MENU_85183815";

	private MenuManager() throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//			 String querySql =
//			 "select name,title,parent,orderno,target,location,layersno,image,altimage,chief,app_name, levelno "
//			 + " from FS_eap_menu where app_name='"
//			 + APP_NAME
//			 + "' AND sfyx='1' and menu_y != 'N' order by parent,orderno ";
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT t.AUTHORITY_NAME,t.AUTHORITY_CODE,t.P_AUTHORITY_UID,t.SERIAL_NO,t.TARGET,t.URL,t.IMAGE,t.AUTHORITY_TYPE,t.AUTHORITY_UID   ");
			sql.append("        from (SELECT * FROM sys_authority  WHERE ENABLED = 1) T  ");
			sql.append("        left join sys_user_auth ua    on T.AUTHORITY_UID = ua.AUTHORITY_UID ");
			//sql.append("         where T.AUTHORITY_TYPE='S'  ");

			//String querySql = "select name,title,parent,orderno,target,location,layersno,image,altimage,chief,app_name, levelno "
			//		+ " from FS_eap_menu where sfyx='1' order by parent,orderno ";

			String[][] list = DBUtil.query(conn, sql.toString());
			if (list != null) {
				menutable = new Hashtable();
				menuarr = new Hashtable(list.length);
				for (int i = 0; i < list.length; i++) {
				
					AuthorityVo m  =  new AuthorityVo();
					m.setAuthorityName(list[i][0]);
					m.setAuthorityCode(list[i][1]);
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
			sql.append(" SELECT t.AUTHORITY_NAME,t.AUTHORITY_CODE,t.P_AUTHORITY_UID,t.SERIAL_NO,t.TARGET,t.URL,t.IMAGE,t.AUTHORITY_TYPE,t.AUTHORITY_UID   ");
			sql.append("        from (SELECT * FROM sys_authority  WHERE ENABLED = 1) T  ");
			sql.append("        left join sys_user_auth ua    on T.AUTHORITY_UID = ua.AUTHORITY_UID ");
			sql.append(" where Ua.USER_UID = "+user.getIdCard());
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

	public synchronized static MenuManager getInstance() throws Exception {
		if (instance == null) {
			instance = new MenuManager();
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
			instance = new MenuManager();
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
			instance = new MenuManager();
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

}