/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectLogService.java
 * 创建日期： 2016-01-14 下午 02:44:26
 * 功能：    接口：项目日志
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-14 下午 02:44:26  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectlog.service.impl;


import java.sql.Connection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.projectlog.vo.PmProjectLogStrucVO;
import com.ccthanking.business.projectlog.vo.PmProjectLogVO;
import com.ccthanking.business.projectlog.dao.PmProjectLogDao;
import com.ccthanking.business.projectlog.service.PmProjectLogService;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmProjectLogService.java </p>
 * <p> 功能：项目日志 </p>
 *
 * <p><a href="PmProjectLogService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-14
 * 
 */

@Service
public class PmProjectLogServiceImpl extends Base1ServiceImpl<PmProjectLogVO, String> implements PmProjectLogService {

	private static Logger logger = LoggerFactory.getLogger(PmProjectLogServiceImpl.class);
	
    
    private PmProjectLogDao pmProjectLogDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
        String domresult = "";
        try {

			domresult = pmProjectLogDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("项目日志{}", e.getMessage());
			SystemException.handleMessageException("项目日志查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmProjectLogVO vo = new PmProjectLogVO();
        PmProjectLogStrucVO svo = new PmProjectLogStrucVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            String targetUid = obj.getString("target_uid");
            // 插入
			//pmProjectLogDao.save(vo);
			Long projectLog_uid = BaseDAO.insert(conn, vo);
			svo.setProject_log_uid(String.valueOf(projectLog_uid));
			//svo.setPrj_struc_uid(obj.getString("PRJ_STRUC_UID"));
			//BaseDAO.insert(conn, svo);
            resultVO = vo.getRowJson();
            
            //更新附件信息
            if(null!=projectLog_uid&&!"".equals(projectLog_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(projectLog_uid));                 
            }

            
        } catch (DaoException e) {
            logger.error("项目日志{}", e.getMessage());
            SystemException.handleMessageException("项目日志新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        
        String resultVO = null;
        PmProjectLogVO vo = new PmProjectLogVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String targetUid = obj.getString("target_uid");
            // 修改
            pmProjectLogDao.update(vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=vo.getProject_log_uid()&&!"".equals(vo.getProject_log_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(vo.getProject_log_uid()));                 
            }
        } catch (DaoException e) {
            logger.error("项目日志{}", e.getMessage());
            SystemException.handleMessageException("项目日志修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {


		String resultVo = null;
		PmProjectLogVO vo = new PmProjectLogVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			pmProjectLogDao.delete(PmProjectLogVO.class, vo.getProject_log_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目日志{}", e.getMessage());
            SystemException.handleMessageException("项目日志删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmProjectLogDaoImpl")
	public void setPmProjectLogDao(PmProjectLogDao pmProjectLogDao) {
		this.pmProjectLogDao = pmProjectLogDao;
	}

	public boolean deleteById(String logUid) {
		// TODO Auto-generated method stub
		return this.pmProjectLogDao.deleteById(logUid);
	}
    
}
