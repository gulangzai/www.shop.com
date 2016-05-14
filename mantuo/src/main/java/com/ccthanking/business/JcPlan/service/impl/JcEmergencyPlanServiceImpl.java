/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcPlan.service.JcEmergencyPlanService.java
 * 创建日期： 2015-10-29 上午 10:46:26
 * 功能：    接口：施工应急预案
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:46:26  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcPlan.service.impl;



import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.json.types.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.JcPlan.vo.JcEmergencyPlanVO;
import com.ccthanking.business.JcPlan.dao.JcEmergencyPlanDao;
import com.ccthanking.business.JcPlan.service.JcEmergencyPlanService;
import com.ccthanking.business.jcsb.vo.JcJkEquipInfoVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcEmergencyPlanService.java </p>
 * <p> 功能：施工应急预案 </p>
 *
 * <p><a href="JcEmergencyPlanService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Service
public class JcEmergencyPlanServiceImpl extends Base1ServiceImpl<JcEmergencyPlanVO, String> implements JcEmergencyPlanService {

	private static Logger logger = LoggerFactory.getLogger(JcEmergencyPlanServiceImpl.class);
	
    private JcEmergencyPlanDao jcEmergencyPlanDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcEmergencyPlanDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("施工应急预案查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JcEmergencyPlanVO vo = new JcEmergencyPlanVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
           
			jcEmergencyPlanDao.save(vo);
            resultVO = vo.getRowJson();
        } catch (DaoException e) {
            SystemException.handleMessageException("施工应急预案新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JcEmergencyPlanVO vo = new JcEmergencyPlanVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String uid = obj.getString("EMERGENCY_PLAN_UID");
            vo.setEmergency_plan_uid(uid);
            // 修改
            jcEmergencyPlanDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            SystemException.handleMessageException("施工应急预案修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		JcEmergencyPlanVO vo = new JcEmergencyPlanVO();
		 String [] uid = null;
		 String eId = null;
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
            String uids = jsonObj.getString("EMERGENCY_PLAN_UID");
            
            uid = uids.split(",");
		if(uids.indexOf(",")!=-1 ){
                if(null!=uids){
                	for (int i = 0; i < uid.length; i++) {
                	eId = uid[i];
                	jcEmergencyPlanDao.delete(JcEmergencyPlanVO.class,eId);
    				}
                }
            }else{//只有一条
            	 //删除   根据据主键
            	 vo.setValueFromJson(jsonObj);
			      jcEmergencyPlanDao.delete(JcEmergencyPlanVO.class,vo.getEmergency_plan_uid());
            }
			resultVo = vo.getRowJson();
	

		} catch (DaoException e) {
            SystemException.handleMessageException("施工应急预案删除失败,请联系相关人员处理");
		} finally {
			
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcEmergencyPlanDaoImpl")
	public void setJcEmergencyPlanDao(JcEmergencyPlanDao jcEmergencyPlanDao) {
		this.jcEmergencyPlanDao = jcEmergencyPlanDao;
	}

	public String query(String msg) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = jcEmergencyPlanDao.query(msg, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("施工应急预案查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

	}
    
}
