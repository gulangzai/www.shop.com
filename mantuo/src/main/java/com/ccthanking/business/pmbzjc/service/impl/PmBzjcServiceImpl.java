/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmbzjc.service.PmBzjcService.java
 * 创建日期： 2016-05-11 上午 09:12:48
 * 功能：    接口：项目标准检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 09:12:48  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmbzjc.service.impl;


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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;
import com.ccthanking.business.pmbzjc.dao.PmBzjcDao;
import com.ccthanking.business.pmbzjc.service.PmBzjcService;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmBzjcService.java </p>
 * <p> 功能：项目标准检查 </p>
 *
 * <p><a href="PmBzjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Service
public class PmBzjcServiceImpl extends Base1ServiceImpl<PmBzjcVO, String> implements PmBzjcService {

	private static Logger logger = LoggerFactory.getLogger(PmBzjcServiceImpl.class);
	
	// 业务类型
   //private String ywlx = YwlxManager.PM_BZJC;
    
    private PmBzjcDao pmBzjcDao;

    public String queryTreeCondition(String json,Map xmmap)throws Exception {
		
		PageManager page = RequestUtil.getPageManager(json);
		int countRows = page.getCountRows();
		String NUMBER=(String)xmmap.get("NUMBER");
		int num=5;
		if(NUMBER!=null&&!"".equals(NUMBER)){
			num=Integer.valueOf(NUMBER);		
		} 
		String resultjson=pmBzjcDao.queryTreeCondition(json,xmmap) ;  
		return resultjson;
	}
    
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmBzjcDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<项目标准检查>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("项目标准检查{}", e.getMessage());
			LogManager.writeUserLog(null, null, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "项目标准检查查询失败", user, "", "");
			SystemException.handleMessageException("项目标准检查查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmBzjcVO vo = new PmBzjcVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            String targetUid = obj.getString("target_uid");
            Long bzjc_uid = BaseDAO.insert(conn,vo);
            //更新附件信息
            if(null!=bzjc_uid&&!"".equals(bzjc_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(bzjc_uid));                 
            } 
            resultVO = vo.getRowJson();

/*            LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "项目标准检查新增成功", user, "", "");
*/
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("项目标准检查{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "项目标准检查新增失败", user, "", "");*/
            SystemException.handleMessageException("项目飞行标准检查新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmBzjcVO vo = new PmBzjcVO();
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
            if(vo!=null&&vo.getBzjc_uid()!=null&&!"".equals(vo.getBzjc_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getBzjc_uid());                 
            }
            
            // 修改
            BaseDAO.update(conn,vo);
            resultVO = vo.getRowJson(); 
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "项目标准检查修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("项目飞行标准检查{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "项目标准检查修改失败", user, "", "");*/
            SystemException.handleMessageException("项目飞行标准检查修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmBzjcVO vo = new PmBzjcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//pmBzjcDao.delete(PmBzjcVO.class, vo.getId());

			resultVo = vo.getRowJson();

			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "项目标准检查删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("项目标准检查{}", e.getMessage());
           /* LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "项目标准检查删除失败", user, "", "");*/
            SystemException.handleMessageException("项目标准检查删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmBzjcDaoImpl")
	public void setPmBzjcDao(PmBzjcDao pmBzjcDao) {
		this.pmBzjcDao = pmBzjcDao;
	}

	@Override
	public String queryNewContent(String bzjc_uid, PmBzjcVO vo) {
		// TODO Auto-generated method stub
		User user = ActionContext.getCurrentUserInThread(); 
        String domresult = "";
        try { 
			domresult = pmBzjcDao.queryNewContent(bzjc_uid, vo, null); 
         /*   LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程整改内容>", user, "", "");*/ 
        }catch (DaoException e) {
        	logger.error("飞行检查内容{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程整改内容查询失败", user, "", "");*/
			SystemException.handleMessageException("飞行检查内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}

	@Override
	public String delete(String project_uid, String bzjc_uid)throws Exception  {
		// TODO Auto-generated method stub
		User user = ActionContext.getCurrentUserInThread(); 
		String resultVo = null;
		PmBzjcVO vo = new PmBzjcVO();
		try { 
			vo.setProject_uid(project_uid);
			vo.setBzjc_uid(bzjc_uid); 
			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh()); 
			//删除   根据据主键 
            if(!"".equals(bzjc_uid)&&bzjc_uid!=null){
            	//deleteZhenggaiContent(zhenggai_uid);
            }
            pmBzjcDao.delete(PmBzjcVO.class,vo.getBzjc_uid());
			resultVo = vo.getRowJson(); 
			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改删除成功", user, "", "");*/ 
		} catch (DaoException e) {
            logger.error("飞行检查{}", e.getMessage());
            /*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改删除失败", user, "", "");*/
            SystemException.handleMessageException("飞行检查删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}
    
}
