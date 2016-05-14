/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcCxDataService.java
 * 创建日期： 2015-12-01 上午 10:44:12
 * 功能：    接口：测斜数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-01 上午 10:44:12  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service.impl;


import java.util.HashMap;
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

import com.ccthanking.business.jkjc.vo.JcCxDataVO;
import com.ccthanking.business.jkjc.dao.JcCxDataDao;
import com.ccthanking.business.jkjc.service.JcCxDataService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.MapSortUtil;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.github.abel533.echarts.Option;



/**
 * <p> JcCxDataService.java </p>
 * <p> 功能：测斜数据 </p>
 *
 * <p><a href="JcCxDataService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-01
 * 
 */

@Service
public class JcCxDataServiceImpl extends Base1ServiceImpl<JcCxDataVO, String> implements JcCxDataService {

	private static Logger logger = LoggerFactory.getLogger(JcCxDataServiceImpl.class);
	
    
    private JcCxDataDao jcCxDataDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcCxDataDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("测斜数据{}", e.getMessage());
			SystemException.handleMessageException("测斜数据查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JcCxDataVO vo = new JcCxDataVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			jcCxDataDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("测斜数据{}", e.getMessage());
            SystemException.handleMessageException("测斜数据新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JcCxDataVO vo = new JcCxDataVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            jcCxDataDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("测斜数据{}", e.getMessage());
            SystemException.handleMessageException("测斜数据修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcCxDataVO vo = new JcCxDataVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			jcCxDataDao.delete(JcCxDataVO.class, vo.getCx_data_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("测斜数据{}", e.getMessage());
            SystemException.handleMessageException("测斜数据删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcCxDataDaoImpl")
	public void setJcCxDataDao(JcCxDataDao jcCxDataDao) {
		this.jcCxDataDao = jcCxDataDao;
	}

	public Option queryBarChart(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcCxDataDao.queryBarChart(map);
	}

	public Option queryLineChart(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcCxDataDao.queryLineChart(map);
	}

	public Map<String,Object>  queryTab(HashMap<String, String> map) {
		Map<String,Object> map0 = this.jcCxDataDao.queryTab(map);
		List<Map<String ,String>> list = (List<Map<String, String>>) map0.get("data");
		Map<String,String> thmap = new HashMap<String, String>();
		if(null!=list&&list.size()>0){
			Map<String,String> m = list.get(0);
			m = MapSortUtil.sortMapByKey(m);
			int i = 0;
			 for(String key : m.keySet()) {
				if(key.indexOf("REPORT_DATE")!=-1){
					thmap.put("NUM"+i, m.get(key));
					i++;
				}
				
			}
			thmap.put("DEEP", "深度(m)\\日期");
			list.add(thmap);
			map0.put("data", list);
		}
		return map0;
	}

	public Option queryLineChart2(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcCxDataDao.queryLineChart2(map);
	}

	public Option queryBarChart2(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcCxDataDao.queryBarChart2(map);
	}
    
}
