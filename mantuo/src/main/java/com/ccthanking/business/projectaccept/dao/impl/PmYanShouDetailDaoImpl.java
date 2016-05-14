/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.projectaccept.PmYanShouDetailDao.java
 * 创建日期： 2016-03-28 上午 11:52:17
 * 功能：   工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:52:17  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectaccept.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.projectaccept.dao.PmYanShouDetailDao;
import com.ccthanking.business.projectaccept.vo.PmYanShouDetailVO;
import com.ccthanking.business.projectaccept.vo.PmYanShouVO;
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
 * <p> PmYanShouDetailDao.java </p>
 * <p> 功能：工程验收 </p>
 *
 * <p><a href="PmYanShouDetailDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

@Component
public class PmYanShouDetailDaoImpl  extends BsBaseDaoTJdbc implements PmYanShouDetailDao {

    public String queryCondition(String json, PmYanShouDetailVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
           // condition += BusinessUtil.getSJYXCondition(null);
            condition += BusinessUtil.getCommonCondition(user, null);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "SELECT * FROM " + "PM_YANSHOU_DETAIL tt";
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
    
    
 
	public List querySon(String json, Object object, Object object2,
			String yanshou_uid) {
    	User user = ActionContext.getCurrentUserInThread(); 
        Connection conn = DBUtil.getConnection();
        List list  = null;   
        try {    
            String sql="select pyd.YANSHOU_DETAIL_UID,pyd.XUHAO,pyd.DETAIL_NAME,pyd.DETAIL_NUMS,pyd.DETAIL_BUWEI,pyd.DETAIL_JCJG,pyd.DETAIL_YSJL from pm_yanshou_detail pyd where pyd.YANSHOU_UID="+yanshou_uid;
            list = DBUtil.queryReturnList(conn, sql);  
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return list;
	}
    
    // 在此可加入其它方法

}
