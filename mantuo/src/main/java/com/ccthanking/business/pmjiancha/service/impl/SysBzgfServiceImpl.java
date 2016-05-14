/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.SysBzgfService.java
 * 创建日期： 2016-02-25 下午 02:46:15
 * 功能：    接口：标准和规范
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-25 下午 02:46:15  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


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

import com.ccthanking.business.pmjiancha.vo.SysBzgfVO;
import com.ccthanking.business.pmjiancha.dao.SysBzgfDao;
import com.ccthanking.business.pmjiancha.service.SysBzgfService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SysBzgfService.java </p>
 * <p> 功能：标准和规范 </p>
 *
 * <p><a href="SysBzgfService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-25
 * 
 */

@Service
public class SysBzgfServiceImpl extends Base1ServiceImpl<SysBzgfVO, String> implements SysBzgfService {

	private static Logger logger = LoggerFactory.getLogger(SysBzgfServiceImpl.class);
	
    private SysBzgfDao sysBzgfDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sysBzgfDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("标准和规范{}", e.getMessage());
			SystemException.handleMessageException("标准和规范查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SysBzgfVO vo = new SysBzgfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			sysBzgfDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("标准和规范{}", e.getMessage());
            SystemException.handleMessageException("标准和规范新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SysBzgfVO vo = new SysBzgfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            sysBzgfDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("标准和规范{}", e.getMessage());
            SystemException.handleMessageException("标准和规范修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SysBzgfVO vo = new SysBzgfVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			sysBzgfDao.delete(SysBzgfVO.class, vo.getBzgf_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("标准和规范{}", e.getMessage());
            SystemException.handleMessageException("标准和规范删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sysBzgfDaoImpl")
	public void setSysBzgfDao(SysBzgfDao sysBzgfDao) {
		this.sysBzgfDao = sysBzgfDao;
	}

	public String creatTree(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.sysBzgfDao.creatTree(map);
	}
    
}
