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

import com.ccthanking.business.project.dao.ProjectBiangengDao;
import com.ccthanking.business.project.service.ProjectBiangengService;
import com.ccthanking.business.project.vo.PmBiangengVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
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
public class ProjectBiangengServiceImpl extends Base1ServiceImpl<PmBiangengVO, String> implements ProjectBiangengService {

	private static Logger logger = LoggerFactory.getLogger(ProjectBiangengServiceImpl.class);
	
    
    private ProjectBiangengDao projectBiangengDao;

    // @Override
    public String queryCondition(String json) throws Exception {        	
    
        String domresult = "";
        try {

			domresult = projectBiangengDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("项目用户{}", e.getMessage());
			SystemException.handleMessageException("项目用户查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmBiangengVO vo = new PmBiangengVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);          
            JSONObject obj = (JSONObject) list.get(0);
            String targetUid = obj.getString("target_uid");
            vo.setValueFromJson(obj);
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            vo.setUpdate_user(user.getIdCard());
            vo.setUpdate_date(new Date());
            // 插入
            Long bid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            
            //更新附件信息
            if(null!=bid){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(bid));                 
            }
            
        } catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmBiangengVO vo = new PmBiangengVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String targetUid = obj.getString("target_uid");
            vo.setValueFromJson(obj);
            
            vo.setUpdate_user(user.getIdCard());
            vo.setUpdate_date(new Date());
           
            String AMOUNT = obj.getString("AMOUNT");
            if(AMOUNT.equals("")){
            	vo.setAmount(null);
            }
            // 修改
            projectBiangengDao.update(vo);
            resultVO = vo.getRowJson();

            //更新附件信息
            if(null!=vo.getBiangeng_uid()&&!"".equals(vo.getBiangeng_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(vo.getBiangeng_uid()));                 
            }
        } catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String bid) throws Exception {

		String resultVo = null;
		PmBiangengVO vo = new PmBiangengVO();
		try {

			//删除   根据据主键
			projectBiangengDao.delete(PmBiangengVO.class, bid);

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目用户{}", e.getMessage());
            SystemException.handleMessageException("项目用户删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("projectBiangengDaoImpl")
	public void setProjectBiangengDao(ProjectBiangengDao projectBiangengDao) {
		this.projectBiangengDao = projectBiangengDao;
	}
  
}
