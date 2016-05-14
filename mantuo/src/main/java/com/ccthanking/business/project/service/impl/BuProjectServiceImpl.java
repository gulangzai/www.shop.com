/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectService.java
 * 创建日期： 2015-10-17 上午 08:22:57
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-17 上午 08:22:57  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service.impl;


import java.sql.Connection;
import java.util.Date;
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

import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.project.vo.BuProjectVO;
import com.ccthanking.business.project.dao.BuProjectDao;
import com.ccthanking.business.project.service.BuProjectService;
import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> BuProjectService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="BuProjectService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-17
 * 
 */

@Service
public class BuProjectServiceImpl extends Base1ServiceImpl<BuProjectVO, String> implements BuProjectService {

	private static Logger logger = LoggerFactory.getLogger(BuProjectServiceImpl.class);
	
    
    private BuProjectDao buProjectDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
     
        String domresult = "";
        try {

			domresult = buProjectDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("项目{}", e.getMessage());
			SystemException.handleMessageException("项目查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        BuProjectVO vo = new BuProjectVO();
        Connection conn = DBUtil.getConnection();
        try {
        	
        	conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            
            //vo.setCreated_user(user.getIdCard());
            vo.setCreated_date(new Date());
            vo.setUpdated_date(new Date());
            String targetUid = obj.getString("target_uid");
            String userUid = obj.getString("CREATED_USER");
            // 插入
			Long project_uid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            
            //更新附件信息
            if(null!=project_uid&&!"".equals(project_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(project_uid));                 
            }
            

            
            //复制用户
            String sql = "INSERT INTO bu_project_user(PROJECT_UID,USER_UID,CREATED_DATE) VALUES ('"+project_uid+"','"+userUid+"',SYSDATE())";
            Long puser = DBUtil.execInsertSql(conn, sql);
            
            String rsql = "SELECT ROLE_UID FROM sys_role WHERE ROLE_UID >2";
            List<Map<String, String>> rlist = DBUtil.queryReturnList(conn, rsql.toString());
            for (Map<String, String> map : rlist) {
                //复制角色
                String roleSql = "INSERT INTO bu_project_role (PROJECT_UID,ROLE_UID,ROLE_NAME,XUHAO,DESCRIPTION,CREATED_DATE,UPDATED_DATE ) " +
                		         " SELECT '"+project_uid+"',ROLE_UID,ROLE_NAME,XUHAO,DESCRIPTION,SYSDATE(),SYSDATE() FROM sys_role where ROLE_UID ='"+map.get("ROLE_UID")+"'";
                Long pru = DBUtil.execInsertSql(conn, roleSql);
                
                //复制角色菜单
                StringBuffer aSql = new StringBuffer();
                aSql.append(" INSERT INTO bu_project_role_auth (PROJECT_ROLE_UID,AUTHORITY_UID) ");
                aSql.append(" SELECT PROJECT_ROLE_UID,AUTHORITY_UID FROM sys_role_auth ra LEFT JOIN ");
                aSql.append(" bu_project_role pr ");
                aSql.append(" ON  ra.ROLE_UID = pr.ROLE_UID WHERE PROJECT_ROLE_UID  = '"+pru+"'");

                DBUtil.exec(conn, aSql.toString());
                
                //添加用户与角色关系
                String auSql = "INSERT INTO bu_project_role_user (PROJECT_ROLE_UID,PROJECT_USER_UID) VALUES ('"+pru+"','"+puser+"')";
                DBUtil.execInsertSql(conn, auSql);
                
                //复制项目资料模板及资料权限模板
                StringBuffer qSql = new StringBuffer();
                qSql.append(" SELECT t.P_DOCS_UID,t.NODE_NAME,t.NODE_TYPE,t.SYS_PROJECT_DOCS_UID FROM sys_project_docs t ");
                qSql.append(" LEFT JOIN sys_role_docs_auth r ");
                qSql.append(" ON t.SYS_PROJECT_DOCS_UID = r.SYS_PROJECT_DOCS_UID ");
                qSql.append(" WHERE r.ROLE_UID = '"+map.get("ROLE_UID")+"' ");
                List<Map<String, String>> syslist = DBUtil.queryReturnList(conn, qSql.toString());
    			String sys_uid = "";
    			
    			for (Map<String, String> map1 : syslist) {
    				StringBuffer rdSql = new StringBuffer();
    				if("".equals(map1.get("P_DOCS_UID"))){
    					PmProjectDocsVO dvo = new PmProjectDocsVO();
    					dvo.setProject_uid(String.valueOf(project_uid));
    					dvo.setNode_type(map1.get("NODE_TYPE"));
    					dvo.setNode_name(map1.get("NODE_NAME"));
    					dvo.setCreate_date(new Date());
    					dvo.setCreate_user(userUid);
    					dvo.setUpdate_date(new Date());
    					dvo.setUpdate_user(userUid);
    					sys_uid = String.valueOf(BaseDAO.insert(conn, dvo));
    					rdSql.append(" INSERT INTO pm_project_docs_auth (PROJECT_DOCS_UID,PROJECT_ROLE_UID,AUTH_LEVEL) ");
    					rdSql.append(" SELECT '"+sys_uid+"','"+pru+"',AUTH_LEVEL FROM sys_role_docs_auth a LEFT JOIN  ");
    					rdSql.append(" sys_project_docs d ON d.SYS_PROJECT_DOCS_UID = a.SYS_PROJECT_DOCS_UID ");
    					rdSql.append("  WHERE a.ROLE_UID = '"+map.get("ROLE_UID")+"' ");
    					rdSql.append(" AND d.SYS_PROJECT_DOCS_UID = '"+map1.get("SYS_PROJECT_DOCS_UID")+"' ");
    					//System.out.println("rdSql==="+rdSql);
    					DBUtil.exec(conn, rdSql.toString());
    				}else{
    					PmProjectDocsVO dvo = new PmProjectDocsVO();
    					dvo.setProject_uid(String.valueOf(project_uid));
    					dvo.setP_docs_uid(sys_uid);
    					dvo.setNode_type(map1.get("NODE_TYPE"));
    					dvo.setNode_name(map1.get("NODE_NAME"));
    					dvo.setCreate_date(new Date());
    					dvo.setCreate_user(userUid);
    					dvo.setUpdate_date(new Date());
    					dvo.setUpdate_user(userUid);
    					String x = String.valueOf(BaseDAO.insert(conn, dvo));
    					rdSql.append(" INSERT INTO pm_project_docs_auth (PROJECT_DOCS_UID,PROJECT_ROLE_UID,AUTH_LEVEL) ");
    					rdSql.append(" SELECT '"+x+"','"+pru+"',AUTH_LEVEL FROM sys_role_docs_auth a LEFT JOIN  ");
    					rdSql.append(" sys_project_docs d ON d.SYS_PROJECT_DOCS_UID = a.SYS_PROJECT_DOCS_UID ");
    					rdSql.append("  WHERE a.ROLE_UID = '"+map.get("ROLE_UID")+"' ");
    					rdSql.append(" AND d.SYS_PROJECT_DOCS_UID = '"+map1.get("SYS_PROJECT_DOCS_UID")+"' ");
    					//System.out.println("rdSql==="+rdSql);
    					DBUtil.exec(conn, rdSql.toString());
    				}
    			}

			}
           

            conn.commit();
        } catch (DaoException e) {
        	conn.rollback();
            logger.error("项目{}", e.getMessage());
            SystemException.handleMessageException("项目新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    //附件 添加更新
    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuProjectVO vo = new BuProjectVO();
        boolean bs= false;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String project_uid = obj.getString("PROJECT_UID");
            vo.setProject_uid(project_uid);
            /**获取到 更改的县区 的region_code 并判断当前城市的天气预报是否存在天气表中 不存在就 插入当前城市的天气。**/
            String contry= obj.getString("DISTRICT");
            if(!"no".equalsIgnoreCase(contry) && contry !=""){
            	bs= buProjectDao.quertTianQi(contry);
                if(bs){
                	vo.setValueFromJson(obj);
                    vo.setUpdated_date(new Date());
                	buProjectDao.update(vo);
                    resultVO = vo.getRowJson();
                 }else{
                	 vo.setValueFromJson(obj);
                     vo.setUpdated_date(new Date());
                 	 buProjectDao.update(vo);
                	 resultVO = null;
                 }
            }
               //说明当前是文件上传操作 就 仅仅更新附件
               String targetUid = (String)obj.get("target_uid");
               if(vo!=null&&vo.getProject_uid()!=null&&!"".equals(vo.getProject_uid())){
                	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getProject_uid());                 
               }
            
            
        } catch (DaoException e) {
            logger.error("项目{}", e.getMessage());
             SystemException.handleMessageException("项目信息修改失败,请联系相关人员处理");
        } 
        
        return resultVO;
    }

	public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		BuProjectVO vo = new BuProjectVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			buProjectDao.delete(BuProjectVO.class, vo.getProject_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目{}", e.getMessage());
            SystemException.handleMessageException("项目删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("buProjectDaoImpl")
	public void setBuProjectDao(BuProjectDao buProjectDao) {
		this.buProjectDao = buProjectDao;
	}

	public String queryProject(String projectUid) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryProject(projectUid);
	}

	public List<?> queryProjectList(String userUid,String projectName) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryProjectList(userUid,projectName);
	}

	public List<?> queryProjectList(String projectUid) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryProjectList(projectUid);
	}

	public String deleteFile(String target_uid, String target_table,
			String file_type) {
	  	    User user = ActionContext.getCurrentUserInThread();
		    Connection conn = DBUtil.getConnection();
		    String result ="";
		try {
			String sqlFile="select FILE_UID  from BU_FILE where TARGET_UID='"+target_uid+"' AND FILE_TYPE='"+file_type+"' ";
			BaseResultSet bs = DBUtil.query(conn, sqlFile.toString(),null);
			result = bs.getJson();
			if(result !="0" && result !=null){//说明存在 文件
				String sql="delete from BU_FILE where TARGET_UID='"+target_uid+"' AND FILE_TYPE='"+file_type+"' ";
				boolean b = DBUtil.exec(conn, sql);
			}
			
		} catch (Exception e) {
			logger.info("*********删除文件 出错!*********");
		} finally{
			DBUtil.closeConnetion(conn);
		}
	   return result;
		
	}

	public List<?> queryDetailById(String projectUid) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryDetailById(projectUid);
	}

	public List<?> queryCity(String msg) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryCity(msg);
	}

	public List<?> queryCityorControyBycode(String code) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryCityorControyBycode(code);
	}

	public List<?> queryWeather(String projectUid) {
	
		return this.buProjectDao.queryWeather(projectUid);
	}

	public String queryGKdetail(String projectUid) {
		
		return this.buProjectDao.queryGKdetail(projectUid);
	}

	public String queryDataJSON(String msg) {
		
		return this.buProjectDao.queryDataJSON(msg);
	}

	public Map<String, Object> queryMyCare(HashMap<String, Object> map) {
		
		return this.buProjectDao.queryMyCare(map);
	}

	public Map<String, Object> queryWarning(HashMap<String, Object> map) {
		
		return this.buProjectDao.queryWarning(map);
	}

	public Map<String, Object> queryAllPoint(HashMap<String, Object> map) {
	
		return this.buProjectDao.queryAllPoint(map);
	}
    
	public List<?> queryCityName(String code) {
		
		return this.buProjectDao.queryCityName(code);
	}

	public String queryid(String projectuid) {
		// TODO Auto-generated method stub
		return this.buProjectDao.queryid(projectuid);
	}
}
