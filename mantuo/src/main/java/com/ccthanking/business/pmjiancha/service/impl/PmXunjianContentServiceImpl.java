/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianContentService.java
 * 创建日期： 2016-03-02 下午 02:21:23
 * 功能：    接口：巡检检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:21:23  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


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

import com.ccthanking.business.pmjiancha.vo.PmXunjianContentVO;
import com.ccthanking.business.pmjiancha.dao.PmXunjianContentDao;
import com.ccthanking.business.pmjiancha.service.PmXunjianContentService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmXunjianContentService.java </p>
 * <p> 功能：巡检检查内容 </p>
 *
 * <p><a href="PmXunjianContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Service
public class PmXunjianContentServiceImpl extends Base1ServiceImpl<PmXunjianContentVO, String> implements PmXunjianContentService {

	private static Logger logger = LoggerFactory.getLogger(PmXunjianContentServiceImpl.class);
	
    
    private PmXunjianContentDao pmXunjianContentDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmXunjianContentDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("巡检检查内容{}", e.getMessage());
			SystemException.handleMessageException("巡检检查内容查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmXunjianContentVO vo = new PmXunjianContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			pmXunjianContentDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("巡检检查内容{}", e.getMessage());
            SystemException.handleMessageException("巡检检查内容新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmXunjianContentVO vo = new PmXunjianContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);

            // 修改
            pmXunjianContentDao.update(vo);
            
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("巡检检查内容{}", e.getMessage());
            SystemException.handleMessageException("巡检检查内容修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmXunjianContentVO vo = new PmXunjianContentVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			pmXunjianContentDao.delete(PmXunjianContentVO.class, vo.getXunjian_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("巡检检查内容{}", e.getMessage());
            SystemException.handleMessageException("巡检检查内容删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmXunjianContentDaoImpl")
	public void setPmXunjianContentDao(PmXunjianContentDao pmXunjianContentDao) {
		this.pmXunjianContentDao = pmXunjianContentDao;
	}

	public List<Map<String, String>> queryByXunjianUid(String xunjianUid) {
		// TODO Auto-generated method stub
		return this.pmXunjianContentDao.queryByXunjianUid(xunjianUid);
	}
    
}
