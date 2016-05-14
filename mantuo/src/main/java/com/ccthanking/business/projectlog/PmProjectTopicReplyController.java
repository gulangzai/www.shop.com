/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectTopicReplyController.java
 * 创建日期： 2016-01-19 下午 04:29:43
 * 功能：    服务控制类：问题回复
 * 所含类:   PmProjectTopicReplyService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:29:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectlog;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.projectlog.service.PmProjectTopicReplyService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmProjectTopicReplyController.java </p>
 * <p> 功能：问题回复 </p>
 *
 * <p><a href="PmProjectTopicReplyController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

@Controller
@RequestMapping("/projectlog/pmProjectTopicReplyController")
public class PmProjectTopicReplyController {

	private static Logger logger = LoggerFactory.getLogger(PmProjectTopicReplyController.class);

    @Autowired
    private PmProjectTopicReplyService pmProjectTopicReplyService;

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
        logger.info("<{}>执行操作【问题回复查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmProjectTopicReplyService.queryCondition(json.getMsg());
        j.setMsg(domresult);
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
        logger.info("<{}>执行操作【问题回复新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicReplyService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【问题回复修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicReplyService.update(json.getMsg());
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
        logger.info("<{}>执行操作【问题回复删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectTopicReplyService.delete(json.getMsg());
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
    	logger.info("<{}>执行操作【问题回复删除】",user.getName());
    	requestJson j = new requestJson();
    	String reply_uid = request.getParameter("PROJECT_TOPIC_REPLY_UID");
    	
    	boolean flag = this.pmProjectTopicReplyService.deleteById(reply_uid);
    	j.setSuccess(flag);
    	return j;
    }

}
