/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.projectlog.PmProjectTopicReplyDao.java
 * 创建日期： 2016-01-19 下午 04:29:43
 * 功能：   问题回复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:29:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectlog.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.projectlog.dao.PmProjectTopicReplyDao;
import com.ccthanking.business.projectlog.vo.PmProjectTopicReplyVO;
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
 * <p> PmProjectTopicReplyDao.java </p>
 * <p> 功能：问题回复 </p>
 *
 * <p><a href="PmProjectTopicReplyDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Component
public class PmProjectTopicReplyDaoImpl  extends BsBaseDaoTJdbc implements PmProjectTopicReplyDao {

    public String queryCondition(String json, PmProjectTopicReplyVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PM_PROJECT_TOPIC_REPLY t";
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

	public String getMaxCode(String topicUid) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
        	String sql = "SELECT MAX(t.XUHAO) XUHAO FROM pm_project_topic_reply t WHERE t.PROJECT_TOPIC_UID ='"+topicUid+"'";
            list = DBUtil.queryReturnList(conn, sql);
            if(list.size()>0){
            	Map<String,String> map = list.get(0);
            	if("".equals(map.get("XUHAO"))){
            		domresult = "1";
            	}else{
            		domresult = String.valueOf(Long.valueOf(map.get("XUHAO"))+1);
            	}
            }
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

	}

	public boolean deleteById(String replyUid) {
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" DELETE FROM pm_project_topic_reply WHERE PROJECT_TOPIC_REPLY_UID = '"+replyUid+"'");
			flag = DBUtil.exec(conn, sql.toString());
		} catch (Exception e) {
			 DBUtil.rollbackConnetion(conn);
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return flag;
	}
    
    // 在此可加入其它方法

}
