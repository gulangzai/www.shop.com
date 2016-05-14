/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmcailiao.service.PmCailiaoService.java
 * 创建日期： 2016-03-24 上午 11:19:37
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-24 上午 11:19:37     创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmcailiao.service.impl;


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

import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;
import com.ccthanking.business.pmcailiao.dao.PmCailiaoDao;
import com.ccthanking.business.pmcailiao.service.PmCailiaoService;
import com.ccthanking.business.xmgk.vo.PmGongkuangVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmCailiaoService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmCailiaoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com"></a>
 * @version 0.1
 * @since 2016-03-24
 * 
 */

@Service
public class PmCailiaoServiceImpl extends Base1ServiceImpl<PmCailiaoVO, String> implements PmCailiaoService {

	private static Logger logger = LoggerFactory.getLogger(PmCailiaoServiceImpl.class);
	

    
    private PmCailiaoDao pmCailiaoDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmCailiaoDao.queryCondition(json, null, null);

         
            
        }catch (DaoException e) {
        	logger.error("监测项目{}", e.getMessage());
			
			SystemException.handleMessageException("监测项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();	
        String resultVO = null;
        PmCailiaoVO vo = new PmCailiaoVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());  
            
            String targetUid = obj.getString("target_uid");
            // 插入
			Long project_uid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            //更新附件信息
            if(null!=project_uid&&!"".equals(project_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(project_uid));                 
            }
			
			 
        } catch (DaoException e) {
            SystemException.handleMessageException("监测项目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmCailiaoVO vo = new PmCailiaoVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
           
            vo.setValueFromJson(obj);
            //设置主键

            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            String CAILIAO_PRICE = (String) obj.get("CAILIAO_PRICE");
            if(CAILIAO_PRICE.equals("")){
            vo.setCailiao_price(null);
            }
            System.out.println(vo);
            // 修改
            pmCailiaoDao.update(vo);
            String targetUid = obj.getString("target_uid");
            
            
            //更新附件
            if(vo!=null&&vo.getCailiao_uid()!=null&&!"".equals(vo.getCailiao_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getCailiao_uid());                 
            }
            resultVO = vo.getRowJson();
 
       
          
        } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
          
            SystemException.handleMessageException("监测项目修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmCailiaoVO vo = new PmCailiaoVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
           
            SystemException.handleMessageException("监测项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmCailiaoDaoImpl")
	public void setPmCailiaoDao(PmCailiaoDao pmCailiaoDao) {
		this.pmCailiaoDao = pmCailiaoDao;
	}

	public boolean deleteId(String cailiaoUid) {
		// TODO Auto-generated method stub
		return this.pmCailiaoDao.deleteId(cailiaoUid);
	}

	public List<Map<String, String>> queryFileList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmCailiaoDao.queryFileList(map);
	}
    
}
