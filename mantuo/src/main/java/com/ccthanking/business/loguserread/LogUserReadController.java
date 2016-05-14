/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    loguserread.service.LogUserReadController.java
 * 创建日期： 2016-04-25 下午 05:34:16
 * 功能：    服务控制类：监测项目
 * 所含类:   LogUserReadService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 05:34:16  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.loguserread;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.loguserread.service.LogUserReadService;
import com.ccthanking.business.loguserread.vo.LogUserReadVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> LogUserReadController.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="LogUserReadController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

@Controller
@RequestMapping("/loguserread/logUserReadController")
public class LogUserReadController {

	private static Logger logger = LoggerFactory.getLogger(LogUserReadController.class);

    @Autowired
    private LogUserReadService logUserReadService;

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
        logger.info("<{}>执行操作【监测项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.logUserReadService.queryCondition(json.getMsg());
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
    @SuppressWarnings("unused")
	@RequestMapping(params = "insert")
    @ResponseBody
    protected requestJson insert(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监测项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        String sessionId = request.getSession().getId();
        //System.out.println(sessionId);
        resultVO = this.logUserReadService.insert(json.getMsg(),sessionId);
        
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
        logger.info("<{}>执行操作【监测项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.logUserReadService.update(json.getMsg());
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
        logger.info("<{}>执行操作【监测项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.logUserReadService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    @RequestMapping(params = "queryid")
    @ResponseBody
    public requestJson query(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        logger.info("<{}>执行操作【监测项目查询】",user.getName());
        String xian_uid=request.getParameter("xian_uid");
        String PROJECT_UID= request.getParameter("PROJECT_UID");
        String TARGET_CODE=request.getParameter("TARGET_CODE");
        String user_uid = request.getParameter("user_uid");
        String domresult = "";
        domresult = this.logUserReadService.query(PROJECT_UID, user_uid, xian_uid, TARGET_CODE);
        j.setMsg(domresult);
        return j;

    }

}
