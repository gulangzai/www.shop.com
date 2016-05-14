/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmscslweekplan.service.PmScslWeekPlanService.java
 * 创建日期： 2016-05-12 上午 10:02:05
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-12 上午 10:02:05  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmscslweekplan.service.impl;


import java.sql.Connection;
import java.util.Date;

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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.pmscslweekplan.vo.PmScslWeekPlanVO;
import com.ccthanking.business.pmscslweekplan.dao.PmScslWeekPlanDao;
import com.ccthanking.business.pmscslweekplan.service.PmScslWeekPlanService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmScslWeekPlanService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmScslWeekPlanService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-05-12
 * 
 */

@Service
public class PmScslWeekPlanServiceImpl extends Base1ServiceImpl<PmScslWeekPlanVO, String> implements PmScslWeekPlanService {

	private static Logger logger = LoggerFactory.getLogger(PmScslWeekPlanServiceImpl.class);
	
	
    private PmScslWeekPlanDao pmScslWeekPlanDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmScslWeekPlanDao.queryCondition(json, null, null);

      
        }catch (DaoException e) {
        	logger.error("监测项目{}", e.getMessage());
			SystemException.handleMessageException("监测项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmScslWeekPlanVO vo = new PmScslWeekPlanVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
        	JSONArray list = vo.doInitJson(json);
            JSONObject obj  = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
           
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            String targetUid = obj.getString("target_uid");
            // 插入
            Long project_uid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=project_uid&&!"".equals(project_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(project_uid));                 
            }
			
      
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
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
        PmScslWeekPlanVO vo = new PmScslWeekPlanVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	//BusinessUtil.setUpdateCommonFields(vo, user);
       
            // 修改
            pmScslWeekPlanDao.update(vo);
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
		PmScslWeekPlanVO vo = new PmScslWeekPlanVO();
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
	@Qualifier("pmScslWeekPlanDaoImpl")
	public void setPmScslWeekPlanDao(PmScslWeekPlanDao pmScslWeekPlanDao) {
		this.pmScslWeekPlanDao = pmScslWeekPlanDao;
	}

	public Boolean deleteById(String SCSLWEEKPLANUID) {
		// TODO Auto-generated method stub
		return this.pmScslWeekPlanDao.deleteById(SCSLWEEKPLANUID);
	}

	public String queryId(String SCSLWEEKPLANUID) {
		// TODO Auto-generated method stub
		return this.pmScslWeekPlanDao.queryId(SCSLWEEKPLANUID);
	}
    
}
