/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmXianchangDafuService.java
 * 创建日期： 2016-01-27 下午 06:45:28
 * 功能：    接口：现场状况答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-27 下午 06:45:28  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang.service.impl;


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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.pmxianchang.vo.PmXianchangDafuVO;
import com.ccthanking.business.pmxianchang.dao.PmXianchangDafuDao;
import com.ccthanking.business.pmxianchang.service.PmXianchangDafuService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmXianchangDafuService.java </p>
 * <p> 功能：现场状况答复 </p>
 *
 * <p><a href="PmXianchangDafuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-27
 * 
 */

@Service
public class PmXianchangDafuServiceImpl extends Base1ServiceImpl<PmXianchangDafuVO, String> implements PmXianchangDafuService {
	private static Logger logger = LoggerFactory.getLogger(PmXianchangDafuServiceImpl.class);
    private PmXianchangDafuDao pmXianchangDafuDao;
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmXianchangDafuDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("现场状况答复查询失败,请联系相关人员处理");
        } finally {
        	
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        PmXianchangDafuVO vo = new PmXianchangDafuVO();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String targetUid= obj.getString("target_uid");
            String xcUid = obj.getString("XIANCHANG_UID");
            String xuhao = queryMaxSort(xcUid);
            vo.setXuhao(xuhao);
            vo.setCreate_date(new Date());
            vo.setUpdate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            
        	Long xcdf_uid = BaseDAO.insert(conn,vo);
        	 resultVO = vo.getRowJson();
            //更新现场状况答复的附件信息
            if(xcdf_uid != null &&!"".equals(xcdf_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(xcdf_uid));                 
            }
            
		
           

        } catch (DaoException e) {
            SystemException.handleMessageException("现场状况答复新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    private String queryMaxSort(String xcUid) {
		String xuhao = "";
		int sort = 1;
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select MAX(XUHAO) as xuhao from pm_xianchang_dafu where XIANCHANG_UID="+xcUid;
			String[][] res = DBUtil.query(conn,sql);
			if(res[0][0] !="" && res.length != 0){
				int s=  Integer.parseInt(res[0][0])+1;
				xuhao = String.valueOf(s);
			}else{
				xuhao = String.valueOf(sort);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeConnetion(conn);
		}
		return xuhao;
	}

	public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmXianchangDafuVO vo = new PmXianchangDafuVO();
        try {
        	 JSONArray list = vo.doInitJson(json);
             JSONObject obj = (JSONObject) list.get(0);
             vo.setValueFromJson(obj);
             vo.setUpdate_date(new Date());
           
             String targetUid= obj.getString("target_uid");
             String xcUid = obj.getString("XIANCHANG_UID");
             String xcdf_uid = obj.getString("XIANCHANG_DAFU_UID");
             
             //更新现场状况答复 的附件信息
             if(xcdf_uid != null &&!"".equals(xcdf_uid)){
             	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(xcdf_uid));                 
             }
             
            // 修改
            pmXianchangDafuDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            SystemException.handleMessageException("现场状况答复修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String xcdfId) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		PmXianchangDafuVO vo = new PmXianchangDafuVO();
		try {
			
			pmXianchangDafuDao.delete(PmXianchangDafuVO.class, xcdfId);

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            SystemException.handleMessageException("现场状况答复删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmXianchangDafuDaoImpl")
	public void setPmXianchangDafuDao(PmXianchangDafuDao pmXianchangDafuDao) {
		this.pmXianchangDafuDao = pmXianchangDafuDao;
		
	}

	//
	public Map<String, List<?>> queryDetails(String dfUid,String fileType)
			throws Exception {
		
		return this.pmXianchangDafuDao.queryDetails(dfUid,fileType);
	}

	public Map<String, List<?>> queryNewAnswer(String xcUid,String fileType)
			throws Exception {
		return this.pmXianchangDafuDao.queryNewAnswer(xcUid,fileType);
	}
    
}
