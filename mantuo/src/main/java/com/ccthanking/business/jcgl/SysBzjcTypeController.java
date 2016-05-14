/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcgl.service.SysBzjcTypeController.java
 * 创建日期： 2016-05-11 上午 11:31:11
 * 功能：    服务控制类：标准检查模板类型
 * 所含类:   SysBzjcTypeService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 11:31:11  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcgl;


import java.util.HashMap;
import java.util.Map;

import oracle.net.aso.h;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jcgl.service.SysBzjcTypeService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> SysBzjcTypeController.java </p>
 * <p> 功能：标准检查模板类型 </p>
 *
 * <p><a href="SysBzjcTypeController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@qq.com">曹伟杰</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

@Controller
@RequestMapping("/jcgl/sysBzjcTypeController")
public class SysBzjcTypeController {

	private static Logger logger = LoggerFactory.getLogger(SysBzjcTypeController.class);

    @Autowired
    private SysBzjcTypeService sysBzjcTypeService;

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
        logger.info("<{}>执行操作【标准检查模板类型查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.sysBzjcTypeService.queryCondition(json.getMsg());
        j.setMsg(domresult);
        return j;

    }
    
    @RequestMapping(params = "copyPm")
    @ResponseBody
    public String copyPm(final HttpServletRequest request, requestJson json) throws Exception {
        User user = RestContext.getCurrentUser();
        logger.info("<{}>执行操作【标准检查模板类型查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        Map map = new HashMap();
        map.put("TYPE_UID", request.getParameter("TYPE_UID"));
        map.put("PID", request.getParameter("PID"));
        domresult = this.sysBzjcTypeService.copyPm(map);
        return domresult;

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
        logger.info("<{}>执行操作【标准检查模板类型新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzjcTypeService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【标准检查模板类型修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzjcTypeService.update(json.getMsg());
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
        logger.info("<{}>执行操作【标准检查模板类型删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.sysBzjcTypeService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
