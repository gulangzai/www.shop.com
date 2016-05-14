/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZgDafuContentService.java
 * 创建日期： 2016-03-30 上午 10:43:36
 * 功能：    接口：工程整改答复内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:43:36  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service.impl;


import java.util.List;

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

import com.ccthanking.business.zhenggai.vo.PmZgDafuContentVO;
import com.ccthanking.business.zhenggai.dao.PmZgDafuContentDao;
import com.ccthanking.business.zhenggai.service.PmZgDafuContentService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmZgDafuContentService.java </p>
 * <p> 功能：工程整改答复内容 </p>
 *
 * <p><a href="PmZgDafuContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Service
public class PmZgDafuContentServiceImpl extends Base1ServiceImpl<PmZgDafuContentVO, String> implements PmZgDafuContentService {

	private static Logger logger = LoggerFactory.getLogger(PmZgDafuContentServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.PM_ZG_DAFU_CONTENT;
    
    private PmZgDafuContentDao pmZgDafuContentDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmZgDafuContentDao.queryCondition(json, null, null);

            /*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程整改答复内容>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("工程整改答复内容{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复内容查询失败", user, "", "");*/
			SystemException.handleMessageException("工程整改答复内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult; 
    }
     
    
    public List querySon(String json,String ZG_DAFU_UID) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread(); 
        List domresult = null;
        try { 
			domresult = pmZgDafuContentDao.querySon(json, null, null,ZG_DAFU_UID); 
            /*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程整改答复内容>", user, "", "");*/ 
        }catch (DaoException e) {
        	logger.error("工程整改答复内容{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复内容查询失败", user, "", "");*/
			SystemException.handleMessageException("工程整改答复内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult; 
    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmZgDafuContentVO vo = new PmZgDafuContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
           //vo.setYwlx(ywlx);

           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		   // vo.setSjbh(eventVO.getSjbh());

            // 插入
			pmZgDafuContentDao.save(vo);
            resultVO = vo.getRowJson();

           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复内容新增成功", user, "", "");*/

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("工程整改答复内容{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复内容新增失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复内容新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmZgDafuContentVO vo = new PmZgDafuContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
           // vo.setYwlx(ywlx);
           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    //vo.setSjbh(eventVO.getSjbh());

            // 修改
            pmZgDafuContentDao.update(vo);
            resultVO = vo.getRowJson();

           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复内容修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("工程整改答复内容{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复内容修改失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复内容修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmZgDafuContentVO vo = new PmZgDafuContentVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		//	EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		//	vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
		//	pmZgDafuContentDao.delete(PmZgDafuContentVO.class, vo.getId());

			resultVo = vo.getRowJson();

		/*	LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复内容删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("工程整改答复内容{}", e.getMessage());
        /*    LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复内容删除失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复内容删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmZgDafuContentDaoImpl")
	public void setPmZgDafuContentDao(PmZgDafuContentDao pmZgDafuContentDao) {
		this.pmZgDafuContentDao = pmZgDafuContentDao;
	}
    
}
