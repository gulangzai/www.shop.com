/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    xmgk.service.PmGongkuangService.java
 * 创建日期： 2015-11-25 下午 01:16:52
 * 功能：    接口：项目工况
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-11-25 下午 01:16:52  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.xmgk.service.impl;


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
import com.ccthanking.framework.fileUpload.service.FileUploadUtilService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.xmgk.vo.PmGongkuangVO;
import com.ccthanking.business.xmgk.dao.PmGongkuangDao;
import com.ccthanking.business.xmgk.service.PmGongkuangService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmGongkuangService.java </p>
 * <p> 功能：项目工况 </p>
 *
 * <p><a href="PmGongkuangService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-11-25
 * 
 */

@Service
public class PmGongkuangServiceImpl extends Base1ServiceImpl<PmGongkuangVO, String> implements PmGongkuangService {

	private static Logger logger = LoggerFactory.getLogger(PmGongkuangServiceImpl.class);
	
    private PmGongkuangDao pmGongkuangDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmGongkuangDao.queryCondition(json, null, null);

        }catch (DaoException e) {
			SystemException.handleMessageException("项目工况查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

    	User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmGongkuangVO vo = new PmGongkuangVO();
        Connection conn = DBUtil.getConnection();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            // 插入
			//pmGongkuangDao.save(vo);
           // resultVO = vo.getRowJson();
            String targetUid = obj.getString("target_uid");
            // 插入
			Long project_uid = BaseDAO.insert(conn, vo);
            resultVO = vo.getRowJson();
            
            //更新附件信息
            if(null!=project_uid&&!"".equals(project_uid)){
            	 FileUploadUtilService. updateBytragetUid (targetUid,String.valueOf(project_uid));                 
            }
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
        } catch (DaoException e) {
            SystemException.handleMessageException("项目工况新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {
        User user = ActionContext.getCurrentUserInThread();
        String resultVO = null;
        PmGongkuangVO vo = new PmGongkuangVO();
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject)list.get(0);
            vo.setValueFromJson(obj);
            // 设置主键
            vo.setCreate_user(user.getIdCard());
            vo.setCreate_date(new Date());
            // 修改
            pmGongkuangDao.update(vo);
            String targetUid = obj.getString("target_uid");
            //更新附件
            if(vo!=null&&vo.getGongkuang_uid()!=null&&!"".equals(vo.getGongkuang_uid())){
            	 FileUploadUtilService. updateBytragetUid (targetUid,vo.getGongkuang_uid());                 
            }
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            SystemException.handleMessageException("项目工况修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

//  工况信息 删除 
  public String delete(String json) throws Exception {

		 User user = ActionContext.getCurrentUserInThread();
		 String resultVo = null;
		 PmGongkuangVO vo = new PmGongkuangVO();
		 String [] uid = null;
		 String eId = null;
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			//获取项目工况的主键uid
          String uids = jsonObj.getString("GONGKUANG_UID");
          
          uid = uids.split(",");
			if(uids.indexOf(",")!=-1 ){//说明选择了多条工况信息进行删除
              if(null!=uids){
              	for (int i = 0; i < uid.length; i++) {
              	eId = uid[i];
              	pmGongkuangDao.delete(PmGongkuangVO.class, eId);
  				}
              }
          }else{//只有一条
          	vo.setValueFromJson(jsonObj);
          	pmGongkuangDao.delete(PmGongkuangVO.class, uids);
          }
			resultVo = vo.getRowJson();
		} catch (DaoException e) {
          SystemException.handleMessageException("项目工况删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}


	@Autowired
	@Qualifier("pmGongkuangDaoImpl")
	public void setPmGongkuangDao(PmGongkuangDao pmGongkuangDao) {
		this.pmGongkuangDao = pmGongkuangDao;
	}

	public List<Map<String, String>> queryFileList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmGongkuangDao.queryFileList(map);
	}

	public boolean pmGongkuangSet(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmGongkuangDao.pmGongkuangSet(map);
	}

	public List<Map<String, Object>> queryFileByType(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmGongkuangDao.queryFileByType(map);
	}

	public boolean deleteByid(String gongkuangUid) {
		// TODO Auto-generated method stub
		return this.pmGongkuangDao.deleteByid(gongkuangUid);
	}
    
}
