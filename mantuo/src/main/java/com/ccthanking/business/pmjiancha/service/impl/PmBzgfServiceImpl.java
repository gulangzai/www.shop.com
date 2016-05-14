/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmBzgfService.java
 * 创建日期： 2016-02-23 上午 10:00:46
 * 功能：    接口：项目标准规范
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 10:00:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.pmjiancha.vo.PmBzgfVO;
import com.ccthanking.business.pmjiancha.dao.PmBzgfDao;
import com.ccthanking.business.pmjiancha.service.PmBzgfService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmBzgfService.java </p>
 * <p> 功能：项目标准规范 </p>
 *
 * <p><a href="PmBzgfService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

@Service
public class PmBzgfServiceImpl extends Base1ServiceImpl<PmBzgfVO, String> implements PmBzgfService {

	private static Logger logger = LoggerFactory.getLogger(PmBzgfServiceImpl.class);
    
    private PmBzgfDao pmBzgfDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmBzgfDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        	logger.error("项目标准规范{}", e.getMessage());
			SystemException.handleMessageException("项目标准规范查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmBzgfVO vo = new PmBzgfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            vo.setUpdate_date(new Date());
            vo.setUpdate_user(user.getIdCard());
            // 插入
			pmBzgfDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("项目标准规范{}", e.getMessage());
            SystemException.handleMessageException("项目标准规范新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmBzgfVO vo = new PmBzgfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setUpdate_date(new Date());
            vo.setUpdate_user(user.getIdCard());
            // 修改
            pmBzgfDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("项目标准规范{}", e.getMessage());
            SystemException.handleMessageException("项目标准规范修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmBzgfVO vo = new PmBzgfVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			pmBzgfDao.delete(PmBzgfVO.class, vo.getBzgf_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目标准规范{}", e.getMessage());
            SystemException.handleMessageException("项目标准规范删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmBzgfDaoImpl")
	public void setPmBzgfDao(PmBzgfDao pmBzgfDao) {
		this.pmBzgfDao = pmBzgfDao;
	}

	public String creatTree(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmBzgfDao.creatTree(map);
	}

	public boolean importGF(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmBzgfDao.importGF(map);
	}

	public String creatTree2(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmBzgfDao.creatTree2(map);
	}

	public boolean deleteById(String uid) {
		boolean flag = false;
		try {
			pmBzgfDao.delete(PmBzgfVO.class,uid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}
    
}
