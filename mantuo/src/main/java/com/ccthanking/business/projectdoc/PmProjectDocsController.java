/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectdoc.service.PmProjectDocsController.java
 * 创建日期： 2016-03-21 上午 09:32:52
 * 功能：    服务控制类：项目工程文档
 * 所含类:   PmProjectDocsService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-21 上午 09:32:52  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectdoc;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.projectdoc.service.PmProjectDocsService;
import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmProjectDocsController.java </p>
 * <p> 功能：项目工程文档 </p>
 *
 * <p><a href="PmProjectDocsController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-21
 * 
 */

@Controller
@RequestMapping("/projectdoc/pmProjectDocsController")
public class PmProjectDocsController {

	private static Logger logger = LoggerFactory.getLogger(PmProjectDocsController.class);

    @Autowired
    private PmProjectDocsService pmProjectDocsService;

    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "query")
    @ResponseBody
    public requestJson queryCondition(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目工程文档查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmProjectDocsService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryFileList")
    @ResponseBody
    public requestJson queryFileList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工程文档查询】",user.getName());
    	requestJson j = new requestJson();
    	String project_uid =  request.getParameter("project_uid");
    	String P_DOCS_UID =  request.getParameter("P_DOCS_UID");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("project_uid", project_uid);
    	map.put("P_DOCS_UID", P_DOCS_UID);
    	List<Map<String, String>> list = this.pmProjectDocsService.queryFileList(map);
    	if(null!=list){
    		j.setSuccess(true);
    	}
    	j.setObj(list);
    	return j;
    	
    }
    
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryFiles")
    @ResponseBody
    public requestJson queryFiles(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工程文档查询】",user.getName());
    	requestJson j = new requestJson();
    	String project_uid =  request.getParameter("project_uid");
    	String keyWords =  request.getParameter("keyWords");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("project_uid", project_uid);
    	map.put("keyWords", keyWords);
    	List<Map<String, String>> list = this.pmProjectDocsService.queryFiles(map);
    	if(null!=list){
    		j.setSuccess(true);
    	}
    	j.setObj(list);
    	return j;
    	
    }

    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目工程文档新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectDocsService.insert(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 保存数据json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "saveDir")
    @ResponseBody
    protected requestJson saveDir(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工程文档新增】",user.getName());
    	requestJson j = new requestJson();
    	String p_docs_uid = request.getParameter("P_DOCS_UID");
    	String project_uid = request.getParameter("project_uid");
    	String dirName = request.getParameter("dirName");
    	PmProjectDocsVO vo = new PmProjectDocsVO();
    	vo.setProject_uid(project_uid);
    	vo.setP_docs_uid(p_docs_uid);
    	vo.setNode_name(dirName);
    	vo.setNode_type("DIR");
    	vo.setCreate_user(user.getIdCard());
    	vo.setCreate_date(new Date());
    	vo.setUpdate_user(user.getIdCard());
    	vo.setUpdate_date(new Date());
   
    	boolean flag = this.pmProjectDocsService.saveDir(vo);
    	j.setSuccess(flag);
    	return j;
    }
    
    /**
     * 重命名
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "updateDir")
    @ResponseBody
    protected requestJson updateDir(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工程文档新增】",user.getName());
    	requestJson j = new requestJson();
    	String docId = request.getParameter("docId");
    	String docName = request.getParameter("docName");
    	PmProjectDocsVO vo = new PmProjectDocsVO();
    	vo.setNode_name(docName);
    	vo.setProject_docs_uid(docId);
    	vo.setUpdate_user(user.getIdCard());
    	vo.setUpdate_date(new Date());
    	
    	boolean flag = this.pmProjectDocsService.updateDir(vo);
    	j.setSuccess(flag);
    	return j;
    }

    /**
     * 修改记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "update")
    @ResponseBody
    protected requestJson update(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目工程文档修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectDocsService.update(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

    /**
     * 删除记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public requestJson delete(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【项目工程文档删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectDocsService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    /**
     * 删除记录.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "deleteFiles")
    @ResponseBody
    public requestJson deleteFiles(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【项目工程文档删除】",user.getName());
    	requestJson j = new requestJson();
    	String doc_uids = request.getParameter("doc_uids");
    	boolean flag  = this.pmProjectDocsService.deleteFiles(doc_uids);
    	j.setSuccess(flag);
    	return j;
    }

}
