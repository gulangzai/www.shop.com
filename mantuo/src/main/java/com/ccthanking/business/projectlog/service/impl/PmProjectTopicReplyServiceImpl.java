/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectTopicReplyService.java
 * 创建日期： 2016-01-19 下午 04:29:43
 * 功能：    接口：问题回复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:29:43  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.projectlog.vo.PmProjectTopicReplyVO;
import com.ccthanking.business.projectlog.dao.PmProjectTopicReplyDao;
import com.ccthanking.business.projectlog.service.PmProjectTopicReplyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmProjectTopicReplyService.java </p>
 * <p> 功能：问题回复 </p>
 *
 * <p><a href="PmProjectTopicReplyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Service
public class PmProjectTopicReplyServiceImpl extends Base1ServiceImpl<PmProjectTopicReplyVO, String> implements PmProjectTopicReplyService {

	private static Logger logger = LoggerFactory.getLogger(PmProjectTopicReplyServiceImpl.class);
	
    
    private PmProjectTopicReplyDao pmProjectTopicReplyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmProjectTopicReplyDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("问题回复{}", e.getMessage());
			SystemException.handleMessageException("问题回复查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmProjectTopicReplyVO vo = new PmProjectTopicReplyVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String targetUid = obj.getString("target_uid");
            vo.setValueFromJson(obj);
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            vo.setXuhao(getMaxCode(vo.getProject_topic_uid()));
            // 插入
			//pmProjectTopicReplyDao.save(vo);
            Long ProjectTopicReplyUid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=ProjectTopicReplyUid&&!"".equals(ProjectTopicReplyUid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(ProjectTopicReplyUid));                 
            }
            
        } catch (DaoException e) {
            logger.error("问题回复{}", e.getMessage());
            SystemException.handleMessageException("问题回复新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmProjectTopicReplyVO vo = new PmProjectTopicReplyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setUpdate_date(new Date());
            String targetUid = obj.getString("target_uid");
            // 修改
            pmProjectTopicReplyDao.update(vo);
            resultVO = vo.getRowJson();

            //更新附件信息
            if(null!=vo.getProject_topic_reply_uid()&&!"".equals(vo.getProject_topic_reply_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(vo.getProject_topic_reply_uid()));                 
            }
        } catch (DaoException e) {
            logger.error("问题回复{}", e.getMessage());
            SystemException.handleMessageException("问题回复修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmProjectTopicReplyVO vo = new PmProjectTopicReplyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			pmProjectTopicReplyDao.delete(PmProjectTopicReplyVO.class, vo.getProject_topic_reply_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("问题回复{}", e.getMessage());
            SystemException.handleMessageException("问题回复删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmProjectTopicReplyDaoImpl")
	public void setPmProjectTopicReplyDao(PmProjectTopicReplyDao pmProjectTopicReplyDao) {
		this.pmProjectTopicReplyDao = pmProjectTopicReplyDao;
	}
	
	public  String getMaxCode(String topicUid){
		return this.pmProjectTopicReplyDao.getMaxCode(topicUid);
	}

	public boolean deleteById(String replyUid) {
		// TODO Auto-generated method stub
		return this.pmProjectTopicReplyDao.deleteById(replyUid);
	}
    
}
