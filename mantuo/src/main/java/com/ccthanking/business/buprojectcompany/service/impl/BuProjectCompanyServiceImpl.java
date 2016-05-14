/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    buprojectcompany.service.BuProjectCompanyService.java
 * 创建日期： 2016-04-29 下午 01:42:29
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-29 下午 01:42:29  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.buprojectcompany.service.impl;


import java.sql.Connection;
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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.buprojectcompany.vo.BuProjectCompanyVO;
import com.ccthanking.business.buprojectcompany.dao.BuProjectCompanyDao;
import com.ccthanking.business.buprojectcompany.service.BuProjectCompanyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuProjectCompanyService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="BuProjectCompanyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-29
 * 
 */

@Service
public class BuProjectCompanyServiceImpl extends Base1ServiceImpl<BuProjectCompanyVO, String> implements BuProjectCompanyService {

	private static Logger logger = LoggerFactory.getLogger(BuProjectCompanyServiceImpl.class);
	

    private BuProjectCompanyDao buProjectCompanyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = buProjectCompanyDao.queryCondition(json, null, null);

      
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
        BuProjectCompanyVO vo = new BuProjectCompanyVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
        	JSONArray list = vo.doInitJson(json);
            JSONObject obj  = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
         
         
    
            // 插入
			buProjectCompanyDao.save(vo);
            resultVO = vo.getRowJson();

        
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
      
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuProjectCompanyVO vo = new BuProjectCompanyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	//BusinessUtil.setUpdateCommonFields(vo, user);
       
            // 修改
            buProjectCompanyDao.update(vo);
            resultVO = vo.getRowJson();

         } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
         
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuProjectCompanyVO vo = new BuProjectCompanyVO();
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
	@Qualifier("buProjectCompanyDaoImpl")
	public void setBuProjectCompanyDao(BuProjectCompanyDao buProjectCompanyDao) {
		this.buProjectCompanyDao = buProjectCompanyDao;
	}

	public boolean deleteid(String projectcompanyid) throws Exception {
		// TODO Auto-generated method stub
		return this.buProjectCompanyDao.deleteid(projectcompanyid);
	}

	public String queryid(String projectcompanyid) {
		// TODO Auto-generated method stub
		return this.buProjectCompanyDao.queryid(projectcompanyid);
	}
    
}
