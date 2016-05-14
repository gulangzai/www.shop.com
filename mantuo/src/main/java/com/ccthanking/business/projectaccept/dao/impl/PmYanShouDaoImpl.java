/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.projectaccept.PmYanShouDao.java
 * 创建日期： 2016-03-28 上午 11:50:46
 * 功能：   工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:50:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectaccept.dao.impl;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.projectaccept.dao.PmYanShouDao;
import com.ccthanking.business.projectaccept.vo.PmYanShouVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiContentVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;


/**
 * <p> PmYanShouDao.java </p>
 * <p> 功能：工程验收 </p>
 *
 * <p><a href="PmYanShouDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

@Component
public class PmYanShouDaoImpl  extends BsBaseDaoTJdbc implements PmYanShouDao {

    public String queryCondition(String json, PmYanShouVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            //condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PM_YANSHOU t";
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
 
    // 在此可加入其它方法 
	public boolean deleteById(String yanshou_uid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try { 
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM PM_YANSHOU  WHERE YANSHOU_UID = '"+yanshou_uid+"'"); 
			flag  = DBUtil.exec(conn, sql.toString()); 
			if(flag){
				deleteYanshou(yanshou_uid);
	        }
		} catch (Exception e) {
			DaoException.handleMessageException("*********删除出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		} 
		return flag;
	}
    
	//删除子表
    private boolean deleteYanshou(String yanshou_uid){
    	Connection con = DBUtil.getConnection(); 
    	boolean bs = false;
	   try { 
		   String sql = "delete from pm_yanshou_detail where YANSHOU_UID ="+yanshou_uid;
		   bs =  DBUtil.execSql(con, sql); 
		   con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			SystemException.handleMessageException("验收表信息详情表数据修改失败,请联系相关人员处理");
		} finally{
			if(con != null){
				DBUtil.closeConnetion(con);
			} 
		} 
	    return bs; 
    }

}
