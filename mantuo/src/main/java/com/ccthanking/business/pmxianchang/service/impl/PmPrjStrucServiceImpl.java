/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmPrjStrucService.java
 * 创建日期： 2016-01-25 下午 04:12:17
 * 功能：    接口：项目结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-25 下午 04:12:17  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang.service.impl;


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

import com.ccthanking.business.pmxianchang.vo.PmPrjStrucVO;
import com.ccthanking.business.pmxianchang.dao.PmPrjStrucDao;
import com.ccthanking.business.pmxianchang.service.PmPrjStrucService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmPrjStrucService.java </p>
 * <p> 功能：项目结构 </p>
 *
 * <p><a href="PmPrjStrucService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-25
 * 
 */

@Service
public class PmPrjStrucServiceImpl extends Base1ServiceImpl<PmPrjStrucVO, String> implements PmPrjStrucService {

	private static Logger logger = LoggerFactory.getLogger(PmPrjStrucServiceImpl.class);
    
    private PmPrjStrucDao pmPrjStrucDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmPrjStrucDao.queryCondition(json, null, null);
        }catch (DaoException e) {
        
			SystemException.handleMessageException("项目结构查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmPrjStrucVO vo = new PmPrjStrucVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
           
			pmPrjStrucDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
          
            SystemException.handleMessageException("项目结构新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmPrjStrucVO vo = new PmPrjStrucVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
          
        } catch (DaoException e) {
         
            SystemException.handleMessageException("项目结构修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmPrjStrucVO vo = new PmPrjStrucVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			

			//删除   根据据主键
			//pmPrjStrucDao.delete(PmPrjStrucVO.class, vo.getId());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            SystemException.handleMessageException("项目结构删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmPrjStrucDaoImpl")
	public void setPmPrjStrucDao(PmPrjStrucDao pmPrjStrucDao) {
		this.pmPrjStrucDao = pmPrjStrucDao;
	}
    
}
