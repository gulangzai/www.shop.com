/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmcailiao.PmCailiaoDao.java
 * 创建日期： 2016-03-24 上午 11:19:37
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-24 上午 11:19:37     创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmcailiao.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmcailiao.dao.PmCailiaoDao;
import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmCailiaoDao.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmCailiaoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com"></a>
 * @version 0.1
 * @since 2016-03-24
 * 
 */

@Component
public class PmCailiaoDaoImpl  extends BsBaseDaoTJdbc implements PmCailiaoDao {

    public String queryCondition(String json, PmCailiaoVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            //condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PM_CAILIAO t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public Boolean deleteId(String cailiaoUid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM PM_CAILIAO WHERE CAILIAO_UID = '"+cailiaoUid+"'");

			flag  = DBUtil.exec(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
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
			sql.append(" WHERE f.TARGET_UID = '"+map.get("cailiao_uid")+"'");
			sql.append(" AND f.FILE_TYPE = '10501'");
			list = DBUtil.queryReturnList(conn, sql.toString());

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return list;
	}
    
    // 在此可加入其它方法

}
