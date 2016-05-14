/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcPlan.service.JcEmergencyPlanController.java
 * 创建日期： 2015-10-29 上午 10:46:26
 * 功能：    服务控制类：施工应急预案
 * 所含类:   JcEmergencyPlanService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:46:26  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcPlan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.JcPlan.service.JcEmergencyPlanService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcEmergencyPlanController.java </p>
 * <p> 功能：施工应急预案 </p>
 *
 * <p><a href="JcEmergencyPlanController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Controller
@RequestMapping("/JcPlan/jcEmergencyPlanController")
public class JcEmergencyPlanController {

	private static Logger logger = LoggerFactory.getLogger(JcEmergencyPlanController.class);

    @Autowired
    private JcEmergencyPlanService jcEmergencyPlanService;

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
        logger.info("<{}>执行操作【施工应急预案查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcEmergencyPlanService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryCondition")
    @ResponseBody
    public requestJson query(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【施工应急预案查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcEmergencyPlanService.query(json.getMsg());
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
        logger.info("<{}>执行操作【施工应急预案新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcEmergencyPlanService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【施工应急预案修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcEmergencyPlanService.update(json.getMsg());
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
        logger.info("<{}>执行操作【施工应急预案删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcEmergencyPlanService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
