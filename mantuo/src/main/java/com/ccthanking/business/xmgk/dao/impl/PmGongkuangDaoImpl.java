/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.xmgk.PmGongkuangDao.java
 * 创建日期： 2015-11-25 下午 01:16:52
 * 功能：   项目工况
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-11-25 下午 01:16:52  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.xmgk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.xmgk.dao.PmGongkuangDao;
import com.ccthanking.business.xmgk.vo.PmGongkuangVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmGongkuangDao.java </p>
 * <p> 功能：项目工况 </p>
 *
 * <p><a href="PmGongkuangDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-11-25
 * 
 */

@Component
public class PmGongkuangDaoImpl  extends BsBaseDaoTJdbc implements PmGongkuangDao {

    public String queryCondition(String json, PmGongkuangVO vo, Map map){
    	User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
//            condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT g.*,u.USER_NAME FROM " + "pm_gongkuang g LEFT JOIN BU_PROJECT p on g.PROJECT_UID = p.PROJECT_UID "+
                         " LEFT JOIN SYS_USER u on g.CREATE_USER = u.USER_UID ";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            //设置报告期日 
            bs.setFieldDateFormat("REPORT_DATE","yyyy-MM-dd");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public List<Map<String, String>> queryFileList(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		List<Map<String,String>> list = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT a.FILE_NAME ,u.USER_NAME,DATE_FORMAT(f.CREATE_DATE,'%y-%m-%d %H:%i:%s') as CREATE_DATE FROM bu_file f  ");
			sql.append(" LEFT JOIN bu_attachment a ");
			sql.append(" ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
			sql.append(" LEFT JOIN sys_user u ");
			sql.append(" ON f.CREATE_USER = u.USER_UID ");
			sql.append(" WHERE f.TARGET_UID = '"+map.get("gongkuang_uid")+"'");
			sql.append(" AND f.FILE_TYPE = '10201'");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}

	/**
	 * 项目工况设置
	 */
	public boolean pmGongkuangSet(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT g.GONGKUANG_UID FROM pm_gongkuang g WHERE g.CURRENT_Y = 'Y'");

			List<Map<String,String>> list = DBUtil.queryReturnList(conn, sql.toString());
			
			//判断是否有当前工况数据，如果有 先将这条数据更新 再更新选中数据
			if(null!=list&&list.size()>0){
				Map<String,String> lmap = list.get(0);
				sql = new StringBuffer();
				sql.append("UPDATE pm_gongkuang g SET g.CURRENT_Y = '',g.FINISH_DATE = SYSDATE() WHERE g.GONGKUANG_UID = '"+lmap.get("GONGKUANG_UID")+"'");
				DBUtil.exec(conn, sql.toString());
			}
			
			sql = new StringBuffer();
			sql.append("UPDATE pm_gongkuang g SET g.CURRENT_Y = 'Y' WHERE g.GONGKUANG_UID = '"+map.get("gongkuang_uid")+"'");
			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}

	public List<Map<String, Object>> queryFileByType(Map<String, String> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEROOT");
		String fileRoot = syspara.PARAVALUE1;
		Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.file_path,a.file_name from bu_file f ");
			sql.append(" left join bu_attachment a ");
			sql.append(" on f.attachment_uid = a.attachment_uid ");
			sql.append(" where f.target_uid = '"+map.get("targetUid")+"' and f.file_type = '"+map.get("file_type")+"' order by f.CREATE_DATE desc limit 0,1 ");// ORDER BY f.FILE_UID DESC LIMIT 1
			ps = conn.prepareStatement(sql.toString());
			
			rs = ps.executeQuery();
			md = rs.getMetaData();
			while(rs.next()){
				Map<String, Object> map1 = new HashMap<String, Object>();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i+1).toUpperCase();
					String sTemp  = rs.getString(columnName);	
					
					if(sTemp==null ||sTemp == ""){
						sTemp = " ";
					}
					map1.put(columnName, sTemp);
				}
				
				map1.put("url", map.get("root") + "/UploadUtilServlet?getfile=" +rs.getString("File_PATH") 
						+ "&fileDir="+ fileRoot );
			
				list.add(map1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean deleteByid(String gongkuangUid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM pm_gongkuang  WHERE GONGKUANG_UID = '"+gongkuangUid+"'");

			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}
    
    // 在此可加入其它方法

}
