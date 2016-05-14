/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPrjPointsService.java
 * 创建日期： 2015-10-30 上午 09:48:59
 * 功能：    接口：项目监测点位
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:48:59  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service.impl;


import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.jkjc.vo.JcPrjPointsVO;
import com.ccthanking.business.jkjc.dao.JcPrjPointsDao;
import com.ccthanking.business.jkjc.service.JcPrjPointsService;
import com.ccthanking.business.userFocus.vo.JcUserFocusPointVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcPrjPointsService.java </p>
 * <p> 功能：项目监测点位 </p>
 *
 * <p><a href="JcPrjPointsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */

@Service
public class JcPrjPointsServiceImpl extends Base1ServiceImpl<JcPrjPointsVO, String> implements JcPrjPointsService {

	private static Logger logger = LoggerFactory.getLogger(JcPrjPointsServiceImpl.class);
	
    
    private JcPrjPointsDao jcPrjPointsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcPrjPointsDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("项目监测点位{}", e.getMessage());
			SystemException.handleMessageException("项目监测点位查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JcPrjPointsVO vo = new JcPrjPointsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			jcPrjPointsDao.save(vo);
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("项目监测点位{}", e.getMessage());
            SystemException.handleMessageException("项目监测点位新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        
        String resultVO = null;
        JcPrjPointsVO vo = new JcPrjPointsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            jcPrjPointsDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("项目监测点位{}", e.getMessage());
            SystemException.handleMessageException("项目监测点位修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcPrjPointsVO vo = new JcPrjPointsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			jcPrjPointsDao.delete(JcPrjPointsVO.class, vo.getPrj_points_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("项目监测点位{}", e.getMessage());
            SystemException.handleMessageException("项目监测点位删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jcPrjPointsDaoImpl")
	public void setJcPrjPointsDao(JcPrjPointsDao jcPrjPointsDao) {
		this.jcPrjPointsDao = jcPrjPointsDao;
	}

	public String queryPointForType(String msg,HashMap<String, String> map) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
        String domresult = "";
        try {

			domresult = jcPrjPointsDao.queryPointForType(msg, map);

        }catch (DaoException e) {
        	logger.error("项目监测点位{}", e.getMessage());
			SystemException.handleMessageException("项目监测点位查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}

	public List<?> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryList(map);
	}

	public List<Map<String,String>> queryIconById(String eleId) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
	    
		List<Map<String, String>>  domresult = null;
        try {

			domresult = jcPrjPointsDao.queryIconById(eleId);

        }catch (DaoException e) {
        	logger.error("项目监测点位{}", e.getMessage());
			SystemException.handleMessageException("项目监测点位查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

	}

	public List<Map<String, String>> queryU3DElementUids(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryU3DElementUids(map);
	}
    
	public List<Map<String, String>> queryPoints(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryPoints(map);
	}

	public List<Map<String, String>> queryItems(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryItems(map);
	}
	
	public List<Map<String, String>> queryItemTypes(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryItemTypes(map);
	}
	
	public List<Map<String, String>> queryPoint_Item(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryPoint_Item(map);
	}
	
	public List<Map<String, String>> queryPointProperty(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryPointProperty(map);
	}

	public List<Map<String, String>> queryPointData_jc(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryPointData_jc(map);
	}
	
	public List<Map<String, String>> queryPointData_cx(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryPointData_cx(map);
	}

	public String queryById(String pRJPOINTSUID) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryById(pRJPOINTSUID);
	}
	
	public List<Map<String, String>> queryNewestDataByPointId_jc(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryNewestDataByPointId_jc(map);
	}
	
	public List<Map<String, String>> queryNewestDataByPointId_cx(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryNewestDataByPointId_cx(map);
	}

	public List<Map<String, String>> queryUser_Focus_Point(
			HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.jcPrjPointsDao.queryUser_Focus_Point(map);
	}

	public String insertFocus(String puid) {
        User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        JcUserFocusPointVO vo = new JcUserFocusPointVO();
        Connection conn = DBUtil.getConnection();
        boolean flag = false;
        try {
        	
        	//设置 用户的id及测点的 主键id
            vo.setPrj_points_uid(puid);
            vo.setUser_uid(user.getIdCard());
            
            deleteFocus(puid);
        	BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            
			
            
        } catch (DaoException e) {
            logger.error("项目监测点位{}", e.getMessage());
            SystemException.handleMessageException("用户关注监测点新增失败,请联系相关人员处理");
        } catch (Exception e1) {
			e1.printStackTrace();
			logger.info("新增用户关注点数据失败！请联系管理员！");
		} finally {
        	DBUtil.closeConnetion(conn);
        }
        
        return resultVO;

	}

	public boolean deleteFocus(String priPointsUid) {
		User user = ActionContext.getCurrentUserInThread();
		JcUserFocusPointVO vo = new JcUserFocusPointVO();
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		try {
			
			String sql ="DELETE  FROM jc_user_focus_point WHERE PRJ_POINTS_UID = "+priPointsUid+" AND USER_UID = "+user.getIdCard();
			//删除   根据据主键
			flag = DBUtil.exec(conn, sql);

		} catch (DaoException e) {
            logger.error("项目监测点位{}", e.getMessage());
            SystemException.handleMessageException("项目监测点位删除失败,请联系相关人员处理");
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
		}
		return flag;

	}

}
