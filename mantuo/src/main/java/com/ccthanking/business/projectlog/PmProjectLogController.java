/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectLogController.java
 * 创建日期： 2016-01-14 下午 02:44:26
 * 功能：    服务控制类：项目日志
 * 所含类:   PmProjectLogService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-14 下午 02:44:26  龚伟雄   创建文件，实现基本功能
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

import com.ccthanking.business.projectlog.service.PmProjectLogService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmProjectLogController.java </p>
 * <p> 功能：项目日志 </p>
 *
 * <p><a href="PmProjectLogController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-14
 * 
 */

@Controller
@RequestMapping("/projectlog/pmProjectLogController")
public class PmProjectLogController {

	private static Logger logger = LoggerFactory.getLogger(PmProjectLogController.class);

    @Autowired
    private PmProjectLogService pmProjectLogService;

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
        logger.info("<{}>执行操作【项目日志查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmProjectLogService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目日志新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectLogService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目日志修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectLogService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目日志删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmProjectLogService.delete(json.getMsg());
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
    	logger.info("<{}>执行操作【项目日志删除】",user.getName());
    	String log_uid = request.getParameter("PROJECT_LOG_UID");
    	requestJson j = new requestJson();
    	boolean flag = this.pmProjectLogService.deleteById(log_uid);
    	j.setSuccess(flag);
    	return j;
    }

}
