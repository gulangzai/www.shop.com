/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcPerson.service.JcPersonInfoService.java
 * 创建日期： 2015-10-29 上午 10:54:59
 * 功能：    接口：负责人联络方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:54:59  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcPerson.service.impl;


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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.JcPerson.vo.JcPersonInfoVO;
import com.ccthanking.business.JcPerson.dao.JcPersonInfoDao;
import com.ccthanking.business.JcPerson.service.JcPersonInfoService;
import com.ccthanking.business.JcPlan.vo.JcEmergencyPlanVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcPersonInfoService.java </p>
 * <p> 功能：负责人联络方式 </p>
 *
 * <p><a href="JcPersonInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Service
public class JcPersonInfoServiceImpl extends Base1ServiceImpl<JcPersonInfoVO,String> implements JcPersonInfoService {

	private static Logger logger = LoggerFactory.getLogger(JcPersonInfoServiceImpl.class);
    
    private JcPersonInfoDao jcPersonInfoDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcPersonInfoDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("负责人联络方式查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JcPersonInfoVO vo = new JcPersonInfoVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
			jcPersonInfoDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            SystemException.handleMessageException("负责人联络方式新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JcPersonInfoVO vo = new JcPersonInfoVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            jcPersonInfoDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            SystemException.handleMessageException("负责人联络方式修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcPersonInfoVO vo = new JcPersonInfoVO();
		 String [] uid = null;
		 String eId = null;
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
            String uids = jsonObj.getString("PERSON_INFO_UID");
            
            uid = uids.split(",");
		if(uids.indexOf(",")!=-1 ){
                if(null!=uids){
                	for (int i = 0; i < uid.length; i++) {
                	eId = uid[i];
                	jcPersonInfoDao.delete(JcPersonInfoVO.class,eId);
    				}
                }
            }else{//只有一条
            	 //删除   根据据主键
            	 vo.setValueFromJson(jsonObj);
            	 jcPersonInfoDao.delete(JcPersonInfoVO.class, vo.getPerson_info_uid());
            }

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            SystemException.handleMessageException("负责人联络方式删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcPersonInfoDaoImpl")
	public void setJcPersonInfoDao(JcPersonInfoDao jcPersonInfoDao) {
		this.jcPersonInfoDao = jcPersonInfoDao;
	}

	public String query(String msg) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = jcPersonInfoDao.query(msg, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("负责人联络方式查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
    
}
