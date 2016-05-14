/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectaccept.service.PmYanShouService.java
 * 创建日期： 2016-03-28 上午 11:50:46
 * 功能：    接口：工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:50:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectaccept.service.impl;


import java.sql.Connection;
import java.util.Date;

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
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.projectaccept.vo.PmYanShouDetailVO;
import com.ccthanking.business.projectaccept.vo.PmYanShouVO;
import com.ccthanking.business.projectaccept.dao.PmYanShouDao;
import com.ccthanking.business.projectaccept.dao.PmYanShouDetailDao;
import com.ccthanking.business.projectaccept.service.PmYanShouService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmYanShouService.java </p>
 * <p> 功能：工程验收 </p>
 *
 * <p><a href="PmYanShouService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

@Service
public class PmYanShouServiceImpl extends Base1ServiceImpl<PmYanShouVO, String> implements PmYanShouService {

	private static Logger logger = LoggerFactory.getLogger(PmYanShouServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.PM_YANSHOU;
    
	@Autowired
    private PmYanShouDao pmYanShouDao;
	
	@Autowired
	private PmYanShouDetailDao pmYanshouDetailDao;
	

    // 
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmYanShouDao.queryCondition(json, null, null);

          /*  LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程验收>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("工程验收{}", e.getMessage());
/*			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程验收查询失败", user, "", "");*/
			SystemException.handleMessageException("工程验收查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception { 
		User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        PmYanShouVO vo = new PmYanShouVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json); 
            JSONObject obj = (JSONObject) list.get(0); 
            vo.setValueFromJson(obj);
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            
            String targetUid = obj.getString("target_uid");
         
            //System.out.println("------------"+ obj.getJSONArray("DETAIL_NAME"));
            System.out.println("json:"+json);
            // 插入详情表返回主键 
             // 插入 
 			 Long yanshou_UID = BaseDAO.insert(conn,vo);
             resultVO = vo.getRowJson();
             //更新附件信息
             if(null!=yanshou_UID&&!"".equals(yanshou_UID)){
             	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(yanshou_UID));                 
             }
         	  
             if(yanshou_UID!=0){
             if(json.indexOf("DETAIL_NAME")!=-1){
            	 String detailNames = obj.getString("DETAIL_NAME"); 
                 	   if(detailNames.indexOf("[")!=-1){
                     	   JSONArray uids = obj.getJSONArray("DETAIL_NAME"); 
                     	   if(null!=uids){
                            	for (int i = 0; i < uids.size(); i++) {
                            		String XUHAO =obj.getJSONArray("XUHAO").getString(i);
                            		String DETAIL_NAME = obj.getJSONArray("DETAIL_NAME").getString(i);
                            		String DETAIL_NUMS = obj.getJSONArray("DETAIL_NUMS").getString(i);
                            		String DETAIL_BUWEI = obj.getJSONArray("DETAIL_BUWEI").getString(i);
                            		String JCJG = obj.getJSONArray("DETAIL_JCJG").getString(i);
                            		String YSJL = obj.getJSONArray("DETAIL_YSJL").getString(i);
                            		
                            		PmYanShouDetailVO pmYanshouDetailVo = new PmYanShouDetailVO();
                            		pmYanshouDetailVo.setYanshou_uid(String.valueOf(yanshou_UID));
                            		pmYanshouDetailVo.setXuhao(XUHAO);
                            		pmYanshouDetailVo.setDetail_name(DETAIL_NAME);
                            		pmYanshouDetailVo.setDetail_nums(DETAIL_NUMS);
                            		pmYanshouDetailVo.setDetail_buwei(DETAIL_BUWEI);
                            		pmYanshouDetailVo.setDetail_jcjg(JCJG);
                            		pmYanshouDetailVo.setDetail_ysjl(YSJL);
                                     // 添加
                            		pmYanshouDetailDao.save(pmYanshouDetailVo);                
                				}
                            }  
                     }else{ 
                    	 PmYanShouDetailVO pmYanshouDetailVo = new PmYanShouDetailVO();
                 		pmYanshouDetailVo.setYanshou_uid(String.valueOf(yanshou_UID));
                 		pmYanshouDetailVo.setXuhao(obj.getString("XUHAO"));
                 		pmYanshouDetailVo.setDetail_name(obj.getString("DETAIL_NAME"));
                 		pmYanshouDetailVo.setDetail_nums(obj.getString("DETAIL_NUMS"));
                 		pmYanshouDetailVo.setDetail_buwei(obj.getString("DETAIL_BUWEI"));
                 		pmYanshouDetailVo.setDetail_jcjg(obj.getString("JCJG"));
                 		pmYanshouDetailVo.setDetail_ysjl(obj.getString("YSJL"));
                          // 添加
                 		pmYanshouDetailDao.save(pmYanshouDetailVo);  
                     }
                       	System.out.println("插入成功"+yanshou_UID);
                   }
            }
            
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键 
            //BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx); 
            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    //vo.setSjbh(eventVO.getSjbh()); 
            resultVO = vo.getRowJson();
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程验收新增成功", user, "", "");
           */
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user); 
        } catch (DaoException e) {
            logger.error("工程验收{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程验收新增失败", user, "", "");
           */ SystemException.handleMessageException("工程验收新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }
    
   
    public String update(String json) throws Exception { 
        User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        PmYanShouVO vo = new PmYanShouVO(); 
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json); 
            JSONObject obj = (JSONObject) list.get(0); 
            vo.setValueFromJson(obj);
            vo.setUpdate_date(new Date());
            vo.setUpdate_user(user.getIdCard());
            //修改 
            BaseDAO.update(conn,vo); 
            
            String targetUid = obj.getString("target_uid"); 
            //更新附件
            if(vo!=null&&vo.getYanshou_uid()!=null&&!"".equals(vo.getYanshou_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getYanshou_uid());                 
            }
            
            // 设置主键
            if(json.indexOf("DETAIL_NAME")!=-1){
           	  String detailNames = obj.getString("DETAIL_NAME"); 
                	   if(detailNames.indexOf("[")!=-1){
                    	   JSONArray uids = obj.getJSONArray("DETAIL_NAME"); 
                    	   if(null!=uids){
                           	 for (int i = 0; i < uids.size(); i++) {
                           		String XUHAO =obj.getJSONArray("XUHAO").getString(i); 
                           		String DETAIL_NAME = obj.getJSONArray("DETAIL_NAME").getString(i);
                           		String DETAIL_NUMS = obj.getJSONArray("DETAIL_NUMS").getString(i);
                           		String DETAIL_BUWEI = obj.getJSONArray("DETAIL_BUWEI").getString(i);
                           		String DETAIL_JCJG = obj.getJSONArray("DETAIL_JCJG").getString(i);
                           		String DETAIL_YSJL = obj.getJSONArray("DETAIL_YSJL").getString(i);
                           		
                           		PmYanShouDetailVO pmYanshouDetailVo = pmYanshouDetailDao.get(PmYanShouDetailVO.class, obj.getJSONArray("YANSHOU_DETAIL_UID").getString(i));
                           		pmYanshouDetailVo.setXuhao(XUHAO);
                           		pmYanshouDetailVo.setDetail_name(DETAIL_NAME);
                           		pmYanshouDetailVo.setDetail_nums(DETAIL_NUMS);
                           		pmYanshouDetailVo.setDetail_buwei(DETAIL_BUWEI);
                           		pmYanshouDetailVo.setDetail_jcjg(DETAIL_JCJG);
                           		pmYanshouDetailVo.setDetail_ysjl(DETAIL_YSJL);
                                // 更新
                           		pmYanshouDetailDao.update(pmYanshouDetailVo);                
               				 } 
                    	   }
                    }else{ 
                   	    PmYanShouDetailVO pmYanshouDetailVo = new PmYanShouDetailVO(); 
                		pmYanshouDetailVo.setYanshou_detail_uid(obj.getString("YANSHOU_DETAIL_UID"));
                		pmYanshouDetailVo.setXuhao(obj.getString("XUHAO"));
                		pmYanshouDetailVo.setDetail_name(obj.getString("DETAIL_NAME"));
                		pmYanshouDetailVo.setDetail_nums(obj.getString("DETAIL_NUMS"));
                		pmYanshouDetailVo.setDetail_buwei(obj.getString("DETAIL_BUWEI"));
                		pmYanshouDetailVo.setDetail_jcjg(obj.getString("DETAIL_JCJG"));
                		pmYanshouDetailVo.setDetail_ysjl(obj.getString("DETAIL_YSJL"));
                         // 添加
                		pmYanshouDetailDao.update(pmYanshouDetailVo);  
                     } 
                  } 
            resultVO = vo.getRowJson(); 
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程验收修改成功", user, "", "");
            */
        } catch (DaoException e) {
            logger.error("工程验收{}", e.getMessage());
          /*  LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程验收修改失败", user, "", "");*/
            SystemException.handleMessageException("工程验收修改失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmYanShouVO vo = new PmYanShouVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//pmYanShouDao.delete(PmYanShouVO.class, vo.getId());

			resultVo = vo.getRowJson();

			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程验收删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("工程验收{}", e.getMessage());
            /*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程验收删除失败", user, "", "");*/
            SystemException.handleMessageException("工程验收删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmYanShouDaoImpl")
	public void setPmYanShouDao(PmYanShouDao pmYanShouDao) {
		this.pmYanShouDao = pmYanShouDao;
	}

 
	public boolean deleteById(String yanshou_uid) {
		// TODO Auto-generated method stub
		return this.pmYanShouDao.deleteById(yanshou_uid);
	}
    
}
