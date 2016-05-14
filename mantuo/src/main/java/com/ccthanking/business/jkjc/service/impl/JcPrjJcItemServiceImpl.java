/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPrjJcItemService.java
 * 创建日期： 2015-12-08 下午 07:52:25
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-08 下午 07:52:25  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service.impl;


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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.jkjc.dao.JcPrjJcItemDao;
import com.ccthanking.business.jkjc.service.JcPrjJcItemService;
import com.ccthanking.business.jkjc.vo.JcPrjJcItemVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcPrjJcItemService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="JcPrjJcItemService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-08
 * 
 */

@Service
public class JcPrjJcItemServiceImpl extends Base1ServiceImpl<JcPrjJcItemVO, String> implements JcPrjJcItemService {

	private static Logger logger = LoggerFactory.getLogger(JcPrjJcItemServiceImpl.class);
	
    
    private JcPrjJcItemDao jcPrjJcItemDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcPrjJcItemDao.queryCondition(json, null, null);
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
        JcPrjJcItemVO vo = new JcPrjJcItemVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			jcPrjJcItemDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
            SystemException.handleMessageException("监测项目新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JcPrjJcItemVO vo = new JcPrjJcItemVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            jcPrjJcItemDao.update(vo);
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
		JcPrjJcItemVO vo = new JcPrjJcItemVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			jcPrjJcItemDao.delete(JcPrjJcItemVO.class, vo.getJc_prj_item_uid());


		} catch (DaoException e) {
            logger.error("监测项目{}", e.getMessage());
            SystemException.handleMessageException("监测项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcPrjJcItemDaoImpl")
	public void setJcPrjJcItemDao(JcPrjJcItemDao jcPrjJcItemDao) {
		this.jcPrjJcItemDao = jcPrjJcItemDao;
	}

	public String queryById(String jCPRJITEMUID) {
		// TODO Auto-generated method stub
		return this.jcPrjJcItemDao.queryById(jCPRJITEMUID);
	}

	public List<Map<String, String>> queryJcObject() {
		// TODO Auto-generated method stub
		return this.jcPrjJcItemDao.queryJcObject();
	}

	public List<Map<String, String>> queryJcType() {
		// TODO Auto-generated method stub
		return this.jcPrjJcItemDao.queryJcType();
	}

	public Boolean removeData(String jCPRJITEMUID) {
		// TODO Auto-generated method stub
		return this.jcPrjJcItemDao.removeData(jCPRJITEMUID);
	}
    
}
