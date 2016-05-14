/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectTopicService.java
 * 创建日期： 2016-01-19 下午 04:30:30
 * 功能：    接口：问题讨论
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:30:30  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectlog.service.impl;


import java.sql.Connection;
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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;
import com.ccthanking.business.projectlog.dao.PmProjectTopicDao;
import com.ccthanking.business.projectlog.service.PmProjectTopicService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmProjectTopicService.java </p>
 * <p> 功能：问题讨论 </p>
 *
 * <p><a href="PmProjectTopicService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Service
public class PmProjectTopicServiceImpl extends Base1ServiceImpl<PmProjectTopicVO, String> implements PmProjectTopicService {

	private static Logger logger = LoggerFactory.getLogger(PmProjectTopicServiceImpl.class);
	
    private PmProjectTopicDao pmProjectTopicDao;

    // @Override
    public String queryCondition(String json,String project_uid) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("project_uid", project_uid);
			domresult = pmProjectTopicDao.queryCondition(json, null, map);

        }catch (DaoException e) {
        	logger.error("问题讨论{}", e.getMessage());
			SystemException.handleMessageException("问题讨论查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmProjectTopicVO vo = new PmProjectTopicVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            vo.setStatus("0");//话题状态，-1－草稿；0－已发布；1-关闭
            String targetUid = obj.getString("target_uid");
            // 插入
			//pmProjectLogDao.save(vo);
			Long projectTop_uid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=projectTop_uid&&!"".equals(projectTop_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(projectTop_uid));                 
            }

            
        } catch (DaoException e) {
            logger.error("问题讨论{}", e.getMessage());
            SystemException.handleMessageException("问题讨论新增失败,请联系相关人员处理");
        } finally {
        	 DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmProjectTopicVO vo = new PmProjectTopicVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setUpdate_date(new Date());
            String targetUid = obj.getString("target_uid");
            // 修改
            pmProjectTopicDao.update(vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=vo.getProject_topic_uid()&&!"".equals(vo.getProject_topic_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(vo.getProject_topic_uid()));                 
            }
        } catch (DaoException e) {
            logger.error("问题讨论{}", e.getMessage());
            SystemException.handleMessageException("问题讨论修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmProjectTopicVO vo = new PmProjectTopicVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			pmProjectTopicDao.delete(PmProjectTopicVO.class, vo.getProject_topic_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("问题讨论{}", e.getMessage());
            SystemException.handleMessageException("问题讨论删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmProjectTopicDaoImpl")
	public void setPmProjectTopicDao(PmProjectTopicDao pmProjectTopicDao) {
		this.pmProjectTopicDao = pmProjectTopicDao;
	}

	public List<Map<String, String>> queryList() {
		// TODO Auto-generated method stub
		return this.pmProjectTopicDao.queryList();
	}

	public Map<String, List<?>> queryDetails(String topicUid) {
		// TODO Auto-generated method stub
		return this.pmProjectTopicDao.queryDetails(topicUid);
	}

	public boolean deleteById(String topicUid) {
		// TODO Auto-generated method stub
		return this.pmProjectTopicDao.deleteById(topicUid);
	}

	public boolean shutDownById(String topic_uid) {
		// TODO Auto-generated method stub
		return this.pmProjectTopicDao.shutDownById(topic_uid);
	}
    
}
