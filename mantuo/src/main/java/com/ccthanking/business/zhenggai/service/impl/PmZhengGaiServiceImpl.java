/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZhengGaiService.java
 * 创建日期： 2016-03-30 上午 10:31:40
 * 功能：    接口：工程整改
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:31:40  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.pmxianchang.vo.PmXianchangStrucVO;
import com.ccthanking.business.projectaccept.vo.PmYanShouDetailVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiContentVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.business.zhenggai.dao.PmZhengGaiContentDao;
import com.ccthanking.business.zhenggai.dao.PmZhengGaiDao;
import com.ccthanking.business.zhenggai.service.PmZhengGaiService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmZhengGaiService.java </p>
 * <p> 功能：工程整改 </p>
 *
 * <p><a href="PmZhengGaiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Service
public class PmZhengGaiServiceImpl extends Base1ServiceImpl<PmZhengGaiVO, String> implements PmZhengGaiService {

	private static Logger logger = LoggerFactory.getLogger(PmZhengGaiServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.PM_ZHENGGAI;
    
    private PmZhengGaiDao pmZhengGaiDao;
    
    @Autowired
    private PmZhengGaiContentDao pmZhengGaiContentDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmZhengGaiDao.queryCondition(json, null, null);

           /* LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程整改>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("工程整改{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程整改查询失败", user, "", "");*/
			SystemException.handleMessageException("工程整改查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmZhengGaiVO vo = new PmZhengGaiVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            String targetUid = obj.getString("target_uid");
            System.out.println("json:"+json);
            Long zhengai_uid = BaseDAO.insert(conn,vo);
            
            //更新附件信息
            if(null!=zhengai_uid&&!"".equals(zhengai_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(zhengai_uid));                 
            }
            
            if(zhengai_uid!=0){
            	if(json.indexOf("BZGF_UID")!=-1){
            		String BZGF_UIDs = obj.getString("BZGF_UID");
            		if(BZGF_UIDs.indexOf("[")!=-1){
            			JSONArray uids = obj.getJSONArray("BZGF_UID");
            			if(null!=uids){
            				for (int i = 0; i < uids.size(); i++) {
	            				 String BZGF_UID = obj.getJSONArray("BZGF_UID").getString(i);
	                    		String XUHAO = obj.getJSONArray("XUHAO").getString(i);
	                    		String WEIGUI_LEVEL = obj.getJSONArray("WEIGUI_LEVEL").getString(i);
	                    		String CONTENT = obj.getJSONArray("CONTENT").getString(i);
	                    		String DESCRIPTION = obj.getJSONArray("DESCRIPTION").getString(i);
	                    		
	                    		PmZhengGaiContentVO pmZhengGaiContentVO = new PmZhengGaiContentVO();
	                    		pmZhengGaiContentVO.setZhenggai_uid(String.valueOf(zhengai_uid));
	                    		pmZhengGaiContentVO.setBzgf_uid(BZGF_UID);
	                    		pmZhengGaiContentVO.setXuhao(XUHAO);
	                    		pmZhengGaiContentVO.setWeigui_level(WEIGUI_LEVEL);
	                    		pmZhengGaiContentVO.setContent(CONTENT);
	                    		pmZhengGaiContentVO.setDescription(DESCRIPTION);
	                    		pmZhengGaiContentDao.save(pmZhengGaiContentVO); 
            				}
            			}
            		}else{
            			PmZhengGaiContentVO pmZhengGaiContentVO = new PmZhengGaiContentVO();
            			pmZhengGaiContentVO.setZhenggai_uid(String.valueOf(zhengai_uid));
            			pmZhengGaiContentVO.setBzgf_uid(obj.getString("BZGF_UID"));
            			pmZhengGaiContentVO.setXuhao(obj.getString("XUHAO"));
            			pmZhengGaiContentVO.setWeigui_level(obj.getString("WEIGUI_LEVEL"));
            			pmZhengGaiContentVO.setContent(obj.getString("CONTENT"));
                		pmZhengGaiContentVO.setDescription(obj.getString("DESCRIPTION"));
            			pmZhengGaiContentDao.save(pmZhengGaiContentVO);
            		} 
            	}
            }
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键 
           //BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx); 
           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    //vo.setSjbh(eventVO.getSjbh());
           
            // 插入
			//pmZhengGaiDao.save(vo);
            resultVO = vo.getRowJson();

         /*   LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改新增成功", user, "", "");*/
            //String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("工程整改{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改新增失败", user, "", "");*/
            SystemException.handleMessageException("工程整改新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmZhengGaiVO vo = new PmZhengGaiVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj =(JSONObject) list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setUpdate_date(new Date());
            vo.setUpdate_user(user.getIdCard());
            // 设置主键 
            String targetUid = obj.getString("target_uid"); 
            //更新附件
            if(vo!=null&&vo.getZhenggai_uid()!=null&&!"".equals(vo.getZhenggai_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getZhenggai_uid());                 
            }
            
            // 修改
            BaseDAO.update(conn,vo);
            if(json.indexOf("ZHENGGAI_CONTENT_UID")!=-1){
             	  String detailNames = obj.getString("ZHENGGAI_CONTENT_UID"); 
                  	   if(detailNames.indexOf("[")!=-1){
                      	   JSONArray uids = obj.getJSONArray("ZHENGGAI_CONTENT_UID"); 
                      	   if(null!=uids){
                             	 for (int i = 0; i < uids.size(); i++) {
                             		String XUHAO =obj.getJSONArray("XUHAO").getString(i); 
                             		//String BZGF_UID = obj.getJSONArray("BZGF_UID").getString(i);
                             		String WEIGUI_LEVEL = obj.getJSONArray("WEIGUI_LEVEL").getString(i);
                             		String CONTENT = obj.getJSONArray("CONTENT").getString(i);
                             		String DESCRIPTION = obj.getJSONArray("DESCRIPTION").getString(i); 
                             		
                             		PmZhengGaiContentVO pmZhengGaiContentVO = pmZhengGaiContentDao.get(PmZhengGaiContentVO.class, obj.getJSONArray("ZHENGGAI_CONTENT_UID").getString(i));
                             		pmZhengGaiContentVO.setXuhao(XUHAO);
                             		//pmZhengGaiContentVO.setBzgf_uid(BZGF_UID);
                             		pmZhengGaiContentVO.setWeigui_level(WEIGUI_LEVEL);
                             		pmZhengGaiContentVO.setContent(CONTENT);
                             		pmZhengGaiContentVO.setDescription(DESCRIPTION); 
                                    // 更新
                             		pmZhengGaiContentDao.update(pmZhengGaiContentVO);                
                 				 } 
                      	   }
                      }else{ 
                    	PmZhengGaiContentVO pmZhengGaiContentVO = new PmZhengGaiContentVO(); 
                    	pmZhengGaiContentVO.setZhenggai_content_uid(obj.getString("ZHENGGAI_CONTENT_UID"));
                    	pmZhengGaiContentVO.setXuhao(obj.getString("XUHAO"));
                    	//pmZhengGaiContentVO.setBzgf_uid(obj.getString("BZGF_UID"));
                    	pmZhengGaiContentVO.setWeigui_level(obj.getString("WEIGUI_LEVEL"));
                    	pmZhengGaiContentVO.setContent(obj.getString("CONTENT"));
                    	pmZhengGaiContentVO.setDescription(obj.getString("DESCRIPTION")); 
                           // 添加
                  		pmZhengGaiContentDao.update(pmZhengGaiContentVO);  
                       } 
                    }  
            resultVO = vo.getRowJson();

           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("工程整改{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改修改失败", user, "", "");*/
            SystemException.handleMessageException("工程整改修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String project_uid,String zhenggai_uid) throws Exception {

		User user = ActionContext.getCurrentUserInThread(); 
		String resultVo = null;
		PmZhengGaiVO vo = new PmZhengGaiVO();
		try { 
			vo.setProject_uid(project_uid);
			vo.setZhenggai_uid(zhenggai_uid); 
			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh()); 
			//删除   根据据主键 
            if(!"".equals(zhenggai_uid)&&zhenggai_uid!=null){
            	deleteZhenggaiContent(zhenggai_uid);
            }
            pmZhengGaiDao.delete(PmZhengGaiVO.class,vo.getZhenggai_uid());
			resultVo = vo.getRowJson(); 
			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改删除成功", user, "", "");*/ 
		} catch (DaoException e) {
            logger.error("工程整改{}", e.getMessage());
            /*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改删除失败", user, "", "");*/
            SystemException.handleMessageException("工程整改删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    //删除子表
    private boolean deleteZhenggaiContent(String zhenggai_uid) throws SQLException{
    	Connection con = DBUtil.getConnection();
    	PmZhengGaiContentVO pvo = new PmZhengGaiContentVO();
    	boolean bs = false;
	   try {  
		   String querySql ="select ZHENGGAI_CONTENT_UID from pm_zhenggai_content where ZHENGGAI_UID ="+zhenggai_uid;
		   BaseResultSet brs = DBUtil.query(con,querySql, null);
		   ResultSet rs = brs.getResultSet();
		   //删pm_zg_dafu_content表
		   while(rs.next()){
			   String ZHENGGAI_CONTENT_UID = rs.getString("ZHENGGAI_CONTENT_UID");
			   String deleteDafuContent = "delete from pm_zg_dafu_content where ZHENGGAI_CONTENT_UID="+ZHENGGAI_CONTENT_UID;
		       bs = DBUtil.execSql(con, deleteDafuContent);
		   }
		   //删pm_zhenggai_content表
		   String sql = "delete from pm_zhenggai_content where ZHENGGAI_UID ="+zhenggai_uid;
		   bs =  DBUtil.execSql(con, sql); 
		   //删pm_zg_dafu表
		   String deleteDafu = "delete from pm_zg_dafu where ZHENGGAI_UID="+zhenggai_uid;
		   bs =  DBUtil.execSql(con, deleteDafu); 
		   con.commit();
		} catch (Exception e) {
			 e.printStackTrace(); 
			 con.rollback(); 
			SystemException.handleMessageException("整改表信息详情表数据修改失败,请联系相关人员处理");
		} finally{
			if(con != null){
				DBUtil.closeConnetion(con);
			} 
		} 
	    return bs; 
    }

	@Autowired
	@Qualifier("pmZhengGaiDaoImpl")
	public void setPmZhengGaiDao(PmZhengGaiDao pmZhengGaiDao) {
		this.pmZhengGaiDao = pmZhengGaiDao;
	}
    
}
