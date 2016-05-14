/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmJianchaBzService.java
 * 创建日期： 2016-02-23 上午 09:54:56
 * 功能：    接口：项目检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 09:54:56  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.pmjiancha.vo.PmJianchaBzVO;
import com.ccthanking.business.pmjiancha.dao.PmJianchaBzDao;
import com.ccthanking.business.pmjiancha.service.PmJianchaBzService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmJianchaBzService.java </p>
 * <p> 功能：项目检查内容 </p>
 *
 * <p><a href="PmJianchaBzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

@Service
public class PmJianchaBzServiceImpl extends Base1ServiceImpl<PmJianchaBzVO, String> implements PmJianchaBzService {

	private static Logger logger = LoggerFactory.getLogger(PmJianchaBzServiceImpl.class);
	
    
    private PmJianchaBzDao pmJianchaBzDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmJianchaBzDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("项目检查内容{}", e.getMessage());
			SystemException.handleMessageException("项目检查内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmJianchaBzVO vo = new PmJianchaBzVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);

     
            vo.setValueFromJson(obj);
            vo.setXuhao(this.pmJianchaBzDao.getMaxCode());

            // 插入
			pmJianchaBzDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("项目检查内容{}", e.getMessage());
            SystemException.handleMessageException("项目检查内容新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmJianchaBzVO vo = new PmJianchaBzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            pmJianchaBzDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("项目检查内容{}", e.getMessage());
            SystemException.handleMessageException("项目检查内容修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmJianchaBzVO vo = new PmJianchaBzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			pmJianchaBzDao.delete(PmJianchaBzVO.class, vo.getJiancha_bz_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目检查内容{}", e.getMessage());
            SystemException.handleMessageException("项目检查内容删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmJianchaBzDaoImpl")
	public void setPmJianchaBzDao(PmJianchaBzDao pmJianchaBzDao) {
		this.pmJianchaBzDao = pmJianchaBzDao;
	}

	public List<Map<String, String>> queryJcjb() {
		// TODO Auto-generated method stub
		return this.pmJianchaBzDao.queryJcjb();
	}

	public String queryBz(String msg) {
		// TODO Auto-generated method stub
		return this.pmJianchaBzDao.queryBz(msg);
	}

	public boolean deleteById(String uid) {
		boolean flag = false;
		try {
			this.pmJianchaBzDao.delete(PmJianchaBzVO.class,uid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}

	public List<Map<String, String>> queryWgnr(String guifan_uid) {
		// TODO Auto-generated method stub
		return this.pmJianchaBzDao.queryWgnr(guifan_uid);
	}
    
}
