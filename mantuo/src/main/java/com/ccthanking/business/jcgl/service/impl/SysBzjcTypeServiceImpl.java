/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcgl.service.SysBzjcTypeService.java
 * 创建日期： 2016-05-11 上午 11:31:11
 * 功能：    接口：标准检查模板类型
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 11:31:11  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcgl.service.impl;


import java.sql.Connection;
import java.util.Date;
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
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.jcgl.vo.SysBzjcTypeVO;
import com.ccthanking.business.jcgl.dao.SysBzjcTypeDao;
import com.ccthanking.business.jcgl.service.SysBzjcTypeService;
import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;
import com.ccthanking.business.pmjiancha.vo.PmBzgfVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SysBzjcTypeService.java </p>
 * <p> 功能：标准检查模板类型 </p>
 *
 * <p><a href="SysBzjcTypeService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@qq.com">曹伟杰</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Service
public class SysBzjcTypeServiceImpl extends Base1ServiceImpl<SysBzjcTypeVO, String> implements SysBzjcTypeService {

	private static Logger logger = LoggerFactory.getLogger(SysBzjcTypeServiceImpl.class);
	
    
    private SysBzjcTypeDao sysBzjcTypeDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sysBzjcTypeDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("标准检查模板类型{}", e.getMessage());
		
			SystemException.handleMessageException("标准检查模板类型查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SysBzjcTypeVO vo = new SysBzjcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           

            // 插入
			sysBzjcTypeDao.save(vo);
            resultVO = vo.getRowJson();

          
            
        } catch (DaoException e) {
            logger.error("标准检查模板类型{}", e.getMessage());
          
            SystemException.handleMessageException("标准检查模板类型新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String copyPm(Map map) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SysBzjcTypeVO vo = new SysBzjcTypeVO();
        Connection conn = DBUtil.getConnection();
        try {
        	String delsql = "delete from pm_bzjc where jc_type = 'BZ'";
        	boolean answer = DBUtil.exec( conn,delsql);
        	if(answer){
            	resultVO = copyPm2(null,null,map.get("TYPE_UID").toString(),map.get("PID").toString());	
        	}
        	

          

        } catch (DaoException e) {
            logger.error("标准检查模板类型{}", e.getMessage());
          
        } finally {
        }
        return resultVO;
    }
    /*
     * pid 父节点ip 导入新表pm_bzjc 使用父节点的id
     * querypid 查询 sys_bzjc 父节点id
     * */
    public String copyPm2(String pid,String querypid,String type,String xmid) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        Connection conn = DBUtil.getConnection();
        try {
        	List<Map<String, String>> list=null;
        	if( querypid==null ){
        		list = DBUtil.queryReturnList(conn," SELECT * FROM sys_bzjc where p_bzjc_uid is null and BZJC_TYPE_UID = '"+ type +"' ");	
        	}else{
        		list = DBUtil.queryReturnList(conn," SELECT * FROM sys_bzjc where p_bzjc_uid = '" + querypid +"' and BZJC_TYPE_UID = '"+ type +"' ");
        	}
        	
            for (int i = 0; i < list.size(); i++) {
            	PmBzjcVO vo = new PmBzjcVO();
            	if( pid!=null ){
            		vo.setP_bzjc_uid(pid);
            	}
            	vo.setProject_uid(xmid);
            	vo.setLevel(list.get(i).get("LEVEL"));
            	vo.setXuhao(list.get(i).get("XUHAO"));
            	vo.setJc_type("BZ");
            	vo.setBzjc_name(list.get(i).get("BZJC_NAME"));
            	vo.setJc_zhibiao(list.get(i).get("JC_ZHIBIAO"));
            	vo.setJc_ssry(list.get(i).get("JC_SSRY"));
            	vo.setJc_biaozhun(list.get(i).get("JC_BIAOZHUN"));
            	vo.setJc_dzms(list.get(i).get("JC_DZMS"));
            	vo.setJc_chengguo(list.get(i).get("JC_CHENGGUO"));
            	vo.setJc_status("未检查");
            	vo.setCreate_date(new Date());
            	vo.setCreate_user(user.getIdCard());
            	
            	String prid = String.valueOf(BaseDAO.insert(conn, vo));
            	
            	String[][] res = DBUtil.query(" select * from sys_bzjc where p_bzjc_uid = '"+ list.get(i).get("BZJC_UID") +"' ");
            	if( res != null && !"".equals(res[0][2]) ){//如果有子节点
            		resultVO = copyPm2(prid,res[0][2],type,xmid);
            	}else{
            		resultVO="1";
            	}
			}
            

          

        } catch (DaoException e) {
            logger.error("标准检查模板类型{}", e.getMessage());
            conn.rollback();
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;
    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SysBzjcTypeVO vo = new SysBzjcTypeVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          

            // 修改
            sysBzjcTypeDao.update(vo);
            resultVO = vo.getRowJson();

          

        } catch (DaoException e) {
            logger.error("标准检查模板类型{}", e.getMessage());
          
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SysBzjcTypeVO vo = new SysBzjcTypeVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			
			//删除   根据据主键
			sysBzjcTypeDao.delete(SysBzjcTypeVO.class, vo.getBzjc_type_uid());

			resultVo = vo.getRowJson();

			
		} catch (DaoException e) {
            logger.error("标准检查模板类型{}", e.getMessage());
        
            SystemException.handleMessageException("标准检查模板类型删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sysBzjcTypeDaoImpl")
	public void setSysBzjcTypeDao(SysBzjcTypeDao sysBzjcTypeDao) {
		this.sysBzjcTypeDao = sysBzjcTypeDao;
	}
    
}
