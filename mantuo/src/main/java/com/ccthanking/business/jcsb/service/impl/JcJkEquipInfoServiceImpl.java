/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcsb.service.JcJkEquipInfoService.java
 * 创建日期： 2015-10-27 下午 03:52:34
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-27 下午 03:52:34  luhonggang   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcsb.service.impl;


import java.sql.Connection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.jcsb.dao.JcJkEquipInfoDao;
import com.ccthanking.business.jcsb.service.JcJkEquipInfoService;
import com.ccthanking.business.jcsb.vo.JcEquipmentVO;
import com.ccthanking.business.jcsb.vo.JcJkEquipInfoVO;
import com.ccthanking.business.jcsb.vo.JcPrjPointsVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JcJkEquipInfoService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="JcJkEquipInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhongang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-10-27
 * 
 */

@Service
public class JcJkEquipInfoServiceImpl extends Base1ServiceImpl<JcJkEquipInfoVO, String> implements JcJkEquipInfoService {

	private static Logger logger = LoggerFactory.getLogger(JcJkEquipInfoServiceImpl.class);
    private JcJkEquipInfoDao jcJkEquipInfoDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jcJkEquipInfoDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("项目设施设备查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        Connection conn  = DBUtil.getConnection();
        //JcJkEquipInfoVO vo = new JcJkEquipInfoVO();//设施设备使用情况
        JcEquipmentVO vo = new JcEquipmentVO();//设施设备表 数据来源
        JcPrjPointsVO jvo = new JcPrjPointsVO();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String puid = obj.getString("PROJECT_UID");
         
            //vo.setProject_uid(puid);
            vo.setCreated_user(user.getIdCard());
            vo.setCreated_date(new Date());
            // 向 设施 设备表中 插入数据 
            //jcJkEquipInfoDao.save(vo);
            long euid = BaseDAO.insert(conn,vo);
            //将设备的 主键 uid插入到 项目监测点位表中
            if(euid !=-1){
             jvo.setProject_uid(puid);
             jvo.setEquipment_uid(String.valueOf(euid));
             jcJkEquipInfoDao.save(jvo);	
            }
           
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            SystemException.handleMessageException("项目设施设备 新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    //更新 项目设施设备 的信息待定 
	public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        Connection conn = DBUtil.getConnection();
        String resultVO = null;
        JcEquipmentVO vo = new JcEquipmentVO();
        try {
                JSONArray list = vo.doInitJson(json);
                JSONObject obj  = (JSONObject) list.get(0);
                vo.setValueFromJson(obj);
            	jcJkEquipInfoDao.update(vo);
                resultVO = vo.getRowJson();
        } catch (DaoException e) {
            SystemException.handleMessageException("项目设施设备修改失败,请联系相关人员处理");
        } finally {
        	
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JcEquipmentVO vo = new JcEquipmentVO();
		 String [] uid = null;
		 String eId = null;
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			//获取 设施设备的主键uid
            String uids = jsonObj.getString("EQUIPMENT_UID");
            
            uid = uids.split(",");
			if(uids.indexOf(",")!=-1 ){
                if(null!=uids){
                	for (int i = 0; i < uid.length; i++) {
                		//eId = jsonObj.getString("sjzzOrsws").split(",");
                	eId = uid[i];
                	jcJkEquipInfoDao.delete(JcEquipmentVO.class, eId);
    				}
                }
            }else{//只有一条
            	//删除   根据据主键
            	vo.setValueFromJson(jsonObj);
    			jcJkEquipInfoDao.delete(JcEquipmentVO.class, uids);
            }
            
			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            SystemException.handleMessageException("项目设施设备 信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

    public String query(String msg) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {

			domresult = jcJkEquipInfoDao.query(msg, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
	@Autowired
	@Qualifier("jcJkEquipInfoDaoImpl")
	public void setJcJkEquipInfoDao(JcJkEquipInfoDao jcJkEquipInfoDao) {
		this.jcJkEquipInfoDao = jcJkEquipInfoDao;
	}

	
    
}
