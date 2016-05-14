/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianService.java
 * 创建日期： 2016-03-02 下午 02:20:13
 * 功能：    接口：巡检
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:20:13  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


import java.sql.Connection;

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
import com.ccthanking.business.pmjiancha.vo.PmXunjianContentVO;
import com.ccthanking.business.pmjiancha.vo.PmXunjianVO;
import com.ccthanking.business.pmjiancha.dao.PmXunjianDao;
import com.ccthanking.business.pmjiancha.service.PmXunjianService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmXunjianService.java </p>
 * <p> 功能：巡检 </p>
 *
 * <p><a href="PmXunjianService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Service
public class PmXunjianServiceImpl extends Base1ServiceImpl<PmXunjianVO, String> implements PmXunjianService {

	private static Logger logger = LoggerFactory.getLogger(PmXunjianServiceImpl.class);
	
    
    private PmXunjianDao pmXunjianDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmXunjianDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("巡检{}", e.getMessage());
			SystemException.handleMessageException("巡检查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        PmXunjianVO vo = new PmXunjianVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setStatus("0");
            String targetUid = obj.getString("target_uid");
            //pmXunjianDao.save(vo);
            // 插入
			String xunjin_uid = String.valueOf(BaseDAO.insert(conn, vo));
			
            String guifan_uid = obj.getString("BZGF_UID");
            if(guifan_uid.indexOf("[")!=-1){
            	JSONArray guifan_uids = obj.getJSONArray("BZGF_UID");
            	for (int i = 0; i < guifan_uids.size(); i++) {
					PmXunjianContentVO cvo = new PmXunjianContentVO();
					
					cvo.setXunjian_uid(xunjin_uid);
					cvo.setXuhao(this.pmXunjianDao.getMaxCode(xunjin_uid));
					cvo.setBzgf_uid(guifan_uids.getString(i));
					cvo.setContent(obj.getJSONArray("NODE_CONTENT").getString(i));
					cvo.setWeigui_level(obj.getJSONArray("WEIGUI_LEVEL").getString(i));
					cvo.setDescription(obj.getJSONArray("DESCRIPTION").getString(i));
					BaseDAO.insert(conn, cvo);
				}
            }else{
            	PmXunjianContentVO cvo = new PmXunjianContentVO();
				cvo.setXunjian_uid(xunjin_uid);
				cvo.setXuhao(this.pmXunjianDao.getMaxCode(xunjin_uid));
				cvo.setBzgf_uid(guifan_uid);
				cvo.setContent(obj.getString("NODE_CONTENT"));
				cvo.setWeigui_level(obj.getString("WEIGUI_LEVEL"));
				cvo.setDescription(obj.getString("DESCRIPTION"));
				BaseDAO.insert(conn, cvo);
            }
            
            //更新附件信息
            if(null!=xunjin_uid&&!"".equals(xunjin_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(xunjin_uid));                 
            }
			
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("巡检{}", e.getMessage());
            SystemException.handleMessageException("巡检新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        PmXunjianVO vo = new PmXunjianVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String targetUid = obj.getString("target_uid");
            // 修改
            pmXunjianDao.update(vo);
            DBUtil.exec(conn, "DELETE FROM pm_xunjian_content WHERE XUNJIAN_UID = '"+vo.getXunjian_uid()+"'");
            String guifan_uid = obj.getString("BZGF_UID");
            if(guifan_uid.indexOf("[")!=-1){
            	JSONArray guifan_uids = obj.getJSONArray("BZGF_UID");
            	for (int i = 0; i < guifan_uids.size(); i++) {
					PmXunjianContentVO cvo = new PmXunjianContentVO();
					
					cvo.setXunjian_uid(vo.getXunjian_uid());
					cvo.setXuhao(this.pmXunjianDao.getMaxCode(vo.getXunjian_uid()));
					cvo.setBzgf_uid(guifan_uids.getString(i));
					cvo.setContent(obj.getJSONArray("CONTENT").getString(i));
					cvo.setWeigui_level(obj.getJSONArray("WEIGUI_LEVEL").getString(i));
					cvo.setDescription(obj.getJSONArray("DESCRIPTION").getString(i));
					BaseDAO.insert(conn, cvo);
				}
            }else{
            	PmXunjianContentVO cvo = new PmXunjianContentVO();
				cvo.setXunjian_uid(vo.getXunjian_uid());
				cvo.setXuhao(this.pmXunjianDao.getMaxCode(vo.getXunjian_uid()));
				cvo.setBzgf_uid(guifan_uid);
				cvo.setContent(obj.getString("CONTENT"));
				cvo.setWeigui_level(obj.getString("WEIGUI_LEVEL"));
				cvo.setDescription(obj.getString("DESCRIPTION"));
				BaseDAO.insert(conn, cvo);
            }
            
            //更新附件信息
            if(null!=vo.getXunjian_uid()&&!"".equals(vo.getXunjian_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(vo.getXunjian_uid()));                 
            }
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("巡检{}", e.getMessage());
            SystemException.handleMessageException("巡检修改失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmXunjianVO vo = new PmXunjianVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			pmXunjianDao.delete(PmXunjianVO.class, vo.getXunjian_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("巡检{}", e.getMessage());
            SystemException.handleMessageException("巡检删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmXunjianDaoImpl")
	public void setPmXunjianDao(PmXunjianDao pmXunjianDao) {
		this.pmXunjianDao = pmXunjianDao;
	}

	public boolean deleteById(String uid) {
		boolean flag = false;
		try {
			pmXunjianDao.delete(PmXunjianVO.class,uid);
			pmXunjianDao.delete(PmXunjianContentVO.class, uid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}
    
}
