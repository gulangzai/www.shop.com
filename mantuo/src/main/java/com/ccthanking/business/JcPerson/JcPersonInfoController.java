/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcPerson.service.JcPersonInfoController.java
 * 创建日期： 2015-10-29 上午 10:54:59
 * 功能：    服务控制类：负责人联络方式
 * 所含类:   JcPersonInfoService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:54:59  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcPerson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.JcPerson.service.JcPersonInfoService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcPersonInfoController.java </p>
 * <p> 功能：负责人联络方式 </p>
 *
 * <p><a href="JcPersonInfoController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Controller
@RequestMapping("/JcPerson/jcPersonInfoController")
public class JcPersonInfoController {

	private static Logger logger = LoggerFactory.getLogger(JcPersonInfoController.class);

    @Autowired
    private JcPersonInfoService jcPersonInfoService;

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
        logger.info("<{}>执行操作【负责人联络方式查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcPersonInfoService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【负责人联络方式查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcPersonInfoService.query(json.getMsg());
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
        logger.info("<{}>执行操作【负责人联络方式新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPersonInfoService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【负责人联络方式修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPersonInfoService.update(json.getMsg());
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
        logger.info("<{}>执行操作【负责人联络方式删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcPersonInfoService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
