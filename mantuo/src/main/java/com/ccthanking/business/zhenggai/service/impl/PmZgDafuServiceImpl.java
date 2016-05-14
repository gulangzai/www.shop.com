/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZgDafuService.java
 * 创建日期： 2016-03-30 上午 10:40:44
 * 功能：    接口：工程整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:40:44  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service.impl;


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

import com.ccthanking.business.zhenggai.vo.PmZgDafuContentVO;
import com.ccthanking.business.zhenggai.vo.PmZgDafuVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.business.zhenggai.dao.PmZgDafuContentDao;
import com.ccthanking.business.zhenggai.dao.PmZgDafuDao;
import com.ccthanking.business.zhenggai.dao.PmZhengGaiDao;
import com.ccthanking.business.zhenggai.service.PmZgDafuService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmZgDafuService.java </p>
 * <p> 功能：工程整改答复 </p>
 *
 * <p><a href="PmZgDafuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

@Service
public class PmZgDafuServiceImpl extends Base1ServiceImpl<PmZgDafuVO, String> implements PmZgDafuService {

	private static Logger logger = LoggerFactory.getLogger(PmZgDafuServiceImpl.class);
	
	// 业务类型
    //private String ywlx = YwlxManager.PM_ZG_DAFU;
    
    private PmZgDafuDao pmZgDafuDao;
    
    @Autowired
    private PmZgDafuContentDao pmZgDafuContentDao;
    
    @Autowired
    private PmZhengGaiDao pmZhengGaiDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmZgDafuDao.queryCondition(json, null, null);

           /* LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<工程整改答复>", user, "", "");*/
            
        }catch (DaoException e) {
        	logger.error("工程整改答复{}", e.getMessage());
			/*LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复查询失败", user, "", "");*/
			SystemException.handleMessageException("工程整改答复查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread(); 
        String resultVO = null;
        PmZgDafuVO vo = new PmZgDafuVO(); 
        Connection conn = null;
        try { 
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setCreate_date(new Date());
            vo.setCreate_user(user.getIdCard());
            String targetUid = obj.getString("target_uid");
            //发布最新一次答复，new_y = 'Y'，旧的答复变为‘N’
            String sql = "update pm_zg_dafu set new_y='N' where ZHENGGAI_UID="+vo.getZhenggai_uid();
            DBUtil.execUpdateSql(conn, sql); 
            // 设置主键 
            // 插入
			Long ZG_DAFU_UID = BaseDAO.insert(conn,vo);
			   //更新附件信息
            if(null!=ZG_DAFU_UID&&!"".equals(ZG_DAFU_UID)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(ZG_DAFU_UID));                 
            }
			
			if(ZG_DAFU_UID!=0){
				if(json.indexOf("ZHENGGAI_CONTENT_UID")!=-1){
					String ZHENGGAI_CONTENT_UIDs = obj.getString("ZHENGGAI_CONTENT_UID");
					if(ZHENGGAI_CONTENT_UIDs.indexOf("[")!=-1){
						JSONArray uids = obj.getJSONArray("ZHENGGAI_CONTENT_UID");
						if(null!=uids){
							for(int i=0;i<uids.size();i++){
								String ZHENGGAI_CONTENT_UID = obj.getJSONArray("ZHENGGAI_CONTENT_UID").getString(i);
								String DAFU_CONTENT = obj.getJSONArray("DAFU_CONTENT").getString(i);
								PmZgDafuContentVO  pmZgDafuContentVO = new PmZgDafuContentVO();
								pmZgDafuContentVO.setZg_dafu_uid(String.valueOf(ZG_DAFU_UID));
								pmZgDafuContentVO.setDafu_content(DAFU_CONTENT);
								pmZgDafuContentVO.setZhenggai_content_uid(ZHENGGAI_CONTENT_UID);
								pmZgDafuContentDao.save(pmZgDafuContentVO);
							}
						}
					}else{
						PmZgDafuContentVO  pmZgDafuContentVO = new PmZgDafuContentVO();
						pmZgDafuContentVO.setZg_dafu_uid(String.valueOf(ZG_DAFU_UID));
						pmZgDafuContentVO.setDafu_content(obj.getString("DAFU_CONTENT"));
						pmZgDafuContentVO.setZhenggai_content_uid(obj.getString("ZHENGGAI_CONTENT_UID"));
						pmZgDafuContentDao.save(pmZgDafuContentVO);
					}
				}
			}
            resultVO = vo.getRowJson(); 
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复新增成功", user, "", "");*/

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("工程整改答复{}", e.getMessage());
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user
                    .getOrgDept().getDeptName() + " " + user.getName() + "工程整改答复新增失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复新增失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmZgDafuVO vo = new PmZgDafuVO();
        Connection conn = null;
        try {
        	conn = DBUtil.getConnection();
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键 
            // 修改
            pmZgDafuDao.update(vo);
            //如果复查结果是关闭,更新pm_zhenggai	
            PmZhengGaiVO pmzhenggaiVo = new PmZhengGaiVO();
            pmzhenggaiVo.setZhenggai_uid(obj.getString("ZHENGGAI_UID"));
            if(vo.getFucha_jieguo().equals("1")){ 
            	PmZhengGaiVO pmzhenggaiVonew = pmZhengGaiDao.get(PmZhengGaiVO.class, obj.getString("ZHENGGAI_UID"));
            	pmzhenggaiVonew.setStatus("1");
            	pmZhengGaiDao.update(pmzhenggaiVonew);
            }
            resultVO = vo.getRowJson(); 
            String targetUid = obj.getString("target_uid"); 
            //更新附件
            if(vo!=null&&vo.getZg_dafu_uid()!=null&&!"".equals(vo.getZg_dafu_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getZg_dafu_uid());                 
            }
            
           /* LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复修改成功", user, "", "");*/

        } catch (DaoException e) {
            logger.error("工程整改答复{}", e.getMessage());
            /*LogManager.writeUserLog(vo.getSjbh(), vo.getYwlx(), Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复修改失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复修改失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmZgDafuVO vo = new PmZgDafuVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//pmZgDafuDao.delete(PmZgDafuVO.class, vo.getId());

			resultVo = vo.getRowJson();

			/*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复删除成功", user, "", "");*/

		} catch (DaoException e) {
            logger.error("工程整改答复{}", e.getMessage());
            /*LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "工程整改答复删除失败", user, "", "");*/
            SystemException.handleMessageException("工程整改答复删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmZgDafuDaoImpl")
	public void setPmZgDafuDao(PmZgDafuDao pmZgDafuDao) {
		this.pmZgDafuDao = pmZgDafuDao;
	}
    
}
