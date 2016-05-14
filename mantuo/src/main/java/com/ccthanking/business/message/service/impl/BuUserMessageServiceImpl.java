/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    message.service.BuUserMessageService.java
 * 创建日期： 2016-04-21 上午 11:18:28
 * 功能：    接口：个人消息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-21 上午 11:18:28  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.message.service.impl;


import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.invite.dao.SysInviteDao;
import com.ccthanking.business.invite.service.SysInviteService;
import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.business.message.dao.BuUserMessageDao;
import com.ccthanking.business.message.service.BuUserMessageService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl; 
import com.ccthanking.framework.util.DateUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuUserMessageService.java </p>
 * <p> 功能：个人消息 </p>
 *
 * <p><a href="BuUserMessageService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-21
 * 
 */

@Service
public class BuUserMessageServiceImpl extends Base1ServiceImpl<BuUserMessageVO, String> implements BuUserMessageService {

	private static Logger logger = LoggerFactory.getLogger(BuUserMessageServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.BU_USER_MESSAGE;
    
    private BuUserMessageDao buUserMessageDao;
    
    @Autowired
    private SysInviteService sysInviteService;

     
    public String queryCondition(String json,String user_uid) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
            Map<String,String> map = new HashMap<String,String>();
			map.put("user_uid", user_uid);
            domresult = buUserMessageDao.queryCondition(json, null, map);

          /*  LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<个人消息>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("个人消息{}", e.getMessage());
		/*	LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "个人消息查询失败", user, "", "");*/
			SystemException.handleMessageException("个人消息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
 
	public int queryMessageCount(String idCard) {
		// TODO Auto-generated method stub
		return buUserMessageDao.queryMessageCount(idCard);
	} 
    
    
    public String queryByUserMessageId(String json,String user_message_uid) throws Exception {
        User user = ActionContext.getCurrentUserInThread(); 
        String domresult = "";
        try {
            Map<String,String> map = new HashMap<String,String>();
			 map.put("user_message_uid", user_message_uid);  
            domresult = buUserMessageDao.queryByUserMessageId(json, null, map); 
        }catch (DaoException e) {
        	logger.error("个人消息{}", e.getMessage());
		/*	LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "个人消息查询失败", user, "", "");*/
			SystemException.handleMessageException("个人消息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuUserMessageVO vo = new BuUserMessageVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx);

          //  EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		   // vo.setSjbh(eventVO.getSjbh());

            // 插入
			buUserMessageDao.save(vo);
            resultVO = vo.getRowJson();

           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "个人消息新增成功", user, "", "");*/

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("个人消息{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "个人消息新增失败", user, "", "");*/
            SystemException.handleMessageException("个人消息新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public void updateById(String user_message_uid,String status)throws Exception{
     	 BuUserMessageVO svo = buUserMessageDao.get(BuUserMessageVO.class,user_message_uid);
     	 if(svo!=null){
     		 if(status.equals("0")) status="1";
             svo.setStatus(status); 
             svo.setSeetime(new Date());
             buUserMessageDao.update(svo);   
     	 } 
    }

    public String update(String json) throws Exception { 
        User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        BuUserMessageVO vo = new BuUserMessageVO(); 
        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
            //vo.setYwlx(ywlx);
            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

            // 修改
            buUserMessageDao.update(vo);
            resultVO = vo.getRowJson();

            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "个人消息修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("个人消息{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "个人消息修改失败", user, "", "");*/
            SystemException.handleMessageException("个人消息修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuUserMessageVO vo = new BuUserMessageVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//buUserMessageDao.delete(BuUserMessageVO.class, vo.getId());

			resultVo = vo.getRowJson();

			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "个人消息删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("个人消息{}", e.getMessage());
           /* LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "个人消息删除失败", user, "", "");*/
            SystemException.handleMessageException("个人消息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    //插入回馈信息
	public void insertVo(String invite_uid,String content) {
		// TODO Auto-generated method stub
		SysInviteVO vod = sysInviteService.get(invite_uid);
	    BuUserMessageVO buUserMessageVO = new BuUserMessageVO();
	    buUserMessageVO.setUser_uid(vod.getInviter_user());
	    buUserMessageVO.setMessage_content(content);
	    buUserMessageVO.setMessage_time(new Date());
	    buUserMessageVO.setStatus("0");
	    this.save(buUserMessageVO);
	}
    
    
	public void save(BuUserMessageVO buUserMessageVo) {
		// TODO Auto-generated method stub
    	Connection conn = null;
    	try {
			conn=DBUtil.getConnection(); 
			Long user_message_uid =BaseDAO.insert(conn, buUserMessageVo);
			System.out.println("user_message_uid"+user_message_uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		} 
	}
    
   
   	public boolean updateMessageVO(BuUserMessageVO buUserMessageVo) {
   		// TODO Auto-generated method stub
       	Connection conn = null;
       	boolean flag = false;
       	try {
   			conn=DBUtil.getConnection(); 
   		    flag   =BaseDAO.update(conn, buUserMessageVo); 
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}finally{
   			DBUtil.closeConnetion(conn);
   		} 
       	return flag;
   	}
    

	@Autowired
	@Qualifier("buUserMessageDaoImpl")
	public void setBuUserMessageDao(BuUserMessageDao buUserMessageDao) {
		this.buUserMessageDao = buUserMessageDao;
	} 
	
}
