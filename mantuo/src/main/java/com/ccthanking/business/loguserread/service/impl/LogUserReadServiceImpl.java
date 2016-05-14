/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    loguserread.service.LogUserReadService.java
 * 创建日期： 2016-04-25 下午 05:34:16
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 05:34:16  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.loguserread.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.loguserread.vo.LogUserReadVO;
import com.ccthanking.business.loguserread.dao.LogUserReadDao;
import com.ccthanking.business.loguserread.service.LogUserReadService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> LogUserReadService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="LogUserReadService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

@Service
public class LogUserReadServiceImpl extends Base1ServiceImpl<LogUserReadVO, String> implements LogUserReadService {

	private static Logger logger = LoggerFactory.getLogger(LogUserReadServiceImpl.class);
	
	
    
    private LogUserReadDao logUserReadDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = logUserReadDao.queryCondition(json, null, null);

          
        }catch (DaoException e) {
        	logger.error("监测项目{}", e.getMessage());
		
			SystemException.handleMessageException("监测项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json ,String session) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        LogUserReadVO vo = new LogUserReadVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
        	JSONArray list = vo.doInitJson(json);
            JSONObject obj  = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setLogin_session(session);
            vo.setRead_time(new Date());
            //vo.setUser_uid(user.getIdCard());
            //vo.setUser_name(user.getName());
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           // BusinessUtil.setInsertCommonFields(vo, user);
           
            // 插入
			logUserReadDao.save(vo);
            resultVO = vo.getRowJson();

			
            
        } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
           
            SystemException.handleMessageException("监测项目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        LogUserReadVO vo = new LogUserReadVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
          
            // 修改
            logUserReadDao.update(vo);
            resultVO = vo.getRowJson();

           
        } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
         
            SystemException.handleMessageException("监测项目修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		LogUserReadVO vo = new LogUserReadVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
		
			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
           
            SystemException.handleMessageException("监测项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("logUserReadDaoImpl")
	public void setLogUserReadDao(LogUserReadDao logUserReadDao) {
		this.logUserReadDao = logUserReadDao;
	}

	public String query(String project_uid, String user_uid,
			String target_uid, String target_code) throws Exception {
		
		return this.logUserReadDao.query(project_uid, user_uid, target_uid, target_code);
	}
    
}
