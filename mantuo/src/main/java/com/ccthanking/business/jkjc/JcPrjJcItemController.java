/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPrjJcItemController.java
 * 创建日期： 2015-12-08 下午 07:52:25
 * 功能：    服务控制类：监测项目
 * 所含类:   JcPrjJcItemService
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-08 下午 07:52:25  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.jkjc.service.JcPrjJcItemService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;


/**
 * <p> JcPrjJcItemController.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="JcPrjJcItemController.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-08
 * 
 */

@Controller
@RequestMapping("/jkjc/jcPrjJcItemController")
public class JcPrjJcItemController {

	private static Logger logger = LoggerFactory.getLogger(JcPrjJcItemController.class);

    @Autowired
    private JcPrjJcItemService jcPrjJcItemService;

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
        domresult = this.jcPrjJcItemService.queryCondition(json.getMsg());
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
    @RequestMapping(params = "queryById")
    @ResponseBody
    public requestJson queryById(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测项目查询】",user.getName());
    	requestJson j = new requestJson();
    	String JC_PRJ_ITEM_UID = request.getParameter("JC_PRJ_ITEM_UID");
    	String domresult = "";
    	domresult = this.jcPrjJcItemService.queryById(JC_PRJ_ITEM_UID);
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
    @RequestMapping(params = "queryJcObject")
    @ResponseBody
    public requestJson queryJcObject(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测项目查询】",user.getName());
    	requestJson j = new requestJson();
    	List<Map<String,String>> list = this.jcPrjJcItemService.queryJcObject();
    	j.setObj(list);
    	return j;
    	
    }
    /**
     * 查询json
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "queryJcType")
    @ResponseBody
    public requestJson queryJcType(final HttpServletRequest request, requestJson json) throws Exception {
    	User user = RestContext.getCurrentUser();
    	logger.info("<{}>执行操作【监测项目查询】",user.getName());
    	requestJson j = new requestJson();
    	List<Map<String,String>> list = this.jcPrjJcItemService.queryJcType();
    	j.setObj(list);
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
        resultVO = this.jcPrjJcItemService.insert(json.getMsg());
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
        resultVO = this.jcPrjJcItemService.update(json.getMsg());
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
        String JC_PRJ_ITEM_UID = request.getParameter("JC_PRJ_ITEM_UID");
        Boolean flag = this.jcPrjJcItemService.removeData(JC_PRJ_ITEM_UID);
        j.setSuccess(flag);
        return j;
    }

}
