/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.message.BuUserMessageDao.java
 * 创建日期： 2016-04-21 上午 11:18:28
 * 功能：   个人消息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-21 上午 11:18:28  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.message.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.message.dao.BuUserMessageDao;
import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> BuUserMessageDao.java </p>
 * <p> 功能：个人消息 </p>
 *
 * <p><a href="BuUserMessageDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-21
 * 
 */

@Component
public class BuUserMessageDaoImpl  extends BsBaseDaoTJdbc implements BuUserMessageDao {

    public String queryCondition(String json, BuUserMessageVO vo, Map map){ 
    	User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
          //  String condition = RequestUtil.getConditionList(json).getConditionWhere();
          //  String orderFilter = RequestUtil.getOrderFilter(json);
           // condition += BusinessUtil.getSJYXCondition(null);
           // condition += BusinessUtil.getCommonCondition(user, null);
           // condition += orderFilter;
            if (page == null)
                page = new PageManager();
           // page.setFilter(condition);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM  BU_USER_MESSAGE bum ");
            if(null!=map.get("user_uid")){
            	sql.append(" where bum.user_uid ='"+map.get("user_uid")+"'");
            }
            
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
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
    
    /**
     * 获取用户未读信息
     */
	public int queryMessageCount(String idCard) {
		// TODO Auto-generated method stub
    	int size = 0;
    	Connection conn = DBUtil.getConnection();
    	StringBuffer sb = new StringBuffer();
    	sb.append("select count(*) as num from bu_user_message where user_uid='"+idCard+"' and STATUS='0'");
    	BaseResultSet bs = DBUtil.query(conn, sb.toString(), null);  
		try {
			ResultSet rs =  bs.getResultSet();
			 if(rs.next()){
				 size = Integer.parseInt(rs.getString("num"));
			 } 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}

	 
	public String queryByUserMessageId(String json, BuUserMessageVO vo, Map map) {
		// TODO Auto-generated method stub 
    	User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {  
            // 组织查询条件
           // PageManager page = RequestUtil.getPageManager(json); 
           // if (page == null)
           //     page = new PageManager();
           // page.setFilter(condition);
       	 
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM  BU_USER_MESSAGE bum ");
            if(null!=map.get("user_message_uid")){
            	sql.append(" where bum.USER_MESSAGE_UID ='"+map.get("user_message_uid")+"'");    
            }
            BaseResultSet bs = DBUtil.query(conn, sql.toString(), null);  
            domresult = bs.getJson(); 
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}


	
 
    
    // 在此可加入其它方法

}
