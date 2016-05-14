/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcJcDataService.java
 * 创建日期： 2015-10-30 上午 09:30:58
 * 功能：    接口：监测数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:30:58  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.jkjc.vo.JcJcDataVO;
import com.ccthanking.business.jkjc.dao.JcJcDataDao;
import com.ccthanking.business.jkjc.service.JcJcDataService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.github.abel533.echarts.Option;

/**
 * <p>
 * JcJcDataService.java
 * </p>
 * <p>
 * 功能：监测数据
 * </p>
 *
 * <p>
 * <a href="JcJcDataService.java.html"><i>查看源代码</i></a>
 * </p>
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Service
public class JcJcDataServiceImpl extends Base1ServiceImpl<JcJcDataVO, String> implements JcJcDataService {

	private static Logger logger = LoggerFactory.getLogger(JcJcDataServiceImpl.class);

	private JcJcDataDao jcJcDataDao;

	// @Override
	public String queryCondition(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String domresult = "";
		try {

			domresult = jcJcDataDao.queryCondition(json, null, null);

		} catch (DaoException e) {
			logger.error("监测数据{}", e.getMessage());
			SystemException.handleMessageException("监测数据查询失败,请联系相关人员处理");
		} finally {
		}
		return domresult;

	}

	public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVO = null;
		JcJcDataVO vo = new JcJcDataVO();

		try {
			JSONArray list = vo.doInitJson(json);
			vo.setValueFromJson((JSONObject) list.get(0));

			// 插入
			jcJcDataDao.save(vo);
			resultVO = vo.getRowJson();

		} catch (DaoException e) {
			logger.error("监测数据{}", e.getMessage());
			SystemException.handleMessageException("监测数据新增失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String update(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVO = null;
		JcJcDataVO vo = new JcJcDataVO();

		try {
			JSONArray list = vo.doInitJson(json);
			vo.setValueFromJson((JSONObject) list.get(0));

			// 修改
			jcJcDataDao.update(vo);
			resultVO = vo.getRowJson();

		} catch (DaoException e) {
			logger.error("监测数据{}", e.getMessage());
			SystemException.handleMessageException("监测数据修改失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcJcDataVO vo = new JcJcDataVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			// 删除 根据据主键
			jcJcDataDao.delete(JcJcDataVO.class, vo.getJc_data_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
			logger.error("监测数据{}", e.getMessage());
			SystemException.handleMessageException("监测数据删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcJcDataDaoImpl")
	public void setJcJcDataDao(JcJcDataDao jcJcDataDao) {
		this.jcJcDataDao = jcJcDataDao;
	}

	public Map<String,Object> queryLineChart(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcJcDataDao.queryLineChart(map);
	}

	public Map<String,Object> queryBarChart(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.jcJcDataDao.queryBarChart(map);
	}

	public 	Map<String,Object> queryTab(HashMap<String, Object> map) {
		Map<String,Object> map1 = this.jcJcDataDao.queryTab(map);
		List<Map<String ,String>> list = (List<Map<String, String>>) map1.get("data");
		List<Map<String ,String>> mmap = this.jcJcDataDao.getTypeByCode(map);
		Map<String,String> thmap = new HashMap<String, String>();
	
		if(null!=list&&list.size()>0){
			thmap.put("REPORT_DATE", "日期");
			if(null!=list&&mmap.size()>0){
				thmap.put("V", mmap.get(0).get("JC_TYPE_NAME")+"本次变化值("+mmap.get(0).get("UNIT")+")");
				thmap.put("B", mmap.get(0).get("JC_TYPE_NAME")+"累计变化值("+mmap.get(0).get("UNIT")+")");
			}else{
				thmap.put("V", "本次变化值");
				thmap.put("B", "累计变化值");
			}

			list.add(thmap);
			map1.put("data", list);
		}
		return map1;
	}


}
