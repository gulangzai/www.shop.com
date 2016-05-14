/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmPrjStrucController.java
 * 创建日期： 2016-01-25 下午 04:12:17
 * 功能：    服务控制类：项目结构
 * 所含类:   PmPrjStrucService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-25 下午 04:12:17  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.pmxianchang.service.PmPrjStrucService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> PmPrjStrucController.java </p>
 * <p> 功能：项目结构 </p>
 *
 * <p><a href="PmPrjStrucController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-25
 * 
 */

@Controller
@RequestMapping("/pmxianchang/pmPrjStrucController")
public class PmPrjStrucController {

	private static Logger logger = LoggerFactory.getLogger(PmPrjStrucController.class);

    @Autowired
    private PmPrjStrucService pmPrjStrucService;

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
        logger.info("<{}>执行操作【项目结构查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.pmPrjStrucService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目结构新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmPrjStrucService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目结构修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmPrjStrucService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目结构删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.pmPrjStrucService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
