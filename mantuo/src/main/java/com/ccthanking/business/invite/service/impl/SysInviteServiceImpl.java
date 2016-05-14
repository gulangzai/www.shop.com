/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    invite.service.SysInviteService.java
 * 创建日期： 2016-04-25 下午 01:12:45
 * 功能：    接口：系统邀请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 01:12:45  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.invite.service.impl;


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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.business.invite.dao.SysInviteDao;
import com.ccthanking.business.invite.service.SysInviteService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.DateUtil;

import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SysInviteService.java </p>
 * <p> 功能：系统邀请 </p>
 *
 * <p><a href="SysInviteService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

@Service
public class SysInviteServiceImpl extends Base1ServiceImpl<SysInviteVO, String> implements SysInviteService {

	private static Logger logger = LoggerFactory.getLogger(SysInviteServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.SYS_INVITE;
    
    private SysInviteDao sysInviteDao;

   
    public String queryCondition(String json) throws Exception { 
    	User user = ActionContext.getCurrentUserInThread(); 
        String domresult = "";
        try { 
			domresult = sysInviteDao.queryCondition(json, null, null); 
         /*   LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<系统邀请>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("系统邀请{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "系统邀请查询失败", user, "", "");*/
			SystemException.handleMessageException("系统邀请查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult; 
    }
    
    
    
    public String insert(String json) throws Exception { 
		User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        SysInviteVO vo = new SysInviteVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键 
            BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx); 
           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		   //vo.setSjbh(eventVO.getSjbh()); 
            // 插入
			sysInviteDao.save(vo);
            resultVO = vo.getRowJson();

            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "系统邀请新增成功", user, "", "");*/

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("系统邀请{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "系统邀请新增失败", user, "", "");*/
            SystemException.handleMessageException("系统邀请新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public void updateVo(SysInviteVO sysInviteVo){
    	sysInviteDao.update(sysInviteVo);
    }

    public String update(String json) throws Exception { 
        User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        SysInviteVO vo = new SysInviteVO(); 
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
            sysInviteDao.update(vo);
            resultVO = vo.getRowJson();

           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "系统邀请修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("系统邀请{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "系统邀请修改失败", user, "", "");*/
            SystemException.handleMessageException("系统邀请修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO; 
    }
    
 
	public void updateById(String invite_uid,String status) {
		// TODO Auto-generated method stub
		SysInviteVO sysInviteVo = get(invite_uid);
		sysInviteVo.setStatus(status);
	    this.update(sysInviteVo);
	}
    
    public void updateByInviter_user(String inviter_user){
    	Connection conn = null; 
		try {
			conn  = DBUtil.getConnection();
			String sql = "select * from sys_invite where inviter_user = '"+inviter_user+"'";
			String[][] result = DBUtil.query(conn, sql);
			if(result.length>0){
				String invite_uid = result[0][0]; 
				if(invite_uid!=null){
					SysInviteVO sysInviteVo = sysInviteDao.get(SysInviteVO.class,invite_uid);
					if(sysInviteVo!=null){
						sysInviteVo.setJoin_date(new Date());
						sysInviteDao.update(sysInviteVo);
					} 
				} 
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConnetion(conn);
		}
    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SysInviteVO vo = new SysInviteVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//sysInviteDao.delete(SysInviteVO.class, vo.getId());

			resultVo = vo.getRowJson();

			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "系统邀请删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("系统邀请{}", e.getMessage());
            /*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "系统邀请删除失败", user, "", "");*/
            SystemException.handleMessageException("系统邀请删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sysInviteDaoImpl")
	public void setSysInviteDao(SysInviteDao sysInviteDao) {
		this.sysInviteDao = sysInviteDao;
	}

	/**
	 * 判断邀请码是否过期
	 */ 
	public boolean isOutDate(String userId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		boolean flag = false;
		try {
			conn  = DBUtil.getConnection();
			String sql = "select INVITE_DATE,EXPIRED_DATE from sys_invite where inviter_user = '"+userId+"'";
			String[][] result = DBUtil.query(conn, sql);
			if(result!=null){
				String invite_date = result[0][1];
				flag = DateUtil.isOverdue(invite_date);
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return flag;
	}
 
	public SysInviteVO get(String invite_uid) {
		// TODO Auto-generated method stub 
		return sysInviteDao.get(SysInviteVO.class,invite_uid);
	}

 
	public void updateStatusAndJoinDate(String invite_uid,String status) {
		// TODO Auto-generated method stub
		SysInviteVO sysInviteVO = this.get(invite_uid);
    	sysInviteVO.setStatus(status);
    	sysInviteVO.setJoin_date(new Date());
    	sysInviteDao.update(sysInviteVO);
	}
 
	public void updateStatus(String invite_uid, String status) {
		// TODO Auto-generated method stub
		SysInviteVO sysInviteVO = this.get(invite_uid);
    	sysInviteVO.setStatus(status); 
    	sysInviteDao.update(sysInviteVO);
	}




    
}
