/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectTopicController.java
 * 创建日期： 2016-01-19 下午 04:30:30
 * 功能：    服务控制类：问题讨论
 * 所含类:   PmProjectTopicService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:30:30  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectlog;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.projectlog.service.PmProjectTopicService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmProjectTopicController.java </p>
 * <p> 功能：问题讨论 </p>
 *
 * <p><a href="PmProjectTopicController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Controller
@RequestMapping("/projectlog/pmProjectTopicController")
public class PmProjectTopicController {

	private static Logger logger = LoggerFactory.getLogger(PmProjectTopicController.class);

    @Autowired
    private PmProjectTopicService pmProjectTopicService;

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
        logger.info("<{}>执行操作【问题讨论查询】",user.getName());
        String project_uid = request.getParameter("project_uid");
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmProjectTopicService.queryCondition(json.getMsg(),project_uid);
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
    @RequestMapping(params = "queryList")
    @ResponseBody
    public requestJson queryList(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【问题讨论查询】",user.getName());
    	requestJson j = new requestJson();
    	List<Map<String,String>> list = this.pmProjectTopicService.queryList();
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
    @RequestMapping(params = "queryDetails")
    @ResponseBody
    public requestJson queryDetails(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【问题讨论查询】",user.getName());
    	String topicUid = request.getParameter("topicUid");
    	requestJson j = new requestJson();
    	Map<String, List<?>> map = this.pmProjectTopicService.queryDetails(topicUid);
    	List<Map<String,String>> topicFileList = (List<Map<String, String>>) map.get("topicFileList");
    	for (Map<String, String> vmap : topicFileList) {
			vmap.put("AFILE_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+vmap.get("FILE_PATH"));
		}
    	List<?> replylist = (List<?>) map.get("replylist");
    	for (int i = 0; i < replylist.size(); i++) {
    		Map rmap = (Map) replylist.get(i);
    		List<Map<String, String>> replyFileList = (List<Map<String, String>>) rmap.get("replyFileList");
    		for (Map<String, String> fmap : replyFileList) {
    			fmap.put("AFILE_PATH", request.getScheme()+"://"+request.getServerName()+":8088/file"+fmap.get("FILE_PATH"));
			}
    		
		}
    	j.setObj(map);
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
        logger.info("<{}>执行操作【问题讨论新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicService.insert(json.getMsg());
        j.setMsg(resultVO);
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
        logger.info("<{}>执行操作【问题讨论修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicService.update(json.getMsg());
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
        logger.info("<{}>执行操作【问题讨论删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicService.delete(json.getMsg());
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
    @RequestMapping(params = "deleteById")
    @ResponseBody
    public requestJson deleteById(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【问题讨论删除】",user.getName());
    	requestJson j = new requestJson();
    	String topic_uid = request.getParameter("PROJECT_TOPIC_UID");
    	
    	boolean flag = this.pmProjectTopicService.deleteById(topic_uid);
    	j.setSuccess(flag);
    	return j;
    }
    
    /**
     * 关闭问题.
     * 
     * @param request
     * @param json
     * @return
     * @throws Exception
     * @since v1.00
     */
    @RequestMapping(params = "shutDownById")
    @ResponseBody
    public requestJson shutDownById(HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【问题讨论删除】",user.getName());
    	requestJson j = new requestJson();
    	String topic_uid = request.getParameter("PROJECT_TOPIC_UID");
    	
    	boolean flag = this.pmProjectTopicService.shutDownById(topic_uid);
    	j.setSuccess(flag);
    	return j;
    }

}
