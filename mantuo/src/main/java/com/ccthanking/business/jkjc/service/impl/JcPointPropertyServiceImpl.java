/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPointPropertyService.java
 * 创建日期： 2015-10-30 上午 09:38:07
 * 功能：    接口：监测点属性值
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:38:07  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service.impl;


import java.util.HashMap;

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

import com.ccthanking.business.jkjc.vo.JcPointPropertyVO;
import com.ccthanking.business.jkjc.dao.JcPointPropertyDao;
import com.ccthanking.business.jkjc.service.JcPointPropertyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcPointPropertyService.java </p>
 * <p> 功能：监测点属性值 </p>
 *
 * <p><a href="JcPointPropertyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Service
public class JcPointPropertyServiceImpl extends Base1ServiceImpl<JcPointPropertyVO, String> implements JcPointPropertyService {

	private static Logger logger = LoggerFactory.getLogger(JcPointPropertyServiceImpl.class);
	

    private JcPointPropertyDao jcPointPropertyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcPointPropertyDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("监测点属性值{}", e.getMessage());
			SystemException.handleMessageException("监测点属性值查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JcPointPropertyVO vo = new JcPointPropertyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			jcPointPropertyDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("监测点属性值{}", e.getMessage());
            SystemException.handleMessageException("监测点属性值新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
     
    /**修改监测点的 属性值**/
    public String update(HashMap<String, String> map) throws Exception {
        String domresult = "";
        try {

			domresult = jcPointPropertyDao.update(map);

        }catch (DaoException e) {
        	logger.error("测点预警值{}", e.getMessage());
			SystemException.handleMessageException("测点预警值更新失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcPointPropertyVO vo = new JcPointPropertyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			jcPointPropertyDao.delete(JcPointPropertyVO.class, vo.getPrj_points_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("监测点属性值{}", e.getMessage());
            SystemException.handleMessageException("监测点属性值删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcPointPropertyDaoImpl")
	public void setJcPointPropertyDao(JcPointPropertyDao jcPointPropertyDao) {
		this.jcPointPropertyDao = jcPointPropertyDao;
	}

	public String querybyTypeId(String itemId,String typeId) throws Exception {
		
		return this.jcPointPropertyDao.querybyTypeId(itemId,typeId);
	}
	
   public String  querybyPointId(String itemId) throws Exception {
		
	   String  resultId = "";
	   try {
		String sql ="SELECT  POINT_PROPERTY_UID FROM JC_POINT_PROPERTY WHERE PRJ_POINT_ITEM_UID="+itemId;
		String [][] arr = DBUtil.query(sql);
		//list<Map<String,String>>
		if(arr !=null){
			resultId = arr[0][0];
			System.out.println(resultId+"===================================sssssssssssss");
			
		}
		//arr[0][0]
	} catch (Exception e) {
		logger.info("监测点的属性 的主键id查询 出错！");
	}
	
	return resultId;
		
	}

	public String querybyPoint_Item_Id(String pointitemId) throws Exception {
		
		return this.jcPointPropertyDao.querybyPoint_Item_Id(pointitemId);
	}
	
	public String queryTypenameByTypeid(String typeId) throws Exception {
		
		return this.jcPointPropertyDao.queryTypenameByTypeid(typeId);
	}
	

}
