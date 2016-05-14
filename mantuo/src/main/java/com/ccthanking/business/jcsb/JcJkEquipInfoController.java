/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcsb.service.JcJkEquipInfoController.java
 * 创建日期： 2015-10-27 下午 03:52:34
 * 功能：    服务控制类：项目
 * 所含类:   JcJkEquipInfoService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-27 下午 03:52:34  luhonggang   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcsb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jcsb.service.JcJkEquipInfoService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcJkEquipInfoController.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="JcJkEquipInfoController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhongang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-10-27
 * 
 */

@Controller
@RequestMapping("/jcsb/jcJkEquipInfoController")
public class JcJkEquipInfoController {

	private static Logger logger = LoggerFactory.getLogger(JcJkEquipInfoController.class);

    @Autowired
    private JcJkEquipInfoService jcJkEquipInfoService;

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
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcJkEquipInfoService.queryCondition(json.getMsg());
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
        logger.info("<{}>执行操作【项目查询】",user.getName());
        requestJson j = new requestJson();
        String domresult = "";
        domresult = this.jcJkEquipInfoService.query(json.getMsg());
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
        logger.info("<{}>执行操作【项目新增】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJkEquipInfoService.insert(json.getMsg());
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
        logger.info("<{}>执行操作【项目修改】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJkEquipInfoService.update(json.getMsg());
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
        logger.info("<{}>执行操作【项目删除】",user.getName());
        requestJson j = new requestJson();
        String resultVO = "";
        resultVO = this.jcJkEquipInfoService.delete(json.getMsg());
        j.setMsg(resultVO);
        return j;
    }

}
