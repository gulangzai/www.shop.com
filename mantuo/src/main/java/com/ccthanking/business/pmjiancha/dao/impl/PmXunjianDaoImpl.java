/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmjiancha.PmXunjianDao.java
 * 创建日期： 2016-03-02 下午 02:20:13
 * 功能：   巡检
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:20:13  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmjiancha.dao.PmXunjianDao;
import com.ccthanking.business.pmjiancha.vo.PmXunjianVO;
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
 * <p> PmXunjianDao.java </p>
 * <p> 功能：巡检 </p>
 *
 * <p><a href="PmXunjianDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Component
public class PmXunjianDaoImpl  extends BsBaseDaoTJdbc implements PmXunjianDao {

    public String queryCondition(String json, PmXunjianVO vo, Map map){
    
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

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT x.*,l.LEVEL_NAME FROM pm_xunjian x ");
            sql.append(" LEFT JOIN sys_jiancha_level l ");
            sql.append(" ON x.JIANCHA_LEVEL_UID = l.JIANCHA_LEVEL_UID ");
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
    
    // 在此可加入其它方法
    
	/**
	 * 获取某一巡检内容最大序号
	 */
	public String getMaxCode(String xunjianUid) {
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {
        	String sql = "SELECT MAX(t.XUHAO) XUHAO FROM pm_xunjian_content t WHERE t.XUNJIAN_UID ='"+xunjianUid+"'";
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

}
