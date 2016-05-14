/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianStrucService.java
 * 创建日期： 2016-03-02 下午 02:24:31
 * 功能：    接口：巡检对应的项目结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:24:31  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service.impl;


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

import com.ccthanking.business.pmjiancha.vo.PmXunjianStrucVO;
import com.ccthanking.business.pmjiancha.dao.PmXunjianStrucDao;
import com.ccthanking.business.pmjiancha.service.PmXunjianStrucService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmXunjianStrucService.java </p>
 * <p> 功能：巡检对应的项目结构 </p>
 *
 * <p><a href="PmXunjianStrucService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

@Service
public class PmXunjianStrucServiceImpl extends Base1ServiceImpl<PmXunjianStrucVO, String> implements PmXunjianStrucService {

	private static Logger logger = LoggerFactory.getLogger(PmXunjianStrucServiceImpl.class);
    
    private PmXunjianStrucDao pmXunjianStrucDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmXunjianStrucDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("巡检对应的项目结构{}", e.getMessage());
			SystemException.handleMessageException("巡检对应的项目结构查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmXunjianStrucVO vo = new PmXunjianStrucVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			pmXunjianStrucDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("巡检对应的项目结构{}", e.getMessage());
            SystemException.handleMessageException("巡检对应的项目结构新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmXunjianStrucVO vo = new PmXunjianStrucVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            pmXunjianStrucDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("巡检对应的项目结构{}", e.getMessage());
            SystemException.handleMessageException("巡检对应的项目结构修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmXunjianStrucVO vo = new PmXunjianStrucVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			pmXunjianStrucDao.delete(PmXunjianStrucVO.class, vo.getXunjian_struc_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("巡检对应的项目结构{}", e.getMessage());
            SystemException.handleMessageException("巡检对应的项目结构删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmXunjianStrucDaoImpl")
	public void setPmXunjianStrucDao(PmXunjianStrucDao pmXunjianStrucDao) {
		this.pmXunjianStrucDao = pmXunjianStrucDao;
	}
    
}
