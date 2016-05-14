/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectUserService.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：    接口：项目用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.model.requestJson;

import com.ccthanking.business.commons.vo.SysUserVO;
import com.ccthanking.business.project.vo.BuProjectRoleUserVO;
import com.ccthanking.business.project.vo.BuProjectRoleVO;
import com.ccthanking.business.project.vo.BuProjectUserVO;
import com.ccthanking.business.project.dao.BuProjectUserDao;
import com.ccthanking.business.project.service.BuProjectUserService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.DateUtil;
import com.ccthanking.framework.util.StringUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuProjectUserService.java </p>
 * <p> 功能：项目用户 </p>
 *
 * <p><a href="BuProjectUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */

@Service
public class BuProjectUserServiceImpl extends Base1ServiceImpl<BuProjectUserVO, String> implements BuProjectUserService {

	private static Logger logger = LoggerFactory.getLogger(BuProjectUserServiceImpl.class);
	
    
    private BuProjectUserDao buProjectUserDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buProjectUserDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("项目用户{}", e.getMessage());
			SystemException.handleMessageException("项目用户查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

        String resultVO = null;
        BuProjectUserVO vo = new BuProjectUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0)); 
            // 插入
			buProjectUserDao.save(vo);
            resultVO = vo.getRowJson(); 
        } catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO; 
    }
    
 
	public requestJson save(String user_uid, String project_uid,String project_role_uid,String user_message_uid) {
		// TODO Auto-generated method stub
		    requestJson requestJson = new requestJson();
	        BuProjectUserVO vo = new BuProjectUserVO(); 
	        try { 
	        	vo.setUser_uid(user_uid);
	        	vo.setProject_uid(project_uid);
	        	boolean flag = buProjectUserDao.getByCondition(vo); 
	        	if(!flag){
	        		// 插入
	        		vo.setCreated_date(new Date()); 
					Long project_user_uid = this.insertBuProjectUserVO(vo); 
					if(project_user_uid!=null){
						BuProjectRoleUserVO buProjectRoleUserVo = new BuProjectRoleUserVO();
						buProjectRoleUserVo.setProject_role_uid(project_role_uid);
						buProjectRoleUserVo.setProject_user_uid(String.valueOf(project_user_uid));
					    Long project_role_user_uid = this.insertBuProjectRoleUserVO(buProjectRoleUserVo); 
					    
					} 
					requestJson.setSuccess(true);
	        	} else{
	        		requestJson.setSuccess(false);
	        		requestJson.setMsg("该用户已经是此项目组成员");
	        	}
	        } catch (DaoException e) {
	        	requestJson.setSuccess(false);
	            logger.error("项目用户{}", e.getMessage());
	            SystemException.handleMessageException("项目用户新增失败,请联系相关人员处理");
	        } finally {
	        } 
		return requestJson;
	}
	
	private Long insertBuProjectRoleUserVO(BuProjectRoleUserVO buProjectRoleUserVo) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		StringBuffer sb= new StringBuffer();
		Long project_role_user_uid = null;
		try {
			sb.append("insert into bu_project_role_user(PROJECT_ROLE_UID,PROJECT_USER_UID)values('"+buProjectRoleUserVo.getProject_role_uid()+"','"+buProjectRoleUserVo.getProject_user_uid()+"')");
			conn = DBUtil.getConnection();
			project_role_user_uid = DBUtil.execInsertSql(conn,sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return project_role_user_uid;
	}

	public Long insertBuProjectUserVO(BuProjectUserVO vo){
		Connection conn = null; 
		StringBuffer sb= new StringBuffer();
		Long project_user_uid = null;
		String create_date = DateUtil.getDateString(vo.getCreated_date());
		try {
			sb.append("insert into bu_project_user(PROJECT_UID,USER_UID,CREATED_DATE)VALUES('"+vo.getProject_uid()+"','"+vo.getUser_uid()+"','"+create_date+"');");
			System.out.println("sql:"+sb.toString());
			conn = DBUtil.getConnection();
			project_user_uid = DBUtil.execInsertSql(conn,sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return project_user_uid;
	}

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuProjectUserVO vo = new BuProjectUserVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            buProjectUserDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuProjectUserVO vo = new BuProjectUserVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			buProjectUserDao.delete(BuProjectUserVO.class, vo.getProject_user_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buProjectUserDaoImpl")
	public void setBuProjectUserDao(BuProjectUserDao buProjectUserDao) {
		this.buProjectUserDao = buProjectUserDao;
	}

	//插入邀请记录
	public Map<String,String> insertInvite(Map<String, String> map) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		Map<String,String> rmap = new HashMap<String, String>();
		String userName = "";
		String userUid = "";
		String login = "";
		//判断是否已注册
		String status = "false";
		String  invite_code =  StringUtil.getUUID();
		try {
			//验证用户有没有注册
			String sql  = "SELECT u.USER_UID,u.USER_NAME,u.LOGON_NAME FROM sys_user u WHERE u.EMAIL = '"+map.get("email")+"'";
			String[][] ulist = DBUtil.query(conn, sql);
			if(null!=ulist && ulist.length>0){
				userName = ulist[0][1];
				userUid  = ulist[0][0]; 
				login  = ulist[0][2]; 
				status="true";
			}
			
			//验证该项目该用户有验证通过的邀请
			String esql = "SELECT * FROM sys_invite WHERE PROJECT_UID ='"+map.get("project_uid")+"' AND  EMAIL ='"+map.get("email")+"' ";
			String[][] vlist = DBUtil.query(conn, esql);
			//没有该用户的，用户userId为注册码 
				
			//没有邀请记录 直接添加
			if(null==vlist){
				String uuid = String.valueOf(System.nanoTime());
				String insertSql = "INSERT INTO sys_invite (INVITE_UID,INVITER_USER,PROJECT_UID,EMAIL,INVITE_DATE,EXPIRED_DATE,CREATE_USER,INVITE_CODE)"+
										"VALUES ('"+uuid+"','"+map.get("INVITER_USER")+"','"+map.get("project_uid")+"','"+map.get("email")+"',SYSDATE(),date_add(SYSDATE(), interval 1 day),'"+
						map.get("create_user")+"','"+invite_code+"')";
				System.out.println("insertSql:"+insertSql);
				DBUtil.execSql(conn, insertSql);
				rmap.put("uuid",uuid);
				rmap.put("INVITE_CODE", invite_code); 
			}else{
				//有效、未确认邀请 查询uuid 准备第二次发送 
				if("".equals(vlist[0][6])&& !DateUtil.isOverdue(vlist[0][5])){
					rmap.put("uuid",vlist[0][0]);
				}
				//过期重新发送
				else{
					//String uuid = String.valueOf(System.nanoTime());
					//String insertSql = "INSERT INTO sys_invite (INVITE_UID,INVITER_USER,PROJECT_UID,EMAIL,INVITE_DATE,EXPIRED_DATE)"+
					//					"VALUES ('"+uuid+"','"+userUid+"','"+map.get("project_uid")+"','"+map.get("email")+"',SYSDATE(),date_add(SYSDATE(), interval 1 day))";
					//DBUtil.execSql(conn, insertSql);
					//rmap.put("uuid",uuid);
					//2015-11-04 需求变更要求覆盖过期记录
					String updateSql = "UPDATE sys_invite SET INVITE_DATE = SYSDATE(),EXPIRED_DATE = date_add(SYSDATE(), interval 1 day) "+
									  " WHERE INVITE_UID = '"+vlist[0][0]+"'";
					DBUtil.execSql(conn, updateSql);
					rmap.put("uuid",vlist[0][0]);
				}
			}
			
			rmap.put("userName", userName);
			rmap.put("userUid", userUid); 
			rmap.put("login", login);
			rmap.put("yqName", user.getName());
            rmap.put("status", status);
            rmap.put("invite_code",invite_code);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return rmap;
	}

	public Map<String,String> checkEmail(String email) {

        return this.buProjectUserDao.checkEmail(email);
	}

	public Map<String,String> checkTime(String uuid) {
		// TODO Auto-generated method stub
		return this.buProjectUserDao.checkTime(uuid);
	}

	public Map<String, String> userConfirm(String uuid) {
		// TODO Auto-generated method stub
		return this.buProjectUserDao.userConfirm(uuid);
	}

	public String queryList(String msg) {
		// TODO Auto-generated method stub
		return this.buProjectUserDao.queryList(msg);
	}

 
	public List<Map<String, String>> getRoleByProject(String project_uid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select *from bu_project_role where PROJECT_UID="+project_uid);
		List<Map<String, String>> buProjectRoleVos = null;
		try {
			conn = DBUtil.getConnection();
			buProjectRoleVos = DBUtil.queryReturnList(conn, sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		return buProjectRoleVos;
	}


	
	 
    
}
