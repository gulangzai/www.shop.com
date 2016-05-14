/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmXianchangService.java
 * 创建日期： 2016-01-22 上午 10:45:08
 * 功能：    接口：进度质量安全
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-22 上午 10:45:08  卢红冈   创建文件，实现基本功能
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

import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.pmxianchang.dao.PmXianchangDao;
import com.ccthanking.business.pmxianchang.service.PmXianchangService;
import com.ccthanking.business.pmxianchang.vo.PmXianchangStrucVO;
import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * <p> PmXianchangService.java </p>
 * <p> 功能：进度质量安全 </p>
 *
 * <p><a href="PmXianchangService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-22
 * 
 */

@Service
public class PmXianchangServiceImpl extends Base1ServiceImpl<PmXianchangVO, String> implements PmXianchangService {
	private static Logger logger = LoggerFactory.getLogger(PmXianchangServiceImpl.class);
    private PmXianchangDao pmXianchangDao;
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmXianchangDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("现场状况信息查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmXianchangVO vo = new PmXianchangVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj  = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            String psUid = obj.getString("PRJ_STRUC_UID");
            String targetUid = obj.getString("target_uid");
            String f_DESCRIPTION = obj.getString("f_DESCRIPTION");
            /**返回当前现场的 主键uid**/
			long xcUid = BaseDAO.insert(conn, vo);
			if(xcUid !=0){//说明 成功插入数据
				insertXianChangStruc(xcUid,psUid);
			}
			
            resultVO = vo.getRowJson();
            
            //更新现场状况的附件信息
            if(xcUid !=0 &&!"".equals(xcUid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(xcUid));                 
            }
            
            if(!"".equals(f_DESCRIPTION)){
                //更新附件描述
                if(f_DESCRIPTION.indexOf("[")!=-1){
                	JSONArray array = obj.getJSONArray("f_DESCRIPTION");
                	String qsql = "SELECT FILE_UID FROM bu_file WHERE TARGET_UID = '"+xcUid+"' AND TARGET_TABLE = 'PM_XIANCHANG' ORDER BY CREATE_DATE ";
                	String[][] data = DBUtil.query(qsql);
                	for (int i = 0; i < array.size(); i++) {
                		System.out.println(data[i][0]);
                		StringBuffer sql = new StringBuffer();
                    	sql.append("UPDATE bu_file SET DESCRIPTION = '"+array.getString(i)+"' WHERE FILE_UID = '"+data[i][0]+"'");
                    	DBUtil.exec(conn, sql.toString());
    				}
                }else{
                	StringBuffer sql = new StringBuffer();
                	sql.append("UPDATE bu_file SET DESCRIPTION = '"+f_DESCRIPTION+"' WHERE TARGET_UID = '"+xcUid+"'");
                	DBUtil.exec(conn, sql.toString());
                }
            }

            
            
        } catch (DaoException e) {
            SystemException.handleMessageException("现场状况信息新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }
    
    /**向 中间表中插入数据**/
    private void insertXianChangStruc(long xcUid, String psUid) {
	    	Connection con = DBUtil.getConnection();
	    	PmXianchangStrucVO pvo = new PmXianchangStrucVO();
	   try {
			pvo.setXianchang_uid(String.valueOf(xcUid));
			pvo.setPrj_struc_uid(psUid);
			long xcsId = BaseDAO.insert(con,pvo);  
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				DBUtil.closeConnetion(con);
			}
			
		}
		
	}

	public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmXianchangVO vo = new PmXianchangVO();
        Connection conn = DBUtil.getConnection();
        boolean flag = false;
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            String status =obj.getString("STATUS");
            if(status.equals("1")){
            	vo.setClose_date(new Date());	
            }
            
            String psUid = obj.getString("PRJ_STRUC_UID");
            String xcUid = obj.getString("XIANCHANG_UID");
            String targetUid = obj.getString("target_uid");
            String xcstUid = obj.getString("XIANCHANG_STRUC_UID");
            /**返回当前现场的 主键uid**/
            pmXianchangDao.update(vo);
			if(!"".equals(xcstUid)){//说明 成功插入数据
		        flag = updateXianChangStruc(obj,xcstUid);
			}else{
				flag=true;
			}
			
			if(flag){
				resultVO = vo.getRowJson();
			
            //更新现场状况的附件信息
            if(!"".equals(xcUid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,xcUid);                 
            }
            
			}

        } catch (DaoException e) {
            SystemException.handleMessageException("现场状况信息修改失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }
	
    /**更新中间表的数据 返回 boolean值**/
    private boolean updateXianChangStruc(JSONObject obj,String xcstUid) {
    	Connection con = DBUtil.getConnection();
    	PmXianchangStrucVO pvo = new PmXianchangStrucVO();
    	boolean bs = false;
	   try {
		   con.setAutoCommit(false);
		   pvo.setValueFromJson(obj);
		   pvo.setXianchang_struc_uid(xcstUid);
		   bs = BaseDAO.update(con,pvo);  
		   con.commit();
	} catch (Exception e) {
		e.printStackTrace();
		SystemException.handleMessageException("现场状况信息数据连接表修改失败,请联系相关人员处理");
	} finally{
		if(con != null){
			DBUtil.closeConnetion(con);
		}
		
	}
	   
	return bs;
		
	}

	public String delete(String xc_uid,String xsUid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		PmXianchangVO vo = new PmXianchangVO();
		try {
			
			pmXianchangDao.delete(PmXianchangVO.class,xc_uid);
			if(!"".equals(xsUid) && xsUid !=null){
				pmXianchangDao.delete(PmXianchangStrucVO.class,xsUid);
			}
			
			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            SystemException.handleMessageException("现场状况信息删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmXianchangDaoImpl")
	public void setPmXianchangDao(PmXianchangDao pmXianchangDao) {
		this.pmXianchangDao = pmXianchangDao;
	}

	public Map<String, List<?>> queryXcQK(String xcUid, String fileType) throws Exception {
		
		return this.pmXianchangDao.queryXcQK(xcUid,fileType);
	}

	
    
}
