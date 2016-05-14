/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmscslweekplan.service.PmScslWeekPlanController.java
 * 创建日期： 2016-05-12 上午 10:02:05
 * 功能：    服务控制类：监测项目
 * 所含类:   PmScslWeekPlanService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-12 上午 10:02:05  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmscslweekplan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmscslweekplan.service.PmScslWeekPlanService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmScslWeekPlanController.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmScslWeekPlanController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-05-12
 * 
 */

@Controller
@RequestMapping("/pmscslweekplan/pmScslWeekPlanController")
public class PmScslWeekPlanController {

	private static Logger logger = LoggerFactory.getLogger(PmScslWeekPlanController.class);

    @Autowired
    private PmScslWeekPlanService pmScslWeekPlanService;

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
        domresult = this.pmScslWeekPlanService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【监测项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmScslWeekPlanService.insert(json.getMsg());
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
        resultVO = this.pmScslWeekPlanService.update(json.getMsg());
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
        resultVO = this.pmScslWeekPlanService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }
    
    
    @RequestMapping(params = "deleteId")
    @ResponseBody
    public requestJson deleteId(HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【监测项目删除】",user.getName());
        String SCSLWEEKPLANUIDS = request.getParameter("SCSLWEEKPLANUID");
        requestJson j = new requestJson();
        
        boolean flag =this.pmScslWeekPlanService.deleteById(SCSLWEEKPLANUIDS);
        j.setSuccess(flag);
        return j;
    }
    @RequestMapping(params = "queryid")
    @ResponseBody
    public requestJson queryid(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        requestJson j = new requestJson();
        logger.info("<{}>执行操作【监测项目查询】",user.getName());
        String SCSLWEEKPLANUIDS=request.getParameter("SCSLWEEKPLANUID");
        String domresult = "";
        domresult = this.pmScslWeekPlanService.queryId(SCSLWEEKPLANUIDS);
        j.setMsg(domresult);
        return j;

    }
}
