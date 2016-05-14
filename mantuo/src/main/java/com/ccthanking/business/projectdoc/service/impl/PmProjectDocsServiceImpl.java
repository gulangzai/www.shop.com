/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectdoc.service.PmProjectDocsService.java
 * 创建日期： 2016-03-21 上午 09:32:52
 * 功能：    接口：项目工程文档
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-21 上午 09:32:52  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectdoc.service.impl;


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

import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.business.projectdoc.dao.PmProjectDocsDao;
import com.ccthanking.business.projectdoc.service.PmProjectDocsService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> PmProjectDocsService.java </p>
 * <p> 功能：项目工程文档 </p>
 *
 * <p><a href="PmProjectDocsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-21
 * 
 */

@Service
public class PmProjectDocsServiceImpl extends Base1ServiceImpl<PmProjectDocsVO, String> implements PmProjectDocsService {

	private static Logger logger = LoggerFactory.getLogger(PmProjectDocsServiceImpl.class);
	
    
    private PmProjectDocsDao pmProjectDocsDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = pmProjectDocsDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("项目工程文档{}", e.getMessage());
			SystemException.handleMessageException("项目工程文档查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        PmProjectDocsVO vo = new PmProjectDocsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 插入
			pmProjectDocsDao.save(vo);
            resultVO = vo.getRowJson();

            
        } catch (DaoException e) {
            logger.error("项目工程文档{}", e.getMessage());
            SystemException.handleMessageException("项目工程文档新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        PmProjectDocsVO vo = new PmProjectDocsVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            pmProjectDocsDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("项目工程文档{}", e.getMessage());
            SystemException.handleMessageException("项目工程文档修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		PmProjectDocsVO vo = new PmProjectDocsVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			
			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
			pmProjectDocsDao.delete(PmProjectDocsVO.class, vo.getProject_docs_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("项目工程文档{}", e.getMessage());
            SystemException.handleMessageException("项目工程文档删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("pmProjectDocsDaoImpl")
	public void setPmProjectDocsDao(PmProjectDocsDao pmProjectDocsDao) {
		this.pmProjectDocsDao = pmProjectDocsDao;
	}

	public List<Map<String, String>> queryFileList(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmProjectDocsDao.queryFileList(map);
	}

	public boolean saveDir(PmProjectDocsVO vo) {
		
		boolean flag = false;
        try {

            // 插入
			pmProjectDocsDao.save(vo);
			flag = true;
        } catch (DaoException e) {
            logger.error("项目工程文档{}", e.getMessage());
            SystemException.handleMessageException("项目工程文档新增失败,请联系相关人员处理");
        } finally {
        }
        return flag;
	}

	public List<Map<String, String>> queryFiles(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return this.pmProjectDocsDao.queryFiles(map);
	}

	public boolean deleteFiles(String doc_uids) {
		// TODO Auto-generated method stub
		return this.pmProjectDocsDao.deleteFiles(doc_uids);
	}

	public boolean updateDir(PmProjectDocsVO vo) {
		boolean flag = false;
        try {

            // 更新
			pmProjectDocsDao.update(vo);
			flag = true;
        } catch (DaoException e) {
            logger.error("项目工程文档{}", e.getMessage());
            SystemException.handleMessageException("项目工程文档更新失败,请联系相关人员处理");
        } finally {
        }
        return flag;
	}
    
}
