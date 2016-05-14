/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectUserController.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：    服务控制类：项目用户
 * 所含类:   BuProjectUserService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.project.service.ProjectBiangengService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> BuProjectUserController.java </p>
 * <p> 功能：项目用户 </p>
 *
 * <p><a href="BuProjectUserController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */

@Controller
@RequestMapping("/biangeng/biangengController")
public class ProjectBiangengController {

	private static Logger logger = LoggerFactory.getLogger(ProjectBiangengController.class);

    @Autowired
    private ProjectBiangengService projectBiangengService;
    
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
        logger.info("<{}>执行操作【项目用户查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.projectBiangengService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目用户新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectBiangengService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目用户修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.projectBiangengService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目用户删除】",user.getName());
        requestJson j = new requestJson();
        String bid = request.getParameter("bid");
        String resultVO = "";
        resultVO = this.projectBiangengService.delete(bid);
        j.setMsg(resultVO);
        return j;
    }  
 
}
