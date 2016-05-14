/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcgl.service.SysBzjcService.java
 * 创建日期： 2016-05-11 上午 11:34:33
 * 功能：    接口：标准检查模板
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 11:34:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcgl.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.jcgl.vo.SysBzjcVO;
import com.ccthanking.business.jcgl.dao.SysBzjcDao;
import com.ccthanking.business.jcgl.service.SysBzjcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> SysBzjcService.java </p>
 * <p> 功能：标准检查模板 </p>
 *
 * <p><a href="SysBzjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@qq.com">曹伟杰</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Service
public class SysBzjcServiceImpl extends Base1ServiceImpl<SysBzjcVO, String> implements SysBzjcService {

	private static Logger logger = LoggerFactory.getLogger(SysBzjcServiceImpl.class);
	
    
    private SysBzjcDao sysBzjcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = sysBzjcDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("标准检查模板{}", e.getMessage());
			SystemException.handleMessageException("标准检查模板查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public void queryTreeZjd(Connection conn,List<Map> data,int id,String type){ 
		List list;
		String sql = " select (select count(*) from sys_bzjc a where jc.BZJC_UID = a.P_BZJC_UID) ZCOUNT,jc.* from sys_bzjc jc where P_BZJC_UID ='"+ id +"'  and jc.BZJC_TYPE_UID = '"+ type +"'  "; 
    	if(id==0){
    	   sql = " select (select count(*) from sys_bzjc a where jc.BZJC_UID = a.P_BZJC_UID) ZCOUNT,jc.* from sys_bzjc jc where jc.P_BZJC_UID is null and jc.BZJC_TYPE_UID = '"+ type +"'  "; 
        } 
		try {
			list = DBUtil.queryReturnList(conn, sql);
			for (Object object : list) {
            	Map map = (Map) object;
            	data.add(map);
            	queryTreeZjd(conn,data,Integer.parseInt((String) map.get("BZJC_UID")), type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
    public String queryTree(String json) throws Exception {
    	
    	JSONArray list = new JSONArray();
    	User user = ActionContext.getCurrentUserInThread();
        Connection conn = null;
        List data= new ArrayList();
        String domresult = "";
        try {
        	
        	conn = DBUtil.getConnection();
        	
        	
        //	String sql = " select (select count(*) from sys_bzjc a where jc.BZJC_UID = a.P_BZJC_UID) ZCOUNT,jc.* from sys_bzjc jc where jc.BZJC_TYPE_UID = '"+ json +"'  order by xuhao ";
        	String sql = "  call showSysBzjc(0, "+json+") ";
        	data = DBUtil.queryReturnList(conn, sql);
     //   	queryTreeZjd(conn,data,0,json);
        	if(data!=null&&data.size()>0){
        	for (int i = 0; i < data.size(); i++) {
        		Map maptemp=(Map)data.get(i);
				JSONObject obj1 = new JSONObject();
				String parent=(String)maptemp.get("PARENT");
				String NODE_TYPE=(String)maptemp.get("NODE_TYPE");
				if(maptemp.get("PARENT")==null || "".equals(parent)){	//父节点
					if(!"JC".equals(NODE_TYPE)){//分类
						obj1.put("isLeaf", false);
						obj1.put("expanded", true);
					}else{//标准检查
						obj1.put("isLeaf", true);
						obj1.put("expanded", true);
					}
				}else{
					obj1.put("isLeaf", true);//无子节点
					obj1.put("expanded", true);
				}	
				obj1.put("loaded", true);
				
				obj1.put("id", maptemp.get("BZJC_UID"));    	
		        obj1.put("level", maptemp.get("LEVEL"));
		        obj1.put("parent", maptemp.get("P_BZJC_UID"));
		        
		        obj1.put("BZJC_TYPE_UID", maptemp.get("BZJC_TYPE_UID"));
		        obj1.put("XUHAO", maptemp.get("XUHAO"));
		        obj1.put("BZJC_NAME", maptemp.get("BZJC_NAME"));
		        obj1.put("JC_ZHIBIAO", maptemp.get("JC_ZHIBIAO"));
		        obj1.put("JC_SSRY", maptemp.get("JC_SSRY"));
		        obj1.put("JC_BIAOZHUN", maptemp.get("JC_BIAOZHUN"));
		        obj1.put("JC_DZMS", maptemp.get("JC_DZMS"));
		        obj1.put("JC_CHENGGUO", maptemp.get("JC_CHENGGUO"));
		        obj1.put("DESCRIPTION", maptemp.get("DESCRIPTION"));
		        obj1.put("CREATE_DATE", maptemp.get("CREATE_DATE"));
		        obj1.put("UPDATE_DATE", maptemp.get("UPDATE_DATE"));
		        
			        list.add(obj1);
			}
        	}

            
        }catch (DaoException e) {
        	logger.error("标准检查模板{}", e.getMessage());
			SystemException.handleMessageException("标准检查模板查询失败,请联系相关人员处理");
        } finally {
        	conn.close();
        }
        return list.toString();

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        SysBzjcVO vo = new SysBzjcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);

            // 插入
			sysBzjcDao.save(vo);
            resultVO = vo.getRowJson();

          
            
        } catch (DaoException e) {
            logger.error("标准检查模板{}", e.getMessage());
          
            SystemException.handleMessageException("标准检查模板新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        SysBzjcVO vo = new SysBzjcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

            // 修改
            sysBzjcDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("标准检查模板{}", e.getMessage());
         
            SystemException.handleMessageException("标准检查模板修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		SysBzjcVO vo = new SysBzjcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			sysBzjcDao.delete(SysBzjcVO.class, vo.getBzjc_uid());

			resultVo = vo.getRowJson();

		

		} catch (DaoException e) {
            logger.error("标准检查模板{}", e.getMessage());
          
            SystemException.handleMessageException("标准检查模板删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("sysBzjcDaoImpl")
	public void setSysBzjcDao(SysBzjcDao sysBzjcDao) {
		this.sysBzjcDao = sysBzjcDao;
	}
    
}
