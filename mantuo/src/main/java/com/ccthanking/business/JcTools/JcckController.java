/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcTools.service.JcckController.java
 * 创建日期： 2015-10-29 下午 02:32:36
 * 功能：    服务控制类：仓库备品
 * 所含类:   JcckService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 下午 02:32:36  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcTools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.JcTools.service.JcckService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcckController.java </p>
 * <p> 功能：仓库备品 </p>
 *
 * <p><a href="JcckController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

@Controller
@RequestMapping("/JcTools/jcckController")
public class JcckController {

	private static Logger logger = LoggerFactory.getLogger(JcckController.class);

    @Autowired
    private JcckService jcckService;

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
        logger.info("<{}>执行操作【仓库备品查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcckService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【仓库备品新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcckService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【仓库备品修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcckService.update(json.getMsg());
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
        logger.info("<{}>执行操作【仓库备品删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcckService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
